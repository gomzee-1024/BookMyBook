package com.bmb.gk.bookmybook.LoginSignup.SignIn;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Application;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bmb.gk.bookmybook.BookMyBookApplication;
import com.bmb.gk.bookmybook.R;
import com.bmb.gk.bookmybook.VerifyPhone.VerifyPhoneActivity;
import com.bmb.gk.bookmybook.data.Constants;
import com.bmb.gk.bookmybook.data.Utils;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignInFragment extends Fragment implements View.OnClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @Inject
    Application application;
    @Inject
    Utils utils;
    private TextView emailIdTextView,passwordTextView;
    private CardView emailIdCardView,passwordCardView;
    private LinearLayout loginDetailsLayout;
    private Button sign_in_button;

    public SignInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignInFragment newInstance(String param1, String param2) {
        SignInFragment fragment = new SignInFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        BookMyBookApplication.getDiComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
    }

    private void initUI(View view) {
        emailIdTextView = (TextView) view.findViewById(R.id.emaildIdTextView);
        passwordTextView = (TextView) view.findViewById(R.id.passwordTextView);
        emailIdCardView = (CardView) view.findViewById(R.id.emailIdCardView);
        passwordCardView = (CardView) view.findViewById(R.id.passCardView);
        loginDetailsLayout = (LinearLayout) view.findViewById(R.id.loginDetailsLayout);
        sign_in_button = (Button) view.findViewById(R.id.sign_in_button);
        sign_in_button.setOnClickListener(this);
        emailIdTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    expandViewAnimation(emailIdCardView);
                }else{
                    contractViewAnimation(emailIdCardView);
                }
            }
        });
        passwordTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    expandViewAnimation(passwordCardView);
                }else{
                    contractViewAnimation(passwordCardView);
                }
            }
        });
    }

    private void expandViewAnimation(CardView cardView) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(cardView.SCALE_X,1.1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(cardView.SCALE_Y,1.1f);
        ObjectAnimator expandAnim = ObjectAnimator.ofPropertyValuesHolder(cardView,pvhX,pvhY);
        //PropertyValuesHolder pvhME = PropertyValuesHolder.ofFloat("maxCardElevation",getPixelsFromDPs(6));
        cardView.setCardElevation(utils.getPixelsFromDPs(6));
        expandAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        expandAnim.setDuration(225);
        expandAnim.start();
    }
    private void contractViewAnimation(CardView cardView) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(cardView.SCALE_X,1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(cardView.SCALE_Y,1f);
        ObjectAnimator contractAnim = ObjectAnimator.ofPropertyValuesHolder(cardView,pvhX,pvhY);
        //PropertyValuesHolder pvhME = PropertyValuesHolder.ofFloat("maxCardElevation",getPixelsFromDPs(6));
        cardView.setCardElevation(utils.getPixelsFromDPs(4));
        contractAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        contractAnim.setDuration(225);
        contractAnim.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_in_button :
                Intent intent = new Intent(getActivity(), VerifyPhoneActivity.class);
                startActivity(intent);
                break;
        }
    }

}
