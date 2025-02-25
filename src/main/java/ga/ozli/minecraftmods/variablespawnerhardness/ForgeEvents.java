package ga.ozli.minecraftmods.variablespawnerhardness;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraftforge.event.entity.player.PlayerEvent;

final class ForgeEvents {
    private ForgeEvents() {}

    static void breakSpeed(PlayerEvent.BreakSpeed event) {
        if (event.getState().getBlock() instanceof SpawnerBlock)
            event.setNewSpeed(event.getOriginalSpeed() * (Config.getPeaceful() / Config.getHardnessByDifficulty()[getDifficulty(event.getEntity().level())]));
    }

    private static int getDifficulty(Level level) {
        if (level.getLevelData().isHardcore()) return 4;
        else return level.getDifficulty().ordinal();
    }
}
