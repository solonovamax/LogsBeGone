/*
 * Copyright (c) 2024-2025 solonovamax <solonovamax@12oclockpoint.com>
 *
 * The file build.gradle.kts is part of LogsBeGone
 * Last modified on 21-01-2025 02:45 p.m.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * LOGSBEGONE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@file:Suppress("UnstableApiUsage")

import ca.solostudios.nyx.plugin.minecraft.NyxMinotaurExtension.VersionType
import ca.solostudios.nyx.util.fabric
import ca.solostudios.nyx.util.soloStudios

plugins {
    java

    alias(libs.plugins.fabric.loom)

    alias(libs.plugins.axion.release)

    alias(libs.plugins.minotaur)

    alias(libs.plugins.nyx)
}

nyx {
    compile {
        sourcesJar = true

        allWarnings = true
        warningsAsErrors = true
        distributeLicense = true
        buildDependsOnJar = true
        jvmTarget = 17
        reproducibleBuilds = true

        java {
            compilerArgs.addAll("-Xlint:-processing")
        }
    }

    info {
        name = "LogsBeGone"
        group = "gay.solonovamax"
        module = "logs-be-gone"
        version = scmVersion.version
        description = """
            Removes annoying and spammy log messages
        """.trimIndent()

        developer {
            id = "solonovamax"
            name = "solonovamax"
            email = "solonovamax@12oclockpoint.com"
            url = "https://solonovamax.gay"
        }

        repository.fromGithub("solonovamax", "LogsBeGone")
        license.useMIT()
    }

    minecraft {
        mixin {
            hotswap = true
            verbose = true
            export = true

            mixinRefmapName("logs-be-gone")
        }

        minotaur {
            versionType = if (isSnapshot) VersionType.BETA else VersionType.RELEASE
            projectId = "logs-be-gone"
            detectLoaders = true
            gameVersions = listOf("1.14", "1.15", "1.16", "1.17", "1.18", "1.19", "1.20", "1.21")
        }
    }
}

repositories {
    soloStudios()
    fabric()
    mavenCentral()
}

dependencies {
    minecraft(libs.minecraft)

    modImplementation(libs.fabric.loader)
    mappings(loom.layered {
        mappings(variantOf(libs.yarn.mappings) { classifier("v2") })
    })

    // the MixinSquared annotation processor must be registered BEFORE the mixin annotation processor
    annotationProcessor(libs.mixinsquared)
    implementationInclude(libs.mixinsquared)

    annotationProcessor(libs.sponge.mixin)
    implementation(libs.sponge.mixin)

    annotationProcessor(libs.mixinextras)
    implementation(libs.mixinextras)

    modCompileOnly(libs.geckolib3.fabric) {
        isTransitive = false
    }

    modCompileOnly(libs.bundles.portinglib) {
        exclude(group = "net.fabricmc.fabric-api")
    }

    // compileOnly instead of modCompileOnly because otherwise it breaks the compilation process (yay)
    compileOnly("maven.modrinth:bclib:3.0.14")
}

tasks {
    processResources {
        val expansion = mapOf(
            "version" to project.version,
            "versions" to mapOf(
                "mixinsquared" to libs.versions.mixinsquared.get(),
            )
        )
        inputs.properties(expansion)

        filesMatching("fabric.mod.json") {
            expand(expansion)
        }
    }
}

val Project.isSnapshot: Boolean
    get() = version.toString().endsWith("-SNAPSHOT")