node {
  stage('SCM Chekout') {
    git 'https://github.com/Sargerion/Epam-Day5-DAO'
  }
  stage('Compile-Package') {
    def mvnHome = tool name: 'M2_HOME', type: 'maven'
    sh "${mvnHome}/bin/mvn package"
  }
}
