package com.dh.architecture;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.dh.core.eventbus.LiveDataBus;

/**
 * Created by Jin on 2021/3/15.
 * Description
 */
public class LiveDataBusSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LiveDataBus.get().getChannel("key_test", Boolean.class)
                .observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        Log.i("247144", "value is " + aBoolean);
                    }
                });

        LiveDataBus.getSticky().getChannel("key_sticky", String.class)
                .observe(this, s -> Log.i("247144", "sticky is " + s));
    }
}
