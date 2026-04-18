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

        stage('Maven Clean') {
            steps {
                echo '🧹 Maven clean...'
                sh 'mvn clean'          // ✅ mvn not maven
            }
        }

        stage('Maven Install') {
            steps {
                echo '📦 Maven install...'
                sh 'mvn install -DskipTests'   // ✅ mvn not maven
            }
        }

        stage('Maven Build') {          // ✅ fixed typo satge → stage
            steps {
                echo '🔨 Building JAR...'
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Check Git') {
            steps {
                echo '🔗 Checking Git...'
                sh 'git --version'
            }
        }
        
        stage('Who Am I'){
			steps{
				echo '👤 Current user...'
				sh 'whoami'
			}
			}
	    stage('Where Am I'){
			steps{
				echo  '📍 Current directory...'
				sh 'pwd'
			}
		} 
		
		stage('What OS'){
			steps{
				echo '💻 Operating System...'
				sh 'uname-a'
				sh 'sw_vers'
			}
		}	
		
		
		        stage('CPU Info') {
            steps {
                echo '⚡ CPU Info...'
                sh 'sysctl -n machdep.cpu.brand_string'   // CPU name on Mac
                sh 'sysctl -n hw.ncpu'                    // number of CPUs
            }
        }

        stage('Memory Info') {
            steps {
                echo '🧠 Memory Info...'
                sh 'vm_stat'                   // memory stats on Mac
                sh 'sysctl hw.memsize'         // total RAM in bytes
            }
        }

        stage('Disk Space') {
            steps {
                echo '💾 Disk Space...'
                sh 'df -h'                     // disk usage human readable
                sh 'du -sh *'                  // size of each folder
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