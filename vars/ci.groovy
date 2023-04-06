def call() {
    if (!env.sonar_extra_opts) {
         env.sonar_extra_opts=""
    }
    pipeline {
        agent any
        stages {
            stage('compile/build') {
                steps {
                    mail bcc: '', body: 'This is Test', cc: '', from: 'murthychiluka@gmail.com', replyTo: '', subject: 'Hello Murthy', to: 'murthychiluka@gmail.com'
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
    }

}
    



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    