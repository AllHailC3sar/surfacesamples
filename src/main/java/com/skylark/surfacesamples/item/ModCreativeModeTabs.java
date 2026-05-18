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
                    .icon(() -> new ItemStack(ModBlocks.REDSTONE_SAMPLE))
                    .title(Component.translatable("creativetab.surfacesamples.blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.GLOWQUARTZ_BLOCK);
                        output.accept(ModBlocks.POLISHED_GLOWQUARTZ_BLOCK);
                        output.accept(ModBlocks.GLOWQUARTZ_LAMP);
                        output.accept(ModBlocks.CRYSTALLISED_REDSTONE_BLOCK);
                        output.accept(ModBlocks.TEST_SAMPLE);
                        output.accept(ModBlocks.COAL_SAMPLE);
                        output.accept(ModBlocks.COPPER_SAMPLE);
                        output.accept(ModBlocks.DIAMOND_SAMPLE);
                        output.accept(ModBlocks.EMERALD_SAMPLE);
                        output.accept(ModBlocks.GOLD_SAMPLE);
                        output.accept(ModBlocks.IRON_SAMPLE);
                        output.accept(ModBlocks.LAPIS_SAMPLE);
                        output.accept(ModBlocks.REDSTONE_SAMPLE);
                        output.accept(ModBlocks.ANCIENT_DEBRIS_SAMPLE);
                        output.accept(ModBlocks.NETHER_GOLD_SAMPLE);
                        output.accept(ModBlocks.NETHER_QUARTZ_SAMPLE);
                        output.accept(ModBlocks.QUARTZ_SAMPLE);
                        output.accept(ModBlocks.ANDESITE_SAMPLE);
                        output.accept(ModBlocks.ZINC_SAMPLE);
                    }).build());

    public static final Supplier<CreativeModeTab> SURFACE_SAMPLES_ITEMS_TAB = CREATIVE_MODE_TAB.register("surface_samples_items_tab",
            () -> CreativeModeTab.builder()
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(SurfaceSamples.MOD_ID, "surface_samples_blocks_tab"))
                    .icon(() -> new ItemStack(ModItems.GLOWQUARTZ_SHARD.get()))
                    .title(Component.translatable("creativetab.surfacesamples.items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.TEST);
                        output.accept(ModItems.REDSTONE_SHARD);
                        output.accept(ModItems.GLOWQUARTZ_SHARD);
                        output.accept(ModItems.ANDESITE_CHUNK);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
