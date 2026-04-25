package ga.ozli.minecraftmods.variablespawnerhardness;

import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("variablespawnerhardness")
public final class VariableSpawnerHardness {
    public VariableSpawnerHardness(FMLJavaModLoadingContext context) {
        CommonLogic.init();

        // Register event listener
        PlayerEvent.BreakSpeed.BUS.addListener(VariableSpawnerHardness::breakSpeed);

        // Setup and register the config
        context.registerConfig(ModConfig.Type.COMMON, ForgeConfigImpl.CONFIG_SPEC);
    }

    private static void breakSpeed(PlayerEvent.BreakSpeed event) {
        if (event.getState().getBlock() instanceof SpawnerBlock)
            event.setNewSpeed(CommonLogic.calculateNewBreakSpeed(event.getOriginalSpeed(), event.getEntity().level()));
    }
}
