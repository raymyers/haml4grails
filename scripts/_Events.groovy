eventPackagingEnd = {
	//println "STATUS: " + msg
    //if (msg.equals("Building WAR file")) {
    	//binding.variables.each { println it.key + " = " + binding.variables.get(it.key)} 
    	def viewdir = new File("${basedir}/grails-app/views")
    	def batchConverter = new com.cadrlife.jhaml.JHamlBatchConverter()
    	batchConverter.setTargetExtenstion("gsp")
    	batchConverter.convertAllInPath(viewdir)
    	//ant.compilegsps()
    //}
    
}


eventCompileEnd = {
	//binding.variables.each { println it.key } 
}