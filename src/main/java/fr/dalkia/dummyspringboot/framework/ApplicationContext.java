package fr.dalkia.dummyspringboot.framework;

public interface ApplicationContext {
    <T> T getBean(Class<T> clazz);
}
