def call(Map params) {

  println("Building Docker image");

 


//log.info(message: "Uploading to openshift")
  sh '''  
GIT_COMMIT=$(git rev-parse HEAD) 
echo -e "
LABEL io.openshift.build.commit.vendor=\'loop software\'
LABEL io.openshift.build.commit.url=\'${GIT_URL}\'
LABEL io.openshift.build.commit.id=\'${GIT_COMMIT}\'
LABEL io.openshift.build.commit.ref=\'${BRANCH_NAME}\'" >> Dockerfile
oc new-build --strategy docker --binary --name myapp2 --to="myapp2"
oc start-build myapp2 --from-dir . --follow
oc new-app myapp2
oc expose svc/myapp2
     '''

}
