package com.drongox.gildedrose;

import java.util.Arrays;
import java.util.function.Consumer;

enum ItemType {
    NORMAL("", ItemType::handleNormalItem),
    SULFURAS("Sulfuras, Hand of Ragnaros", item -> {
    }),
    BACKSTAGES("Backstage passes to a TAFKAL80ETC concert", ItemType::handleBackstageItem),
    AGED_BRIE("Aged Brie", ItemType::handleAgedBrie),
    ;

    public static final int NORMAL_ITEM_MAX_QUALITY = 50;
    public static final int SULFURAS_QUALITY = 80;

    private String label;
    private Consumer<Item> updateHandler;

    ItemType(String label, Consumer<Item> updateHandler) {
        this.label = label;
        this.updateHandler = updateHandler;
    }

    public static ItemType of(Item item) {
        return Arrays.stream(ItemType.values())
                .filter(itemType -> itemType.label.equals(item.name))
                .findFirst()
                .orElse(ItemType.NORMAL);
    }

    public void handle(Item item) {
        this.updateHandler.accept(item);
    }

    private static void handleNormalItem(final Item item) {
        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            decreaseQualityWhenPositive(item);
            decreaseQualityWhenPositive(item);
        } else {
            decreaseQualityWhenPositive(item);
        }
    }

    private static void handleAgedBrie(final Item item) {
        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            increaseQualityIfPossible(item);
            increaseQualityIfPossible(item);
        } else {
            increaseQualityIfPossible(item);
        }
    }

    private static void handleBackstageItem(Item item) {

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

    private static void setQualityToZeroIfSellInExpired(Item item) {
        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }

    private static boolean isQualityPositive(Item item) {
        return item.quality > 0;
    }

    private static boolean isLessThanMaxNormalQuality(Item item) {
        return item.quality < NORMAL_ITEM_MAX_QUALITY;
    }

    private static void increaseQualityIfPossible(Item item) {
        if (isLessThanMaxNormalQuality(item)) {
            item.quality = item.quality + 1;
        }
    }

    private static void decreaseQualityWhenPositive(Item item) {
        if (isQualityPositive(item)) {
            item.quality--;
        }
    }
}
