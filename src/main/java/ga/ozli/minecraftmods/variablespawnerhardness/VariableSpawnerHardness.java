package ga.ozli.minecraftmods.variablespawnerhardness;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("variablespawnerhardness")
public final class VariableSpawnerHardness {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public VariableSpawnerHardness() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("VariableSpawnerHardness loading");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    /*
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @ObjectHolder("minecraft:spawner")
        public static final Block SPAWNER = null;

        Blocks.SPAWNER.Properties.hardness

        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a block here
            blockRegistryEvent.getRegistry().register(
                    SPAWNER
            );
            //new SpawnerBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(50.0F).sound(SoundType.METAL).notSolid()).setRegistryName("minecraft:spawner")
        }
    }*/
}