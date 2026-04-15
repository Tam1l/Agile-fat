pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'jdk17'
    }

    environment {
        DOCKER_IMAGE = 'yourdockerhubusername/online-donation-system'
        // Add your Docker Hub credentials snippet to Jenkins and provide the ID below
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'
    }

    stages {
        stage('Checkout') {
            steps {
                // Checks out code from your configured GitHub Repository
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                // Using isUnix() helps ensure this works on both Windows and Linux Jenkins nodes
                script {
                    if (isUnix()) {
                        sh 'mvn -B clean package'
                    } else {
                        bat 'mvn -B clean package'
                    }
                }
            }
            post {
                always {
                    // Record JUnit test results
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                script {
                    if (isUnix()) {
                        withCredentials([usernamePassword(credentialsId: "${DOCKER_CREDENTIALS_ID}", passwordVariable: 'DOCKER_PWD', usernameVariable: 'DOCKER_USER')]) {
                            sh "echo \${DOCKER_PWD} | docker login -u \${DOCKER_USER} --password-stdin"
                            sh "docker build -t ${DOCKER_IMAGE}:latest ."
                            sh "docker push ${DOCKER_IMAGE}:latest"
                        }
                    } else {
                        withCredentials([usernamePassword(credentialsId: "${DOCKER_CREDENTIALS_ID}", passwordVariable: 'DOCKER_PWD', usernameVariable: 'DOCKER_USER')]) {
                            bat "echo %DOCKER_PWD% | docker login -u %DOCKER_USER% --password-stdin"
                            bat "docker build -t ${DOCKER_IMAGE}:latest ."
                            bat "docker push ${DOCKER_IMAGE}:latest"
                        }
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'kubectl apply -f k8s/deployment.yaml'
                        sh 'kubectl apply -f k8s/service.yaml'
                    } else {
                        bat 'kubectl apply -f k8s/deployment.yaml'
                        bat 'kubectl apply -f k8s/service.yaml'
                    }
                }
            }
        }
    }
}
