package ga.ozli.minecraftmods.variablespawnerhardness.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldProperties;
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
	@Inject(method = "getHardness(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)F", at = @At("TAIL"), cancellable = true)
	public void getHardness(BlockView worldIn, BlockPos pos, CallbackInfoReturnable<Float> cir) {
		if (this.getBlock() == Blocks.SPAWNER && worldIn instanceof WorldAccess) {
			cir.setReturnValue(onGetBlockHardness(worldIn));
		}
	}

	private static final float[] hardnessByDifficulty = {5F, 9F, 22.5F, 30F};
	private static float onGetBlockHardness(BlockView worldIn) {
		final WorldProperties worldInfo = ((WorldAccess) worldIn).getLevelProperties();
		return worldInfo.isHardcore() ? 50F : hardnessByDifficulty[worldInfo.getDifficulty().ordinal()];
	}
}