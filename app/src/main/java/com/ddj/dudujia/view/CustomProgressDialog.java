package com.ddj.dudujia.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ddj.dudujia.R;

/**
 * Created by hanshaobo on 05/06/2017.
 */

public class CustomProgressDialog {

    public static Dialog createLoadingDialog(Context context, String msg) {
        View v = LayoutInflater.from(context).inflate(R.layout.loading_dialog, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.llView);

        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);
        loadingDialog.setCancelable(false);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return loadingDialog;
    }
}
