package fr.dalkia.dummyspringboot.framework.config;

import lombok.Data;

import java.util.List;

@Data
public final class Config {
    private List<BeanConfig> beans;

    public boolean exists(Class clazz) {
        return beans.stream().anyMatch(beanConfig -> beanConfig.getClazz().equals(clazz.getCanonicalName()));
    }

}
