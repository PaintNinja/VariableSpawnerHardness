FabricModsDotGroovy.make {
    id = 'variablespawnerhardness'
    version = buildProperties['mod_version']

    name = 'VariableSpawnerHardness'
    description = 'Makes spawners take longer to destroy depending on your difficulty for a slight extra challenge.\n\nSpawner hardnesses\n------------------\nPeaceful: Same hardness as vanilla (5F)\nEasy: Same as end stone (9F)\nNormal: Same as ender chests (22.5F)\nHard: Same as ancient debris (30F)\nHardcore: Same as obsidian (50F)'
    authors {
        person 'Paint_Ninja'
    }
    contact {
        homepage = 'https://www.curseforge.com/minecraft/mc-mods/variable-spawner-hardness'
        sources = 'https://github.com/PaintNinja/VariableSpawnerHardness/tree/master-fabric'
        issues = 'https://github.com/PaintNinja/VariableSpawnerHardness/issues'
    }

    license = 'MIT'

    environment = Environment.ANY
    entrypoints {
        main 'ga.ozli.minecraftmods.variablespawnerhardness.VariableSpawnerHardness'
    }
    mixins {
        mixin 'variablespawnerhardness.mixins.json'
    }

    depends {
//        mod {
//            id = 'fabricloader'
//            versionRange = v('>=0.15.6')
//        }
//        mod {
//            id = 'minecraft'
//            versionRange = rawVersionRange(buildProperties['mc_dep_version'] as String)
//        }
//        mod {
//            id = 'java'
//            versionRange = v('>=17')
//        }
        fabricloader = v('>=' + buildProperties['loader_version'])
        minecraft = buildProperties['mc_dep_version'] as String
        java = v('>=' + buildProperties['java_version'])
    }

    suggests {
//        mod {
//            id = 'modmenu'
//            versionRange = v('*')
//        }
        modmenu = v('*')
    }
}
