# PrivilegedTask
Requesting Runtime Permissions is bound with Activity, Fragment or DialogFragment. This Library Helps decouple your task from those context in just a few steps.

### Add Gradle Dependencies

```groovy
dependencies {
    compile 'com.nillith:privilegedtask:0.2.1'
}
```
### How to use
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

###2 In your Activity, Fragment or DialogFragment
```java
   IPermissionResolver permissionResolver = PermissionResolver.create(this);
   
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        permissionResolver.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
   
```

###3 Run the task

####3.1 For oneshot tasks

```java
permissionResolver.execute(new DemoTask(), ...taskParams);
```

###3.2 For tasks which might be run multiple times

```java
  IPermissionSession<Param> demoSession =  permissionResolver.createSession(new DemoTask());
  demoSession.start(Param...params);
```

###4 Caution
The session object created by permissionResolver.createSession lives as long as the permissionResolver object lives. If That is not what you want, use permissionResolver.execute.
  
  
