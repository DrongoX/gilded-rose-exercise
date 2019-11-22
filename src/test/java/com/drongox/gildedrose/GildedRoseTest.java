package com.drongox.gildedrose;

import org.junit.Test;

import static com.drongox.gildedrose.ItemType.NORMAL_ITEM_MAX_QUALITY;
import static com.drongox.gildedrose.ItemType.SULFURAS_QUALITY;
import static org.assertj.core.api.Assertions.assertThat;

public class GildedRoseTest {

    @Test
    public void updateQuality_withNoItems_shouldNotThrow() {
        //Given
        Item[] items = new Item[]{};
        GildedRose gildedRose = new GildedRose(items);
        //When
        gildedRose.updateQuality();
        //Then should not throw
    }

    @Test
    public void updateQuality_withNormalItemHavingQuality50AndSellIn10_shouldDecreaseQualityTo49AndSellInTo9() {
        //Given
        int sellIn = 10;
        Item normalItem = new Item("normal item", sellIn, NORMAL_ITEM_MAX_QUALITY);
        Item[] items = new Item[]{normalItem};
        GildedRose gildedRose = new GildedRose(items);
        //When
        gildedRose.updateQuality();
        //Then
        assertThat(normalItem.quality).isEqualTo(49);
        assertThat(normalItem.sellIn).isEqualTo(9);
    }

    @Test
    public void updateQuality_qualityShouldNeverBeNegative() {
        // Given
        Item normalItem = new Item("unicorn", 40, 0);
        Item[] items = new Item[]{normalItem};
        GildedRose gildedRose = new GildedRose(items);
        //When
        gildedRose.updateQuality();
        //Then
        assertThat(normalItem.quality).isEqualTo(0);
        assertThat(normalItem.sellIn).isEqualTo(39);
    }

    @Test
    public void updateQuality_onceTheSellByDateHasPassed_qualityDegradesTwiceAsFast() {
        // Given
        Item normalItem = new Item("unicorn", -1, 20);
        Item[] items = new Item[]{normalItem};
        GildedRose gildedRose = new GildedRose(items);
        //When
        gildedRose.updateQuality();
        //Then
        assertThat(normalItem.quality).isEqualTo(18);
        assertThat(normalItem.sellIn).isEqualTo(-2);
    }

    @Test
    public void updateQuality_withAgedBrie_shouldIncreasesInQuality() {
        // Given
        Item normalItem = new Item("Aged Brie", -10, 20);
        Item[] items = new Item[]{normalItem};
        GildedRose gildedRose = new GildedRose(items);
        //When
        gildedRose.updateQuality();
        //Then
        assertThat(normalItem.quality).isEqualTo(22);
        assertThat(normalItem.sellIn).isEqualTo(-11);
    }

    @Test
    public void updateQuality_withQuality50_shouldNeverBeMoreThan50() {
        // Given
        Item normalItem = new Item("Aged Brie", -10, NORMAL_ITEM_MAX_QUALITY);
        Item[] items = new Item[]{normalItem};
        GildedRose gildedRose = new GildedRose(items);
        //When
        gildedRose.updateQuality();
        //Then
        assertThat(normalItem.quality).isEqualTo(NORMAL_ITEM_MAX_QUALITY);
        assertThat(normalItem.sellIn).isEqualTo(-11);
    }

    @Test
    public void updateQuality_withSulfuras_shouldHaveQuality80AndNeverAlters() {
        // Given
        Item normalItem = new Item("Sulfuras, Hand of Ragnaros", 0, SULFURAS_QUALITY);
        Item[] items = new Item[]{normalItem};
        GildedRose gildedRose = new GildedRose(items);
        //When
        gildedRose.updateQuality();
        //Then
        assertThat(normalItem.quality).isEqualTo(SULFURAS_QUALITY);
        assertThat(normalItem.sellIn).isEqualTo(0);
    }

    @Test
    public void updateQuality_withBackstagePassesAndSellInGreaterThan10_shouldIncreaseInQuality() {
        // Given
        Item normalItem = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 30);
        Item[] items = new Item[]{normalItem};
        GildedRose gildedRose = new GildedRose(items);
        //When
        gildedRose.updateQuality();
        //Then
        assertThat(normalItem.quality).isEqualTo(31);
        assertThat(normalItem.sellIn).isEqualTo(10);
    }

    @Test
    public void updateQuality_withBackstagePassesAndSellInBetween6And10_shouldIncreaseInQualityBy2() {
        // Given
        Item normalItem = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 30);
        Item[] items = new Item[]{normalItem};
        GildedRose gildedRose = new GildedRose(items);
        //When
        gildedRose.updateQuality();
        //Then
        assertThat(normalItem.quality).isEqualTo(32);
        assertThat(normalItem.sellIn).isEqualTo(9);
    }

    @Test
    public void updateQuality_withBackstagePassesAndSellInBetween0And5_shouldIncreaseInQualityBy3() {
        // Given
        Item normalItem = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 30);
        Item[] items = new Item[]{normalItem};
        GildedRose gildedRose = new GildedRose(items);
        //When
        gildedRose.updateQuality();
        //Then
        assertThat(normalItem.quality).isEqualTo(33);
        assertThat(normalItem.sellIn).isEqualTo(4);
    }

    @Test
    public void updateQuality_withBackstagePassesAndSellISNegative_shouldSetQualityTo0() {
        // Given
        Item normalItem = new Item("Backstage passes to a TAFKAL80ETC concert", -1, 30);
        Item[] items = new Item[]{normalItem};
        GildedRose gildedRose = new GildedRose(items);
        //When
        gildedRose.updateQuality();
        //Then
        assertThat(normalItem.quality).isEqualTo(0);
        assertThat(normalItem.sellIn).isEqualTo(-2);
    }
}