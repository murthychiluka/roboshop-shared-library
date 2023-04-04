def call() {
    pipeline {
        agent any
        parameters {
            string(name: 'ENV', defaultValue: '', description: 'Which environment?')

        }
        
        stages {
            stage('Init') {
                steps {
                    sh 'terraform init -backend-config=env-${ENV}/state.tfvars'

                }
            }

            stage('apply') {
                steps {
                    // sh 'terraform apply -auto-approve -var-file=env-dev/main.tfvars' 
                    sh 'echo'
                }
            }
        }

    }

}

    


