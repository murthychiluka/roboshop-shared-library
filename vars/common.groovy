def compile() {
      if (env.app_lang == "nodejs") {
        sh 'npm install'                 
      }        
                    
      if (env.app_lang == "java") {
        sh 'maven package'           
      }
   
}

def testcases() {
    // npm test
  // mvn test
  // python -m unittests
  // go test
  sh 'echo OK'
}
    


}