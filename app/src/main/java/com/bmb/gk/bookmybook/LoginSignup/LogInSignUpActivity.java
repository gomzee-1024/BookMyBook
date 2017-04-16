package com.bmb.gk.bookmybook.LoginSignup;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.bmb.gk.bookmybook.LoginSignup.SignIn.SignInFragment;
import com.bmb.gk.bookmybook.LoginSignup.SignUp.SignUpFragment;
import com.bmb.gk.bookmybook.R;

public class LogInSignUpActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyPagerAdapter mPagerAdapter;
    private SignInFragment signInFragment;
    private SignUpFragment signUpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_sign_up);
        initUI();
    }

    private void initUI() {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout_sign_in);
        mViewPager = (ViewPager) findViewById(R.id.view_pager_sign_in);
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        signInFragment = SignInFragment.newInstance("","");
        signUpFragment = SignUpFragment.newInstance("","");
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return signInFragment;
            }else{
                return signUpFragment;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position==0){
                return "Sign In";
            }else{
                return "Sign Up";
            }
        }
    }
}
