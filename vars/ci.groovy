def call() {
    if (!env.sonar_extra_opts) {
         env.sonar_extra_opts=""
    }
    pipeline {
        agent any
        stages {
            stage('compile/build') {
                steps {
                    sh 'env'
                    script {
                        common.compile()
                    }
          
                }
            }
            stage ('test cases') {
                steps {
                    script {
                        common.testcases()
                    }
                }

            }
             
            stage ('codequality') {
                steps {
                    script {
                        common.codequality()
                    }
                }
 
                    
            }
        }
        post {
            failure {
                mail body: "<h1>${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: 'murthychiluka@gmail.com', subject: "${component} - Pipeline Failed", to: 'murthychiluka@gmail.com', mimeType: 'text/html' 
            }
        }



    }

}
    



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    