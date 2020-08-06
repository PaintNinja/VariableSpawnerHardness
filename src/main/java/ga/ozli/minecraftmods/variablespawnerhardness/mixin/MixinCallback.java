package ga.ozli.minecraftmods.variablespawnerhardness.mixin;

import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.storage.IWorldInfo;

public final class MixinCallback {
    static final float[] hardnessByDifficulty = {5F, 9F, 22.5F, 30F};

    public static float onGetBlockHardness(IBlockReader worldIn) {
        final IWorldInfo worldInfo = ((IWorld) worldIn).getWorld().getWorldInfo();
        return worldInfo.isHardcore() ? 50F : hardnessByDifficulty[worldInfo.getDifficulty().ordinal()];
    }
}
