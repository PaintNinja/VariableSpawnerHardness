package ga.ozli.minecraftmods.variablespawnerhardness;

import net.minecraftforge.common.ForgeConfigSpec;

class Config {

    static ForgeConfigSpec configSpec;

    static ForgeConfigSpec.DoubleValue
            peaceful, easy, medium, hard, hardcore;

    static float[] hardnessByDifficulty;

    static void init() {
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("Hardnesses");
            peaceful = builder.defineInRange("Peaceful", 5D, 5D, 50D);
            easy = builder.defineInRange("Easy", 9D, 5D, 50D);
            medium = builder.defineInRange("Medium", 22.5D, 5D, 50D);
            hard = builder.defineInRange("Hard", 30D, 5D, 50D);
            hardcore = builder.defineInRange("Hardcore", 50D, 5D, 50D);
        builder.pop();

        configSpec = builder.build();

        hardnessByDifficulty = new float[] {
                peaceful.get().floatValue(),
                easy.get().floatValue(),
                medium.get().floatValue(),
                hard.get().floatValue(),
                hardcore.get().floatValue()
        };
    }
}
