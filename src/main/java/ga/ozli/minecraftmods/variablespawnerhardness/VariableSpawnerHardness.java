package ga.ozli.minecraftmods.variablespawnerhardness;

import com.mojang.logging.LogUtils;
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
        // Register the commonSetup method for mod loading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);

        // Setup and register the config
        Config.init();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.configSpec);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("VariableSpawnerHardness starting");
        Config.load();
    }
}