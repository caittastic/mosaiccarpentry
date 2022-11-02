package caittastic.mosaiccarpentry.datagen.models;

import caittastic.mosaiccarpentry.ModItemBlockRegistry;
import caittastic.mosaiccarpentry.MosaicCarpentry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static caittastic.mosaiccarpentry.ModItemBlockRegistry.getMosaicBlockTypeName;
import static caittastic.mosaiccarpentry.MosaicCarpentry.PARENT_PLANK_AND_CRAFT_BLOCK;

public class ModItemModels extends ItemModelProvider {

    public ModItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MosaicCarpentry.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (Block[] pParentBlock : PARENT_PLANK_AND_CRAFT_BLOCK) {
            String name = getMosaicBlockTypeName(pParentBlock[0]);

            RegisterWithExistingParent(name, ModItemBlockRegistry.MOSAIC_BLOCK_MAP.get(name), "_mosaic");
            RegisterWithExistingParent(name, ModItemBlockRegistry.MOSAIC_SLAB_MAP.get(name), "_mosaic_slab");
            RegisterWithExistingParent(name, ModItemBlockRegistry.MOSAIC_STAIRS_MAP.get(name), "_mosaic_stairs");
        }
    }

    private ItemModelBuilder RegisterWithExistingParent(String name, RegistryObject<Block> block, String appendix) {
        return withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).toString(), modLoc("block/" + name + appendix));
    }
}

