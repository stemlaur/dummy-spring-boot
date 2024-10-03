package fr.dalkia.dummyspringboot;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SpringContext {

    public static <T> T getBean(Class<T> clazz) {
        try {
            // Get constructor with no arguments
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (NoSuchMethodException e) {
            // Try finding a constructor with arguments
            Constructor constructor = findConstructorWithArguments(clazz);
            if (constructor != null) {
                try {
                    // Simulate dependency injection (replace with actual implementation)
                    Object[] args = getConstructorArguments(constructor);
                    constructor.setAccessible(true);
                    return (T) constructor.newInstance(args);
                } catch (Exception ex) {
                    throw new RuntimeException("Error creating bean: " + clazz.getName(), ex);
                }
            } else {
                throw new RuntimeException("No suitable constructor found for: " + clazz.getName());
            }
        } catch(InstantiationException | IllegalAccessException e){
            throw new RuntimeException("Error creating bean: " + clazz.getName(), e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static Constructor<?> findConstructorWithArguments(Class<?> clazz) {
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.getParameterTypes().length > 0) {
                return constructor;
            }
        }
        return null;
    }

    private static Object[] getConstructorArguments(Constructor<?> constructor) {
        // Simulate dependency injection (replace with actual implementation)
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] args = new Object[parameterTypes.length];
        for (int i = 0; i < args.length; i++) {
            args[i] = getBean(parameterTypes[i]);
        }
        return args;
    }

}
