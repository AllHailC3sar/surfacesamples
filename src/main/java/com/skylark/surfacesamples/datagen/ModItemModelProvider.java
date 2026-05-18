package com.skylark.surfacesamples.datagen;

import com.skylark.surfacesamples.SurfaceSamples;
import com.skylark.surfacesamples.block.ModBlocks;
import com.skylark.surfacesamples.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SurfaceSamples.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.GLOWQUARTZ_SHARD.get());
        basicItem(ModItems.REDSTONE_SHARD.get());
//        basicItem(ModItems.TEST.get());
        basicItem(ModItems.ANDESITE_CHUNK.get());
    }
}
