pipeline {
    agent any

    tools {
        jdk "Temurin Java 21"
    }

    triggers {
        githubPush()
    }

    stages {
        stage('Setup Gradle') {
            steps {
                sh 'chmod +x gradlew'
            }
        }

        stage('Remapping Classpath') {
            steps {
                withGradle {
                    sh './gradlew generateRemapClasspath'
                }
            }
        }

        stage('Build') {
            steps {
                withGradle {
                    sh './gradlew build'
                }
            }
            post {
                success {
                    archiveArtifacts artifacts: '**/build/libs/*.jar', fingerprint: true, onlyIfSuccessful: true
                }
            }
        }

        stage('Deploy Release') {
            when {
                expression { env.TAG_NAME != null && env.TAG_NAME.matches('v\\d+\\.\\d+\\.\\d+') }
            }
            steps {
                withCredentials([
                        string(credentialsId: 'modrinth-publish-token', variable: 'ORG_GRADLE_PROJECT_modrinth.token'),
                ]) {
                    withGradle {
                        sh './gradlew modrinth'
                    }
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}