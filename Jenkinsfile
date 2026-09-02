pipeline{
    agent {
        label "agent-1"
    }
    environment {
        IMAGE_NAME = "abhishekkargeti/chatapp-backend-image"
        NAMESPACE  = "production-namespace"
        DEPLOYMENT = "chatapp-backend-deployment"
        DEPLOYMENT_NAME = "chat-app-deployment"
        CONTAINER  = "chat-app-server"
    }
    stages{
        stage("Code Cloning"){
            steps{
                sh 'echo "Code Cloning"'
                git url :"https://github.com/abhishekkargeti1/ChatApplication-Backend.git",branch:"main"
            }
        }
        stage("Building-JAR"){
            steps{
                sh 'echo "Code Building"'
                sh 'mvn clean package -DskipTests'
            }
        }
        stage("Building-Docker-Image"){
            steps{
                sh 'echo "Docker Image Building"'
                sh """
                    docker build \
                    -t ${IMAGE_NAME}:${BUILD_NUMBER} \
                    .
                """
            }
        }
        stage("Testing"){
            steps{
                sh 'echo "Code Testing"'
            }
        }
        stage("Pushing-Docker-Image"){
            steps{
                sh 'echo "Pushing Docker Image"'
            withCredentials([
             usernamePassword(
                 credentialsId: 'DockerCred',
                 usernameVariable: 'DOCKER_USERNAME',
                 passwordVariable: 'DOCKER_PASSWORD'
            )
                ]){
                    sh 'echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin'
                     sh """
                        docker push ${IMAGE_NAME}:${BUILD_NUMBER}
                    """
                    sh 'echo "Image Push Successfully"'
                }
            }
        } 
        stage("Deployment"){
            steps{
                sh 'echo "Code Deployment"'
                sh """
                    kubectl apply \
                    -f /home/ubuntu/kubernetes/chat-application-backend/${DEPLOYMENT}.yml

                    kubectl set image deployment/${DEPLOYMENT_NAME} \
                    ${CONTAINER}=${IMAGE_NAME}:${BUILD_NUMBER} \
                    -n ${NAMESPACE}

                    kubectl rollout status deployment/${DEPLOYMENT_NAME} \
                    -n ${NAMESPACE}
                """
                
        }
    }
        stage("Verify Deployment") {
            steps {
                echo "Checking Kubernetes deployment..."

                sh """
                    kubectl get nodes
                    kubectl get pods -n ${NAMESPACE} -o wide
                    kubectl get svc -n ${NAMESPACE}
                    kubectl get deployment -n ${NAMESPACE}
                """
            }
        }
    }


 post {

        success {
            echo "CI/CD pipeline completed successfully!"
        }

        failure {
            echo "CI/CD pipeline failed!"
        }
    }
}


