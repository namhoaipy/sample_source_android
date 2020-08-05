package nam.com.rsa_demo.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;

public class PermissionController {
    public static boolean CHECK_WRITE_READ_STORAGE_PERMISSION(Context context,int request_code){
        boolean isCheck = false;
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions((Activity)context
                    ,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                            ,Manifest.permission.WRITE_EXTERNAL_STORAGE},request_code);
            isCheck = false;
        }else {
            isCheck = true;
        }
        return isCheck;
    }
}
