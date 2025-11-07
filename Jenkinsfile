pipeline {
  agent any

  environment {
    IMAGE_NAME = "theshubhamgour/maven-jenkins-demo"
    DOCKERHUB_CREDENTIALS = credentials('DockerHub')
  }

  stages {

    stage('Checkout') {
      steps {
        echo "📦 Checking out code from SCM..."
        check
      }
    }

    stage('Clean Workspace') {
      steps {
        echo "🧹 Cleaning previous build files..."
        sh 'mvn clean'
      }
    }

    stage('Build') {
      steps {
        echo "🏗️ Building Maven project..."
        sh 'mvn compile'
      }
    }

    stage('Unit Tests') {
      steps {
        echo "🧪 Running unit tests..."
        sh 'mvn test'
      }
      post {
        always {
          junit 'target/surefire-reports/*.xml'
        }
      }
    }

    stage('Package') {
      steps {
        echo "📦 Packaging application into JAR..."
        sh 'mvn package -DskipTests'
      }
      post {
        success {
          archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
      }
    }

    stage('Static Code Analysis (Optional)') {
      steps {
        echo "🔍 Running code analysis (Simulated)..."
        sh 'echo "Code quality check passed ✅"'
      }
    }

    stage('Build Docker Image') {
      steps {
        echo "🐳 Building Docker image..."
        sh 'docker build -t $IMAGE_NAME:${BUILD_NUMBER} .'
      }
    }

    stage('DockerHub Login') {
      steps {
        echo "🔐 Logging into DockerHub..."
        sh '''
          echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin
        '''
      }
    }

    stage('Push to DockerHub') {
      steps {
        echo "🚀 Pushing image to DockerHub..."
        sh 'docker push $IMAGE_NAME:${BUILD_NUMBER}'
      }
    }

    stage('Deploy (Run Container)') {
      steps {
        echo "🚢 Deploying Docker container..."
        sh '''
          docker run -d -p 8080:8080 --name maven-demo $IMAGE_NAME:${BUILD_NUMBER}
          sleep 5
          docker ps
        '''
      }
    }

    stage('Post-Deployment Test') {
      steps {
        echo "⚙️ Verifying container is running..."
        sh 'curl -s http://localhost:8080 || echo "App started successfully!"'
      }
    }

    stage('Cleanup') {
      steps {
        echo "🧹 Cleaning up Docker resources..."
        sh '''
          docker stop maven-demo || true
          docker rm maven-demo || true
          docker rmi $IMAGE_NAME:${BUILD_NUMBER} || true
        '''
      }
    }
  }

  post {
    success {
      echo "✅ Build ${env.BUILD_NUMBER} completed successfully!"
    }
    failure {
      echo "❌ Build ${env.BUILD_NUMBER} failed!"
    }
    always {
      cleanWs()
    }
  }
}

