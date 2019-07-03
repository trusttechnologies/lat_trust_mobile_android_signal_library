package com.trust.signal.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.trust.signal.PushService;

public class MainActivity extends AppCompatActivity implements MainContract.View{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startService(new Intent(this, PushService.class));
    }
}
