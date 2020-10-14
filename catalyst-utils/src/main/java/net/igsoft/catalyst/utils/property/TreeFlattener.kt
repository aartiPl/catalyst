package net.igsoft.catalyst.utils.property;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.List;
import java.util.Map;

public class TreeFlattener {

    public static Map<String, Object> flatten(String prefix, Map<String, Object> propertiesMap, Object treeOrLeaf) {
        String newPrefix = isBlank(prefix) ? "" : prefix + ".";

        if (treeOrLeaf instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) treeOrLeaf;
            map.forEach((key, value) -> flatten(newPrefix + key, propertiesMap, value));
        } else if (treeOrLeaf instanceof List) {
            List<Object> list = (List<Object>) treeOrLeaf;

            for (int i = 0; i < list.size(); i++) {
                flatten(newPrefix + i, propertiesMap, list.get(i));
            }
        } else {
            //Value
            propertiesMap.put(prefix, treeOrLeaf);
        }

        return propertiesMap;
    }
}
