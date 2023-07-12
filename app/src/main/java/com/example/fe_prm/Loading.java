package com.example.fe_prm;

import android.app.ProgressDialog;
import android.content.Context;

public class Loading {
    private static ProgressDialog progressDialog;
    public static void setLoading(Context context, boolean show){
        if (progressDialog == null){
            progressDialog =new ProgressDialog(context);
            progressDialog.setTitle("Loading");
            progressDialog.setMessage("Wait while loading...");
            progressDialog.setCancelable(false); // disable dismiss by tapping outside of the dialog
        }
        if (show)progressDialog.show();
        else progressDialog.dismiss();
    }
}
