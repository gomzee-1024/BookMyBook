package com.bmb.gk.bookmybook;

import android.app.Application;

/**
 * Created by Gautam on 08/04/17.
 */

public class BookMyBookApplication extends Application {
    private static DiComponent mDiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mDiComponent = DaggerDiComponent.builder()
                .appModule(new AppModule(this)).build();
    }

    public static DiComponent getDiComponent(){
        return mDiComponent;
    }

}
