package io.github.zhaomenghuan.nativeview.widgets;

import android.view.ViewGroup;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Object;

import io.github.zhaomenghuan.nativeview.core.NativeObject;

public class Window extends NativeObject {
    private V8 v8Runtime;
    private V8Object mObject;
    private ViewGroup viewGroup;

    public V8Object getV8Object() {
        return mObject;
    }

    public Window(V8 v8Runtime, ViewGroup viewGroup) {
        super(v8Runtime);
        this.viewGroup = viewGroup;
    }

    @Override
    protected void initV8Object() {
        mObject = new V8Object(v8Runtime);
    }

    @Override
    public void clean() {
        getObject().release();
    }
}
