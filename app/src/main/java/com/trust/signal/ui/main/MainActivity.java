package com.trust.signal.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.trust.signal.utils.PushService;

public class MainActivity extends AppCompatActivity {
    private static Activity activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        startService(new Intent(this, PushService.class));
    }

    public Activity getActivity() {
        return activity;
    }
}
