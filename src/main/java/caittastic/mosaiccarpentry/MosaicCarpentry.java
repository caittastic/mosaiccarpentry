package caittastic.mosaiccarpentry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MosaicCarpentry.MOD_ID)
public class MosaicCarpentry {
    /*----------------------------------------------- variables -----------------------------------------------*/
    public static final String MOD_ID = "mosaic_carpentry"; //a string that contains our modid, accessable anywhere
    //all the wood types for our mod to work with
    public static final Block[][] PARENT_PLANK_AND_CRAFT_BLOCK = {
            {Blocks.OAK_PLANKS, Blocks.OAK_SLAB},
            {Blocks.BIRCH_PLANKS, Blocks.BIRCH_SLAB},
            {Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_SLAB},
            {Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_SLAB},
            {Blocks.ACACIA_PLANKS, Blocks.ACACIA_SLAB},
            {Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_SLAB},
            {Blocks.WARPED_PLANKS, Blocks.WARPED_SLAB},
            {Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_SLAB}};

    /*----------------------------------------------- tab -----------------------------------------------*/
    //a creative tab for the stuff in our mod
    public static final CreativeModeTab MOSAIC_CARPENTRY_TAB = new CreativeModeTab(MOD_ID) {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack(ModItemBlockRegistry.MOSAIC_BLOCK_MAP.get("oak").get());
        }
    };
    /*----------------------------------------------- mod setup and running -----------------------------------------------*/
    public MosaicCarpentry() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItemBlockRegistry.ITEMS.register(bus); //registers items
        ModItemBlockRegistry.BLOCKS.register(bus); //registers blocks

        MinecraftForge.EVENT_BUS.register(this); // Register ourselves for server and other game events we are interested in
    }
}
