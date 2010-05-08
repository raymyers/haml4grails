

/*
 * Avoid compilation errors on plugin install.
 */
loadbatchConverterClass = { ->
  def doLoad = { -> classLoader.loadClass('com.cadrlife.jhaml.JHamlBatchConverter') }
  try {
    doLoad()
  } catch (ClassNotFoundException e) {
    includeTargets << grailsScript("_GrailsCompile") 
    compile()
    doLoad()
  }  
}


/*
 * In the war target, packageApp runs immediately before compilegsp,
 * Thus at eventPackagingEnd, we convert all haml files to gsp.
 * Unfortunately, packageApp also runs when we call "grail run-app",
 * so we will have .gsp files created throughout the view folder.
 * Currently seeking a work around for this. -- RM, 05/08/2010
 */

eventPackagingEnd = {
	def viewdir = new File("${basedir}/grails-app/views")
	def batchConverter = loadbatchConverterClass().newInstance()
	//def batchConverter = new com.cadrlife.jhaml.JHamlBatchConverter()
	batchConverter.setTargetExtenstion("gsp")
	batchConverter.convertAllInPath(viewdir)
}
