package nam.com.rsa_demo.presenter;

import android.content.Context;

import java.io.File;
import java.security.NoSuchAlgorithmException;

import nam.com.rsa_demo.event.MainEvent;
import nam.com.rsa_demo.rsa.GenerateKeys;
import nam.com.rsa_demo.util.Entity;
import nam.com.rsa_demo.util.PermissionController;

public class MainPresenter {
    MainEvent mainEvent;
    Context context;
    GenerateKeys generateKeys;

    public MainPresenter(Context context) {
        this.context = context;
    }

    public void onMainEvent(MainEvent mainEvent) {
        this.mainEvent = mainEvent;
    }

    public void generateKeys() {
        try {
            generateKeys = GenerateKeys.getInstance(2048);
            generateKeys.createKeys();
            mainEvent.onViewSuccess();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            mainEvent.onViewFails();
        }
    }

    public void getPrivateKey() throws Exception {
        if (PermissionController.CHECK_WRITE_READ_STORAGE_PERMISSION(context, 1))
            if (!new File(Entity.PRIVATE_FILE).exists()) {
                generateKeys.generateKeysToFile(Entity.PRIVATE_FILE, context, 1, generateKeys.getPrivateKey().getEncoded());
            }

    }

    public void getPublicKey() throws Exception {
        if (PermissionController.CHECK_WRITE_READ_STORAGE_PERMISSION(context, 1))
            if (!new File(Entity.PUBLIC_FILE).exists()) {
                generateKeys.generateKeysToFile(Entity.PUBLIC_FILE, context, 1, generateKeys.getPublicKey().getEncoded());
            }

    }
}
