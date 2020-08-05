package nam.com.rsa_demo.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Cache {
    SharedPreferences sharedPreferences;
    private static final String CACHE = "CACHE";
    private static Cache cache;

    public static Cache getInstance(Context context) {
        if (cache == null) {
            cache = new Cache();
            cache.init(context);
        }
        return cache;
    }

    private void init(Context context) {
        sharedPreferences = context.getSharedPreferences(CACHE, Context.MODE_PRIVATE);
    }

    public void putString(String key, String val) {
        sharedPreferences.edit().putString(key, val).apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

}
