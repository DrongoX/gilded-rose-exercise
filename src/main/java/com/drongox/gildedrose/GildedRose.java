package com.drongox.gildedrose;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

class GildedRose
{
  Item[] items;


  public GildedRose(Item[] items)
  {
    this.items = items;
  }


  public void updateQuality()
  {
    for (Item item : items) {
      ItemType.of(item.name).orElse(ItemType.STANDARD).updateQuality(item);
    }
  }


  private enum ItemType
  {
    SULFURAS("Sulfuras, Hand of Ragnaros", item -> {}),
    AGE_BRIE("Aged Brie", ItemType::handleAgedBrie),
    BACKSTAGE("Backstage passes to a TAFKAL80ETC concert", ItemType::handleBackstage),
    STANDARD("", ItemType::handleStandardItem),
    CONJURED("Conjured mana Cake",ItemType::handleConjuredItem);


    private static void handleConjuredItem(Item item)
    {
      decreaseSellIn(item);
      descreaseQualityIfPossible(item);
      descreaseQualityIfPossible(item);

    }


    private final String name;
    private final Consumer<Item> updateQuality;


    ItemType(String name, Consumer<Item> updateQuality)
    {
      this.name = name;
      this.updateQuality = updateQuality;
    }


    public static Optional<ItemType> of(String name)
    {
      return Stream.of(values())
                   .filter(itemType -> itemType.name.equals(name))
                   .findFirst();
    }


    public void updateQuality(Item item)
    {
      this.updateQuality.accept(item);
    }


    private static void handleBackstage(Item item)
    {
      increaseQualityIfPossible(item);
      if (item.sellIn < 11) {
        increaseQualityIfPossible(item);
      }
      if (item.sellIn < 6) {
        increaseQualityIfPossible(item);
      }

      decreaseSellIn(item);
      if (item.sellIn < 0) {
        item.quality = 0;
      }
    }


    private static void handleStandardItem(Item item)
    {
      descreaseQualityIfPossible(item);
      decreaseSellIn(item);
      if (item.sellIn < 0) {
        descreaseQualityIfPossible(item);
      }
    }


    private static void descreaseQualityIfPossible(Item item)
    {
      if (isPositiveQuality(item)) {
        item.quality--;
      }
    }


    private static void handleAgedBrie(Item item)
    {
      increaseQualityIfPossible(item);
      decreaseSellIn(item);

      if (item.sellIn < 0) {
        increaseQualityIfPossible(item);
      }
    }


    private static void increaseQualityIfPossible(Item item)
    {
      if (isNotMaxQuality(item)) {
        item.quality++;
      }
    }


    private static boolean isNotMaxQuality(Item item)
    {
      return item.quality < 50;
    }


    private static boolean isPositiveQuality(Item item)
    {
      return item.quality > 0;
    }


    private static void decreaseSellIn(Item item)
    {
      item.sellIn--;
    }
  }
}
