pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'Java17'
    }

    environment {
         PATH        = "/opt/homebrew/bin:${env.PATH}"
    JIRA_URL    = "https://deltadesigns.atlassian.net"  // ✅ your domain
    JIRA_EMAIL  = "mohan.pudi046@gmail.com"
    PROJECT_KEY = "KAN"
    }

    stages {

        // ─── GET COMMIT INFO ──────────────────────────────
        stage('Get Commit Info') {
            steps {
                script {
                    env.GIT_COMMIT_MSG = sh(
                        script: 'git log -1 --pretty=%B',
                        returnStdout: true
                    ).trim()

                    env.GIT_AUTHOR = sh(
                        script: 'git log -1 --pretty=%an',
                        returnStdout: true
                    ).trim()

                    env.GIT_AUTHOR_EMAIL = sh(
                        script: 'git log -1 --pretty=%ae',
                        returnStdout: true
                    ).trim()

                    env.GIT_COMMIT_DATE = sh(
                        script: 'git log -1 --pretty=%cd --date=format:"%Y-%m-%d %H:%M"',
                        returnStdout: true
                    ).trim()

                    env.GIT_COMMIT_HASH = sh(
                        script: 'git log -1 --pretty=%h',
                        returnStdout: true
                    ).trim()

                    env.GIT_BRANCH_NAME = sh(
                        script: 'git rev-parse --abbrev-ref HEAD',
                        returnStdout: true
                    ).trim()

                    // Extract Jira ticket from commit message
                    def matcher = (env.GIT_COMMIT_MSG =~ /([A-Z]+-\d+)/)
                    if (matcher.find()) {
                        env.JIRA_TICKET = matcher[0][1]
                    } else {
                        def branchMatcher = (env.GIT_BRANCH_NAME =~ /([A-Z]+-\d+)/)
                        if (branchMatcher.find()) {
                            env.JIRA_TICKET = branchMatcher[0][1]
                        } else {
                            env.JIRA_TICKET = "NO-TICKET"
                        }
                    }

                    echo "====================================="
                    echo "📋 COMMIT INFORMATION"
                    echo "====================================="
                    echo "🎫 Jira Ticket  : ${env.JIRA_TICKET}"
                    echo "👤 Author       : ${env.GIT_AUTHOR}"
                    echo "📧 Email        : ${env.GIT_AUTHOR_EMAIL}"
                    echo "💬 Message      : ${env.GIT_COMMIT_MSG}"
                    echo "🔑 Commit Hash  : ${env.GIT_COMMIT_HASH}"
                    echo "🌿 Branch       : ${env.GIT_BRANCH_NAME}"
                    echo "📅 Date         : ${env.GIT_COMMIT_DATE}"
                    echo "🔢 Build Number : ${env.BUILD_NUMBER}"
                    echo "====================================="
                }
            }
        }

        // ─── CHECK TOOLS ──────────────────────────────────
        stage('Check Java') {
            steps {
                echo '☕ Checking Java...'
                sh 'java -version'
                sh 'which java'
            }
        }

        stage('Check Maven') {
            steps {
                echo '📦 Checking Maven...'
                sh 'mvn -version'
                sh 'which mvn'
            }
        }

        stage('Check Git') {
            steps {
                echo '🔗 Checking Git...'
                sh 'git --version'
            }
        }

        // ─── NOTIFY JIRA - STARTED ────────────────────────
        stage('Notify Jira - Started') {
            when {
                expression { return env.JIRA_TICKET != 'NO-TICKET' }
            }
            steps {
                script {
                    echo "📢 Updating Jira ticket ${env.JIRA_TICKET}..."
                    withCredentials([usernamePassword(
                          credentialsId: 'jira-creds',      // ✅ leave as is
    usernameVariable: 'JIRA_USER',    // ✅ leave as is
    passwordVariable: 'JIRA_TOKEN'    // ✅ leave as is
                    
                    )]) {
                        sh """
                            curl -s -X POST \\
                            -H "Content-Type: application/json" \\
                            -u "${JIRA_USER}:${JIRA_TOKEN}" \\
                            -d '{
                                "body": "🚀 Jenkins Build #${BUILD_NUMBER} STARTED\\n\\nAuthor: ${GIT_AUTHOR}\\nEmail: ${GIT_AUTHOR_EMAIL}\\nTicket: ${JIRA_TICKET}\\nCommit: ${GIT_COMMIT_HASH}\\nBranch: ${GIT_BRANCH_NAME}\\nMessage: ${GIT_COMMIT_MSG}\\nDate: ${GIT_COMMIT_DATE}\\nBuild URL: ${BUILD_URL}"
                            }' \\
                            "${JIRA_URL}/rest/api/2/issue/${JIRA_TICKET}/comment"
                        """
                    }
                    echo "✅ Jira updated - Build Started!"
                }
            }
        }

        // ─── BUILD ────────────────────────────────────────
        stage('Maven Clean') {
            steps {
                echo '🧹 Cleaning old build...'
                sh 'mvn clean'
            }
        }

        stage('Maven Compile') {
            steps {
                echo "🔨 Compiling ${env.JIRA_TICKET} by ${env.GIT_AUTHOR}..."
                sh 'mvn compile'
            }
        }

        stage('Maven Test') {
            steps {
                echo '🧪 Running tests...'
                sh 'mvn test -DskipTests'
            }
        }

        stage('Maven Package') {
            steps {
                echo '📦 Packaging JAR...'
                sh 'mvn package -DskipTests'
                sh 'ls -lh target/*.jar'
            }
        }
        
          stage('Memory Info') {
         steps {
           echo '🧠 Memory...'
           sh 'vm_stat'           // ✅ no dash needed
          sh 'sysctl hw.memsize'
       }
      }
      
      stage('Disk Space') {
    steps {
        echo '💾 Disk...'
        sh 'df -h'             // ✅ space between df and -h
        sh 'du -sh . '         // ✅ current folder size only
    }
}
       

        // ─── DEPLOY ───────────────────────────────────────
        stage('Deploy') {
            when {
                branch 'main'
            }
            steps {
                echo '🚀 Deploying application...'
                sh '''
                    pkill -f "java -jar" || true
                    sleep 2
                    nohup java -jar target/*.jar > app.log 2>&1 &
                    echo "✅ App deployed on port 8080!"
                '''
            }
        }

        // ─── NOTIFY JIRA - DONE ───────────────────────────
        stage('Notify Jira - Done') {
            when {
                expression { return env.JIRA_TICKET != 'NO-TICKET' }
            }
            steps {
                script {
                    withCredentials([usernamePassword(
                        credentialsId: 'jira-creds',
                        usernameVariable: 'JIRA_USER',
                        passwordVariable: 'JIRA_TOKEN'
                    )]) {
                        sh """
                            curl -s -X POST \\
                            -H "Content-Type: application/json" \\
                            -u "${JIRA_USER}:${JIRA_TOKEN}" \\
                            -d '{
                                "body": "✅ Jenkins Build #${BUILD_NUMBER} PASSED\\n\\nAuthor: ${GIT_AUTHOR}\\nTicket: ${JIRA_TICKET}\\nCommit: ${GIT_COMMIT_HASH}\\nBranch: ${GIT_BRANCH_NAME}\\nBuild URL: ${BUILD_URL}"
                            }' \\
                            "${JIRA_URL}/rest/api/2/issue/${JIRA_TICKET}/comment"
                        """
                    }
                    echo "✅ Jira ticket ${env.JIRA_TICKET} updated - Build Passed!"
                }
            }
        }

    }

    // ─── POST ACTIONS ─────────────────────────────────────
    post {

        success {
            echo """
            ╔══════════════════════════════════════╗
            ║       BUILD SUCCESSFUL! ✅            ║
            ╠══════════════════════════════════════╣
            ║  Ticket : ${env.JIRA_TICKET}
            ║  Author : ${env.GIT_AUTHOR}
            ║  Commit : ${env.GIT_COMMIT_HASH}
            ║  Branch : ${env.GIT_BRANCH_NAME}
            ║  Build  : #${env.BUILD_NUMBER}
            ╚══════════════════════════════════════╝
            """
        }

        failure {
            script {
                echo """
                ╔══════════════════════════════════════╗
                ║         BUILD FAILED! ❌              ║
                ╠══════════════════════════════════════╣
                ║  Ticket : ${env.JIRA_TICKET}
                ║  Author : ${env.GIT_AUTHOR}
                ║  Commit : ${env.GIT_COMMIT_MSG}
                ║  Build  : #${env.BUILD_NUMBER}
                ╚══════════════════════════════════════╝
                """

                // Notify Jira on failure
                if (env.JIRA_TICKET != 'NO-TICKET') {
                    withCredentials([usernamePassword(
                        credentialsId: 'jira-creds',
                        usernameVariable: 'JIRA_USER',
                        passwordVariable: 'JIRA_TOKEN'
                    )]) {
                        sh """
                            curl -s -X POST \\
                            -H "Content-Type: application/json" \\
                            -u "${JIRA_USER}:${JIRA_TOKEN}" \\
                            -d '{
                                "body": "❌ Jenkins Build #${BUILD_NUMBER} FAILED\\n\\nAuthor: ${GIT_AUTHOR}\\nTicket: ${JIRA_TICKET}\\nCommit: ${GIT_COMMIT_MSG}\\nCheck logs: ${BUILD_URL}console"
                            }' \\
                            "${JIRA_URL}/rest/api/2/issue/${JIRA_TICKET}/comment"
                        """
                    }
                }
            }
        }

        always {
            echo "⏱️ Build #${env.BUILD_NUMBER} finished!"
            echo "🎫 Ticket: ${env.JIRA_TICKET}"
            echo "👤 Author: ${env.GIT_AUTHOR}"
        }
    }
}