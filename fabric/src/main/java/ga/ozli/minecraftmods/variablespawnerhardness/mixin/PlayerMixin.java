package ga.ozli.minecraftmods.variablespawnerhardness.mixin;

import ga.ozli.minecraftmods.variablespawnerhardness.CommonLogic;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Inject(at = @At("TAIL"), method = "getDestroySpeed", cancellable = true)
    public void getDestroySpeed(BlockState state, CallbackInfoReturnable<Float> cir) {
        if (state.getBlock() instanceof SpawnerBlock)
            cir.setReturnValue(CommonLogic.calculateNewBreakSpeed(cir.getReturnValue(), ((Player) (Object) this).level()));
    }
}
