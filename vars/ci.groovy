def call() {
    pipeline {
    agent any
    stages {
        stage ('compile/build'){
            steps {
                script {
                    if (env.app_lang == "nodejs") {
                sh 'npm install'
               }
               if (env.app_lang == "java") {
                sh 'maven package'
               }
                

                }
               
                
            }

        }

        stage ('test'){
            steps {
                echo 'test'
            }

        }

    }
}


}
