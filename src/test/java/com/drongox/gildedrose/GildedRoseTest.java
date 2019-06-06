/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.drongox.gildedrose;

import org.assertj.core.api.Assertions;
import org.junit.Test;


public class GildedRoseTest
{
  @Test
  public void should_decrease_by_one_day_given_standard_item()
  {
    //given
    String expected = "milk, 3, 14";
    Item[] items = new Item[]{
        new Item("milk",4,15)
    };

    GildedRose gildedRose = new GildedRose(items);

    //when
    gildedRose.updateQuality();

    //then
    Assertions.assertThat(gildedRose.items[0].toString()).isEqualTo(expected);
  }

  @Test
  public void should_decrease_quality_by_two_when_the_sellin_passed()
  {
    //given
    String expected = "milk, -2, 13";
    Item[] items = new Item[]{
        new Item("milk",-1,15)
    };

    GildedRose gildedRose = new GildedRose(items);

    //when
    gildedRose.updateQuality();

    //then
    Assertions.assertThat(gildedRose.items[0].toString()).isEqualTo(expected);
  }


}
