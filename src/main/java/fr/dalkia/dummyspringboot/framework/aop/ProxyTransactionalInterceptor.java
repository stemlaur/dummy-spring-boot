package fr.dalkia.dummyspringboot.framework.aop;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class ProxyTransactionalInterceptor {
    @RuntimeType
    public static Object intercept(@Origin Method method,
                                   @SuperCall Callable<?> callable) {
        System.out.println("BEGIN TRANSACTION");
        try {
            Object result = callable.call();
            System.out.println("COMMIT");
            return result;
        } catch (Exception e) {
            System.out.println("ROLLBALL");
            throw new RuntimeException(e);
        }
    }
}
