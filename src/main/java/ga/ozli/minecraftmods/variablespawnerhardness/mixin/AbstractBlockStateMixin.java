package ga.ozli.minecraftmods.variablespawnerhardness.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
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
            final IWorldInfo worldInfo = ((IWorld) worldIn).getWorld().getWorldInfo();

            if (worldInfo.isHardcore()) {
                cir.setReturnValue(50.0F); // same as obsidian
                return;
            }

            final Difficulty difficulty = worldInfo.getDifficulty();

            switch (difficulty) {
                case HARD:
                    cir.setReturnValue(30.0F); // same as ancient debris
                    break;
                case NORMAL:
                    cir.setReturnValue(22.5F); // same as enderchest
                    break;
                case EASY:
                    cir.setReturnValue(9.0F); // same as end stone
                    break;
                default: // PEACEFUL
                    cir.setReturnValue(5.0F); // same as vanilla spawner
                    break;
            }
        }
    }
}
