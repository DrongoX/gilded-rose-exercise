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
      if (isStandardItem(item)) {
        handleStandardItem(item);
        continue;
      }
      else {
        if (isNotMaxQuality(item)) {
          increaseQuality(item);

          if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.sellIn < 11) {
              increaseQualityIfPossible(item);
            }

            if (item.sellIn < 6) {
              increaseQualityIfPossible(item);
            }
          }
        }
      }

      if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
        decreaseSellIn(item);
      }

      if (item.sellIn < 0) {
        if (!item.name.equals("Aged Brie")) {
          if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
              descreaseQualityIfPossible(item);
            }
          }
          else {
            item.quality = 0;
          }
        }
        else {
          increaseQualityIfPossible(item);
        }
      }
    }
  }


  private boolean isStandardItem(Item item)
  {
    return !item.name.equals("Aged Brie")
        && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")
        && !item.name.equals("Sulfuras, Hand of Ragnaros");
  }


  private void handleStandardItem(Item item)
  {
    descreaseQualityIfPossible(item);
    decreaseSellIn(item);
    if (item.sellIn < 0) {
      descreaseQualityIfPossible(item);
    }
    return;
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
      increaseQuality(item);
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


  private void increaseQuality(Item item)
  {
    item.quality++;
  }
}
