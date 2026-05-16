package com.skylark.surfacesamples.item;

import com.skylark.surfacesamples.SurfaceSamples;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    // create a deferred register called ITEMS that we can add items to
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SurfaceSamples.MOD_ID);

    // add an item called testitem to the ITEMS register
    public static final DeferredItem<Item> TEST = ITEMS.register("testitem",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> REDSTONE_SHARD = ITEMS.register("redstone_shard",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GLOWQUARTZ_SHARD = ITEMS.register("glowquartz_shard",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ANDESITE_CHUNK = ITEMS.register("andesite_chunk",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
