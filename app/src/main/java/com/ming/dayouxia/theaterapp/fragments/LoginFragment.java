package com.ming.dayouxia.theaterapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ming.dayouxia.theaterapp.R;
import com.ming.dayouxia.theaterapp.SignupFragment;
import com.ming.dayouxia.theaterapp.TheaterWelcomeActivity;


public class LoginFragment extends Fragment implements View.OnClickListener{

    private EditText mPasswordField;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        mPasswordField = (EditText)rootView.findViewById(R.id.password_login_text);
        mPasswordField.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        Button btnLogin=(Button)rootView.findViewById(R.id.login_button);
        btnLogin.setOnClickListener(this);
        Button btnNewUser=(Button)rootView.findViewById(R.id.signup_button);
        btnNewUser.setOnClickListener(this);
        Button btnPassReset=(Button)rootView.findViewById(R.id.password_reset);
        btnPassReset.setOnClickListener(this);
        return rootView;
    }

    private class AsteriskPasswordTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return new PasswordCharSequence(source);
        }
    }

    private class PasswordCharSequence implements CharSequence {
        private CharSequence mSource;
        public PasswordCharSequence(CharSequence source) {
            mSource = source; // Store char sequence
        }
        public char charAt(int index) {
            return '*'; // This is the important part
        }
        public int length() {
            return mSource.length(); // Return default
        }
        public CharSequence subSequence(int start, int end) {
            return mSource.subSequence(start, end); // Return default
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                startActivity(new Intent(getActivity(), TheaterWelcomeActivity.class));
                break;
            case R.id.signup_button:
                Log.d("Login Page", "Signup fragment");
               // new SignupFragment();
                //startActivity(new Intent((getActivity(),SignupFragment())));
                break;
            case R.id.password_reset:
                //startActivity(new Intent(this, PasswordResetActivity.class));
                break;

        }
    }
}
