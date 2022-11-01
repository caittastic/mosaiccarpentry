package caittastic.mosaiccarpentry.datagen;

import caittastic.mosaiccarpentry.MosaicCarpentry;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MosaicCarpentry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        //server datagen
        boolean isServer = event.includeServer();
        generator.addProvider(isServer, new ModBlockTags(generator, existingFileHelper));
        generator.addProvider(isServer, new ModRecipes(generator));
        generator.addProvider(isServer, new ModLootTables(generator));

        //client datagen
        boolean isClient = event.includeClient();
        generator.addProvider(isClient, new ModBlockStatesAndModels(generator, existingFileHelper));
        generator.addProvider(isClient, new ModItemModels(generator, existingFileHelper));
        generator.addProvider(isClient, new ModEnUsLanguageProvider(generator, "en_us"));
    }
}