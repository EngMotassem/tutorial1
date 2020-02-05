package com.example.tutorial1.helper;

import android.content.Context;

import com.example.tutorial1.login;
import com.kaopiz.kprogresshud.KProgressHUD;

public class LoadingHelper {

    private String title,details;
    Context context;
    KProgressHUD hud;


    public LoadingHelper() {
    }

    public void showDialg(Context context, String label, String details){
        hud = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(label)
                .setDetailsLabel(details)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }


    public void dismiss(){

        hud.dismiss();
    }
}
