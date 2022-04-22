package com.sabinApps.indieRunner;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.sabinApps.mylibgdx.MenuScreen;

public class MyActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
       cfg.useWakelock = false;
        cfg.useAccelerometer = false;
        cfg.useCompass = false;
        cfg.useGL20=false;
        initialize(new MenuScreen(), cfg);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
