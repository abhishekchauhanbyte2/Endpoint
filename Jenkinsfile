pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'abhishekchauhannagarro/endpointapp:v1'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }
        stage('Docker build and push image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]){
                    script {
                       def dockerImage =  docker.build(DOCKER_IMAGE)
                       docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-creds') {
                             dockerImage.push()
                        }
                    }
                }
            }
            post{
                success {
                    echo "successfully pushed image"        
                }
                }
        }

        stage('Deploy') {
            
            steps {
               
                    script {
  
                            sh "docker stop ${DOCKER_IMAGE} || true"
                            sh "docker rm ${DOCKER_IMAGE} || true"
                            sh "docker pull ${DOCKER_IMAGE}"
                            sh "docker run -d -p 8761:8761 ${DOCKER_IMAGE}"
                        }
                    }
                }
    }      
}
