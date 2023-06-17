package ga.ozli.minecraftmods.variablespawnerhardness

import groovy.transform.CompileStatic;
import groovy.transform.PackageScope
import groovy.util.logging.Slf4j
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.config.ModConfig
import org.groovymc.gml.GMod

@Slf4j
@GMod(MOD_ID)
@CompileStatic
final class VariableSpawnerHardness {
    @PackageScope static final String MOD_ID = 'variablespawnerhardness'

    VariableSpawnerHardness() {
        // Register event listeners
        forgeBus.register(ForgeEvents)
        modBus.onCommonSetup {
            log.info 'VariableSpawnerHardness starting'
            Config.load()
        }

        // Setup and register the config
        Config.init()
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.configSpec)
    }
}
