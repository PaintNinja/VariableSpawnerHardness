package ga.ozli.minecraftmods.variablespawnerhardness;

import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;

public final class FabricConfigImpl implements Config {
    private record Config(float peaceful, float easy, float medium, float hard, float hardcore) {}

    private static final Logger LOGGER = LoggerFactory.getLogger("VariableSpawnerHardness");
    private static final Config CONFIG;
    static {
        var configPath = FabricLoader.getInstance().getConfigDir().resolve("variablespawnerhardness.json");
        var gson = new GsonBuilder()
                .disableJdkUnsafe()
                .setPrettyPrinting()
                .create();

        if (Files.exists(configPath)) {
            try {
                CONFIG = gson.fromJson(Files.readString(configPath), Config.class);
            } catch (IOException e) {
                LOGGER.error("Failed to load config", e);
                throw new RuntimeException(e);
            }
        } else {
            LOGGER.warn("No config found, using default");
            var defaultConfig = new Config(5F, 9F, 22.5F, 30F, 50F);
            String json = gson.toJson(defaultConfig);
            try {
                Files.writeString(configPath, json);
                CONFIG = defaultConfig;
            } catch (IOException e) {
                LOGGER.error("Failed to write default config", e);
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public float getPeaceful() {
        return CONFIG.peaceful;
    }

    @Override
    public float getHardness(int difficulty) {
        return switch (difficulty) {
            case 0 -> CONFIG.peaceful;
            case 1 -> CONFIG.easy;
            case 2 -> CONFIG.medium;
            case 3 -> CONFIG.hard;
            case 4 -> CONFIG.hardcore;
            default -> throw new UnsupportedOperationException("Unknown difficulty: " + difficulty);
        };
    }
}
