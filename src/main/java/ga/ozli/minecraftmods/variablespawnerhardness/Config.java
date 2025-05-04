package ga.ozli.minecraftmods.variablespawnerhardness;

import net.minecraftforge.common.ForgeConfigSpec;

final class Config {
    private Config() {}

    static final ForgeConfigSpec CONFIG_SPEC;

    private static final ForgeConfigSpec.FloatValue
            peaceful, easy, medium, hard, hardcore;

    private static final class LazyInit {
        private LazyInit() {}

        private static final float PEACEFUL = peaceful.get();

        private static final float[] HARDNESS_BY_DIFFICULTY = new float[] {
                PEACEFUL,
                easy.get(),
                medium.get(),
                hard.get(),
                hardcore.get()
        };
    }

    static {
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("Hardnesses");
            peaceful = builder.defineInRange("Peaceful", 5F, 5F, 50F);
            easy = builder.defineInRange("Easy", 9F, 5F, 50F);
            medium = builder.defineInRange("Medium", 22.5F, 5F, 50F);
            hard = builder.defineInRange("Hard", 30F, 5F, 50F);
            hardcore = builder.defineInRange("Hardcore", 50F, 5F, 50F);
        builder.pop();

        CONFIG_SPEC = builder.build();
    }

    static float getPeaceful() {
        return LazyInit.PEACEFUL;
    }

    static float[] getHardnessByDifficulty() {
        return LazyInit.HARDNESS_BY_DIFFICULTY;
    }
}
