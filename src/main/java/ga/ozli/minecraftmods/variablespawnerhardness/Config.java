package ga.ozli.minecraftmods.variablespawnerhardness;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public record Config(float peaceful, float easy, float normal, float hard, float hardcore) {
    public static final Config DEFAULT = new Config(5F, 9F, 22.5F, 30F, 50F);
    public static Config instance = DEFAULT;
    private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("variablespawnerhardness.json");

    public float[] hardnessByDifficulty() {
        return new float[] { peaceful, easy, normal, hard, hardcore };
    }

    static void load() {
        if (!Files.exists(PATH)) {
            VariableSpawnerHardness.LOGGER.warn("No config found, using default");
            writeDefault();
        }

        try {
            instance = new Gson().fromJson(Files.readString(PATH), Config.class);
        } catch (IOException e) {
            VariableSpawnerHardness.LOGGER.fatal("Failed to load config", e);
            throw new RuntimeException(e);
        }
    }

    private static void writeDefault() {
        String defaultConfigJson = new GsonBuilder().setPrettyPrinting().create().toJson(DEFAULT);
        try {
            Files.writeString(PATH, defaultConfigJson);
        } catch (IOException e) {
            VariableSpawnerHardness.LOGGER.fatal("Failed to write default config", e);
            throw new RuntimeException(e);
        }
    }
}
