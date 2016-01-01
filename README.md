# PrivilegedTask
Requesting Runtime Permissions is bound with Activity, Fragment or DialogFragment. This Library Helps decouple your task from those context in just a few steps.

### Add Gradle Dependencies

```groovy
dependencies {
    compile 'com.nillith:privilegedtask:0.1.0'
}
```

###1 implement a standalone task
```java
class DemoTask extends PrivilegedTask<Param> {
    public String[] getRequiredPermissions(){
      //Required
    }
    
    public void onPermissionsAllowed(Param...params){
      //Required
    }
    
    public void onShowRationale(String permission) {
      //Optional
    }

    @Override
    public void onPermissionsDenied(String[] deniedPermissions){
      //Optional
    }
}
```

###2 In your Activity,Frgament or DialogFragment
```java
   IPermissionResolver permissionResolver = PermissionResolver.create(this);
   
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        permissionResolver.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
   
```

###3 Create a Session

```java
  IPermissionSession<Param> demoSession =  permissionResolver.createSession(new DemoTask());
```

###4 Do the task
```java
  demoSession.initiate(Param...params);// This will request permissions at runtime and call the callback of PrivilegedTask accordingly.
```
  
  
