package io.github.bubblie01.bubbliewisps.items;

import io.github.bubblie01.bubbliewisps.Main;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {
    public static final Item FLUTE_ITEM = new Item(new Item.Settings().group(ItemGroup.MATERIALS));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "flute_item"), FLUTE_ITEM);
    }

}
