package com.skylark.surfacesamples.block;

import com.skylark.surfacesamples.SurfaceSamples;
import com.skylark.surfacesamples.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(SurfaceSamples.MOD_ID);

    public static final DeferredBlock<Block> CRYSTALLISED_REDSTONE_BLOCK = registerBlock("crystallised_redstone_block",
            () -> new PoweredBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.FIRE)
                    .requiresCorrectToolForDrops()
                    .lightLevel(p_50872_ -> 5)
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> GLOWQUARTZ_BLOCK = registerBlock("glowquartz_block",
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.SAND)
                            .instrument(NoteBlockInstrument.PLING)
                            .strength(0.3F)
                            .sound(SoundType.GLASS)
                            .lightLevel(p_50872_ -> 7)
            ));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
