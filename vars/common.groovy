def compile() {
  if (env.app_lang == "nodejs") {
    sh 'npm install' 
  }             
  if (env.app_lang == "maven") {
    sh 'mvn package ; mv target/${component}-1.0.jar ${component}.jar'          
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
  sh 'echo ${TAG_NAME} >VERSION'
  if (app_lang == "nodejs" || app_lang == "angular") {
    sh 'zip -r ${component}-${TAG_NAME}.zip * -x Jenkinsfile'
  }
  if (app_lang == "maven" ) {
    sh 'zip -r ${component}-${TAG_NAME}.zip ${component}.jar VERSION'

  }
  
}
def artifactUpload() {
  sh 'echo ${TAG_NAME} >VERSION'
  if (app_lang == "nodejs" || app_lang == "angular") {
    sh 'curl -v -u admin:admin123 --upload-file ${component}-${TAG_NAME}.zip http://172.31.1.10:8081/repository/${component}/${component}-${TAG_NAME}.zip'
  }

}
  
  




