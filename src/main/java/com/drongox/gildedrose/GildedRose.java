package com.drongox.gildedrose;

class GildedRose {

    public static final int NORMAL_ITEM_MAX_QUALITY = 50;
    public static final int SULFURAS_QUALITY = 80;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isBackstage(item)){
                handleBackstageItem(item);

            } else {
                if (!isAgedBrie(item)) {
                    if (!isSulfuras(item)) {
                        decreaseQualityWhenPositive(item);
                    }
                } else {
                    increaseQualityIfPossible(item);
                }

                if (!isSulfuras(item)) {
                    item.sellIn = item.sellIn - 1;
                }

                if (item.sellIn < 0) {
                    if (!isAgedBrie(item)) {

                        if (!isSulfuras(item)) {
                            decreaseQualityWhenPositive(item);
                        }

                    } else {
                        increaseQualityIfPossible(item);
                    }
                }

            }


        }
    }

    private void handleBackstageItem(Item item) {

        if (item.sellIn < 6) {
            increaseQualityIfPossible(item);
            increaseQualityIfPossible(item);
            increaseQualityIfPossible(item);

        } else if (item.sellIn < 11) {
            increaseQualityIfPossible(item);
            increaseQualityIfPossible(item);

        } else {
            increaseQualityIfPossible(item);
        }

        item.sellIn = item.sellIn - 1;

        setQualityToZeroIfSellInExpired(item);
    }

    private void setQualityToZeroIfSellInExpired(Item item) {
        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private boolean isBackstage(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isQualityPositive(Item item) {
        return item.quality > 0;
    }

    private boolean isLessThanMaxNormalQuality(Item item) {
        return item.quality < NORMAL_ITEM_MAX_QUALITY;
    }

    private void increaseQualityIfPossible(Item item) {
        if (isLessThanMaxNormalQuality(item)) {
            item.quality = item.quality + 1;
        }
    }

    private void decreaseQualityWhenPositive(Item item) {
        if (isQualityPositive(item)) {
            item.quality--;
        }
    }
}
