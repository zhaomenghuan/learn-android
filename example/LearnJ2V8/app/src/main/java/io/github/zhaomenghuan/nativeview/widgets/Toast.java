package io.github.zhaomenghuan.nativeview.widgets;

import com.eclipsesource.v8.JavaVoidCallback;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

import io.github.zhaomenghuan.nativeview.core.NativeObject;
import io.github.zhaomenghuan.nativeview.util.Helper;

public class Toast extends NativeObject {
    private V8 v8Runtime;
    private V8Object mObject;

    public Toast(V8 v8Runtime) {
        super(v8Runtime);
    }

    public V8Object getV8Object() {
        return mObject;
    }

    public android.widget.Toast makeText(String text, String type) {
        int duration = type.equals("S") ? android.widget.Toast.LENGTH_SHORT : android.widget.Toast.LENGTH_LONG;
        return android.widget.Toast.makeText(Helper.getApplication(), text, duration);
    }

    @Override
    protected void initV8Object() {
        mObject = new V8Object(mRuntime);
        mObject.registerJavaMethod(this, "makeText", "makeText", new Class<?>[] { String.class });
        mObject.registerJavaMethod(this, "setText", "setText", new Class<?>[] { String.class });
    }

    @Override
    public void clean() {
        mObject.release();
    }
}
