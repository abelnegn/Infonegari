package com.infonegari.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class SafeUIBlockingUtility {

    public static String utilityTitle = "Updating";
    public static String utilityMessage = "Infonegari is Updating your data...";

    private ProgressDialog progressDialog;

    private Context context;

    public SafeUIBlockingUtility(Context context){
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(utilityMessage);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle(utilityTitle);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    public void safelyBlockUI(){
        progressDialog.show();
    }

    public void safelyUnBlockUI(){
        progressDialog.dismiss();
    }

    public static String getUtilityTitle() {
        return utilityTitle;
    }

    public static void setUtilityTitle(String utilityTitle) {
        SafeUIBlockingUtility.utilityTitle = utilityTitle;
    }

    public static String getUtilityMessage() {
        return utilityMessage;
    }

    public static void setUtilityMessage(String utilityMessage) {
        SafeUIBlockingUtility.utilityMessage = utilityMessage;
    }

    public void safelyUnblockUIForFailure(String tag, String message){

        progressDialog.dismiss();
        Toast.makeText(context, "Some Problem Executing Request", Toast.LENGTH_SHORT).show();
        Log.i(tag,message);

    }

}
