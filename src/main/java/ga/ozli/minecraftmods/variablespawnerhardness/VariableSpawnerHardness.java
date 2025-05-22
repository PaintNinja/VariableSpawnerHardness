package ga.ozli.minecraftmods.variablespawnerhardness;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class VariableSpawnerHardness implements ModInitializer {
	// Directly reference a log4j logger.
	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void onInitialize() {
		LOGGER.info("VariableSpawnerHardness loading");
		Config.load();
	}
}
