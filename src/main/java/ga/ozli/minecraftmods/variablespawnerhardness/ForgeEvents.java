package ga.ozli.minecraftmods.variablespawnerhardness;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = VariableSpawnerHardness.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
class ForgeEvents {

    @SubscribeEvent
    public static void breakSpeed(final PlayerEvent.BreakSpeed event) {
        if (event.getState().getBlock() instanceof SpawnerBlock)
            event.setNewSpeed(event.getOriginalSpeed() * (Config.peaceful.get().floatValue() / Config.hardnessByDifficulty[getDifficulty(event.getPlayer().level)]));
    }

    private static int getDifficulty(final Level level) {
        if (level.getLevelData().isHardcore()) return 4;
        else return level.getDifficulty().ordinal();
    }
}
