package net.igsoft.catalyst.utils.property

import net.igsoft.catalyst.utils.property.Properties.Companion.builder
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Properties Test")
internal class PropertiesTest {
    @Test
    @DisplayName("Test Equals Equivalence Relation")
    fun testEqualsEquivalenceRelation() {
        // Given
        val properties1 = builder().withProperty("name", "test").build()
        val properties2 = builder().withProperty("name", "test").build()
        val properties3 = builder().withProperty("name", "test").build()

        // When-Then
        // is reflexive
        Assertions.assertThat(properties1.equals(properties1)).isTrue
        // is symmetric
        Assertions.assertThat(properties1.equals(properties2)).isTrue
        Assertions.assertThat(properties2.equals(properties1)).isTrue
        // is transitive
        Assertions.assertThat(properties2.equals(properties3)).isTrue
        Assertions.assertThat(properties1.equals(properties3)).isTrue
        // null reference
        Assertions.assertThat(properties1.equals(null)).isFalse
        // wrong type
        Assertions.assertThat(properties1.equals(5)).isFalse
    }

    @Test
    @DisplayName("Should Fail To Equals For Bigger Properties")
    fun shouldFailToEqualsForBiggerProperties() {
        // Given
        val properties1 = builder().withProperty("name", "test").withProperty("fail", "something").build()
        val properties2 = builder().withProperty("name", "test").build()

        // When-Then
        Assertions.assertThat(properties1.equals(properties2)).isFalse
    }

    @Test
    @DisplayName("Should Fail To Equals For Different Properties")
    fun shouldFailToEqualsForDifferentProperties() {
        // Given
        val properties1 = builder().withProperty("myName", "test").build()
        val properties2 = builder().withProperty("name", "test").build()

        // When-Then
        Assertions.assertThat(properties1.equals(properties2)).isFalse
    }

    @Test
    @DisplayName("Should Fail To Equals For Different Properties Value")
    fun shouldFailToEqualsForDifferentPropertiesValue() {
        // Given
        val properties1 = builder().withProperty("name", "tet").withProperty("fail", "something").build()
        val properties2 = builder().withProperty("name", "test").withProperty("fail", "something").build()

        // When-Then
        Assertions.assertThat(properties1.equals(properties2)).isFalse
    }
}
