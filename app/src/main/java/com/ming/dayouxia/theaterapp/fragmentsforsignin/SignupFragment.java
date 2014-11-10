package com.ming.dayouxia.theaterapp.fragmentsforsignin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.DialogFragment;

import com.ming.dayouxia.theaterapp.R;


public class SignupFragment extends DialogFragment {

    public SignupFragment(){
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_signup, null);

        return builder.setView(v)
                .create();
    }


}