# PrivilegedTask
Helper classes for Requesting Android Runtime Permissions.

### Add Gradle Dependencies

```groovy
dependencies {
    compile 'com.nillith:privilegedtask:0.3.1'
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
    DemoTask task = new DemoTask();
   
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        task.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
   
```

###3 Run the task

```java
task.start(THost host, TParam...params); // The host is the Activity, Fragment or DialogFragment from which the task is to run.

...
  
  
