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
    
def codequality() {
  

  // sh 'sonar-scanner -Dsonar.host.url=http://172.31.14.167:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=${component} ${sonar_extra_opts} -Dsonar.qualitygate.wait=true'
  sh 'echo okay'
}

def prepareArtifacts() {
  if (app_lang == nodejs) {
    sh 'zip -r ${component}-${TAG_NAME}.zip *'

  }
  sh 'zip -r ${component}-${TAG_NAME}.zip server.js node_modules'
}
  
  




