package com.trust.signaldemo.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.trust.signal.utils.SignalApp;
import com.trust.signaldemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Activity activity = this;
        Context context = this;

        SignalApp.init(activity, context);
    }
}
