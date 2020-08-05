package ga.ozli.minecraftmods.variablespawnerhardness.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.storage.IWorldInfo;
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
     * todo: Change to insert at top rather than overwriting the whole thing
     */
    @Overwrite
    public float getBlockHardness(IBlockReader worldIn, BlockPos pos) {
        if (this.getBlock() == Blocks.SPAWNER && worldIn instanceof IWorld) {
            final IWorldInfo worldInfo = ((IWorld) worldIn).getWorld().getWorldInfo();

            if (worldInfo.isHardcore())
                return 50.0F; // same as obsidian

            final Difficulty difficulty = worldInfo.getDifficulty();

            switch (difficulty) {
                case HARD:
                    return 30.0F; // same as ancient debris
                case NORMAL:
                    return 22.5F; // same as enderchest
                case EASY:
                    return 9.0F; // same as end stone
                default: // PEACEFUL
                    return 5.0F; // same as vanilla spawner
            }
        }
        return this.hardness;
    }
}
