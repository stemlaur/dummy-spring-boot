package fr.dalkia.dummyspringboot;

import lombok.Getter;

@Getter
public class Product {
    private final String name;

    public Product(String name) {
        this.name = name;
    }
}
