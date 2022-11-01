package caittastic.mosaiccarpentry.datagen;

import caittastic.mosaiccarpentry.ModItemBlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import static caittastic.mosaiccarpentry.ModItemBlockRegistry.getMosaicBlockTypeName;
import static caittastic.mosaiccarpentry.MosaicCarpentry.PARENT_PLANK_AND_CRAFT_BLOCK;

public class ModLootTables extends ModBaseLootTableProvider {

    public ModLootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        for (Block[] pParentBlock : PARENT_PLANK_AND_CRAFT_BLOCK) {
            String name = getMosaicBlockTypeName(pParentBlock[0]);
            putLootTable(name, ModItemBlockRegistry.MOSAIC_BLOCK_MAP.get(name), "_mosaic");
            putLootTable(name, ModItemBlockRegistry.MOSAIC_SLAB_MAP.get(name), "_mosaic_slab");
            putLootTable(name, ModItemBlockRegistry.MOSAIC_STAIRS_MAP.get(name), "_mosaic_stairs");
        }
    }

    @Nullable
    private LootTable.Builder putLootTable(String name, RegistryObject<Block> blockRegistryObject, String appendix) {
        return lootTables.put(blockRegistryObject.get(), createSimpleTable(name + appendix, blockRegistryObject.get()));
    }
}