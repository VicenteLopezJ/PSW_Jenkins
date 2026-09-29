pipeline {
    agent any

    stages {
        stage('Descargar proyecto') {
            steps {
                git branch: 'feature/calculadora',
                    credentialsId: 'github-psw-read',
                    url: 'https://github.com/VicenteLopezJ/PSW_Jenkins.git'
            }
        }

        stage('Proyecto Java/Maven') {
            steps {
                dir('S09-Slack') {
                    bat 'mvn clean compile'
                }
            }
        }

        stage('Pruebas') {
            steps {
                dir('S09-Slack') {
                    bat 'mvn clean test'
                }
            }
        }
    }

    post {
        success {
            slackSend(
                channel: '#notificaciones-jenkins',
                color: 'good',
                message: "Build SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER} - Las pruebas unitarias finalizaron exitosamente. (<${env.BUILD_URL}|Ver build>)"
            )
        }
        failure {
            slackSend(
                channel: '#notificaciones-jenkins',
                color: 'danger',
                message: "Build FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER} - El Pipeline presento errores en la fase de pruebas. Revisar logs: ${env.BUILD_URL}console"
            )
        }
    }
}
