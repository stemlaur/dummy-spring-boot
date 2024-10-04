package fr.dalkia.dummyspringboot.framework;

import fr.dalkia.dummyspringboot.Application;
import fr.dalkia.dummyspringboot.framework.annotation.Transactional;
import fr.dalkia.dummyspringboot.framework.aop.ProxyTransactionalInterceptor;
import fr.dalkia.dummyspringboot.framework.config.AnnotationConfigParser;
import fr.dalkia.dummyspringboot.framework.config.BeanConfig;
import fr.dalkia.dummyspringboot.framework.config.Config;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import static fr.dalkia.dummyspringboot.framework.aop.AnnotationProxyBuilder.proxify;

public class Spring {
    private static final Map<Class<?>, Object> BEANS = new HashMap<>();

    public static ApplicationContext init(Class<Application> applicationClass) throws IOException, ClassNotFoundException {
        Config config = AnnotationConfigParser.parseConfig(applicationClass.getPackageName());
        for (BeanConfig bean : config.getBeans()) {
            buildBean(config, Class.forName(bean.getClazz()));
        }

        return Spring::getBean;
    }

    @SuppressWarnings("unchecked")
    private static <T> T getBean(Class<T> clazz) {
        if (!BEANS.containsKey(clazz)) {
            throw new NoSuchBeanDefinitionException(clazz);
        }
        return (T) BEANS.get(clazz);
    }

    @SuppressWarnings("unchecked")
    private static <T> T buildBean(Config config, Class<T> clazz) {
        if (!config.exists(clazz)) throw new NoSuchBeanDefinitionException(clazz);
        if (BEANS.containsKey(clazz)) return (T) BEANS.get(clazz);

        Class<? extends T> proxiedClass;
        try {
            proxiedClass = proxify(clazz, Transactional.class, ProxyTransactionalInterceptor.class);
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
        Constructor<?> constructor = findConstructorWithArguments(proxiedClass);
        try {
            Object[] constructorArguments = getConstructorArguments(config, constructor);
            constructor.setAccessible(true);
            Object result = constructor.newInstance(constructorArguments);
            BEANS.put(clazz, result);
            return (T) result;
        } catch (Exception ex) {
            throw new RuntimeException("Error creating bean: " + clazz.getName(), ex);
        }
    }

    private static Constructor<?> findConstructorWithArguments(Class<?> clazz) {
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.getParameterTypes().length > 0) {
                return constructor;
            }
        }
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.getParameterTypes().length == 0) {
                return constructor;
            }
        }
        throw new RuntimeException("No suitable constructor found for: " + clazz.getName());
    }

    private static Object[] getConstructorArguments(Config config, Constructor<?> constructor) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] args = new Object[parameterTypes.length];
        for (int i = 0; i < args.length; i++) {
            args[i] = buildBean(config, parameterTypes[i]);
        }
        return args;
    }

}
