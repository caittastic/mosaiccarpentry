package caittastic.mosaiccarpentry.datagen;

import caittastic.mosaiccarpentry.MosaicCarpentry;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = MosaicCarpentry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        //server datagen
        boolean isServer = event.includeServer();
        if(isServer){
            generator.addProvider(new ModBlockTags(generator, existingFileHelper));
            generator.addProvider(new ModRecipes(generator));
            generator.addProvider(new ModLootTables(generator));
        }

        //client datagen
        boolean isClient = event.includeClient();
        if(isClient){
            generator.addProvider(new ModBlockStatesAndModels(generator, existingFileHelper));
            generator.addProvider(new ModItemModels(generator, existingFileHelper));
            generator.addProvider(new ModEnUsLanguageProvider(generator, "en_us"));
        }
    }
}