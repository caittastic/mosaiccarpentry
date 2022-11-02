package caittastic.mosaiccarpentry.datagen.models;

import caittastic.mosaiccarpentry.ModItemBlockRegistry;
import caittastic.mosaiccarpentry.MosaicCarpentry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static caittastic.mosaiccarpentry.ModItemBlockRegistry.getMosaicBlockTypeName;
import static caittastic.mosaiccarpentry.MosaicCarpentry.PARENT_PLANK_AND_CRAFT_BLOCK;

public class ModBlockStatesAndModels extends BlockStateProvider {
    public ModBlockStatesAndModels(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, MosaicCarpentry.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (Block[] pParentBlock : PARENT_PLANK_AND_CRAFT_BLOCK) {
            String name = getMosaicBlockTypeName(pParentBlock[0]);
            Block mosaicBlock = ModItemBlockRegistry.MOSAIC_BLOCK_MAP.get(name).get();
            //register block model
            simpleBlock(mosaicBlock);

            //register stair model
            stairsBlock((StairBlock) ModItemBlockRegistry.MOSAIC_STAIRS_MAP.get(name).get(),
                    blockTexture(mosaicBlock));

            //register slab model
            slabBlock((SlabBlock) ModItemBlockRegistry.MOSAIC_SLAB_MAP.get(name).get(),
                    blockTexture(mosaicBlock),
                    blockTexture(mosaicBlock));
        }
    }
}

