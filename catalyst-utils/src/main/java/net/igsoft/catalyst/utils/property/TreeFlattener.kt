package net.igsoft.catalyst.utils.property

import org.apache.commons.lang3.StringUtils

object TreeFlattener {
    fun flatten(prefix: String, propertiesMap: MutableMap<String?, Any?>, treeOrLeaf: Any?): Map<String?, Any?> {
        val newPrefix = if (StringUtils.isBlank(prefix)) "" else "$prefix."
        if (treeOrLeaf is Map<*, *>) {
            val map = treeOrLeaf as Map<String, Any>
            map.forEach { (key: String, value: Any?) -> flatten(newPrefix + key, propertiesMap, value) }
        } else if (treeOrLeaf is List<*>) {
            val list = treeOrLeaf as List<Any>
            for (i in list.indices) {
                flatten(newPrefix + i, propertiesMap, list[i])
            }
        } else {
            //Value
            propertiesMap[prefix] = treeOrLeaf
        }
        return propertiesMap
    }
}
