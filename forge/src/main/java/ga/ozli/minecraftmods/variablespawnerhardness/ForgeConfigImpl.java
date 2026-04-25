package ga.ozli.minecraftmods.variablespawnerhardness;

import net.minecraftforge.common.ForgeConfigSpec;

public final class ForgeConfigImpl implements Config {
    static final ForgeConfigSpec CONFIG_SPEC;

    private static final ForgeConfigSpec.FloatValue
            peaceful, easy, medium, hard, hardcore;

    private static final class LazyInit {
        private LazyInit() {}

        private static final float PEACEFUL = peaceful.get();
        private static final float EASY = easy.get();
        private static final float MEDIUM = medium.get();
        private static final float HARD = hard.get();
        private static final float HARDCORE = hardcore.get();
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

    @Override
    public float getPeaceful() {
        return LazyInit.PEACEFUL;
    }

    @Override
    public float getHardness(int difficulty) {
        return switch (difficulty) {
            case 0 -> LazyInit.PEACEFUL;
            case 1 -> LazyInit.EASY;
            case 2 -> LazyInit.MEDIUM;
            case 3 -> LazyInit.HARD;
            case 4 -> LazyInit.HARDCORE;
            default -> throw new UnsupportedOperationException("Unknown difficulty: " + difficulty);
        };
    }
}
