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
             
            stage ('common.codequality') {
                steps {
                    script {
                        common.codequality()
                    }
                }
 
                    
            }
        }
        post {
            failure {
                mail body: ${component} - Pipeline Failed \n ${BUILD_URL}, from: 'murthychiluka@gmail.com', subject: "${component} - Pipeline Failed", to: 'murthychiluka@gmail.com'
            }
        }



    }

}
    



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    