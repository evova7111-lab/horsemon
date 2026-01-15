plugins {
    id 'java-library'
    id 'eclipse'
    id 'idea'
    id 'net.neoforged.moddev' version '1.0.14'
}

version = '1.0.0'
group = 'com.example.horsemon'
base { archivesName = 'horsemon' }

java.toolchain.languageVersion = JavaLanguageVersion.of(21)

neoForge {
    version = '21.1.5'

    parchment {
        mappingsVersion = '2024.07.28'
        minecraftVersion = '1.21'
    }

    runs {
        client {
            client()
            systemProperty 'neoforge.enabledGameTestNamespaces', 'horsemon'
        }
    }

    mods {
        horsemon {
            sourceSet sourceSets.main
        }
    }
}

// ВОТ ЭТОТ БЛОК МЫ ДОБАВИЛИ
dependencies {
    // Говорим Gradle брать всё из папки libs
    implementation fileTree(dir: 'libs', include: ['*.jar', '*.zip'])
}

sourceSets.main.resources { srcDir 'src/main/resources' }

tasks.withType(JavaCompile).configureEach {
    options.encoding = 'UTF-8'
}
