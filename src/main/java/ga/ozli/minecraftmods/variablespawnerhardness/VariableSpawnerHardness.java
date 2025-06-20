package ga.ozli.minecraftmods.variablespawnerhardness;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.LoggerFactory;

@Mod("variablespawnerhardness")
public final class VariableSpawnerHardness {
    public VariableSpawnerHardness(FMLJavaModLoadingContext context) {
        LoggerFactory.getLogger(VariableSpawnerHardness.class).info("VariableSpawnerHardness starting");

        // Register event listener
        PlayerEvent.BreakSpeed.BUS.addListener(VariableSpawnerHardness::breakSpeed);

        // Setup and register the config
        context.registerConfig(ModConfig.Type.COMMON, Config.CONFIG_SPEC);
    }

    private static void breakSpeed(PlayerEvent.BreakSpeed event) {
        if (event.getState().getBlock() instanceof SpawnerBlock)
            event.setNewSpeed(event.getOriginalSpeed() * (Config.getPeaceful() / Config.getHardnessByDifficulty()[getDifficulty(event.getEntity().level())]));
    }

    private static int getDifficulty(Level level) {
        if (level.getLevelData().isHardcore()) return 4;
        else return level.getDifficulty().ordinal();
    }
}
