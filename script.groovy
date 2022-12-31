def buildapp() {
    echo "building the APP..."
    sh 'mvn clean package'
}

def testApp() {
    echo "Testing APP...."
    sh 'mvn test'
}

def buildImage() {
    echo "building docker image....."
    withCredentials([usernamePassword(credentialsId: 'jenkins-dockerhub', usernameVariable: 'username', passwordVariable: 'passwd')]) {
        sh 'docker build -t ahmadkhdocker/my-private-repo:my-app-1.0 .'
        sh 'echo $passwd | docker login -u $username --password-stdin'
        sh 'docker push ahmadkhdocker/my-private-repo:my-app-1.0'
        }
    }

def deployingAPP() {
    echo 'deploying the Application...'
}

return this
