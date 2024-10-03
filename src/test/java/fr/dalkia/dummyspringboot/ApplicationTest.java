package fr.dalkia.dummyspringboot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {

    @Test
    void shouldWork() {
        assertThat(2).isEqualTo(1 + 1);
    }
}