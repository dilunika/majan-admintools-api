def version = '0.1.0'
def releaseVersion = version + '.d' +  env.BUILD_NUMBER
def binaryFileName = 'majadadmintools-' + releaseVersion  + '.zip'

node {

    stage('Checkout'){

        try {

            git 'https://github.com/dilunika/majan-admintools-api.git'
            echo 'Checked out repository.'

        } catch (Exception e) {

            def message = 'Failed to checkout from repository. ' + e.message
            notifyBuildBreaks(message)
            throw e
        }
    }

    stage('Unit Tests'){
        sh './gradlew clean test'
        junit 'build/test-results/junit-platform/TEST-*.xml'
    }

    stage('Generate Binaries') {

        sh './gradlew shadowJar'

        deleteFolder('archive')

        copyFile('*-fat.jar', 'archive')

        copyFolder('ops', 'archive')

        copyFile('appspec.yml', 'archive')

        zip archive: true, dir: 'archive', glob: '', zipFile: binaryFileName

    }

}

def notifyBuildBreaks(errorMessage) {

    def commiter = sh (
            script: 'git --no-pager show -s --format=\'%ae\'',
            returnStdout: true
    ).trim()

    currentBuild.result = 'FAILURE'
    def message = 'Majan Admin Tools API Build Failed. Last Commit is done by : ' + commiter + ' and reason is - ' + errorMessage + '. Visit ' + currentBuild.absoluteUrl;
    sendSlack('#FF0000', message)
}

def sendSlack(color, message) {

    slackSend channel: '#api-delivery',
            color: color,
            message: message,
            teamDomain: 'majan',
            tokenCredentialId: 'majanslack'
}

def deleteFolder(folderToBeDeleted){

    step([$class: 'FileOperationsBuilder',
          fileOperations: [
                  [
                          $class: 'FolderDeleteOperation',
                          folderPath: folderToBeDeleted
                  ]
          ]
    ])
}

def copyFolder(from, to) {

    step([$class: 'FileOperationsBuilder',
          fileOperations: [
                  [
                          $class: 'FolderCopyOperation',
                          destinationFolderPath: to,
                          sourceFolderPath: from
                  ]
          ]
    ])
}

def copyFile(file, toFolder) {

    step([$class: 'FileOperationsBuilder',
          fileOperations: [
                  [
                          $class: 'FileCopyOperation',
                          excludes: '',
                          flattenFiles: false,
                          includes: file,
                          targetLocation: toFolder
                  ]
          ]
    ])
}