pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'my-spring-boot-app'
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
        stage('Docker build') {
            steps {
                script {
                    docker.build(DOCKER_IMAGE)
                }
            }
        }
        stage('Docker push') {
            when {
                branch 'main'
            }
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    script {
                        docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-creds') {
                            dockerImage.push()
                        }
                    }
                }
            }
        }
        stage('Deploy') {
            when {
                branch 'main'
            }
            steps {
                withCredentials([sshUserPrivateKey(credentialsId: 'ssh-key', keyFileVariable: 'SSH_KEY', passphraseVariable: 'SSH_PASSPHRASE', usernameVariable: 'SSH_USER')]) {
                    script {
                        def ssh = remote {
                            host = 'my-server.com'
                            user = env.SSH_USER
                            keyFile = env.SSH_KEY
                            passphrase = env.SSH_PASSPHRASE
                            allowAnyHosts()
                            return session
                        }
                        ssh.with {
                            sh "docker stop ${DOCKER_IMAGE} || true"
                            sh "docker rm ${DOCKER_IMAGE} || true"
                            sh "docker pull ${DOCKER_IMAGE}"
                            sh "docker run -d --name ${DOCKER_IMAGE} -p 8080:8080 ${DOCKER_IMAGE}"
                        }
                    }
                }
            }
        }
    }
}