package com.bmb.gk.bookmybook.VerifyPhone;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bartoszlipinski.viewpropertyobjectanimator.ViewPropertyObjectAnimator;
import com.bmb.gk.bookmybook.BookMyBookApplication;
import com.bmb.gk.bookmybook.R;
import com.bmb.gk.bookmybook.data.Constants;
import com.bmb.gk.bookmybook.data.Utils;

import javax.inject.Inject;

public class VerifyPhoneActivity extends AppCompatActivity implements View.OnClickListener{

    @Inject
    Utils utils;

    private EditText phone_no_edit_text,digit1,digit2,digit3,digit4;
    private CardView digit1Card,digit2Card,digit3Card,digit4Card;
    private TextView editPhoneTextView,resendCodeTextView;
    private Button verifyButton;
    private ImageView phone_image_view;
    private boolean verifyButtonState = Constants.VERIFY_BUTTON_STATE_PHONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);
        initUI();
    }

    private void initUI() {
        BookMyBookApplication.getDiComponent().inject(this);
        phone_no_edit_text = (EditText) findViewById(R.id.verify_edit_text);
        digit1 = (EditText) findViewById(R.id.digit1);
        digit2 = (EditText) findViewById(R.id.digit2);
        digit3 = (EditText) findViewById(R.id.digit3);
        digit4 = (EditText) findViewById(R.id.digit4);
        digit1Card = (CardView) findViewById(R.id.card1);
        digit2Card = (CardView) findViewById(R.id.card2);
        digit3Card = (CardView) findViewById(R.id.card3);
        digit4Card = (CardView) findViewById(R.id.card4);
        editPhoneTextView = (TextView) findViewById(R.id.edit_phone_text_view);
        resendCodeTextView = (TextView) findViewById(R.id.resend_verify_code_text_view);
        verifyButton = (Button) findViewById(R.id.verify_button);
        phone_image_view = (ImageView) findViewById(R.id.verify_edit_text_image);
        verifyButton.setOnClickListener(this);
        editPhoneTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_phone_text_view : changeButtonStateAndAnimate();
                break;
            case R.id.verify_button : changeButtonStateAndAnimate();
                break;
        }
    }

    private void changeButtonStateAndAnimate() {
        if(verifyButtonState == Constants.VERIFY_BUTTON_STATE_PHONE){
            verifyButtonState = Constants.VERIFY_BUTTON_STATE_CODE;
            getVisibilityObjectAnimator(editPhoneTextView,true).start();
            getVisibilityObjectAnimator(resendCodeTextView,true).start();
            getVisibilityObjectAnimator(phone_image_view,false).start();
            phone_image_view.setVisibility(View.INVISIBLE);
            getVisibilityObjectAnimator(phone_no_edit_text,false).start();
            phone_no_edit_text.setVisibility(View.INVISIBLE);
            getCardObjectAnimator(digit1Card,true).start();
            getCardObjectAnimator(digit2Card,true).start();
            getCardObjectAnimator(digit3Card,true).start();
            getCardObjectAnimator(digit4Card,true).start();
            getVisibilityObjectAnimator(digit1,true).start();
            getVisibilityObjectAnimator(digit2,true).start();
            getVisibilityObjectAnimator(digit3,true).start();
            getVisibilityObjectAnimator(digit4,true).start();
        }else{
            verifyButtonState = Constants.VERIFY_BUTTON_STATE_PHONE;
            getVisibilityObjectAnimator(editPhoneTextView,false).start();
            editPhoneTextView.setVisibility(View.INVISIBLE);
            getVisibilityObjectAnimator(resendCodeTextView,false).start();
            resendCodeTextView.setVisibility(View.INVISIBLE);
            getVisibilityObjectAnimator(phone_image_view,true).start();
            getVisibilityObjectAnimator(phone_no_edit_text,true).start();
            getCardObjectAnimator(digit1Card,false).start();
            getCardObjectAnimator(digit2Card,false).start();
            getCardObjectAnimator(digit3Card,false).start();
            getCardObjectAnimator(digit4Card,false).start();
            getVisibilityObjectAnimator(digit1,false).start();
            digit1.setVisibility(View.INVISIBLE);
            getVisibilityObjectAnimator(digit2,false).start();
            digit2.setVisibility(View.INVISIBLE);
            getVisibilityObjectAnimator(digit3,false).start();
            digit3.setVisibility(View.INVISIBLE);
            getVisibilityObjectAnimator(digit4,false).start();
            digit4.setVisibility(View.INVISIBLE);
        }
    }

    private ViewPropertyObjectAnimator getCardObjectAnimator(View v , boolean contract) {
        if(contract){
            ViewPropertyObjectAnimator anim = ViewPropertyObjectAnimator.animate(v)
                                                .width(v.getWidth()-(int)utils.getPixelsFromDPs(16))
                                                .leftMargin(v.getLeft()+(int)utils.getPixelsFromDPs(8))
                                                .rightMargin(v.getRight()+(int)utils.getPixelsFromDPs(8))
                                                .setInterpolator(new OvershootInterpolator())
                                                .setDuration(225);
            return anim;
        }else{
            ViewPropertyObjectAnimator anim = ViewPropertyObjectAnimator.animate(v)
                    .width(v.getWidth()+(int)utils.getPixelsFromDPs(16))
                    .leftMargin(v.getLeft()-(int)utils.getPixelsFromDPs(8))
                    .rightMargin(v.getRight()-(int)utils.getPixelsFromDPs(8))
                    .setInterpolator(new OvershootInterpolator())
                    .setDuration(225);
            return  anim;
        }
    }

    private ValueAnimator getVisibilityObjectAnimator(View v, boolean toVisible){
        if(toVisible) {
            v.setVisibility(View.VISIBLE);
            ObjectAnimator anim = ObjectAnimator.ofFloat(v, View.ALPHA, 1f);
            anim.setInterpolator(new DecelerateInterpolator());
            anim.setDuration(225);
            return anim;
        }else{
            ObjectAnimator anim = ObjectAnimator.ofFloat(v, View.ALPHA, 0f);
            anim.setInterpolator(new DecelerateInterpolator());
            anim.setDuration(225);
            return anim;
        }
    }
}
