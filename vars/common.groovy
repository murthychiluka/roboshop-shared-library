def call() {
      if (env.app_lang == "nodejs") {
        sh 'npm install'                 
      }        
                    
      if (env.app_lang == "java") {
        sh 'maven package'           
      }
   
}