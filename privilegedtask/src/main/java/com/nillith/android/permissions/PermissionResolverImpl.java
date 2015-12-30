package com.nillith.android.permissions;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nil on 2015/12/26.
 */
abstract class PermissionResolverImpl extends PermissionResolver {

    class PermissionSession<TParam> implements IPermissionSession<TParam> {
        IPrivilegedTask<TParam> task;
        List<String> pendingPermissions = new ArrayList<>();
        List<String> deniedPermissions = new ArrayList<>();
        TParam[] params;
        int requestCode;

        public PermissionSession(IPrivilegedTask task, int requestCode) {
            this.task = task;
            this.requestCode = requestCode;
        }

        @Override
        public void initiate(TParam... params) {
            this.params = params;
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

        private void onSessionComplete() {
            pendingPermissions.clear();
            deniedPermissions.clear();
            params = null;
        }

        private void onShowRationale(String permission) {
            task.onShowRationale(permission);
        }

        private void onPermissionsAllowed() {
            task.onPermissionsAllowed(params);
            onSessionComplete();
        }

        private void onPermissionsDenied() {
            task.onPermissionsDenied(deniedPermissions.toArray(new String[deniedPermissions.size()]));
            onSessionComplete();
        }

        private void onRequestPermissionsResult(String[] permissions, int[] grantResults) {
            for (int i = 0; i < grantResults.length; i++) {
                if (PackageManager.PERMISSION_GRANTED != grantResults[i]) {
                    deniedPermissions.add(permissions[i]);
                }
            }

            if (deniedPermissions.isEmpty()) {
                onPermissionsAllowed();
            } else {
                onPermissionsDenied();
            }

        }
    }


    SparseArray<PermissionSession> sessions = new SparseArray<>();

    private boolean hasPermission(String permission) {
        return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(getContext(), permission);
    }

    abstract protected Context getContext();

    abstract protected boolean shouldShowRequestPermissionRationale(String permission);

    abstract protected void requestPermissions(String[] permissions, int requestCode);

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        PermissionSession requestSession = sessions.get(requestCode);
        if (null != requestSession){
            requestSession.onRequestPermissionsResult(permissions, grantResults);
        }
    }

    @Override
    public <TParam> IPermissionSession<TParam> createSession(IPrivilegedTask<TParam> privilegedTask) {
        int requestCode = RequestCodeGenerator.next();
        PermissionSession result = new PermissionSession(privilegedTask, requestCode);
        sessions.append(requestCode,result);
        return result;
    }

}
