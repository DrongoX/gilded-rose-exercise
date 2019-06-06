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

  @Test
  public void should_not_make_quality_negative()
  {
    //given
    String expected = "milk, -3, 0";
    Item[] items = new Item[]{
        new Item("milk",-2,0)
    };

    GildedRose gildedRose = new GildedRose(items);

    //when
    gildedRose.updateQuality();

    //then
    Assertions.assertThat(gildedRose.items[0].toString()).isEqualTo(expected);
  }

  @Test
  public void should_increase_quality_when_product_is_aged_brie()
  {
    //given
    String expected = "Aged Brie, 41, 16";
    Item[] items = new Item[]{
        new Item("Aged Brie",42,15)
    };

    GildedRose gildedRose = new GildedRose(items);

    //when
    gildedRose.updateQuality();

    //then
    Assertions.assertThat(gildedRose.items[0].toString()).isEqualTo(expected);
  }

  @Test
  public void should_keep_quality_below_fifty()
  {
    //given
    String expected = "Aged Brie, 41, 50";
    Item[] items = new Item[]{
        new Item("Aged Brie",42,50)
    };

    GildedRose gildedRose = new GildedRose(items);

    //when
    gildedRose.updateQuality();

    //then
    Assertions.assertThat(gildedRose.items[0].toString()).isEqualTo(expected);
  }

  @Test
  public void should_not_change_quality_and_sellin_for_sulfuras()
  {
    //given
    String expected = "Sulfuras, Hand of Ragnaros, 24, 80";
    Item[] items = new Item[]{
        new Item("Sulfuras, Hand of Ragnaros",24,80)
    };

    GildedRose gildedRose = new GildedRose(items);

    //when
    gildedRose.updateQuality();

    //then
    Assertions.assertThat(gildedRose.items[0].toString()).isEqualTo(expected);
  }


  @Test
  public void should_increase_quality_when_sellin_above_or_equal_10_for_backstage()
  {
    //given
    String expected = "Backstage passes to a TAFKAL80ETC concert, 23, 43";
    Item[] items = new Item[]{
        new Item("Backstage passes to a TAFKAL80ETC concert",24,42)
    };

    GildedRose gildedRose = new GildedRose(items);

    //when
    gildedRose.updateQuality();

    //then
    Assertions.assertThat(gildedRose.items[0].toString()).isEqualTo(expected);
  }

}
