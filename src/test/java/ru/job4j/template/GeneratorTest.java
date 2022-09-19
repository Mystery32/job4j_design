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
    public void whenInvalidTemplateKeys() {
        Generator generator = null;
        String template = "I am a ${name}, Who are ${subject}?";
        String s1 = template.substring(template.indexOf("{") + 1, template.indexOf("}"));
        String s2 = template.substring(template.lastIndexOf("{") + 1, template.lastIndexOf("}"));
        Map<String, String> args = new HashMap<>();
        args.put("names", "Ivan");
        args.put("subjects", "you");
        assertThat(args.containsKey(s1)).isFalse();
        assertThat(args.containsKey(s2)).isFalse();
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
        String wrongKey1 = "id";
        String wrongKey2 = "value";
        assertThat(args.containsKey(wrongKey1)).isTrue();
        assertThat(args.containsKey(wrongKey2)).isTrue();
        assertThrows(IllegalArgumentException.class, () -> generator.produce(template, args));
    }
}