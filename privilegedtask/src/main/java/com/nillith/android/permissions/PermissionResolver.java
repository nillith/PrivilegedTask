package com.nillith.android.permissions;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nil on 2015/12/26.
 */
public abstract class PermissionResolver implements IPermissionResolver {

    class PermissionSession implements IPermissionSession {
        IPrivilegedTask task;
        List<String> pendingPermissions = new ArrayList<>();
        List<String> deniedPermissions = new ArrayList<>();
        int requestCode;

        public PermissionSession(IPrivilegedTask task, int requestCode) {
            this.task = task;
            this.requestCode = requestCode;
        }

        @Override
        public void initiate() {
            for (String permission : task.getRequiredPermissions()) {
                if (!hasPermission(permission)) {
                    pendingPermissions.add(permission);
                }
            }
            if (pendingPermissions.isEmpty()) {
                onPermissionsAllowed();
            } else {
                for (String permission : pendingPermissions) {
                    if (shouldShowRequestPermissionRationale(permission)) {
                        onShowRationale(permission);
                    }
                }
                requestPermissions(pendingPermissions.toArray(new String[pendingPermissions.size()]), requestCode);

            }

        }

        private void onSessionComplete(){
            pendingPermissions.clear();
            deniedPermissions.clear();
        }

        private void onShowRationale(String permission) {
            task.onShowRationale(permission);
        }

        private void onPermissionsAllowed() {
            task.onPermissionsAllowed();
            onSessionComplete();
        }

        private void onPermissionsDenied() {
            task.onPermissionsDenied(deniedPermissions.toArray(new String[deniedPermissions.size()]));
            onSessionComplete();
        }

        private void onRequestPermissionsResult(String[] permissions, int[] grantResults) {
            for (int i = 0; i < grantResults.length; i++) {
                if (PackageManager.PERMISSION_GRANTED != grantResults[i]){
                    deniedPermissions.add(permissions[i]);
                }
            }

            if (deniedPermissions.isEmpty()){
                onPermissionsAllowed();
            }else {
                onPermissionsDenied();
            }

        }
    }

    List<PermissionSession> sessions = new ArrayList<>();

    abstract protected boolean hasPermission(String permission);

    abstract protected boolean shouldShowRequestPermissionRationale(String permission);

    abstract protected void requestPermissions(String[] permissions, int requestCode);

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        sessions.get(requestCode).onRequestPermissionsResult(permissions, grantResults);
    }

    @Override
    public IPermissionSession createSession(IPrivilegedTask privilegedTask) {
        PermissionSession result = new PermissionSession(privilegedTask, sessions.size());
        sessions.add(result);
        return result;
    }

    public static IPermissionResolver create(final Activity activity){
        return new PermissionResolver() {
            @Override
            protected boolean hasPermission(String permission) {
                return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(activity,permission);
            }

            @Override
            protected boolean shouldShowRequestPermissionRationale(String permission) {
               return ActivityCompat.shouldShowRequestPermissionRationale(activity,permission);
            }

            @Override
            protected void requestPermissions(String[] permissions, int requestCode) {
                ActivityCompat.requestPermissions(activity,permissions,requestCode);
            }
        };
    }

}
