package caittastic.mosaiccarpentry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.block.StairBlock;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import static caittastic.mosaiccarpentry.MosaicCarpentry.*;

public class ModItemBlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID); //block registry
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID); //item registry
    public static final Map<String,RegistryObject<Block>> MOSAIC_BLOCK_MAP = new HashMap<>(); //list of all block plank variants paired with registered block of that type
    public static final Map<String,RegistryObject<Block>> MOSAIC_SLAB_MAP = new HashMap<>();
    public static final Map<String,RegistryObject<Block>> MOSAIC_STAIRS_MAP = new HashMap<>();

    //method to register block and block item
    private static <T extends Block> RegistryObject<T> registerBlockWithBlockItem(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        ITEMS.register(name, () -> new BlockItem(toReturn.get(),
                new Item.Properties().tab(tab)));
        return toReturn;
    }

    //register planks
    static{
        for(Block[] parentArray: PARENT_PLANK_AND_CRAFT_BLOCK){
            Block parentBlock = parentArray[0];
            String name = getMosaicBlockTypeName(parentBlock);
            BlockBehaviour.Properties properties = BlockBehaviour.Properties.copy(parentBlock);

            MOSAIC_BLOCK_MAP.put(name, registerBlockWithBlockItem(name + "_mosaic", () -> new Block(properties), MOSAIC_CARPENTRY_TAB));
            MOSAIC_SLAB_MAP.put(name, registerBlockWithBlockItem(name + "_mosaic_slab", () -> new SlabBlock(properties), MOSAIC_CARPENTRY_TAB));
            MOSAIC_STAIRS_MAP.put(name, registerBlockWithBlockItem(name + "_mosaic_stairs", () -> new StairBlock(parentBlock.defaultBlockState(),properties), MOSAIC_CARPENTRY_TAB));

        }
    }

    public static String getMosaicBlockTypeName(Block parentBlock) {
        return ForgeRegistries.BLOCKS.getKey(parentBlock).toString().substring(10, ForgeRegistries.BLOCKS.getKey(parentBlock).toString().indexOf("_plank"));
    }
}
