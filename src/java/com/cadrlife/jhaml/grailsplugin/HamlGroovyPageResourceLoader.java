package com.cadrlife.jhaml.grailsplugin;

import grails.util.PluginBuildSettings;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.codehaus.groovy.grails.web.pages.GroovyPageResourceLoader;
import org.springframework.core.io.Resource;

import com.cadrlife.jhaml.JHaml;

public class HamlGroovyPageResourceLoader extends GroovyPageResourceLoader {

	private GroovyPageResourceLoader baseResourceLoader = new GroovyPageResourceLoader();
	
	@Override
	public Resource getResource(String location) {
		System.err.println(location);
		if (isGspView(location)) {
			Resource hamlR = getBaseResourceLoader().getResource(pathToHaml(location));
			if (hamlR.exists()) {
				Resource gspR = getBaseResourceLoader().getResource(pathForGeneratedGsp(location));
				try {
					String haml = FileUtils.readFileToString(hamlR.getFile());
					String gsp = new JHaml().parse(haml);
					new File(gspR.getFile().getParent()).mkdirs();
					FileUtils.writeStringToFile(gspR.getFile(), gsp);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				return getBaseResourceLoader().getResource(pathForGeneratedGsp(location));
			}
		}
		return getBaseResourceLoader().getResource(location);
	}
	
	private String pathForGeneratedGsp(String location) {
		return location.replaceFirst("/views/", "/views-generated/");
	}

	private String pathToHaml(String location) {
		return location.replaceFirst(".gsp$", ".haml");
	}

	private boolean isGspView(String location) {
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
	
	private GroovyPageResourceLoader getBaseResourceLoader() {
		return baseResourceLoader;
	}


}
