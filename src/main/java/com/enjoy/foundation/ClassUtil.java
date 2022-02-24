package com.enjoy.foundation;

//import com.sun.istack.internal.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Map;

public abstract class ClassUtil {

    private static final StringBuilder stringBuilder = new StringBuilder();

    /**
     * Class对象所表示的类或接口与指定的class参数所示的类或接口是否相同，或是否是它的超类或超接口。
     *
     * @param clazz
     * @param cls
     */
    public static void isAssignableFrom(Class<?> clazz, Class<?> cls) {
        stringBuilder.append("isAssignableFrom(clazz): " + clazz.isAssignableFrom(cls)).append("\r\n");
        System.out.println(stringBuilder.toString());
        stringBuilder.setLength(0);
    }

    /**
     * 指定object是否是此Class表示对象兼容
     *
     * @param clazz
     * @param obj
     */
    public static void isInstance(Class<?> clazz, Object obj) {
        stringBuilder.append("isInstance(object)): " + clazz.isInstance(obj)).append("\r\n");
        System.out.println(stringBuilder.toString());
        stringBuilder.setLength(0);
    }

    public static void println(Class<?> clazz) {
        stringBuilder.append("----------------------------类-----------------------------------\r\n");
        //Class<?> clazz = Class.forName("com.enjoy.foundation.GenericSample$GenricSample3");
        // class
        // asSubclass尝试将clazz对象强制转换成指定Class对象。(判断class对象是否是指定Class子类)
        //stringBuilder.append("asSubclass(Base.class): " + clazz.asSubclass(Base.class)).append("\r\n");
        // 尝试将对象转换成class所表示的类对象
        //stringBuilder.append("cast(new GenricSample3()): " + clazz.cast(new GenricSample3())).append("\r\n");
        stringBuilder.append("desiredAssertionStatus(): " + clazz.desiredAssertionStatus()).append("\r\n");
        stringBuilder.append("getCanonicalName(): " + clazz.getCanonicalName()).append("\r\n");
        stringBuilder.append("getClasses().length: " + clazz.getClasses().length).append("\r\n");
        stringBuilder.append("getClassLoader(): " + clazz.getClassLoader()).append("\r\n");
        stringBuilder.append("getComponentType(): " + clazz.getComponentType()).append("\r\n");
        stringBuilder.append("getDeclaredClasses().length: " + clazz.getDeclaredClasses().length).append("\r\n");
        stringBuilder.append("getDeclaringClass(): " + clazz.getDeclaringClass()).append("\r\n");
        stringBuilder.append("getEnclosingClass(): " + clazz.getEnclosingClass()).append("\r\n");
        stringBuilder.append("getEnumConstants(): " + clazz.getEnumConstants()).append("\r\n");
        stringBuilder.append("getInterfaces().length: " + clazz.getInterfaces().length).append("\r\n");
        stringBuilder.append("getModifiers(): " + clazz.getModifiers()).append("\r\n");
        stringBuilder.append("getName(): " + clazz.getName()).append("\r\n");
        stringBuilder.append("getTypeName(): " + clazz.getTypeName()).append("\r\n");
        stringBuilder.append("getTypeParameters().length: " + clazz.getTypeParameters().length).append("\r\n");
        stringBuilder.append("getGenericSuperclass(): " + clazz.getGenericSuperclass()).append("\r\n");
        stringBuilder.append("getGenericInterfaces().length: " + clazz.getGenericInterfaces().length).append("\r\n");
        stringBuilder.append("getSimpleName(): " + clazz.getSimpleName()).append("\r\n");
        stringBuilder.append("getPackage(): " + clazz.getPackage()).append("\r\n");
        stringBuilder.append("getProtectionDomain(): " + clazz.getProtectionDomain()).append("\r\n");
        stringBuilder.append("getResource(\"\"): " + clazz.getResource("")).append("\r\n");
        stringBuilder.append("getResourceAsStream(\"\"): " + clazz.getResourceAsStream("")).append("\r\n");
        stringBuilder.append("getSigners(): " + clazz.getSigners()).append("\r\n");
        stringBuilder.append("getSuperclass(): " + clazz.getSuperclass()).append("\r\n");
        stringBuilder.append("getTypeParameters().length: " + clazz.getTypeParameters().length).append("\r\n");
        stringBuilder.append("isAnonymousClass(): " + clazz.isAnonymousClass()).append("\r\n");
        stringBuilder.append("isArray(): " + clazz.isArray()).append("\r\n");
        // Class对象所表示的类或接口与指定的class参数所示的类或接口是否相同，或是否是它的超类或超接口。
        //stringBuilder.append("isAssignableFrom(Base.class): " + clazz.isAssignableFrom(Base.class)).append("\r\n");
        stringBuilder.append("isEnum(): " + clazz.isEnum()).append("\r\n");
        //指定object是否是此Class表示对象兼容
        //stringBuilder.append("isInstance(Base.class)): " + clazz.isInstance(Base.class)).append("\r\n");
        stringBuilder.append("isInterface(): " + clazz.isInterface()).append("\r\n");
        stringBuilder.append("isLocalClass(): " + clazz.isLocalClass()).append("\r\n");
        stringBuilder.append("isMemberClass(): " + clazz.isMemberClass()).append("\r\n");
        stringBuilder.append("isPrimitive(): " + clazz.isPrimitive()).append("\r\n");
        stringBuilder.append("isSynthetic(): " + clazz.isSynthetic()).append("\r\n");

        try {
            stringBuilder.append("----------------------------构造-----------------------------------\r\n");
            // constructor
            stringBuilder.append("getConstructor(): " + clazz.getConstructor()).append("\r\n");
            stringBuilder.append("getConstructor(Map.class): " + clazz.getConstructor(Map.class)).append("\r\n");
            stringBuilder.append("getConstructors().length: " + clazz.getConstructors().length).append("\r\n");
            stringBuilder.append("getDeclaredConstructor(Map.class): " + clazz.getDeclaredConstructor(Map.class)).append("\r\n");
            stringBuilder.append("getDeclaredConstructors().length: " + clazz.getDeclaredConstructors().length).append("\r\n");
            stringBuilder.append("getEnclosingConstructor(): " + clazz.getEnclosingConstructor()).append("\r\n");
            stringBuilder.append("getGenericInterfaces().length: " + clazz.getGenericInterfaces().length).append("\r\n");
            stringBuilder.append("getGenericSuperclass(): " + clazz.getGenericSuperclass()).append("\r\n");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        stringBuilder.append("----------------------------注解-----------------------------------\r\n");
        //annotation
//        stringBuilder.append("getAnnotation(Nullable.class): " + clazz.getAnnotation(Nullable.class)).append("\r\n");
        stringBuilder.append("getAnnotations().length: " + clazz.getAnnotations().length).append("\r\n");
        stringBuilder.append("getDeclaredAnnotations().length: " + clazz.getDeclaredAnnotations().length).append("\r\n");
        stringBuilder.append("getAnnotatedInterfaces().length: " + clazz.getAnnotatedInterfaces().length).append("\r\n");
        stringBuilder.append("getAnnotatedSuperclass(): " + clazz.getAnnotatedSuperclass()).append("\r\n");
//        stringBuilder.append("getDeclaredAnnotation(Nullable.class): " + clazz.getDeclaredAnnotation(Nullable.class)).append("\r\n");
        stringBuilder.append("isAnnotation(): " + clazz.isAnnotation()).append("\r\n");
//        stringBuilder.append("isAnnotationPresent(Nullable.class): " + clazz.isAnnotationPresent(Nullable.class)).append("\r\n");

        try {
            stringBuilder.append("----------------------------字段-----------------------------------\r\n");
            // field
            stringBuilder.append("getDeclaredField(\"map\"): " + clazz.getDeclaredField("map")).append("\r\n");
            stringBuilder.append("getDeclaredFields().length: " + clazz.getDeclaredFields().length).append("\r\n");
            stringBuilder.append("getFields().length: " + clazz.getFields().length).append("\r\n");
            // 获取公共字段（私有字段无法获取），基类公共字段
            stringBuilder.append("getField(\"test\"): " + clazz.getField("test")).append("\r\n");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        try {
            stringBuilder.append("----------------------------方法-----------------------------------\r\n");
            // method
            stringBuilder.append("getDeclaredMethod(\"getMap\"): " + clazz.getDeclaredMethod("getMap")).append("\r\n");
            stringBuilder.append("getDeclaredMethods().length: " + Arrays.asList(clazz.getDeclaredMethods())).append("\r\n");
            stringBuilder.append("getEnclosingMethod(): " + clazz.getEnclosingMethod()).append("\r\n");
            stringBuilder.append("getMethod(\"getMap\",null): " + clazz.getMethod("getMap", null)).append("\r\n");
            stringBuilder.append("getMethods().length: " + clazz.getMethods().length).append("\r\n");
            System.out.println(stringBuilder.toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            stringBuilder.setLength(0);
        }
    }

    /**
     * getFields()和getDeclaredFields()
     *
     * @param clazz
     */
    public static void printlnFields(Class<?> clazz) {
        try {
            // 返回类所有公共字段（包含inherited fields）
            for (int i = 0; i < clazz.getFields().length; i++) {
                printlnFieldWithInstance(clazz.getFields()[i], null);
            }
            // 只返回此类定义的所有字段（包含private,default,protected,public）
            for (int i = 0; i < clazz.getDeclaredFields().length; i++) {
                printlnFieldWithInstance(clazz.getDeclaredFields()[i], null);
            }
        } finally {
            stringBuilder.setLength(0);
        }
    }

    /**
     * 获取字段属性
     *
     * @param field
     * @param obj
     */
    public static void printlnFieldWithInstance(Field field, Object obj) {
        try {
            System.out.println("-------------字段" + field.getName() + "属性---------------");
            stringBuilder.append("field.getDeclaredAnnotations(): " + field.getDeclaredAnnotations()).append("\r\n");
            stringBuilder.append("field.getDeclaringClass(): " + field.getDeclaringClass()).append("\r\n");
            stringBuilder.append("field.getType(): " + field.getType()).append("\r\n");
            stringBuilder.append("field.getGenericType(): " + field.getGenericType()).append("\r\n");
            stringBuilder.append("field.getModifiers(): " + field.getModifiers()).append("\r\n");
            stringBuilder.append("field.getName(): " + field.getName()).append("\r\n");
            stringBuilder.append("field.isEnumConstant(): " + field.isEnumConstant()).append("\r\n");
            stringBuilder.append("field.isSynthetic(): " + field.isSynthetic()).append("\r\n");
            stringBuilder.append("field.toGenericString(): " + field.toGenericString()).append("\r\n");
            stringBuilder.append("field.toString(): " + field.toString()).append("\r\n");

            System.out.println(stringBuilder.toString());
        } finally {
            stringBuilder.setLength(0);
        }
    }

    /**
     * 获取字段对象值
     *
     * @param field
     * @param obj
     */
    public static void getFieldValueFromObject(Field field, Object obj) {
        try {
            if (obj != null) {
                System.out.println("-------------字段" + field.getName() + "指定对象数据---------------");
                stringBuilder.append("field.get(obj): " + field.get(obj)).append("\r\n");
//                stringBuilder.append("field.getAnnotation(Nullable.class): " + field.getAnnotation(Nullable.class)).append("\r\n");
                stringBuilder.append("field.getBoolean(obj): " + field.getBoolean(obj)).append("\r\n");
                stringBuilder.append("field.getByte(obj): " + field.getByte(obj)).append("\r\n");
                stringBuilder.append("field.getChar(obj): " + field.getChar(obj)).append("\r\n");
                stringBuilder.append("field.getDouble(obj): " + field.getDouble(obj)).append("\r\n");
                stringBuilder.append("field.getFloat(obj): " + field.getFloat(obj)).append("\r\n");
                stringBuilder.append("field.getInt(obj): " + field.getInt(obj)).append("\r\n");
                stringBuilder.append("field.getLong(obj): " + field.getLong(obj)).append("\r\n");
                stringBuilder.append("field.getShort(obj): " + field.getShort(obj)).append("\r\n");
                System.out.println(stringBuilder.toString());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            stringBuilder.setLength(0);
        }
    }


    /**
     * 打印Method相关数据
     * @param method
     */
    public static void printMethodInfo(Method method) {
        try {
            System.out.println("-------------方法" + method.getName() + "---------------");
//            stringBuilder.append("getAnnotation(Nullable.class): " + method.getAnnotation(Nullable.class)).append("\r\n");
            stringBuilder.append("getDeclaredAnnotations().length: " + method.getDeclaredAnnotations().length).append("\r\n");
            stringBuilder.append("getDeclaringClass(): " + method.getDeclaringClass()).append("\r\n");
            stringBuilder.append("getDefaultValue(): " + method.getDefaultValue()).append("\r\n");
            stringBuilder.append("getExceptionTypes().length: " + method.getExceptionTypes().length).append("\r\n");
            stringBuilder.append("getGenericExceptionTypes().length: " + method.getGenericExceptionTypes().length).append("\r\n");
            stringBuilder.append("getGenericParameterTypes().length: " + method.getGenericParameterTypes().length).append("\r\n");
            stringBuilder.append("getGenericReturnType(): " + method.getGenericReturnType()).append("\r\n");
            stringBuilder.append("getModifiers(): " + method.getModifiers()).append("\r\n");
            stringBuilder.append("getName(): " + method.getName()).append("\r\n");
            stringBuilder.append("getParameterAnnotations().length: " + method.getParameterAnnotations().length).append("\r\n");
            stringBuilder.append("getParameterTypes().length: " + method.getParameterTypes().length).append("\r\n");
            stringBuilder.append("getParameters().length: " +method.getParameters().length).append("\r\n");

            stringBuilder.append("getReturnType(): " + method.getReturnType()).append("\r\n");
            stringBuilder.append("getTypeParameters(): " + method.getTypeParameters()).append("\r\n");
            stringBuilder.append("isBridge(): " + method.isBridge()).append("\r\n");
            stringBuilder.append("isSynthetic(): " + method.isSynthetic()).append("\r\n");
            stringBuilder.append("isVarArgs(): " + method.isVarArgs()).append("\r\n");
            stringBuilder.append("toGenericString(): " + method.toGenericString()).append("\r\n");
            stringBuilder.append("toString(): " + method.toString()).append("\r\n");


            for (TypeVariable<Method> typeParameter : method.getTypeParameters()) {
                 typeParameter.getName();
                 typeParameter.getGenericDeclaration();
            }



//            stringBuilder.append("invoke(): " + method.invoke()).append("\r\n");
            System.out.println(stringBuilder);

        } finally {
            stringBuilder.setLength(0);
        }
    }


}
