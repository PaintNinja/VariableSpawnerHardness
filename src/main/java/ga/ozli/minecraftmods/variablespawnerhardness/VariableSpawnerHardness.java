package ga.ozli.minecraftmods.variablespawnerhardness;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static ga.ozli.minecraftmods.variablespawnerhardness.VariableSpawnerHardness.MOD_ID;

@Mod(MOD_ID)
public final class VariableSpawnerHardness {
    private static final Logger LOGGER = LogUtils.getLogger();
    static final String MOD_ID = "variablespawnerhardness";

    public VariableSpawnerHardness() {
        // Register event listeners
        MinecraftForge.EVENT_BUS.register(ForgeEvents.class);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(VariableSpawnerHardness::onCommonSetup);

        // Setup and register the config
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.configSpec);
    }

    public static void onCommonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("VariableSpawnerHardness starting");
    }
}
