/*
  Author: Sebastian Katszer
  Redistributions of source code must retain the above authorship notice
 */

package net.igsoft.catalyst.utils.property;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PropertiesTest {

    @Test
    public void testEqualsEquivalenceRelation() {
        //Given
        Properties properties1 = new Properties();
        Properties properties2 = new Properties();
        Properties properties3 = new Properties();
        properties1.put("name", "test");
        properties2.put("name", "test");
        properties3.put("name", "test");

        //When-Then
        assertThat(properties1.equals(properties1)).isTrue();   //is reflexive
        assertThat(properties1.equals(properties2)).isTrue();   //is symmetric
        assertThat(properties2.equals(properties1)).isTrue();
        assertThat(properties2.equals(properties3)).isTrue();   //is transitive
        assertThat(properties1.equals(properties3)).isTrue();
        assertThat(properties1.equals(null)).isFalse();         //null reference
        assertThat(properties1.equals(5)).isFalse();            //wrong type
    }

    @Test
    public void shouldFailToEqualsForBiggerProperties() {
        //Given
        Properties properties1 = new Properties();
        Properties properties2 = new Properties();
        properties1.put("name", "test");
        properties1.put("fail", "something");
        properties2.put("name", "test");

        //When-Then
        assertThat(properties1.equals(properties2)).isFalse();
    }

    @Test
    public void shouldFailToEqualsForDifferentProperties() {
        //Given
        Properties properties1 = new Properties();
        Properties properties2 = new Properties();
        properties1.put("myName", "test");
        properties2.put("name", "test");

        //When-Then
        assertThat(properties1.equals(properties2)).isFalse();
    }

    @Test
    public void shouldFailToEqualsForDifferentPropertiesValue() {
        //Given
        Properties properties1 = new Properties();
        Properties properties2 = new Properties();
        properties1.put("name", "tet");
        properties2.put("name", "test");

        //When-Then
        assertThat(properties1.equals(properties2)).isFalse();
    }
}
