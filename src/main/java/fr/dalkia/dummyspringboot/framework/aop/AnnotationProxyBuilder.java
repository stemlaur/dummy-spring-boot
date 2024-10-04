package fr.dalkia.dummyspringboot.framework.aop;

import fr.dalkia.dummyspringboot.Application;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;

import static net.bytebuddy.matcher.ElementMatchers.isAnnotatedWith;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class AnnotationProxyBuilder {
    public static <T> Class<? extends T> proxify(
            Class<T> classToInstrument,
            Class<?> annotation,
            Class<ProxyTransactionalInterceptor> methodInterceptor
    ) throws InstantiationException, IllegalAccessException {
        try (
                DynamicType.Unloaded<T> make = new ByteBuddy()
                        .subclass(classToInstrument)
                        .method(isAnnotatedWith(named(annotation.getCanonicalName()))) //here
                        .intercept(MethodDelegation.to(methodInterceptor))
                        .make()
        ) {
            return make
                    .load(Application.class.getClassLoader())
                    .getLoaded();
        }
    }
}
