package ga.ozli.minecraftmods.variablespawnerhardness;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.ConfigValue<Float> HARDNESS_PEACEFUL;
    public static final ForgeConfigSpec.ConfigValue<Float> HARDNESS_EASY;
    public static final ForgeConfigSpec.ConfigValue<Float> HARDNESS_NORMAL;
    public static final ForgeConfigSpec.ConfigValue<Float> HARDNESS_HARD;
    public static final ForgeConfigSpec.ConfigValue<Float> HARDNESS_HARDCORE;

    static {
        BUILDER.push("GENERAL");

        HARDNESS_PEACEFUL = BUILDER.comment("Peaceful Default: 5.0").define("HARDNESS_PEACEFUL", 5.0F);
        HARDNESS_EASY = BUILDER.comment("Easy Default: 9.0").define("HARDNESS_EASY", 9.0F);
        HARDNESS_NORMAL = BUILDER.comment("Normal Default: 22.5").define("HARDNESS_NORMAL", 22.5F);
        HARDNESS_HARD = BUILDER.comment("Hard Default: 30.0").define("HARDNESS_HARD", 30.0F);
        HARDNESS_HARDCORE = BUILDER.comment("Hardcore Default: 50.0").define("HARDNESS_HARDCORE", 50.0F);


        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
