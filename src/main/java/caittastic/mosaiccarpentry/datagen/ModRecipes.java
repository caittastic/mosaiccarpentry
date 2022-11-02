package caittastic.mosaiccarpentry.datagen;

import caittastic.mosaiccarpentry.ModItemBlockRegistry;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

import static caittastic.mosaiccarpentry.ModItemBlockRegistry.getMosaicBlockTypeName;
import static caittastic.mosaiccarpentry.MosaicCarpentry.PARENT_PLANK_AND_CRAFT_BLOCK;

public class ModRecipes extends RecipeProvider {
    public ModRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        for (Block[] parentBlockAndSlab : PARENT_PLANK_AND_CRAFT_BLOCK) {
            Block parentBlock = parentBlockAndSlab[0];
            Block parentSlab = parentBlockAndSlab[1];
            String blockKey = getMosaicBlockTypeName(parentBlock);
            String hasMosaicBlockPredicate = "has_" + blockKey + "_mosaic";
            Block mosaicBlock = ModItemBlockRegistry.MOSAIC_BLOCK_MAP.get(blockKey).get();
            InventoryChangeTrigger.TriggerInstance hasMosaicBlock = has(mosaicBlock);
            Ingredient ingredientOfMosaicBlock = Ingredient.of(mosaicBlock);

            //mosaic
            ShapedRecipeBuilder.shaped(mosaicBlock)
                    .define('#', Ingredient.of(parentSlab))
                    .pattern("#")
                    .pattern("#")
                    .unlockedBy(hasMosaicBlockPredicate, hasMosaicBlock)
                    .unlockedBy("has_" + blockKey + "_plank", has(parentBlock))
                    .unlockedBy("has_" + blockKey + "_slab", has(parentSlab))
                    .save(consumer);
            //slabs
            ShapedRecipeBuilder.shaped(ModItemBlockRegistry.MOSAIC_SLAB_MAP.get(blockKey).get(), 6)
                    .define('#', ingredientOfMosaicBlock)
                    .pattern("###")
                    .unlockedBy(hasMosaicBlockPredicate, hasMosaicBlock)
                    .save(consumer);
            //stairs
            ShapedRecipeBuilder.shaped(ModItemBlockRegistry.MOSAIC_STAIRS_MAP.get(blockKey).get(), 4)
                    .define('#', ingredientOfMosaicBlock)
                    .pattern("#  ")
                    .pattern("## ")
                    .pattern("###")
                    .unlockedBy(hasMosaicBlockPredicate, hasMosaicBlock)
                    .save(consumer);
        }
    }
}

