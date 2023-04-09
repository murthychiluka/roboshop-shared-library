def call() {
    if (!env.sonar_extra_opts) {
         env.sonar_extra_opts=""
    }
    if (env.TAG_NAME ==~ ".*") {
        env.GTAG = "true"

    } else {
        env.GTAG = "false"
    }
    node("workstation") {
        try {
            stage('check out code') {
                cleanWs() 
                git branch: 'main', url: 'https://github.com/murthychiluka/catalogue'
            
            }
            sh 'env'

            if (env.BRANCH_NAME != "main") {
                stage('compile/build') {
                  common.compile()
                }
            }
            if (env.GTAG != "true" || env.BRANCH_NAME != "main") {
                stage('testcases') {
                  common.testcases()
                }
            }

                 
            stage('common.codequality') {
               common.codequality()
            }
        } catch (e) {
            mail body: "<h1>${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: 'murthychiluka@gmail.com', subject: "${component} - Pipeline Failed", to: 'murthychiluka@gmail.com', mimeType: 'text/html' 
        }
    }
}


    


    



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    