def call() {
    if (!env.sonar_extra_opts) {
         env.sonar_extra_opts=""
    }
    node("workstation") {
        try {
            stage('check out code') {
                sh 'ls -l'
                cleanWs()
                sh 'ls -l'
                git branch: 'main', url: 'https://github.com/murthychiluka/catalogue'
                sh 'ls -l'
            }
            if (env.BRANCH_NAME != "main") {
                stage('compile/build') {
                  common.compile()
                }
            }

            stage('testcases') {
              common.testcases()
            }       
            stage('common.codequality') {
               common.codequality()
            }
        } catch (e) {
            mail body: "<h1>${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: 'murthychiluka@gmail.com', subject: "${component} - Pipeline Failed", to: 'murthychiluka@gmail.com', mimeType: 'text/html' 
        }
    }
}


    


    



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    