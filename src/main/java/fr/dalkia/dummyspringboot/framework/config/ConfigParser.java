package fr.dalkia.dummyspringboot.framework.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class ConfigParser {

    public static Config parseConfig() throws IOException {
        String configContent = getConfigContent();
        return new ObjectMapper().readValue(configContent, Config.class);
    }

    private static String getConfigContent() throws IOException {
        try (var in = ConfigParser.class.getResourceAsStream("/beans.json")) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
