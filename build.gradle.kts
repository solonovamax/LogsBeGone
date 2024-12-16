/*
 * Copyright (c) 2024 solonovamax <solonovamax@12oclockpoint.com>
 *
 * The file build.gradle.kts is part of LogsBeGone
 * Last modified on 16-12-2024 01:23 a.m.
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
 * GRADLE-CONVENTIONS-PLUGIN IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
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
        javadocJar = true
        sourcesJar = true

        allWarnings = true
        // warningsAsErrors = true
        distributeLicense = true
        buildDependsOnJar = true
        jvmTarget = 17
        reproducibleBuilds = true
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

    annotationProcessor(libs.sponge.mixin)
    implementation(libs.sponge.mixin)

    annotationProcessor(libs.mixinextras)
    implementation(libs.mixinextras)
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("fabric.mod.json") {
            expand("version" to project.version)
        }
    }
}

val Project.isSnapshot: Boolean
    get() = version.toString().endsWith("-SNAPSHOT")