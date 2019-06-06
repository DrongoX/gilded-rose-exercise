package com.drongox.gildedrose;

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
      if (isSulfuras(item)) {
        continue;
      }

      if (isAgedBrie(item)) {
        handleAgedBrie(item);
        continue;
      }

      if (isBackstage(item)) {
        handleBackstage(item);
        continue;
      }

      handleStandardItem(item);
    }
  }


  private boolean isBackstage(Item item)
  {
    return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
  }


  private void handleBackstage(Item item)
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


  private boolean isAgedBrie(Item item)
  {
    return item.name.equals("Aged Brie");
  }


  private void handleAgedBrie(Item item)
  {
    increaseQualityIfPossible(item);
    decreaseSellIn(item);

    if (item.sellIn < 0) {
      increaseQualityIfPossible(item);
    }
  }


  private boolean isSulfuras(Item item)
  {
    return item.name.equals("Sulfuras, Hand of Ragnaros");
  }


  private void handleStandardItem(Item item)
  {
    descreaseQualityIfPossible(item);
    decreaseSellIn(item);
    if (item.sellIn < 0) {
      descreaseQualityIfPossible(item);
    }
  }


  private void descreaseQualityIfPossible(Item item)
  {
    if (isPositiveQuality(item)) {
      item.quality--;
    }
  }


  private void increaseQualityIfPossible(Item item)
  {
    if (isNotMaxQuality(item)) {
      item.quality++;
    }
  }


  private boolean isNotMaxQuality(Item item)
  {
    return item.quality < 50;
  }


  private boolean isPositiveQuality(Item item)
  {
    return item.quality > 0;
  }


  private void decreaseSellIn(Item item)
  {
    item.sellIn--;
  }

}
