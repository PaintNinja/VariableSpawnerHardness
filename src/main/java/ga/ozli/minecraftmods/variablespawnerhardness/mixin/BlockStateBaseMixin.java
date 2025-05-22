package ga.ozli.minecraftmods.variablespawnerhardness.mixin;

import ga.ozli.minecraftmods.variablespawnerhardness.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
	public void getHardness(BlockGetter worldIn, BlockPos pos, CallbackInfoReturnable<Float> cir) {
		if (this.getBlock() instanceof SpawnerBlock && worldIn instanceof LevelAccessor) {
			cir.setReturnValue(onGetBlockHardness(worldIn));
		}
	}

	private static float onGetBlockHardness(BlockGetter worldIn) {
		LevelData worldInfo = ((LevelAccessor) worldIn).getLevelData();
		return worldInfo.isHardcore()
				? Config.instance.hardcore()
				: Config.instance.hardnessByDifficulty()[worldInfo.getDifficulty().ordinal()];
	}
}
