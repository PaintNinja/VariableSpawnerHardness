package ga.ozli.minecraftmods.variablespawnerhardness;

import net.minecraft.world.level.block.SpawnerBlock;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@Mod("variablespawnerhardness")
public final class VariableSpawnerHardness {
    public VariableSpawnerHardness(ModContainer modContainer) {
        CommonLogic.init();

        // Register event listener
        NeoForge.EVENT_BUS.addListener(PlayerEvent.BreakSpeed.class, VariableSpawnerHardness::breakSpeed);

        // Setup and register the config
        modContainer.registerConfig(ModConfig.Type.COMMON, NeoConfigImpl.CONFIG_SPEC);
    }

    private static void breakSpeed(PlayerEvent.BreakSpeed event) {
        if (event.getState().getBlock() instanceof SpawnerBlock)
            event.setNewSpeed(CommonLogic.calculateNewBreakSpeed(event.getOriginalSpeed(), event.getEntity().level()));
    }
}
