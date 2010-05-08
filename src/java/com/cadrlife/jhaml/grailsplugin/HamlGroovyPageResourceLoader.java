package com.cadrlife.jhaml.grailsplugin;

import grails.util.PluginBuildSettings;

import java.io.IOException;

import org.codehaus.groovy.grails.web.pages.GroovyPageResourceLoader;
import org.springframework.core.io.Resource;

import com.cadrlife.jhaml.JHamlBatchConverter;

/*
 * Wraps GroovyPageResourceLoader, generating the requested gsp from a haml file
 * when one exists. 
 */
public class HamlGroovyPageResourceLoader extends GroovyPageResourceLoader {

	private GroovyPageResourceLoader baseResourceLoader = new GroovyPageResourceLoader();
	private JHamlBatchConverter batchConverter = new JHamlBatchConverter();
	{
		batchConverter.setTargetExtenstion("gsp");
	}
	
	@Override
	public Resource getResource(String location) {
		if (isGspView(location)) {
			Resource hamlResource = getBaseResourceLoader().getResource(pathToHaml(location));
			if (hamlResource.exists()) {
				try {
					batchConverter.convertAllInPath(hamlResource.getFile());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return getBaseResourceLoader().getResource(location);
	}
	
	protected String pathToHaml(String location) {
		return location.replaceFirst(".gsp$", ".haml");
	}

	protected boolean isGspView(String location) {
		return location.endsWith(".gsp") && location.contains("/views/");
	}
	
	@Override
	public void setPluginSettings(PluginBuildSettings settings) {
		getBaseResourceLoader().setPluginSettings(settings);
	}
	
	@Override
	public void setBaseResource(Resource baseResourceLoader) {
		getBaseResourceLoader().setBaseResource(baseResourceLoader);
	}

	public void setBaseResourceLoader(GroovyPageResourceLoader baseResourceLoader) {
		this.baseResourceLoader = baseResourceLoader;
	}
	
	protected GroovyPageResourceLoader getBaseResourceLoader() {
		return baseResourceLoader;
	}


}
