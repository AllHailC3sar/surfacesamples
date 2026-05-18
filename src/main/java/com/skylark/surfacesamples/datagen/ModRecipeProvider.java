package com.skylark.surfacesamples.datagen;

import com.skylark.surfacesamples.SurfaceSamples;
import com.skylark.surfacesamples.block.ModBlocks;
import com.skylark.surfacesamples.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StonecutterBlock;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> REDSTONE_CRYSTALLISATION = List.of(Items.REDSTONE);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.CRYSTALLISED_REDSTONE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.REDSTONE_SHARD)
                .unlockedBy("has_redstone_shard", has(ModItems.REDSTONE_SHARD)).save(recipeOutput, "surfacesamples:crystallised_redstone_block_from_shards");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, ModItems.REDSTONE_SHARD, 9)
                .requires(ModBlocks.CRYSTALLISED_REDSTONE_BLOCK.get())
                .unlockedBy("has_redstone_shard", has(ModItems.REDSTONE_SHARD)).save(recipeOutput, "surfacesamples:redstone_shard_from_block");

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.GLOWQUARTZ_BLOCK.get()), RecipeCategory.REDSTONE, ModBlocks.POLISHED_GLOWQUARTZ_BLOCK.get(), 1)
                .unlockedBy("has_glowquartz", has(ModItems.GLOWQUARTZ_SHARD))
                .save(recipeOutput, "polished_glowquartz_from_stonecutting");

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.GLOWQUARTZ_BLOCK.get())
                .pattern("GGG")
                .pattern("GGG")
                .pattern("GGG")
                .define('G', ModItems.GLOWQUARTZ_SHARD)
                .unlockedBy("has_glowquartz_shard", has(ModItems.GLOWQUARTZ_SHARD)).save(recipeOutput, "surfacesamples:glowquartz_block_from_shards");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GLOWQUARTZ_SHARD, 4)
                .pattern("QG")
                .pattern("GQ")
                .define('G', Items.GLOWSTONE_DUST)
                .define('Q', Items.QUARTZ)
                .unlockedBy("has_glowquartz_shard", has(ModItems.GLOWQUARTZ_SHARD)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GLOWQUARTZ_SHARD, 9)
                .requires(ModBlocks.GLOWQUARTZ_BLOCK.get())
                .unlockedBy("has_glowquartz_shard", has(ModItems.GLOWQUARTZ_SHARD)).save(recipeOutput, "surfacesamples:glowquartz_shard_from_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Blocks.ANDESITE)
                .pattern("CCC")
                .pattern("CCC")
                .pattern("CCC")
                .define('C', ModItems.ANDESITE_CHUNK)
                .unlockedBy("has_andesite_chunk", has(ModItems.ANDESITE_CHUNK)).save(recipeOutput, "surfacesamples:andesite_block_from_chunks");

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.GLOWQUARTZ_LAMP.get())
                .pattern("NGN")
                .pattern("GSG")
                .pattern("RGR")
                .define('S', ModItems.GLOWQUARTZ_SHARD)
                .define('G', Items.GLASS_PANE)
                .define('R', Items.REDSTONE)
                .define('N', Items.IRON_NUGGET)
                .unlockedBy("has_glowquartz_shard", has(ModItems.GLOWQUARTZ_SHARD)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, Items.REDSTONE, 1)
                .requires(ModItems.REDSTONE_SHARD)
                .unlockedBy("has_redstone_shard", has(ModItems.REDSTONE_SHARD)).save(recipeOutput, "surfacesamples:redstone_from_shard");

        crystallisation(recipeOutput, REDSTONE_CRYSTALLISATION, RecipeCategory.MISC, ModItems.REDSTONE_SHARD.get(), 0.25f, 300, "redstone");
    }



    protected static void crystallisation(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_crystallisation_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, SurfaceSamples.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}