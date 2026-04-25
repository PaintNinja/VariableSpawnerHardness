package ga.ozli.minecraftmods.variablespawnerhardness;

import net.minecraft.world.level.Level;
import org.slf4j.LoggerFactory;

public final class CommonLogic {
    private CommonLogic() {}

    static void init() {
        LoggerFactory.getLogger("VariableSpawnerHardness").info("VariableSpawnerHardness starting");
    }

    public static float calculateNewBreakSpeed(float originalSpeed, Level level) {
        return originalSpeed * (Config.INSTANCE.getPeaceful() / Config.INSTANCE.getHardness(getDifficulty(level)));
    }

    private static int getDifficulty(Level level) {
        var levelData = level.getLevelData();
        return levelData.isHardcore() ? 4 : levelData.getDifficulty().ordinal();
    }
}
