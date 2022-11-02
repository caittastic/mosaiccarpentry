package caittastic.mosaiccarpentry.datagen;

import caittastic.mosaiccarpentry.ModItemBlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

import static caittastic.mosaiccarpentry.ModItemBlockRegistry.getMosaicBlockTypeName;
import static caittastic.mosaiccarpentry.MosaicCarpentry.MOD_ID;
import static caittastic.mosaiccarpentry.MosaicCarpentry.PARENT_PLANK_AND_CRAFT_BLOCK;

public class ModBlockTags extends BlockTagsProvider {

    public ModBlockTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        for (Block[] parentBlock : PARENT_PLANK_AND_CRAFT_BLOCK) {
            String name = getMosaicBlockTypeName(parentBlock[0]);
            Block slabBlock = ModItemBlockRegistry.MOSAIC_SLAB_MAP.get(name).get();
            Block stairBlock = ModItemBlockRegistry.MOSAIC_STAIRS_MAP.get(name).get();

            //mining tag
            tag(BlockTags.MINEABLE_WITH_AXE)
                    .add(ModItemBlockRegistry.MOSAIC_BLOCK_MAP.get(name).get())
                    .add(slabBlock)
                    .add(stairBlock);
            //slab tab
            tag(BlockTags.WOODEN_SLABS)
                    .add(slabBlock);
            //stair tab
            tag(BlockTags.WOODEN_STAIRS)
                    .add(stairBlock);
        }
    }

    @Override
    public String getName() {
        return "Mosaic Carpentry Tags";
    }
    //------------------------------------- ===== -------------------------------------//
}