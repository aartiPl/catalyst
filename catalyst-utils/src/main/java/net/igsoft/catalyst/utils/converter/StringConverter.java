package net.igsoft.catalyst.utils.converter;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class StringConverter {

    public static final Converter<String, String> TO_STRING = input -> input;

    public static final Converter<String, Path> TO_PATH = input -> Paths.get(input);

    public static final Converter<String, Boolean> TO_BOOLEAN = Boolean::parseBoolean;

    public static final Converter<String, Long> TO_LONG = Long::parseLong;

    public static final Converter<String, Integer> TO_INT = Integer::parseInt;

    public static final Converter<String, Short> TO_SHORT = Short::parseShort;

    public static final Converter<String, Byte> TO_BYTE = Byte::parseByte;

    public static final Converter<String, Double> TO_DOUBLE = Double::parseDouble;

    public static final Converter<String, Float> TO_FLOAT = Float::parseFloat;

    private StringConverter() {
    }
}
