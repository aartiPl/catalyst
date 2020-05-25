package net.igsoft.catalyst.utils.property;

import static com.google.common.base.Preconditions.checkState;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class PropertiesBuilder {
    private final Map<String, String> internalMap = new HashMap<>();
    private boolean emptyValuesAllowed = false;
    private boolean trimmedValues = true;

    public PropertiesBuilder withProperty(String key, String value) {
        internalMap.put(key, value);
        return this;
    }

    public PropertiesBuilder withMap(Map<String, String> propertyMap) {
        for (Map.Entry<String, String> entry: propertyMap.entrySet()) {
            internalMap.put(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public PropertiesBuilder withProperties(Properties properties) {
        for (Map.Entry<String, String> property: properties) {
            internalMap.put(property.getKey(), property.getValue());
        }
        return this;
    }

    public PropertiesBuilder withEmptyValuesAllowed() {
        this.emptyValuesAllowed = true;
        return this;
    }

    public PropertiesBuilder withNotTrimmedValues() {
        this.trimmedValues = false;
        return this;
    }

    public Properties build() {
        Map<String, String> map = new HashMap<>();

        for(Map.Entry<String, String> entry: internalMap.entrySet()) {
            String key = StringUtils.trimToNull(entry.getKey().trim());
            String value = entry.getValue();

            checkState(key != null, "Keys can not be null, empty or only whitespace!");

            if (trimmedValues && value != null) {
                value = value.trim();
            }

            if (!emptyValuesAllowed && value == null) {
                throw new IllegalStateException("Value for key '" + key + "' can not be null!");
            }

            map.put(key, value);
        }

        return new Properties(map);
    }
}
