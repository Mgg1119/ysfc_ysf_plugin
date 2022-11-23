package com.fiveelements.unpay.xp.utils;

import com.fiveelements.unpay.xp.utils.log.LogUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassLoaderUtils {

    public static String TAG = "ClassLoaderUtils : ";

    public static Class hookGetClass(String packageName, ClassLoader classLoader) {
        try {
            return classLoader.loadClass(packageName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object hookGetClass(Object paramObject, String paramString, Object[] paramArrayOfObject) {
        try {
            paramObject = hookGetMethod(paramObject.getClass(), paramString, paramArrayOfObject).invoke(paramObject, paramArrayOfObject);
            return paramObject;
        } catch (Exception exception) {
        }
        return null;
    }


    public static Method hookGetMethod(Class hookClass, String methodName, Object[] paramArrayOfObject) {
        Method localMethod = null;
        try {
            if (paramArrayOfObject == null) {
                localMethod = hookClass.getMethod(methodName, new Class[0]);
            } else {
                localMethod = hookClass.getDeclaredMethod(methodName, getClassArrays(paramArrayOfObject));
            }
        } catch (Exception localException) {
            try {
                if (paramArrayOfObject == null) {
                    localMethod = hookClass.getDeclaredMethod(methodName, new Class[0]);
                } else {
                    localMethod = hookClass.getDeclaredMethod(methodName, getClassArrays(paramArrayOfObject));
                }
                localMethod.setAccessible(true);
            } catch (Exception exception) {
            }
        }
        return localMethod;
    }


    public static Class[] getClassArrays(Object[] paramArrayOfObject) {
        try {
            Class[] arrayOfClass = new Class[paramArrayOfObject.length];
            for (int i = 0; i < paramArrayOfObject.length; i++) {
                if ((paramArrayOfObject[i] instanceof Integer)) {
                    arrayOfClass[i] = Integer.TYPE;
                } else if ((paramArrayOfObject[i] instanceof Boolean)) {
                    arrayOfClass[i] = Boolean.TYPE;
                } else if ((paramArrayOfObject[i] instanceof Long)) {
                    arrayOfClass[i] = Long.TYPE;
                } else {
                    arrayOfClass[i] = paramArrayOfObject[i].getClass();
                }
            }
            return arrayOfClass;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * 执行方法
     *
     * @return
     */
    public static Object invokeMethod(String packageName, String methodName, Object[] objects, ClassLoader classLoader) {
        try {
            return hookGetMethod(hookGetClass(packageName, classLoader)
                    , methodName, objects)
                    .invoke(null, objects);
        } catch (Exception exception) {
            LogUtils.d(TAG + exception.getMessage());
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * 获取实例
     */
    public static Object getConstructorInstance(String className, Class[] classes, Object[] objects, ClassLoader classLoader) {
        Constructor constructor;
        Object instance;
        try {
            constructor = getConstructor(classLoader.loadClass(className), classes);
            if (constructor == null) {
                return null;
            }
            instance = constructor.newInstance(objects);
            return instance;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * 获取构造函数
     */
    public static Constructor getConstructor(Class clazz, Class[] classes) {
        Constructor constructor;
        try {
            constructor = clazz.getConstructor(classes);
            return constructor;
        } catch (Exception localException) {
            try {
                constructor = clazz.getDeclaredConstructor(classes);
                constructor.setAccessible(true);
                return constructor;
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        return null;
    }

    public static void setFiled(Object paramObject1, String paramString, Object paramObject2) {
        try {
            getField(paramObject1, paramString).set(paramObject1, paramObject2);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static Field getField(Object paramObject, String paramString) {
        Class localClass = paramObject.getClass();
        try {
            paramObject = localClass.getField(paramString);
            return (Field) paramObject;
        } catch (Exception eee) {
            try {
                paramObject = localClass.getDeclaredField(paramString);
                ((Field) paramObject).setAccessible(true);
                return (Field) paramObject;
            } catch (Exception eeee) {
            }
        }
        for (; ; ) {
            try {
                localClass = localClass.getSuperclass();
                Field localField = localClass.getDeclaredField(paramString);
                localField.setAccessible(true);
                return localField;
            } catch (Exception exception) {
                return null;
            }
        }
    }

    public static Object getNetWorkField(String className, String fieldName, ClassLoader classLoader) {
        try {
            Object netWorkField;
            netWorkField = getField(classLoader.loadClass(className), fieldName).get(null);
            return netWorkField;
        } catch (Exception ee) {
        }
        return null;
    }

    public static Field getField(Class clazz, String fieldName) {
        Class localClass1;
        try {
            Field localField1 = clazz.getField(fieldName);
            return localField1;
        } catch (Exception localException1) {
            try {
                Field localField2 = clazz.getDeclaredField(fieldName);
                localField2.setAccessible(true);
                return localField2;
            } catch (Exception localException2) {
                localClass1 = clazz;
            }
        }
        for (; ; ) {
            try {
                localClass1 = localClass1.getSuperclass();
                Field localField3 = localClass1.getDeclaredField(fieldName);
                localField3.setAccessible(true);
                return localField3;
            } catch (Exception e) {
                return null;
            }
        }
    }

    /**
     * 执行 "com.unionpay.network.y", "a" 方法
     */
    public static Object invokeNetWorkYaMethod(String packageName, String methodName, Class[] classes, Object[] objects, ClassLoader classLoader) {
        try {
            return getNetWorkYaMethod(hookGetClass(packageName, classLoader), methodName, classes).invoke(null, objects);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * 获取 "com.unionpay.network.y", "a" 方法
     */
    private static Method getNetWorkYaMethod(Class clazz, String methodName, Class[] classes) {
        Method localMethod = null;
        try {
            localMethod = clazz.getMethod(methodName, classes);
        } catch (Exception localException) {
            try {
                localMethod = clazz.getDeclaredMethod(methodName, classes);
                localMethod.setAccessible(true);
            } catch (Exception er) {
                er.printStackTrace();
            }
        }
        return localMethod;
    }

    /**
     * invoke "j" 方法
     */
    public static Object invokeJ(Object paramObject, String methodName, Object[] objects) {
        try {
            return getMethod(paramObject.getClass(), methodName, objects).invoke(paramObject, objects);
        } catch (Exception er) {
            System.out.println("================ invokeJ : " + er.getMessage());
        }
        return null;
    }

    public static Method getMethod(Class clazz, String methodName, Object[] objects) {
        Method localMethod = null;
        try {
            if (objects == null) {
                localMethod = clazz.getMethod(methodName);
            } else {
                localMethod = clazz.getMethod(methodName, getClassArrays(objects));
            }
        } catch (Exception localException) {
            try {
                if (objects == null) {
                    localMethod = clazz.getDeclaredMethod(methodName);
                } else {
                    localMethod = clazz.getDeclaredMethod(methodName, getClassArrays(objects));
                }
                localMethod.setAccessible(true);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return localMethod;
    }

}
