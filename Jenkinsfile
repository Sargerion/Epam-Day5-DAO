node {
  stage('SCM Chekout') {
    git 'https://github.com/Sargerion/Epam-Day5-DAO'
  }
  stage('Compile-Package') {
    sh "${M2_HOME}/bin/mvn package"
  }
}
