package fr.dalkia.dummyspringboot.framework.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class Config {
    private List<BeanConfig> beans;

    public boolean exists(Class clazz) {
        return beans.stream().anyMatch(beanConfig -> beanConfig.getClazz().equals(clazz.getCanonicalName()));
    }

}
