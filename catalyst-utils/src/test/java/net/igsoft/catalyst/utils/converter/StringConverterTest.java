package net.igsoft.catalyst.utils.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.nio.file.Paths;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("String Converter Test")
class StringConverterTest {

    @Test
    @DisplayName("Should Convert To Path")
    void shouldConvertToPath() {
        // Given-When-Then
        assertThat(StringConverter.TO_PATH.apply("path")).isEqualTo(Paths.get("path"));
    }

    @Test
    @DisplayName("Should Convert To String")
    void shouldConvertToString() {
        // Given-When-Then
        assertThat(StringConverter.TO_STRING.apply("input")).isEqualTo("input");
    }

    @Test
    @DisplayName("Should Convert To Boolean")
    void shouldConvertToBoolean() {
        // Given-When-Then
        assertThat(StringConverter.TO_BOOLEAN.apply("true")).isTrue();
        assertThat(StringConverter.TO_BOOLEAN.apply("True")).isTrue();
        assertThat(StringConverter.TO_BOOLEAN.apply("TRUE")).isTrue();
        assertThat(StringConverter.TO_BOOLEAN.apply("false")).isFalse();
        assertThat(StringConverter.TO_BOOLEAN.apply("False")).isFalse();
        assertThat(StringConverter.TO_BOOLEAN.apply("wrong")).isFalse();
    }

    @Test
    @DisplayName("Should Convert To Long")
    void shouldConvertToLong() {
        // Given-When-Then
        assertThat(StringConverter.TO_LONG.apply("50000")).isEqualTo((long) 50000);
    }

    @Test
    @DisplayName("Should Convert To Int")
    void shouldConvertToInt() {
        // Given-When-Then
        assertThat(StringConverter.TO_INT.apply("10")).isEqualTo((int) 10);
    }

    @Test
    @DisplayName("Should Convert To Short")
    void shouldConvertToShort() {
        // Given-When-Then
        assertThat(StringConverter.TO_SHORT.apply("2")).isEqualTo((short) 2);
    }

    @Test
    @DisplayName("Should Convert To Byte")
    void shouldConvertToByte() {
        // Given-When-Then
        assertThat(StringConverter.TO_BYTE.apply("1")).isEqualTo((byte) 1);
    }

    @Test
    @DisplayName("Should Convert To Double")
    void shouldConvertToDouble() {
        // Given-When-Then
        assertThat(StringConverter.TO_DOUBLE.apply("800.54321")).isEqualTo((double) 800.54321);
    }

    @Test
    @DisplayName("Should Convert To Float")
    void shouldConvertToFloat() {
        // Given-When-Then
        assertThat(StringConverter.TO_FLOAT.apply("1.23")).isEqualTo((float) 1.23);
    }

    @Test
    @DisplayName("Should Throw Exception When Wrong Format For Short")
    void shouldThrowExceptionWhenWrongFormatForShort() {
        assertThatExceptionOfType(NumberFormatException.class).isThrownBy(() -> {
            StringConverter.TO_SHORT.apply("1.234");
        });
    }

    @Test
    @DisplayName("Should Throw Exception When Wrong Format For Long")
    void shouldThrowExceptionWhenWrongFormatForLong() {
        assertThatExceptionOfType(NumberFormatException.class).isThrownBy(() -> {
            StringConverter.TO_LONG.apply("1.234");
        });
    }

    @Test
    @DisplayName("Should Throw Exception When Wrong Format For Int")
    void shouldThrowExceptionWhenWrongFormatForInt() {
        assertThatExceptionOfType(NumberFormatException.class).isThrownBy(() -> {
            StringConverter.TO_INT.apply("1.234");
        });
    }

    @Test
    @DisplayName("Should Throw Exception When Wrong Format For Byte")
    void shouldThrowExceptionWhenWrongFormatForByte() {
        assertThatExceptionOfType(NumberFormatException.class).isThrownBy(() -> {
            StringConverter.TO_INT.apply("1.234A");
        });
    }

    @Test
    @DisplayName("Should Throw Exception When Wrong Format For Float")
    void shouldThrowExceptionWhenWrongFormatForFloat() {
        assertThatExceptionOfType(NumberFormatException.class).isThrownBy(() -> {
            StringConverter.TO_INT.apply("0.123456789");
        });
    }

    @Test
    @DisplayName("Should Throw Exception When Wrong Format For Double")
    void shouldThrowExceptionWhenWrongFormatForDouble() {
        assertThatExceptionOfType(NumberFormatException.class).isThrownBy(() -> {
            StringConverter.TO_INT.apply("abc");
        });
    }
}
