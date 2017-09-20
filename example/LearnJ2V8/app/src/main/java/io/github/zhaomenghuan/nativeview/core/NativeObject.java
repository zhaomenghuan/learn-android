package io.github.zhaomenghuan.nativeview.core;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Object;

public abstract class NativeObject {
    protected V8 mRuntime;
    protected V8Object mObject;

    public NativeObject(V8 v8Runtime) {
        mRuntime = v8Runtime;
        initV8Object();
    }

    public V8Object getObject() {
        return mObject;
    }

    protected abstract void initV8Object();

    public abstract void clean();
}
