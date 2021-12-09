pipeline {
  agent any
tools {
     maven 'Maven'
   }
  stages {
  stage('Build') {
      steps {
        sh 'mvn clean package'
      }
    }
    stage('SonarQube') {
          steps {
          withSonarQubeEnv('nci-student-accommodation-app'){
          sh 'mvn sonar:sonar'
          }
          }
        }
       stage('Deploy') {
               steps {
               sh 'JENKINS_NODE_COOKIE=dontKillMe nohup java -jar target/accommodationmanager-0.0.1-SNAPSHOT.jar & '
               sh 'sudo pwd'
               }
             }
    }
  }


//
// node{
//     stage('SCM Checkout'){
//         git 'https://github.com/raasalgul/nci-student-accomodation-service.git'
//     }
//     stage('Compile-Package'){
//     def mvnHome = tool name: 'maven-3', type: 'maven'
//     sh "${mvnHome}/bin/mvn package"
//     }
//     stage('SonarQube Analysis'){
//     def mvnHome = tool name: 'maven-3', type: 'maven'
//     withSonarQubeEnv('nci-accommodation-sonar'){
//     sh "${mvnHome}/bin/mvn sonar:sonar"
//     }
//     }
// }