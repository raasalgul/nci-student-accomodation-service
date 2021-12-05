// pipeline {
//   agent any
//   tools {
//     maven 'Maven'
//   }
//   stages {
//   stage('Build') {
//       steps {
//         sh 'mvn clean package'
//       }
//     }
//     }
//   }



node{
    stage('SCM Checkout'){
        git 'https://github.com/raasalgul/nci-student-accomodation-service.git'
    }
    stage('Compile-Package'){
    def mvnHome = tool name: 'maven-3', type: 'maven'
    sh "${mvnHome}/bin/mvn package"
    }
    stage('SonarQube Analysis'){
    def mvnHome = tool name: 'maven-3', type: 'maven'
    withSonarQubeEnv('sonar-token'){
    sh "${mvnHome}/bin/mvn sonar:sonar"
    }
    }
}