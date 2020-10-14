package net.igsoft.catalyst.utils.converter

import java.nio.file.Path
import java.nio.file.Paths

object StringConverter {
    @JvmField
    val TO_STRING: Converter<String, String> = Converter { input: String -> input }
    @JvmField
    val TO_PATH: Converter<String, Path> = Converter { input: String -> Paths.get(input) }
    @JvmField
    val TO_BOOLEAN: Converter<String, Boolean> = Converter { s: String ->
        java.lang.Boolean.parseBoolean(s)
    }
    @JvmField
    val TO_LONG: Converter<String, Long> = Converter { s: String -> s.toLong() }
    @JvmField
    val TO_INT: Converter<String, Int> = Converter { s: String -> s.toInt() }
    @JvmField
    val TO_SHORT: Converter<String, Short> = Converter { s: String -> s.toShort() }
    @JvmField
    val TO_BYTE: Converter<String, Byte> = Converter { s: String -> s.toByte() }
    @JvmField
    val TO_DOUBLE: Converter<String, Double> = Converter { s: String -> s.toDouble() }
    @JvmField
    val TO_FLOAT: Converter<String, Float> = Converter { s: String -> s.toFloat() }
}
