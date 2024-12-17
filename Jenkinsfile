/*
 * Copyright (c) 2024 solonovamax <solonovamax@12oclockpoint.com>
 *
 * The file Jenkinsfile is part of LogsBeGone
 * Last modified on 16-12-2024 08:00 p.m.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * LOGSBEGONE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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