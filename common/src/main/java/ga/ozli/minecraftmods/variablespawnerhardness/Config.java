package ga.ozli.minecraftmods.variablespawnerhardness;

import java.util.ServiceLoader;

public interface Config {
    Config INSTANCE = ServiceLoader.load(Config.class).findFirst().orElseThrow();

    float getPeaceful();
    float getHardness(int difficulty);
}
