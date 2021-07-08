node {
  stage('SCM Chekout') {
    git 'https://github.com/Sargerion/Epam-Day5-DAO'
  }
  stage('Compile-Package') {
    def mvnHome = tool name: 'maven-3.6.3', type: 'maven'
    bat "${mvnHome}/bin/mvn package"
  }
}
