import static cicd.VersionManager.getNextVersion

def call(Map params) {

  println("Building Docker image");

 


log.info(message: "Uploading to openshift")
  sh '''  
oc new-build --strategy docker --binary --name myapp2 --to="myapp2"
oc start-build myapp2 --from-dir . --follow
oc new-app myapp2
oc expose svc/myapp2
     '''

}
