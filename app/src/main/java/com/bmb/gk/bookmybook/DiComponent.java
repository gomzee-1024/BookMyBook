package com.bmb.gk.bookmybook;

import android.content.SharedPreferences;

import com.bmb.gk.bookmybook.LoginSignup.SignIn.SignInFragment;
import com.bmb.gk.bookmybook.LoginSignup.SignUp.SignUpFragment;
import com.bmb.gk.bookmybook.VerifyPhone.VerifyPhoneActivity;
import com.bmb.gk.bookmybook.data.DataModule;
import com.bmb.gk.bookmybook.data.UserModule;
import com.bmb.gk.bookmybook.data.UtilsModule;
import com.bmb.gk.bookmybook.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Gautam on 08/04/17.
 */
@Singleton
@Component(modules={AppModule.class, UserModule.class,DataModule.class, UtilsModule.class})
public interface DiComponent {

    SharedPreferences providesSharedPreferences();

    void inject(SplashActivity splashActivity);
    void inject(SignInFragment signInFragment);
    void inject(SignUpFragment signUpFragment);
    void inject(VerifyPhoneActivity verifyPhoneActivity);
}
