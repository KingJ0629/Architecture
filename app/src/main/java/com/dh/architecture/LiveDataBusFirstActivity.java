package com.dh.architecture;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dh.core.eventbus.LiveDataBus;

/**
 * Created by Jin on 2021/3/15.
 * Description
 */
public class LiveDataBusFirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_test_activity);

        findViewById(R.id.main_layout).setOnClickListener(view -> {
            startActivity(new Intent(LiveDataBusFirstActivity.this, LiveDataBusSecondActivity.class));
        });

        LiveDataBus.get().getChannel("key_test", Boolean.class).postValue(false);
        LiveDataBus.getSticky().getChannel("key_sticky", String.class).postValue("sticky");
    }
}
