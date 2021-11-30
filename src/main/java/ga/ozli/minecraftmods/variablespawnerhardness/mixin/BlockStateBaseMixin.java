package ga.ozli.minecraftmods.variablespawnerhardness.mixin;

import ga.ozli.minecraftmods.variablespawnerhardness.Config;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.storage.LevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockStateBaseMixin {

    @Shadow
    public abstract Block getBlock();

    /**
     * @reason Make block hardness variable for spawner blocks depending on world difficulty
     * @author Paint_Ninja
     */
    @Inject(method = "getDestroySpeed(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)F", at = @At("TAIL"), cancellable = true)
    public void getDestroySpeed(BlockGetter worldIn, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if (this.getBlock() == Blocks.SPAWNER && worldIn instanceof LevelAccessor) {
            cir.setReturnValue(onGetDestroySpeed(worldIn));
        }
    }

    private static final float[] hardnessByDifficulty = {Config.HARDNESS_PEACEFUL.get(), Config.HARDNESS_EASY.get(), Config.HARDNESS_NORMAL.get(), Config.HARDNESS_HARD.get()};
    private static float onGetDestroySpeed(BlockGetter worldIn) {
        final LevelData worldInfo = ((LevelAccessor) worldIn).getLevelData();
        return worldInfo.isHardcore() ? Config.HARDNESS_HARDCORE.get() : hardnessByDifficulty[worldInfo.getDifficulty().ordinal()];
    }
}
