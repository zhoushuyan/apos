package com.wbasic.apos.appbridge.jsbridge;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;

/**
 * Created by pengwei on 16/5/13.
 */
final class Utils {

    /**
     * 获取类的静态公开方法
     *
     * @param injectedCls
     * @return
     * @throws Exception
     */
    public static HashMap<String, JsMethod> getAllMethod(String module, Class injectedCls) throws Exception {
        HashMap<String, JsMethod> mMethodsMap = new HashMap<>();
        Method[] methods = injectedCls.getDeclaredMethods();
        for (Method method : methods) {
            String name;
            if (method.getModifiers() != (Modifier.PUBLIC | Modifier.STATIC) || (name = method.getName()) == null) {
                continue;
            }
            Class[] parameters = method.getParameterTypes();
            int parameterType = ParameterType.getParameterType(parameters);
            switch (parameterType) {
                case ParameterType.TYPE_O:
                case ParameterType.TYPE_AO:
                case ParameterType.TYPE_WO:
                case ParameterType.TYPE_AWO:
                    mMethodsMap.put(name, JsMethod.create(false, module, method, parameterType));
                    break;
                case ParameterType.TYPE_AOJ:
                case ParameterType.TYPE_OJ:
                case ParameterType.TYPE_WOJ:
                case ParameterType.TYPE_AWOJ:
                    mMethodsMap.put(name, JsMethod.create(true, module, method, parameterType));
                    break;
                default:
                    break;
            }
        }
        return mMethodsMap;
    }

    /**
     * 是否相关
     *
     * @param cla1
     * @param cla2
     * @return
     */
    public static boolean classIsRelative(Class cla1, Class cla2) {
        if (cla1 != null && cla2 != null) {
            return cla2.isAssignableFrom(cla1) || cla2.equals(cla1);
        }
        return false;
    }
}
