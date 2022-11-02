package caittastic.mosaiccarpentry.datagen.loot;

import caittastic.mosaiccarpentry.ModItemBlockRegistry;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import static caittastic.mosaiccarpentry.ModItemBlockRegistry.getMosaicBlockTypeName;
import static caittastic.mosaiccarpentry.MosaicCarpentry.PARENT_PLANK_AND_CRAFT_BLOCK;

public class ModBlockLootTables extends BlockLoot {
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModItemBlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    @Override
    protected void addTables() {
        for (Block[] pParentBlock : PARENT_PLANK_AND_CRAFT_BLOCK) {
            String name = getMosaicBlockTypeName(pParentBlock[0]);

            this.dropSelf(ModItemBlockRegistry.MOSAIC_BLOCK_MAP.get(name).get());
            this.dropSelf(ModItemBlockRegistry.MOSAIC_SLAB_MAP.get(name).get());
            this.dropSelf(ModItemBlockRegistry.MOSAIC_STAIRS_MAP.get(name).get());
        }
    }
}
