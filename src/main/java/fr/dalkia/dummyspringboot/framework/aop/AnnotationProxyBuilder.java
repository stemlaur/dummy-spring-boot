package fr.dalkia.dummyspringboot.framework.aop;

import fr.dalkia.dummyspringboot.Application;
import fr.dalkia.dummyspringboot.framework.annotation.Transactional;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;

import static net.bytebuddy.matcher.ElementMatchers.isAnnotatedWith;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class AnnotationProxyBuilder {
    public static <T> Class<? extends T> instrument(
            Class<T> classToInstrument,
            Class<Transactional> annotation,
            Class<ProxyTransactionalInterceptor> methodInterceptor
    ) throws InstantiationException, IllegalAccessException {
        return new ByteBuddy()
                .subclass(classToInstrument)
                .method(isAnnotatedWith(named(annotation.getCanonicalName()))) //here
                .intercept(MethodDelegation.to(methodInterceptor))
                .make()
                .load(Application.class.getClassLoader())
                .getLoaded();
    }

//    public static <T> Class<? extends T> instrument(
//            Class<T> classToInstrument,
//            Class<Transactional> annotation,
//            Class<ProxyTransactionalInterceptor> methodInterceptor
//    ) throws NoSuchMethodException, InstantiationException, IllegalAccessException {
//        return new ByteBuddy()
//                .subclass(classToInstrument)
//                .method(isAnnotatedWith(named(annotation.getCanonicalName()))) //here
//                .intercept(MethodDelegation.to(methodInterceptor))
//                .make()
//                .load(Application.class.getClassLoader())
//                .getLoaded();
//    }
}
