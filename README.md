# PrivilegedTask
Helper classes for Requesting Android Runtime Permissions.

### Add Gradle Dependencies

```groovy
dependencies {
    compile 'com.nillith:privilegedtask:0.2.2'
}
```
### How to use
###1 Implement a standalone task
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
demoSession =  permissionResolver.createSession(new DemoTask());
  
...
demoSession.start(Param...params);// add to where you need run the task.
```

### Caution
The session object created by permissionResolver.createSession lives as long as the permissionResolver object lives. If that is not what you want, use permissionResolver.execute.
  
  
