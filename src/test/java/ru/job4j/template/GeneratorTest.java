package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertThrows;

@Disabled
class GeneratorTest {

    @Test
    public void whenValidTemplateKeys() {
        Generator generator = null;
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Ivan");
        args.put("subject", "you");
        assertThat(generator.produce(template, args)).isEqualTo("I am a Ivan, Who are you?");
    }

    @Test
    public void whenInvalidTemplateKeys() {
        Generator generator = null;
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("names", "Ivan");
        args.put("subjects", "you");
        assertThat(args.containsKey("name")).isFalse();
        assertThat(args.containsKey("subject")).isFalse();
        assertThrows(IllegalArgumentException.class, () -> generator.produce(template, args));
    }

    @Test
    public void whenExtraKeys() {
        Generator generator = null;
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Ivan");
        args.put("subject", "you");
        args.put("id", "Stepan");
        args.put("value", "student");
        assertThat(args.containsKey("id")).isTrue();
        assertThat(args.containsKey("value")).isTrue();
        assertThrows(IllegalArgumentException.class, () -> generator.produce(template, args));
    }
}