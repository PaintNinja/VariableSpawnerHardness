package ga.ozli.minecraftmods.variablespawnerhardness.mixin;

import ga.ozli.minecraftmods.variablespawnerhardness.VariableSpawnerHardness;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin {

    @Shadow
    @Final
    private float hardness;

    @Shadow
    public abstract Block getBlock();

    /**
     * @reason Make block hardness variable for spawner blocks depending on world difficulty
     * @author Paint_Ninja
     * Todo: Change to insert at top rather than overwriting the whole thing
     */
    @Overwrite
    public float getBlockHardness(IBlockReader worldIn, BlockPos pos) {
        VariableSpawnerHardness.LOGGER.warn("this.getBlock() = " + this.getBlock()); // warn to make it obvious for dev
        if (this.getBlock() == Blocks.SPAWNER) {
            return 50.0F;
        }
        return this.hardness;
    }
}
