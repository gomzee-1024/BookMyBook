package com.bmb.gk.bookmybook.splash;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.bmb.gk.bookmybook.BookMyBookApplication;
import com.bmb.gk.bookmybook.LoginSignup.LogInSignUpActivity;
import com.bmb.gk.bookmybook.R;
import com.bmb.gk.bookmybook.data.Constants;
import com.bmb.gk.bookmybook.data.Utils;


import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class SplashActivity extends AppCompatActivity {

    @Inject
    SharedPreferences preferences;
    @Inject
    Utils utils;

    TextView spashTitle;

    Subscription msubscription;
    AnimatorSet fadeInOutAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        BookMyBookApplication.getDiComponent().inject(this);
        //((BookMyBookApplication)getApplication()).getmDiComponent().inject(this);
        initUI();
        checkPreferencesData();
    }

    private void initUI() {
        spashTitle = (TextView) findViewById(R.id.splash_title);
        setAnimationOnTitle();

    }

    private void checkPreferencesData() {
        if(preferences.getString(Constants.SHARED_PREFERENCE_USER_EMAIL,null)==null){
            Log.d(Constants.APPLICATION_TAG, "checkPreferencesData: "+"null");
        }else{
            Log.d(Constants.APPLICATION_TAG, "checkPreferencesData: "+"!null");
        }
    }

    private void setAnimationOnTitle() {
        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(spashTitle,"scaleX",0f,1f);
        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(spashTitle,"scaleY",0f,1f);
        ObjectAnimator fadeOutAnim = ObjectAnimator.ofFloat(spashTitle,"alpha",1f,0f);
        ObjectAnimator fadeInAnim = ObjectAnimator.ofFloat(spashTitle,"alpha",0f,1f);
        AnimatorSet overshootAnim = new AnimatorSet();
        overshootAnim.playTogether(scaleXAnim,scaleYAnim);
        overshootAnim.setInterpolator(new OvershootInterpolator());
        overshootAnim.setDuration(275);
        fadeInOutAnim = new AnimatorSet();
        fadeInOutAnim.playSequentially(fadeOutAnim,fadeInAnim);
        fadeInOutAnim.setDuration(1000);
        AnimatorSet overShootFadeInOut = new AnimatorSet();
        overShootFadeInOut.playSequentially(overshootAnim,fadeInOutAnim);
        overShootFadeInOut.setStartDelay(300);
        overShootFadeInOut.start();
        fadeInOutAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                subscribeToCheckUserObservable();
                animation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.d(Constants.APPLICATION_TAG, "onAnimationRepeat: ");
            }
        });
    }

    private void subscribeToCheckUserObservable() {
        if(msubscription==null) {
            msubscription = checkUserObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Boolean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Boolean aBoolean) {
                            if (aBoolean.booleanValue()) {
                                fadeInOutAnim.cancel();
                                Intent intent = new Intent(SplashActivity.this, LogInSignUpActivity.class);
                                startActivity(intent);
                                SplashActivity.this.finish();
                            }
                        }
                    });
        }
    }

    public Observable<Boolean> checkUserObservable(){
        Observable<Boolean> observeCheckUser = Observable.create(new Observable.OnSubscribe<Boolean>(){

            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                if(preferences.getString(Constants.SHARED_PREFERENCE_USER_EMAIL,null)==null){
                    subscriber.onNext(new Boolean(true));
                }else{
                    subscriber.onNext(new Boolean(false));
                }
            }
        });
        return observeCheckUser;
    }

    @Override
    protected void onPause() {
        super.onPause();
        msubscription.unsubscribe();
    }
}
