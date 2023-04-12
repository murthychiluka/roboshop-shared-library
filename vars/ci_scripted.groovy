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
                git branch: 'main', url: "https://github.com/murthychiluka/${component}"
            
            }
            sh 'env'

            if (env.BRANCH_NAME != "main") {
                stage('compile/build') {
                  common.compile()
                }
            }
            println GTAG
            println BRANCH_NAME
            if (env.GTAG != "true" && env.BRANCH_NAME != "main") {
                stage('testcases') {
                  common.testcases()
                }
            }
            if (BRANCH_NAME ==~ "PR-.*") {
                stage('Code quality') {
                  common.codequality()
                }   
            } 
            if (env.GTAG == "true") {
                stage('package') {
                  common.prepareArtifacts()
                }
                stage('Artifact upload') {
                  common.testcases()
                }
            }     
           
        } catch (e) {
            mail body: "<h1>${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: 'murthychiluka@gmail.com', subject: "${component} - Pipeline Failed", to: 'murthychiluka@gmail.com', mimeType: 'text/html' 
        }
    }
}


    


    



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    