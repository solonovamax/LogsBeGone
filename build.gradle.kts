plugins {
    // id("fabric-loom") version "0.12-SNAPSHOT"
    id("org.quiltmc.loom") version "1.0.9"
    id("maven-publish")
}

version = "1.1.0"
group = "ca.solo-studios"

repositories {
    // Add repositories to retrieve artifacts from in here.
    // You should only use this when depending on other mods because
    // Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
    // See https://docs.gradle.org/current/userguide/declaring_repositories.html
    // for more information about repositories.
}

dependencies {
    val minecraftVersion by project.properties
    val quiltMappingsVersion by project.properties
    val loaderVersion by project.properties
    val quiltedFabricApiVersion by project.properties
    
    // To change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:$minecraftVersion")
    // mappings("net.fabricmc:yarn:$quiltMappingsVersion:v2")
    mappings(loom.layered {
        mappings("org.quiltmc:quilt-mappings:${minecraftVersion}+build.${quiltMappingsVersion}:intermediary-v2")
        // addLayer("org.quiltmc:quilt-mappings:${quiltMappingsVersion}:intermediary-v2")
    })
    // mappings("org.quiltmc:quilt-mappings:${minecraftVersion}+build.${quiltMappingsVersion}:intermediary-v2")
    modImplementation("org.quiltmc:quilt-loader:$loaderVersion")
    
    // Fabric API. This is technically optional, but you probably want it anyway.
    // modImplementation("net.fabricmc.fabric-api:fabric-api:$quiltedFabricApiVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withSourcesJar()
}

tasks {
    processResources {
        inputs.property("version", project.version)
        filteringCharset = "UTF-8"
        
        filesMatching("fabric.mod.json") {
            expand("version" to project.version)
        }
    }
    
    withType(JavaCompile::class) {
        // ensure that the encoding is set to UTF-8, no matter what the system default is
        // this fixes some edge cases with special characters not displaying correctly
        // see http://yodaconditions.net/blog/fix-for-java-file-encoding-problems-with-gradle.html
        // If Javadoc is generated, this must be specified in that task too.
        options.encoding = "UTF-8"
        var release by options.release
        release = 17
    }
    
    jar {
        from("LICENSE")
    }
}
