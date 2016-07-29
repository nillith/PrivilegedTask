# PrivilegedTask
Helper classes for Requesting Android Runtime Permissions.

### Add Gradle Dependencies

```groovy
dependencies {
    compile 'com.nillith:privilegedtask:0.4.0'
}
```
### How to use

###1 In your Activity, Fragment or DialogFragment
```java
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PrivilegedTask.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ...
    }

```

###2 Implement a standalone task
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
###3 Run the task

```java
DemoTask task = new DemoTask();
task.initiate(THost host, TParam...params);
// The host is the Activity, Fragment or DialogFragment from which the task is to run.

```


### Quick way

```java
PrivilegedTask.initiate(THost host, String[] requiredPermissions, Runnable onGranted, Runnable onDenied);
```

  
  
