package io.github.zhaomenghuan.learnj2v8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.eclipsesource.v8.V8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.github.zhaomenghuan.nativeview.NVSDK;
import io.github.zhaomenghuan.utils.FileUtil;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "J2V8";
    V8 runtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        runtime = NVSDK.init();

        String script = FileUtil.getFromAssets(this, "www/index.js");
        runtime.executeScript(script);

        TextView  tv = new TextView(this);
        tv.setText("hello j2v8");
        setContentView(tv);

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
    }

    public void finish()
    {
        super.finish();
        runtime.release();
    }


    public boolean require(String file) {
        try {
            StringBuilder buf=new StringBuilder();
            InputStream is = this.getAssets().open(file);
            BufferedReader in= new BufferedReader(new InputStreamReader(is));
            String str;
            while ((str=in.readLine()) != null) {
                buf.append(str+"\n");
            }
            in.close();
            runtime.executeScript("var module={}; module.exports = {}; var exports = module.exports;" + buf.toString());
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }
}
