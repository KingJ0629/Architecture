package com.dh.core.eventbus;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Jin on 2021/3/15.
 * Description
 */
class BusMutableLiveData<T> extends MutableLiveData<T> {

    private final boolean sticky;

    public BusMutableLiveData(boolean sticky) {
        this.sticky = sticky;
    }

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner, observer);
        if (!sticky) hook(observer);
    }

    /**
     * 修改{@link ObserverWrapper#mLastVersion }值，
     * 使其满足{@link LiveData#considerNotify(ObserverWrapper)}方法中的 if (observer.mLastVersion >= mVersion) 判断
     */
    private void hook(Observer<? super T> observer) {
        try {
            // 拿到mObservers变量
            Class<?> liveDataClass = LiveData.class;
            Field mObserversField = liveDataClass.getDeclaredField("mObservers");
            mObserversField.setAccessible(true);
            Object mObservers = mObserversField.get(this);

            //从mObservers中拿到Entry<K, V>
            Class<?> SafeIterableMapClass = mObservers.getClass();
            Method getMethod = SafeIterableMapClass.getDeclaredMethod("get", Object.class);
            getMethod.setAccessible(true);
            Object entryField = getMethod.invoke(mObservers, observer);

            // 拿到LifecycleBoundObserver object
            Object lifecycleBoundObserverObject;
            if (entryField instanceof Map.Entry) {
                lifecycleBoundObserverObject = ((Map.Entry<?, ?>)entryField).getValue();
            } else {
                throw new RuntimeException("LifecycleBoundObserverObject can not be null");
            }

            // 拿到LiveData最新的mVersion值
            Field mVersionField = liveDataClass.getDeclaredField("mVersion");
            mVersionField.setAccessible(true);
            Object mVersion = mVersionField.get(this);

            Log.i("247144", "mVersion is " + mVersion);

            // 设置mLastVersion值为LiveData最新的mVersion值
            Class<?> observerWrapperClass = lifecycleBoundObserverObject.getClass().getSuperclass();
            assert observerWrapperClass != null;
            Field mLastVersionField = observerWrapperClass.getDeclaredField("mLastVersion");
            mLastVersionField.setAccessible(true);

            Log.i("247144", "mLastVersionField is " + mLastVersionField.get(lifecycleBoundObserverObject));
            mLastVersionField.set(lifecycleBoundObserverObject, mVersion);
            Log.i("247144", "mLastVersionField change to " + mLastVersionField.get(lifecycleBoundObserverObject));
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
