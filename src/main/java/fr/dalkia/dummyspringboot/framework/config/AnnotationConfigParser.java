package fr.dalkia.dummyspringboot.framework.config;

import fr.dalkia.dummyspringboot.framework.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class AnnotationConfigParser {

    public static Config parseConfig(String packageName) throws IOException {
        Set<Class<?>> classes = getClassesInPackage(packageName);

        Set<Class<?>> annotatedClasses = new HashSet<>();
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Bean.class)) {
                annotatedClasses.add(clazz);
            }
        }

        return new Config(
                annotatedClasses.stream().map(
                        c -> new BeanConfig(c.getCanonicalName())
                ).toList()
        );
    }

    private static Set<Class<?>> getClassesInPackage(String packageName) {
        Set<Class<?>> classes = new HashSet<>();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Enumeration<URL> resources = classLoader.getResources(packageName.replace(".", "/"));
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                File directory = new File(resource.getFile());
                if (directory.isDirectory()) {
                    classes.addAll(findClasses(directory, packageName));
                }
            }
        } catch (Exception e) {
            // Handle the exception, e.g., log an error
            e.printStackTrace();
        }
        return classes;
    }

    private static Set<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        Set<Class<?>> classes = new HashSet<>();

        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + "." + file.getName().substring(0, file.getName().length() - 6);

                Class<?> clazz = Class.forName(className);
                classes.add(clazz);
            }
        }
        return classes;
    }

}
