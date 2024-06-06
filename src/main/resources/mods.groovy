ModsDotGroovy.make {
    modLoader = 'javafml'
    loaderVersion = '[50,)'

    license = 'MIT'
    issueTrackerUrl = 'https://github.com/PaintNinja/VariableSpawnerHardness/issues'

    mod {
        modId = 'variablespawnerhardness'
        displayName = 'Variable Spawner Hardness'
        version = '1.20.6-1.4.1'
        author = 'Paint_Ninja'

        displayUrl = 'https://www.curseforge.com/minecraft/mc-mods/variable-spawner-hardness'

        description = '''
            Makes spawners take longer to destroy depending on your difficulty for a slight extra challenge.
            
            Default spawner hardnesses
            --------------------------
            Peaceful: Same hardness as vanilla (5F)
            Easy: Same as end stone (9F)
            Normal: Same as ender chests (22.5F)
            Hard: Same as ancient debris (30F)
            Hardcore: Same as obsidian (50F)
            
            The spawner hardnesses are configurable in the variablespawnerhardness-common.toml file.
            
            Unlike some of the blocks mentioned above, you can still destroy a spawner with any tool you want as long as you don't use your bare hands.
        '''.stripIndent(true).trim()

        credits = 'Thanks to Commoble and IItemstack on the MMD discord for helping make this possible. Thanks to LexManos from the Forge team for helping make this work without any mixins by using more of the Forge API.'

        dependencies {
            forge = '>=50.1.0'
            minecraft = this.minecraftVersionRange
        }
    }
}
