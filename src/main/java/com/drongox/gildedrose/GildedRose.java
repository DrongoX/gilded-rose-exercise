package com.drongox.gildedrose;

class GildedRose {

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            handleItem(item);
        }
    }

    private void handleItem(Item item) {
        ItemType itemType = ItemType.of(item);
        itemType.handle(item);
    }
}
