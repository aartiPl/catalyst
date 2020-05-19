/*
  Author: Marcin Kuszczak
  Redistributions of source code must retain the above authorship notice
 */

package net.igsoft.catalyst.utils.property;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import net.igsoft.catalyst.utils.converter.StringConverter;

public class Properties implements Iterable<Map.Entry<String, String>> {

    private final Map<String, String> internalProperties;

    public Properties() {
        this.internalProperties = new HashMap<>();
    }

    public Properties(ConcurrentHashMap<String, String> properties) {
        this.internalProperties = properties;
    }

    protected Properties(Properties properties) {
        this.internalProperties = new HashMap<>(properties.internalProperties);
    }

    public String getOrThrow(String key) {
        return getOrThrow(key, StringConverter.TO_STRING);
    }

    private <T> T getOrThrow(String key, Function<String, T> converter) {
        String value = internalProperties.get(key);

        if (value == null) {
            throw new IllegalArgumentException("Property '" + key + "' is not defined.");
        }

        return converter.apply(value);
    }

    public String getOrDefault(String key, String defaultValue) {
        return getOrDefault(key, defaultValue, StringConverter.TO_STRING);
    }

    private <T> T getOrDefault(String key, T defaultValue, Function<String, T> converter) {
        String value = internalProperties.get(key);

        if (value == null) {
            return defaultValue;
        }

        return converter.apply(value);
    }

    public String get(String key) {
        return get(key, StringConverter.TO_STRING);
    }

    private <T> T get(String key, Function<String, T> converter) {
        return converter.apply(internalProperties.get(key));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Properties that = (Properties) o;
        return Objects.equals(internalProperties, that.internalProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalProperties);
    }

    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        return internalProperties.entrySet().iterator();
    }

    public static PropertiesBuilder builder() {
        return new PropertiesBuilder();
    }
}
