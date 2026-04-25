package ga.ozli.minecraftmods.variablespawnerhardness;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class NeoConfigImpl implements Config {
    static final ModConfigSpec CONFIG_SPEC;

    // Note: At the time of writing, Neo's config system doesn't have an equivalent to
    //       Forge's ConfigSpec.FloatValue, so we have to use DoubleValue and manually convert them to floats
    private static final ModConfigSpec.DoubleValue
            peaceful, easy, medium, hard, hardcore;

    private static final class LazyInit {
        private LazyInit() {}

        private static final float PEACEFUL = peaceful.get().floatValue();
        private static final float EASY = easy.get().floatValue();
        private static final float MEDIUM = medium.get().floatValue();
        private static final float HARD = hard.get().floatValue();
        private static final float HARDCORE = hardcore.get().floatValue();
    }

    static {
        final ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.push("Hardnesses");
            peaceful = builder.defineInRange("Peaceful", 5D, 5D, 50D);
            easy = builder.defineInRange("Easy", 9D, 5D, 50D);
            medium = builder.defineInRange("Medium", 22.5D, 5D, 50D);
            hard = builder.defineInRange("Hard", 30D, 5D, 50D);
            hardcore = builder.defineInRange("Hardcore", 50D, 5D, 50D);
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
