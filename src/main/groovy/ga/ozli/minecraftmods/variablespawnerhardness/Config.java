package ga.ozli.minecraftmods.variablespawnerhardness;

import net.minecraftforge.common.ForgeConfigSpec;

final class Config {
    static final ForgeConfigSpec configSpec;

    static final ForgeConfigSpec.DoubleValue
            peaceful, easy, medium, hard, hardcore;

    private static final class LazyInit {
        private LazyInit() {}

        private static final float[] HARDNESS_BY_DIFFICULTY = new float[] {
                peaceful.get().floatValue(),
                easy.get().floatValue(),
                medium.get().floatValue(),
                hard.get().floatValue(),
                hardcore.get().floatValue()
        };
    }

    static {
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("Hardnesses");
        peaceful = builder.defineInRange("Peaceful", 5D, 5D, 50D);
        easy = builder.defineInRange("Easy", 9D, 5D, 50D);
        medium = builder.defineInRange("Medium", 22.5D, 5D, 50D);
        hard = builder.defineInRange("Hard", 30D, 5D, 50D);
        hardcore = builder.defineInRange("Hardcore", 50D, 5D, 50D);
        builder.pop();

        configSpec = builder.build();
    }

    static float[] getHardnessByDifficulty() {
        return LazyInit.HARDNESS_BY_DIFFICULTY;
    }
}
