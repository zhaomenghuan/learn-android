package io.github.zhaomenghuan.nativeview.widgets;

import android.content.Context;

import com.eclipsesource.v8.JavaVoidCallback;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

/**
 * Created by zhaomenghuan on 2017/9/10.
 */

public class Button {
    private Context mContext;
    private V8 runtime;
    private V8Object button;
    private V8Object prototype;

    public Button(Context mContext, V8 v8Runtime) {
        this.mContext = mContext;
        this.runtime = v8Runtime;
        initV8Object();
    }

    public V8Object getObject() {
        return button;
    }

    protected void initV8Object() {
        runtime.registerJavaMethod(new JavaVoidCallback() {
            public void invoke(final V8Object receiver, final V8Array parameters) {
                android.widget.Button button = new android.widget.Button(mContext);
            }
        }, "init");
        runtime.executeVoidScript("init();");
    }

    public void setText(final String message) {
        System.out.println("[INFO] " + message);
    }

    public void clean() {
        button.release();
        // prototype.release();
    }

//    private void init() {
//        android.widget.Button button = new android.widget.Button();
//        button = runtime.getObject("Button");
//        V8Object prototype = runtime.executeObjectScript("Button.prototype");
//        prototype.registerJavaMethod(this, "setText", "setText",
//                new Class<?>[] { V8Object.class, Object[].class }, true);
//        button.setPrototype(prototype);
//        button.release();
//        prototype.release();
//    }
//
//    public void setText(final V8Object button, String... s) {
//        runtime.registerJavaMethod(new JavaVoidCallback() {
//            public void invoke(final V8Object receiver, final V8Array parameters) {
//
//            }
//        }, "setText");
//    }
}
