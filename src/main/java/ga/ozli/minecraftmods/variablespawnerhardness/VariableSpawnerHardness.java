package ga.ozli.minecraftmods.variablespawnerhardness;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod("variablespawnerhardness")
public final class VariableSpawnerHardness {
    private static final Logger LOGGER = LogUtils.getLogger();

    public VariableSpawnerHardness(FMLJavaModLoadingContext context) {
        // Register event listeners
        MinecraftForge.EVENT_BUS.addListener(ForgeEvents::breakSpeed);
        context.getModEventBus().addListener(VariableSpawnerHardness::onCommonSetup);

        // Setup and register the config
        context.registerConfig(ModConfig.Type.COMMON, Config.CONFIG_SPEC);
    }

    static void onCommonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("VariableSpawnerHardness starting");
    }
}
