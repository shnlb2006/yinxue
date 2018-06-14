package org.yinxueframework.util;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理生成Util
 *
 * @author zengjian
 * @create 2018-06-06 14:50
 * @since 1.0.0
 */
public abstract class ProxyUtil {

    public static Object buildProxy(final Object src, final NestInvoker invoker) {
        ProxyMode mode = determineProxyMode(src);
        if (mode == ProxyMode.JDK) {
            return doBuildProxyByJDK(src, invoker);
        } else if (mode == ProxyMode.CGLIB) {
            return doBuildProxyByCGLIB(src, invoker);
        } else {
            return EmptyUtil.getEmptyObject();
        }
    }

    public static Object doBuildProxyByJDK(final Object src, final NestInvoker invoker) {
        Class clazz = src.getClass();
        final NestInvoker nestInvoker = invoker != null ? invoker : new EmptyInvoker();
        Class[] interfaces = clazz.getInterfaces();

        Object target = Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                nestInvoker.preHandle();
                Object result = method.invoke(src, args);
                nestInvoker.handleResult(result);
                nestInvoker.postHandle();
                return result;
            }
        });
        return target;
    }

    public static Object doBuildProxyByCGLIB(final Object src, final NestInvoker invoker) {
        final NestInvoker nestInvoker = invoker != null ? invoker : new EmptyInvoker();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(src.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                nestInvoker.preHandle();
                Object result  = proxy.invokeSuper(obj, args);
                nestInvoker.handleResult(result);
                nestInvoker.postHandle();
                return result;
            }
        });
        return enhancer.create();
    }

    private static ProxyMode determineProxyMode(Object src) {
        Class[] classes = src.getClass().getInterfaces();
        if (ValidateUtil.isNotEmpty(classes)) {
            return ProxyMode.JDK;
        } else {
            return ProxyMode.CGLIB;
        }
    }

    interface NestInvoker {
        Object preHandle();

        Object handleResult(Object result);

        Object postHandle();
    }

    static class EmptyInvoker implements NestInvoker {

        @Override
        public Object preHandle() {
            return null;
        }

        @Override
        public Object handleResult(Object result) {
            return null;
        }

        @Override
        public Object postHandle() {
            return null;
        }
    }

    enum ProxyMode {
        JDK, CGLIB;
    }

}
