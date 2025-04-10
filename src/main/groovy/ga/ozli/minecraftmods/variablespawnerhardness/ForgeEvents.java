package ga.ozli.minecraftmods.variablespawnerhardness;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

final class ForgeEvents {
    @SubscribeEvent
    public static void breakSpeed(final PlayerEvent.BreakSpeed event) {
        if (event.getState().getBlock() instanceof SpawnerBlock)
            event.setNewSpeed(event.getOriginalSpeed() * (Config.peaceful.get().floatValue() / Config.getHardnessByDifficulty()[getDifficulty(event.getEntity().level())]));
    }

    private static int getDifficulty(final Level level) {
        if (level.getLevelData().isHardcore()) return 4;
        else return level.getDifficulty().ordinal();
    }
}
