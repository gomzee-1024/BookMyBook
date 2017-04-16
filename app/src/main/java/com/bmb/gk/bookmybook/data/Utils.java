package com.bmb.gk.bookmybook.data;

import android.app.Application;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Gautam on 10/04/17.
 */
@Singleton
public class Utils {

    private Application application;

    @Inject
    Utils(Application application){
        this.application = application;
    }

    public float getPixelsFromDPs(int dps){
        Resources r = application.getResources();
        int  px = (int) (TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dps, r.getDisplayMetrics()));
        Log.d(Constants.APPLICATION_TAG, "getPixelsFromDPs: "+px);
        return px;
    }

    public void logMessage(String subject,String msg){
        Log.d(Constants.APPLICATION_TAG,  subject+": "+msg);
    }

    public void toastMessageShort(String msg){
        Toast.makeText(application.getApplicationContext(),msg,Toast.LENGTH_SHORT);
    }

    public void toastMessageLong(String msg){
        Toast.makeText(application.getApplicationContext(),msg,Toast.LENGTH_LONG);
    }
}
