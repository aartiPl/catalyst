package net.igsoft.catalyst.utils.property

import net.igsoft.catalyst.utils.converter.StringConverter
import java.util.*
import java.util.function.Function

class Properties internal constructor(private val internalProperties: MutableMap<String, String>) : Iterable<Map.Entry<String, String>> {
    fun getOrThrow(key: String): String {
        return getOrThrow(key, StringConverter.TO_STRING)
    }

    private fun <T> getOrThrow(key: String, converter: Function<String, T>): T {
        val value = internalProperties[key] ?: throw IllegalArgumentException("Property '$key' is not defined.")
        return converter.apply(value)
    }

    fun getOrDefault(key: String, defaultValue: String): String {
        return getOrDefault(key, defaultValue, StringConverter.TO_STRING)
    }

    private fun <T> getOrDefault(key: String, defaultValue: T, converter: Function<String, T>): T {
        val value = internalProperties[key] ?: return defaultValue
        return converter.apply(value)
    }

    operator fun get(key: String): String {
        return get(key, StringConverter.TO_STRING)
    }

    private operator fun <T> get(key: String, converter: Function<String, T>): T {
        return converter.apply(internalProperties.getValue(key))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }
        val that = other as Properties
        return internalProperties == that.internalProperties
    }

    override fun hashCode(): Int {
        return Objects.hash(internalProperties)
    }

    override fun iterator(): MutableIterator<Map.Entry<String, String>> {
        return internalProperties.entries.iterator()
    }

    companion object {
        @JvmStatic
        fun builder(): PropertiesBuilder {
            return PropertiesBuilder()
        }
    }
}
