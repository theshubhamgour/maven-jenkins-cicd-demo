pipeline {
  agent any

  environment {
    IMAGE_NAME = "theshubhamgour/maven-jenkins-demo"
    DOCKERHUB_CREDENTIALS = credentials('DockerHub')
  }

  stages {

    stage('Checkout') {
      steps {
        echo "Checking out code from SCM..."
        checkout scm
      }
    }

    stage('Clean Workspace') {
      steps {
        echo "Cleaning previous build files..."
        sh 'mvn clean'
      }
    }

    stage('Build') {
      steps {
        echo "Building Maven project..."
        sh 'mvn compile'
      }
    }

    stage('Unit Tests') {
      steps {
        echo "Running unit tests..."
        sh 'mvn test'
      }
      post {
        always {
          script {
            def testResults = findFiles(glob: 'target/surefire-reports/*.xml')
            if (testResults && testResults.length > 0) {
              echo "Publishing JUnit test results..."
              junit 'target/surefire-reports/*.xml'
            } else {
              echo "⚠️ No JUnit test report files found — skipping test report publishing."
            }
          }
        }
      }
    }

    stage('Package') {
      steps {
        echo "Packaging application into JAR..."
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
        echo "Pushing image to DockerHub..."
        sh 'docker push $IMAGE_NAME:${BUILD_NUMBER}'
      }
    }

    stage('Deploy (Run Container)') {
      steps {
        echo "Deploying Docker container..."
        sh '''
            docker logs maven-demo
        '''
      }
    }

    stage('Cleanup') {
      steps {
        echo "Cleaning up Docker resources..."
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
