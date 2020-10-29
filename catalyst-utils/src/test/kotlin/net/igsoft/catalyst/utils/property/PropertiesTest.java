package net.igsoft.catalyst.utils.property;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Properties Test")
class PropertiesTest {

    @Test
    @DisplayName("Test Equals Equivalence Relation")
    void testEqualsEquivalenceRelation() {
        // Given
        Properties properties1 = Properties.builder().withProperty("name", "test").build();
        Properties properties2 = Properties.builder().withProperty("name", "test").build();
        Properties properties3 = Properties.builder().withProperty("name", "test").build();

        // When-Then
        // is reflexive
        assertThat(properties1.equals(properties1)).isTrue();
        // is symmetric
        assertThat(properties1.equals(properties2)).isTrue();
        assertThat(properties2.equals(properties1)).isTrue();
        // is transitive
        assertThat(properties2.equals(properties3)).isTrue();
        assertThat(properties1.equals(properties3)).isTrue();
        // null reference
        assertThat(properties1.equals(null)).isFalse();
        // wrong type
        assertThat(properties1.equals(5)).isFalse();
    }

    @Test
    @DisplayName("Should Fail To Equals For Bigger Properties")
    void shouldFailToEqualsForBiggerProperties() {
        // Given
        Properties properties1 =
                Properties.builder().withProperty("name", "test").withProperty("fail", "something").build();
        Properties properties2 = Properties.builder().withProperty("name", "test").build();

        // When-Then
        assertThat(properties1.equals(properties2)).isFalse();
    }

    @Test
    @DisplayName("Should Fail To Equals For Different Properties")
    void shouldFailToEqualsForDifferentProperties() {
        // Given
        Properties properties1 = Properties.builder().withProperty("myName", "test").build();
        Properties properties2 = Properties.builder().withProperty("name", "test").build();

        // When-Then
        assertThat(properties1.equals(properties2)).isFalse();
    }

    @Test
    @DisplayName("Should Fail To Equals For Different Properties Value")
    void shouldFailToEqualsForDifferentPropertiesValue() {
        // Given
        Properties properties1 =
                Properties.builder().withProperty("name", "tet").withProperty("fail", "something").build();
        Properties properties2 =
                Properties.builder().withProperty("name", "test").withProperty("fail", "something").build();

        // When-Then
        assertThat(properties1.equals(properties2)).isFalse();
    }
}
