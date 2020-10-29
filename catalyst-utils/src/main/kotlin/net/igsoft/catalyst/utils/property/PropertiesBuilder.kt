package net.igsoft.catalyst.utils.property

import com.google.common.base.Preconditions
import org.apache.commons.lang3.StringUtils
import java.util.*

class PropertiesBuilder {
    private val internalMap: MutableMap<String, String> = HashMap()
    private var emptyValuesAllowed = false
    private var trimmedValues = true
    fun withProperty(key: String, value: String): PropertiesBuilder {
        internalMap[key] = value
        return this
    }

    fun withMap(propertyMap: Map<String, String>): PropertiesBuilder {
        for ((key, value) in propertyMap) {
            internalMap[key] = value
        }
        return this
    }

    fun withProperties(properties: Properties): PropertiesBuilder {
        for ((key, value) in properties) {
            internalMap[key] = value
        }
        return this
    }

    fun withEmptyValuesAllowed(): PropertiesBuilder {
        emptyValuesAllowed = true
        return this
    }

    fun withNotTrimmedValues(): PropertiesBuilder {
        trimmedValues = false
        return this
    }

    fun build(): Properties {
        val map: MutableMap<String, String> = HashMap()
        for (entry in internalMap.entries) {
            val key = StringUtils.trimToNull(entry.key.trim { it <= ' ' })
            var value = entry.value
            Preconditions.checkState(key != null, "Keys can not be null, empty or only whitespace!")
            if (trimmedValues && value != null) {
                value = value.trim { it <= ' ' }
            }
            check(!(!emptyValuesAllowed && value == null)) { "Value for key '$key' can not be null!" }
            map[key] = value
        }
        return Properties(map)
    }
}
