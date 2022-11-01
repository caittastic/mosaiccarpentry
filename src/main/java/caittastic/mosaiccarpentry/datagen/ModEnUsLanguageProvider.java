package caittastic.mosaiccarpentry.datagen;

import caittastic.mosaiccarpentry.ModItemBlockRegistry;
import caittastic.mosaiccarpentry.MosaicCarpentry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;


import static caittastic.mosaiccarpentry.ModItemBlockRegistry.getMosaicBlockTypeName;
import static caittastic.mosaiccarpentry.MosaicCarpentry.PARENT_PLANK_AND_CRAFT_BLOCK;
import static org.apache.commons.lang3.text.WordUtils.capitalizeFully;

public class ModEnUsLanguageProvider extends LanguageProvider {
    public ModEnUsLanguageProvider(DataGenerator gen, String locale) {
        super(gen, MosaicCarpentry.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        for (Block[] parentBlockAndSlab : PARENT_PLANK_AND_CRAFT_BLOCK) {
            String key = getMosaicBlockTypeName(parentBlockAndSlab[0]);
            String baseDisplayName = capitalizeFully(key.replace("_", " "));
            //lang entries
            add(ModItemBlockRegistry.MOSAIC_BLOCK_MAP.get(key).get(), baseDisplayName + " Mosaic");//planks
            add(ModItemBlockRegistry.MOSAIC_SLAB_MAP.get(key).get(), baseDisplayName + " Mosaic Slab");//slab
            add(ModItemBlockRegistry.MOSAIC_STAIRS_MAP.get(key).get(), baseDisplayName + " Mosaic Stairs");//slab
        }
        //item group
        add("itemGroup." + MosaicCarpentry.MOSAIC_CARPENTRY_TAB.getRecipeFolderName(), "Mosaic Carpentry");
    }

}
