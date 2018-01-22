package io.github.zhaomenghuan.nativeview;

import com.eclipsesource.v8.V8;

import io.github.zhaomenghuan.nativeview.console.Console;

public class NVSDK {
    private static V8 runtime;

    public static V8 getJSRuntime() {
        return runtime;
    }

    public static V8 init() {
        // 创建JS上下文
        runtime = V8.createV8Runtime();

        // 注册console对象
        registerConsole(runtime);

        return runtime;
    }

    private static void registerConsole(V8 runtime) {
        Console console = new Console(runtime);
        runtime.add("console", console.getV8Object());
        console.clean();
    }
}
