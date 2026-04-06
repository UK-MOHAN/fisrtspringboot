pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'Java17'
    }

    environment {
        PATH = "/opt/homebrew/bin:${env.PATH}"
    }

    stages {

        stage('Check Java') {
            steps {
                echo '☕ Checking Java...'
                sh 'java -version'
            }
        }

        stage('Check Maven') {
            steps {
                echo '📦 Checking Maven...'
                sh 'mvn -version'
            }
        }

        stage('Check Git') {
            steps {
                echo '🔗 Checking Git...'
                sh 'git --version'
            }
        }

    }

    post {
        success {
            echo '✅ ALL TOOLS WORKING!'
        }
        failure {
            echo '❌ SOMETHING MISSING!'
        }
    }
}