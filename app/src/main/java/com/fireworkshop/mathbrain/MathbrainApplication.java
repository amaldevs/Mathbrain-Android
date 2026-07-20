package com.fireworkshop.mathbrain;

import android.app.Application;
import com.google.android.gms.ads.MobileAds;

public class MathbrainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this);
    }
}
