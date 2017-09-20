package io.github.zhaomenghuan.learnj2v8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eclipsesource.v8.V8;

import io.github.zhaomenghuan.nativeview.NVSDK;
import io.github.zhaomenghuan.utils.FileUtil;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "J2V8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        V8 runtime = NVSDK.init();
        String script = FileUtil.getFromAssets(this, "www/index.js");
        runtime.executeScript(script);



//        Button button = new Button(getApplicationContext(), runtime);
//        runtime.add("button", button.getObject());
//        runtime.registerJavaMethod(button, "setText", "setText", new Class<?>[] { String.class });

//        V8Object button = runtime.getObject("Button");
//        V8Object prototype = runtime.executeObjectScript("Button.prototype");
//        prototype.registerJavaMethod(this, "setText", "setText",
//                new Class<?>[] { V8Object.class, Object[].class }, true);
//        button.setPrototype(prototype);

//        runtime.registerJavaMethod(new JavaVoidCallback() {
//            public void invoke(final V8Object receiver, final V8Array parameters) {
//                Button button = new Button(getApplicationContext());
//                button.setText("Hello World");
//                MainActivity.this.addContentView(button, new RelativeLayout.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT)
//                );
//            }
//        }, "Button");



//        V8Object person = runtime.getObject("person");

//        runtime.executeVoidScript();
//        V8Object person = runtime.getObject("person");
//        Log.i(TAG, person.getString("name"));
//        person.release();

        runtime.release();
    }
}
