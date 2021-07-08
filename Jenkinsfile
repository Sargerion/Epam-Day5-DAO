node {
  stage('SCM Chekout') {
    git 'https://github.com/Sargerion/Epam-Day5-DAO'
  }
  stage('Compile-Package') {
    bat mvn package
  }
}
