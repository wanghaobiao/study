package com.study.aspect;
import com.study.util.UIRuleUtil;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @description  本地调试切面,正式环境默认不开启
 * @date  20/04/20 14:05
 * @author  wanghb
 * @edit  
 */
@Component
@Aspect
public class DebugLogAop {
    /**
     * 是否开启本地调试
     */
    private final boolean isOpne = true;
    /**
     * 是否开启进入方法打印
     */
    private final boolean isOpneIn = true;
    /**
     * 是否开启返回值打印
     */
    private final boolean isOpneOut = false;
    /**
     * 是否开启控制层打印
     */
    private final boolean isOpneController = false;
    /**
     * 是否开启服务层打印
     */
    private final boolean isOpneServe = true;
    /**
     * 实体标识
     */
    private final String entityMark = "entity";

    /**
     * @description  用于打印入参
     * @param  joinPoint
     * @return  void
     * @date  20/04/25 11:29
     * @author  wanghb
     * @edit
     */
    //execution表达式  可自行定义
    @Before("execution(* com..*(..)) ")
    public void advice(JoinPoint joinPoint) {
        if(isOpne  && isOpneIn && isPrint(joinPoint)){
            StackTraceElement stackTraceElement = getStackTraceElement(joinPoint);
            //类名
            String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
            Object[] args = joinPoint.getArgs();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < args.length; i++) {
                sb.append("第" + (i+1) + "个参数为:");
                Object arg = args[i];
                if(arg instanceof HttpServletRequest){
                    HttpServletRequest temp = (HttpServletRequest) arg;
                    sb.append( toMap( temp ));
                }else if(!(arg instanceof Class) && UIRuleUtil.getString( arg ).indexOf( entityMark ) != -1){
                    sb.append( toMap( arg ));
                }else{
                    sb.append(arg);
                }
                sb.append("   ");
            }
            if((className.indexOf( "Controller" ) != -1 && isOpneController) || (className.indexOf( "Service" ) != -1 && isOpneServe)){
                System.err.println(stackTraceElement + sb.toString());
            }
        }
    }

    /**
     * @description  用于打印返回值
     * @param  joinPoint
     * @return
     * @date  20/04/25 11:29
     * @author  wanghb
     * @edit
     */
    //execution表达式  可自行定义
    @Around("execution(* com..*(..))  ")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = "";
        Object[] args = joinPoint.getArgs();
        if(isOpne && isOpneOut && isPrint(joinPoint)){
            StringBuffer sb = new StringBuffer("返回值为:");
            //类名
            String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
            StackTraceElement stackTraceElement = getStackTraceElement(joinPoint);
            result = joinPoint.proceed(args);
            if(result instanceof HttpServletRequest){
                HttpServletRequest temp = (HttpServletRequest) result;
                sb.append( toMap( temp ));
            }else if(UIRuleUtil.getString( result ).indexOf( entityMark ) != -1){
                sb.append( toMap( result ));
            }else{
                sb.append( ( UIRuleUtil.getString( result ).equals( "" ) ? "void" : UIRuleUtil.getString( result )));
            }
            if((className.indexOf( "Controller" ) != -1 && isOpneController) || (className.indexOf( "Service" ) != -1 && isOpneServe)){
                System.err.println(stackTraceElement + sb.toString());
            }
        }else{
            result = joinPoint.proceed(args);
        }
        return result;
    }

    /**
     * @description  判断方法是否包含不打印注解
     * @param  joinPoint
     * @return  boolean 判断结果
     * @date  20/04/26 11:11
     * @author  wanghb
     * @edit  .
     */
    public boolean isPrint(JoinPoint joinPoint){
        //方法名
        String methodName = joinPoint.getSignature().getName();
        Class<?> className = joinPoint.getTarget().getClass();
        Method[] methods = className.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if(method.getName().equals(  methodName)){
                return !method.isAnnotationPresent(NoPrintDebug.class);
            }
        }
        return false;
    }

    /**
     * @description  ProceedingJoinPoint 转 StackTraceElement
     * @param  joinPoint 待转类型
     * @return  stackTraceElement  转换结果
     * @date  20/04/24 19:09
     * @author  wanghb
     * @edit
     */
    public static StackTraceElement getStackTraceElement(JoinPoint joinPoint) {
        //完整类名
        String declaringClass = (joinPoint.getSignature().getDeclaringType() + "").replace( "class ","" );
        //类名
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        //方法名
        String methodName = joinPoint.getSignature().getName();
        //文件名
        String fileName = new StringBuilder(className).append( ".java" ).toString();

        ClassPool pool = ClassPool.getDefault();
        CtMethod methodX = null;
        try {
            CtClass cc = pool.get(declaringClass);
            methodX = cc.getDeclaredMethod(methodName);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        int xlineNumber = methodX.getMethodInfo().getLineNumber(0);
        return new StackTraceElement(className, methodName, fileName, xlineNumber - 1);
    }

    /**
     * @description  Object转Map
     * @param  bean  待转类型
     * @@return  map  Map数据
     * @date  20/05/20 17:53
     * @author  wanghb
     * @edit
     */
    public static Map<String, Object> toMap(Object bean){

        if(bean == null){
            return new HashMap<>();
        }
        Class type = bean.getClass();
        Map<String,Object> returnMap = new HashMap<>();
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(type);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean, new Object[0]);
                    if (result != null) {
                        returnMap.put(propertyName, result);
                    } else {
                        returnMap.put(propertyName, "");
                    }
                }
            }
        }catch (IntrospectionException e){
        }catch (InvocationTargetException e){
        }catch (IllegalAccessException e){}

        return  returnMap;
    }

    /**
     * @description  Object转字符串
     * @param  value  待转字段
     * @@return  String  String字符串
     * @date  20/05/20 17:52
     * @author  wanghb
     * @edit
     */
    public static String getString(Object value){
       return value == null ? "" : String.valueOf(value);
    }
}
