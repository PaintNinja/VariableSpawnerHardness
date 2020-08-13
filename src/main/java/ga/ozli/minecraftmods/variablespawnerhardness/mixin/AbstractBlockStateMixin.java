package ga.ozli.minecraftmods.variablespawnerhardness.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.storage.IWorldInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin {

    @Shadow
    public abstract Block getBlock();

    /**
     * @reason Make block hardness variable for spawner blocks depending on world difficulty
     * @author Paint_Ninja
     */
    @Inject(method = "getBlockHardness(Lnet/minecraft/world/IBlockReader;Lnet/minecraft/util/math/BlockPos;)F", at = @At("TAIL"), cancellable = true)
    public void getBlockHardness(IBlockReader worldIn, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if (this.getBlock() == Blocks.SPAWNER && worldIn instanceof IWorld) {
            cir.setReturnValue(onGetBlockHardness(worldIn));
        }
    }

    private static final float[] hardnessByDifficulty = {5F, 9F, 22.5F, 30F};
    private static float onGetBlockHardness(IBlockReader worldIn) {
        final IWorldInfo worldInfo = ((IWorld) worldIn).getWorldInfo();
        return worldInfo.isHardcore() ? 50F : hardnessByDifficulty[worldInfo.getDifficulty().ordinal()];
    }
}

