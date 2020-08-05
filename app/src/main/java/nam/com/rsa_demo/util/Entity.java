package nam.com.rsa_demo.util;

import android.os.Environment;

import java.io.File;

public class Entity {
    public static final String LIST_RSA = "LIST_RSA";


    public static final String PRIVATE_FILE = Environment.getExternalStorageDirectory()
            + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator + "private_key";
    public static final String PUBLIC_FILE = Environment.getExternalStorageDirectory()
            + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator + "public_key";

}
