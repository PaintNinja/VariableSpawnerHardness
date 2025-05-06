package ga.ozli.minecraftmods.variablespawnerhardness;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.LoggerFactory;

@Mod("variablespawnerhardness")
public final class VariableSpawnerHardness {
    public VariableSpawnerHardness() {
        LoggerFactory.getLogger(VariableSpawnerHardness.class).info("VariableSpawnerHardness starting");

        // Register event listener
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, PlayerEvent.BreakSpeed.class, VariableSpawnerHardness::breakSpeed);

        // Setup and register the config
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.CONFIG_SPEC);
    }

    public static void breakSpeed(PlayerEvent.BreakSpeed event) {
        if (event.getState().getBlock() instanceof SpawnerBlock)
            event.setNewSpeed(event.getOriginalSpeed() * (Config.getPeaceful() / Config.getHardnessByDifficulty()[getDifficulty(event.getEntity().level())]));
    }

    private static int getDifficulty(Level level) {
        if (level.getLevelData().isHardcore()) return 4;
        else return level.getDifficulty().ordinal();
    }
}
