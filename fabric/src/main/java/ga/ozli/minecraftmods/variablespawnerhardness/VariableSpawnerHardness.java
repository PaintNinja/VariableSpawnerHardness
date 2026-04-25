package ga.ozli.minecraftmods.variablespawnerhardness;

import net.fabricmc.api.ModInitializer;

public final class VariableSpawnerHardness implements ModInitializer {
    @Override
    public void onInitialize() {
        CommonLogic.init();
    }
}
