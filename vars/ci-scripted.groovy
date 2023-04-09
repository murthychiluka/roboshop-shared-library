def call() {
    if (!env.sonar_extra_opts) {
         env.sonar_extra_opts=""
    }
    node ("work station") {
        try {
            stage('compile/build') {
                sh 'env'
               common.compile()
            }
        
            stage ('test cases') {
               common.testcases()
            }       
            stage ('common.codequality') {
               common.codequality()
            }
        } catch (e) {
            mail body: "<h1>${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: 'murthychiluka@gmail.com', subject: "${component} - Pipeline Failed", to: 'murthychiluka@gmail.com', mimeType: 'text/html' 
        }
    }
}


    


    



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    