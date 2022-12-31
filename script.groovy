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
    withcredentials([usernamePassword(credentialsId: 'jenkins-dockerhub', usernameVariable: 'username' passwordVariable: 'passwd')]) {
        sh 'docker build -t ahmadkhdocker/my-private-repo:my-app-'
        sh 'echo $passwd | docker login -u $username --password-stdin'
        sh 'docker push ahmadkhdocker/my-private-repo:my-app-'
        }
    }

def deployingAPP() {
    echo 'deploying the Application...'
}

return this