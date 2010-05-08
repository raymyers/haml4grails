import org.springframework.core.io.FileSystemResource;

import com.cadrlife.jhaml.grailsplugin.HamlGroovyPageResourceLoader;

class HamlGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.2.2 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    
    // Can't get onChange to do anything useful.
    //def watchedResources = "file:./grails-app/views/**/*.haml"
                     
    
    //def loadAfter = ['groovy-pages']
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def author = "Ray Myers"
    def authorEmail = "ray.myers@gmail.com"
    def title = "haml"
    def description = '''\\
Haml support for grails, based on JHaml.

http://github.com/raymyers/haml4grails

http://github.com/raymyers/JHaml
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/haml"

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before 
    }

    def doWithSpring = {
		/* Seems to only work when included in the resources.groovy of the app.
		 * Maybe a "loadAfter" def above would fix that. -- RM 
		 groovyPageResourceLoader(com.cadrlife.jhaml.grailsplugin.HamlGroovyPageResourceLoader) {
			baseResource = new FileSystemResource(".")
		}
		*/
    }

    def doWithDynamicMethods = { ctx ->

    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
	    /* Not working so far
    	def batchConverter = new com.cadrlife.jhaml.JHamlBatchConverter()
		batchConverter.setTargetExtenstion("gsp")
		batchConverter.convertAllInPath(event.source.getFile())
		*/
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
