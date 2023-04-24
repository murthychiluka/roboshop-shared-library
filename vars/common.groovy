def compile() {
  if(app_lang == "nodejs") {
    sh 'npm install' 
  } 
  if(app_lang == "maven") {
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
  // sh 'echo ${TAG_NAME} >VERSION'
  //   if (app_lang == "maven") {
  //   sh 'zip -r ${component}-${TAG_NAME}.zip ${component}.jar schema VERSION'
  //   } else {
  //   sh 'zip -r ${component}-${TAG_NAME}.zip * -x Jenkinsfile'
  //   }
  sh 'docker build -t 208528724622.dkr.ecr.us-east-1.amazonaws.com/${component}:${TAG_NAME} .'
 
}


def artifactUpload() {
  // env.NEXUS_USER = sh ( script: 'aws ssm get-parameter --name prod.nexus.user --with-decryption | jq .Parameter.Value | xargs', returnStdout: true ).trim()
  // env.NEXUS_PASS = sh ( script: 'aws ssm get-parameter --name prod.nexus.pass --with-decryption | jq .Parameter.Value | xargs', returnStdout: true ).trim()
  // wrap([$class: 'MaskPasswordsBuildWrapper',
  //       varPasswordPairs: [[password: NEXUS_PASS],[password: NEXUS_USER]]]) {
  //   sh 'echo ${TAG_NAME} >VERSION'
  //   sh 'curl -v -u ${NEXUS_USER}:${NEXUS_PASS} --upload-file ${component}-${TAG_NAME}.zip http://172.31.1.10:8081/repository/${component}/${component}-${TAG_NAME}.zip'
  // } 
  sh 'aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 208528724622.dkr.ecr.us-east-1.amazonaws.com'
  sh 'docker push 208528724622.dkr.ecr.us-east-1.amazonaws.com/${component}:${TAG_NAME}'
  

 
  
}  




