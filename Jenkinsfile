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
     stage('Testing') {
             steps {
             sh 'mvn test'
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
//        sh 'JENKINS_NODE_COOKIE=dontKillMe nohup java -jar target/accommodationmanager-0.0.1-SNAPSHOT.jar & '
        sh "pid=\$(lsof -i:8089 -t); kill -TERM \$pid "
                  + "|| kill -KILL \$pid"
        withEnv(['JENKINS_NODE_COOKIE=dontkill']) {
        sh 'nohup ./mvnw spring-boot:run -Dserver.port=8089 &'
               }
       sh 'pwd'
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