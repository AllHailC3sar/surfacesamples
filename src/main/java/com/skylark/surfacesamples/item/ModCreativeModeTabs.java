package com.skylark.surfacesamples.item;

import com.skylark.surfacesamples.SurfaceSamples;
import com.skylark.surfacesamples.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SurfaceSamples.MOD_ID);

    public static final Supplier<CreativeModeTab> SURFACE_SAMPLES_BLOCKS_TAB = CREATIVE_MODE_TAB.register("surface_samples_blocks_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.GLOWQUARTZ_BLOCK))
                    .title(Component.translatable("creativetab.surfacesamples.blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.GLOWQUARTZ_BLOCK);
                        output.accept(ModBlocks.CRYSTALLISED_REDSTONE_BLOCK);
                    }).build());

    public static final Supplier<CreativeModeTab> SURFACE_SAMPLES_ITEMS_TAB = CREATIVE_MODE_TAB.register("surface_samples_items_tab",
            () -> CreativeModeTab.builder()
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(SurfaceSamples.MOD_ID, "surface_samples_blocks_tab"))
                    .icon(() -> new ItemStack(ModItems.TEST.get()))
                    .title(Component.translatable("creativetab.surfacesamples.items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.TEST);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
