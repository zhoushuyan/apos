package com.wbasic.apos.appbridge.jsbridge;

import android.net.Uri;
import android.text.TextUtils;

import java.lang.reflect.Method;

/**
 * Created by pengwei on 16/5/11.
 */
class JsMethod {
    private boolean needCallback;
    private Method javaMethod;
    private String moduleName;
    private String methodName;

    private String params;

    private int parameterType = -1;

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    private JsMethod() {
    }

    public boolean needCallback() {
        return needCallback;
    }

    public void setNeedCallback(boolean needCallback) {
        this.needCallback = needCallback;
    }

    public Method getJavaMethod() {
        return javaMethod;
    }

    public void setJavaMethod(Method javaMethod) {
        this.javaMethod = javaMethod;
        if (javaMethod != null) {
            methodName = javaMethod.getName();
        }
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * 执行方法名
     * @return
     */
    public String getFunction() {
        return String.format("%s.%s.%s", JsBridgeConfigImpl.getInstance().getProtocol(),
                getModuleName(), getMethodName());
    }

    /**
     * module + method
     * @return
     */
    public String getModuleAndMethod() {
        return getModuleName() + "." + getMethodName();
    }

    /**
     * 回调函数名称
     * @return
     */
    public String getCallbackFunction() {
        if (needCallback()) {
            return getFunction() + "Callback";
        }
        return null;
    }

    /**
     * 注入的JS代码
     *
     * @return
     */
    public String getInjectJs() {
        StringBuilder builder = new StringBuilder(getMethodName() + ":function(option){");
        builder.append("try{");
        builder.append("if(option === undefined){return prompt('" + getUrl() + "');};");
        builder.append("var port = Math.floor(Math.random() * (1 << 30));");
        if (needCallback()) {
            builder.append("if(option.onListener && typeof(option.onListener) === 'function'){");
            builder.append("if(!(" + getCallbackFunction() + " instanceof Array)){" + getCallbackFunction() + "=[];};");
            builder.append(getCallbackFunction().trim() + "[port] = option.onListener;");
            builder.append("}");
        }
        builder.append("var params;");
        builder.append("if(option.onSuccess ===  undefined && option.onFailure === undefined && option.onListener === undefined && option.data === undefined){");
        builder.append("params = option;}else{params = option.data;}");
        // 将js对象转化为string
        builder.append("if(typeof params === 'object'){params = JSON.stringify(params);} ");
        builder.append("var requestUri = '" + getUrl() + "'.replace(/:0/, ':'+port);");
        builder.append("var result = prompt(requestUri + (encodeURIComponent(params ||'')));");
        builder.append("if(result === undefined || result === null || result === '') return;");
        builder.append("var data = eval('(' + result + ')');");
        builder.append("if(data && data.onSuccess && option.onSuccess){option.onSuccess(data.data);}");
        builder.append("else if(data && data.onFailure && option.onFailure){option.onFailure(data.data);}");
        builder.append("}catch(e){console.error(e);};");
        builder.append("},");
        return builder.toString();
    }

    /**
     * 获取协议的url
     *
     * @return
     */
    public String getUrl() {
        return getUrl(0);
    }

    public String getUrl(int port) {
        return String.format("%s://%s:%d/%s?", JsBridgeConfigImpl.getInstance().getProtocol(),
                getModuleName(), port, getMethodName());
    }

    public Object invoke(Object... args) throws Exception {
        if (javaMethod != null) {
            return javaMethod.invoke(null, args);
        }
        return null;
    }

    public int getParameterType() {
        return parameterType;
    }

    public void setParameterType(int parameterType) {
        this.parameterType = parameterType;
    }

    public static JsMethod create(boolean needCallback, String moduleName, Method method, int parameterType) {
        JsMethod jsMethod = new JsMethod();
        jsMethod.setNeedCallback(needCallback);
        jsMethod.setJavaMethod(method);
        jsMethod.setModuleName(moduleName);
        jsMethod.setParameterType(parameterType);
        return jsMethod;
    }

    /**
     * 获取JsMethod
     *
     * @param url
     * @return
     */
    public static JsMethod get(String url) {
        Uri uri = Uri.parse(url);
        if (uri != null) {
            String scheme = uri.getScheme();
            String host = uri.getHost();
            String path = uri.getPath();
            if (!TextUtils.isEmpty(scheme) && scheme.equals(JsBridgeConfigImpl.getInstance().getProtocol())
                    && !TextUtils.isEmpty(host) && !TextUtils.isEmpty(path)) {
                JsMethod method = new JsMethod();
                method.setMethodName(path.replace("/", ""));
                method.setModuleName(host);
                method.setParams(uri.getQuery());
                return method;
            }
        }
        return null;
    }
}
