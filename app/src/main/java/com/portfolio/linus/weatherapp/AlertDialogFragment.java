package com.portfolio.linus.weatherapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Johan on 2018-09-27.
 */

public class AlertDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.title_msg)
        .setMessage(R.string.error_msg)
        .setPositiveButton(R.string.confirm_btn,null);

        return  builder.create();
    }

}
