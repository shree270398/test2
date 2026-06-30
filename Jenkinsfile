pipeline {
    agent any

    
    environment {
    DOCKER_IMAGE = "shree270398/demo"
    IMAGE_TAG = "v1"
}

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE:$IMAGE_TAG .'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {

                    sh '''
                    echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                    docker push $DOCKER_IMAGE:$IMAGE_TAG
                    docker logout
                    '''
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh '''
                kubectl set image deployment/demo demo=$DOCKER_IMAGE:$IMAGE_TAG || \
                kubectl create deployment demo --image=$DOCKER_IMAGE:$IMAGE_TAG
                '''
            }
        }

        stage('Expose Service') {
            steps {
                sh '''
                kubectl get service demo || \
                kubectl expose deployment demo --type=NodePort --port=8080 --target-port=8080
                '''
            }
        }

        stage('Verify') {
            steps {
                sh 'kubectl get all'
            }
        }
    }

    post {
        success {
            echo 'CI/CD Pipeline Executed Successfully!'
        }

        failure {
            echo 'Pipeline Failed!'
        }
    }
}
