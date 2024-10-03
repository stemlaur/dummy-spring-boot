package fr.dalkia.dummyspringboot.framework;

public class NoSuchBeanDefinitionException extends RuntimeException {

    public NoSuchBeanDefinitionException(Class clazz) {
        super("No qualifying bean of type [%s]".formatted(clazz.getCanonicalName()));
    }
}
