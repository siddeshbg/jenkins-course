job('NodeJS Docker example') {
    scm {
        git('https://github.com/siddeshbg/jenkins-course.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Siddesh BG')
            node / gitConfigEmail('siddesh.bg@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('siddeshbg/jenkins-course.git')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('my-docker-hub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
