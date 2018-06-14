package org.yinxueframework.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProxyUtilTest {

    interface Hello{
        void hello();
    }

    /**
     * 加 static 避免和这个类关联在一起
     * 成员内部类new 时会出现 java.collection.IllegalArgumentException: Superclass has no null constructors but no arguments were given的报错 <》
     */
    static class WorldImpl {

        public void world(){
            System.out.println("----------world no interface----");
        }
    }


    class HelloImpl implements Hello {
        @Override
        public void hello() {
            System.out.println("hello word");
        }
    }


    class Invoker implements ProxyUtil.NestInvoker {

        @Override
        public Object preHandle() {
            printCurrentTime();
            return null;
        }

        private void printCurrentTime() {
            System.out.println(System.currentTimeMillis());
        }

        @Override
        public Object handleResult(Object result) {
            printCurrentTime();
            return null;
        }

        @Override
        public Object postHandle() {
            printCurrentTime();
            return null;
        }

    }

    @Test
    public void buildProxy() {
        HelloImpl hello  = new HelloImpl();
        Hello hello1 = (Hello) ProxyUtil.buildProxy(hello, new Invoker());
        hello1.hello();
    }

    @Test
    public void buildProxy2() {
        WorldImpl WorldImpl  = new WorldImpl();
        WorldImpl WorldImpl2 = (WorldImpl) ProxyUtil.buildProxy(WorldImpl, new Invoker());
        WorldImpl2.world();
    }

    @Test
    public void buildProxy3() {
        Controller controller = new Controller();
        Controller controller2 = (Controller) ProxyUtil.buildProxy(controller, new Invoker());
        controller2.hello();
        controller2.getClass().getMethods();
    }

}