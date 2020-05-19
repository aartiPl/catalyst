/*
  Author: Marcin Kuszczak
  Redistributions of source code must retain the above authorship notice
 */

package net.igsoft.catalyst.utils.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Paths;
import java.time.Duration;

import org.junit.Test;

public class StringConverterTest {

    @Test
    public void shouldConvertToDuration() {
        //Given-When-Them
        assertThat(StringConverter.TO_DURATION.apply(null)).isEqualTo(null);
        assertThat(StringConverter.TO_DURATION.apply("0")).isEqualTo(Duration.ofNanos(0));
    }

    @Test
    public void shouldConvertToPath() {
        //Given-When-Then
        assertThat(StringConverter.TO_PATH.apply("path")).isEqualTo(Paths.get("path"));
    }

    @Test
    public void shouldConvertToString() {
        //Given-When-Then
        assertThat(StringConverter.TO_STRING.apply("input")).isEqualTo("input");
    }

    @Test
    public void shouldConvertToBoolean() {
        //Given-When-Then
        assertThat(StringConverter.TO_BOOLEAN.apply("true")).isTrue();
        assertThat(StringConverter.TO_BOOLEAN.apply("True")).isTrue();
        assertThat(StringConverter.TO_BOOLEAN.apply("TRUE")).isTrue();
        assertThat(StringConverter.TO_BOOLEAN.apply("false")).isFalse();
        assertThat(StringConverter.TO_BOOLEAN.apply("False")).isFalse();

        assertThat(StringConverter.TO_BOOLEAN.apply("wrong")).isFalse();
    }

    @Test
    public void shouldConvertToLong() {
        //Given-When-Then
        assertThat(StringConverter.TO_LONG.apply("50000")).isEqualTo((long) 50000);
    }

    @Test
    public void shouldConvertToInt() {
        //Given-When-Then
        assertThat(StringConverter.TO_INT.apply("10")).isEqualTo((int) 10);
    }

    @Test
    public void shouldConvertToShort() {
        //Given-When-Then
        assertThat(StringConverter.TO_SHORT.apply("2")).isEqualTo((short) 2);
    }

    @Test
    public void shouldConvertToByte() {
        //Given-When-Then
        assertThat(StringConverter.TO_BYTE.apply("1")).isEqualTo((byte) 1);
    }

    @Test
    public void shouldConvertToDouble() {
        //Given-When-Then
        assertThat(StringConverter.TO_DOUBLE.apply("800.54321")).isEqualTo((double) 800.54321);
    }

    @Test
    public void shouldConvertToFloat() {
        //Given-When-Then
        assertThat(StringConverter.TO_FLOAT.apply("1.23")).isEqualTo((float) 1.23);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenEmptyString() {
        StringConverter.TO_DURATION.apply("");
    }

    @Test(expected = NumberFormatException.class)
    public void shouldThrowExceptionWhenWrongFormatForShort() {
        StringConverter.TO_SHORT.apply("1.234");
    }

    @Test(expected = NumberFormatException.class)
    public void shouldThrowExceptionWhenWrongFormatForLong() {
        StringConverter.TO_LONG.apply("1.234");
    }

    @Test(expected = NumberFormatException.class)
    public void shouldThrowExceptionWhenWrongFormatForInt() {
        StringConverter.TO_INT.apply("1.234");
    }

    @Test(expected = NumberFormatException.class)
    public void shouldThrowExceptionWhenWrongFormatForByte() {
        StringConverter.TO_INT.apply("1.234A");
    }

    @Test(expected = NumberFormatException.class)
    public void shouldThrowExceptionWhenWrongFormatForFloat() {
        StringConverter.TO_INT.apply("0.123456789");
    }

    @Test(expected = NumberFormatException.class)
    public void shouldThrowExceptionWhenWrongFormatForDouble() {
        StringConverter.TO_INT.apply("abc");
    }
}
