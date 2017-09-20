package io.github.zhaomenghuan.nativeview.core;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.io.Serializable;

public class NativeView extends View implements Serializable {
    Context mContext;

    public NativeView(Context context) {
        super(context);
    }

    public NativeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
