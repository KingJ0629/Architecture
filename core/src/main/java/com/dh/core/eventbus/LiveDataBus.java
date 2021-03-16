package com.dh.core.eventbus;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jin on 2021/3/15.
 * Description 消息总线
 */
public final class LiveDataBus {

    private final Map<String, BusMutableLiveData<Object>> bus;

    private static boolean sticky = false; // 是否是粘性消息

    private LiveDataBus() {
        bus = new HashMap<>();
    }

    private static class SingletonHolder {
        private static final LiveDataBus DATA_BUS = new LiveDataBus();
    }

    public static LiveDataBus get() {
        return SingletonHolder.DATA_BUS;
    }

    /**
     * 粘性消息
     */
    public static LiveDataBus getSticky() {
        sticky = true;
        return SingletonHolder.DATA_BUS;
    }

    public <T> MutableLiveData<T> getChannel(String target, Class<T> type) {
        if (!bus.containsKey(target)) {
            bus.put(target, new BusMutableLiveData<>(sticky));
        }
        return (MutableLiveData<T>) bus.get(target);
    }

    public MutableLiveData<Object> getChannel(String target) {
        return getChannel(target, Object.class);
    }
}
