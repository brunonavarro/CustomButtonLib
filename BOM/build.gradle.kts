import groovy.util.Node
import org.gradle.api.publish.maven.MavenPom

plugins {
    `java-library`
    `maven-publish`
}

group = libs.versions.bomProject.group.get() // Obtén el grupo si lo definiste en versions, o mantenlo directo.
version = libs.versions.bomProject.version.get() // ¡Así accedes a la versión del BOM!

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

publishing {
    publications {
        create<MavenPublication>("release") {
            // Configura tu artefacto para que sea un BOM
            pom {
                name.set("CustomButtonLib BOM")
                description.set("A Bill of Materials for CustomButtonLib")
                // URL de tu repositorio
                url.set("https://github.com/brunonavarro/CustomButtonLib")
                addLicences()
                addDeveloper()
                scm {
                    connection
                        .set("scm:git:github.com/brunonavarro/CustomButtonLib.git")
                    developerConnection
                        .set("scm:git:ssh://github.com/brunonavarro/CustomButtonLib.git")
                    url.set("https://github.com/brunonavarro/CustomButtonLib")
                }
                // Aquí es donde defines tus dependencias gestionadas
                // Por ejemplo, si tienes un módulo llamado "core" y "ui"
                withXml {
                    asNode().appendNode("dependencyManagement")
                        .appendNode("dependencies").apply {
                        // Usamos las referencias del catálogo aquí para CustomButtonLib
                        addCustomLibDependency()
                        // Ejemplo de dependencia externa que podrías gestionar con tu BOM
                        addMaterialExternalDependency()
                    }
                }
            }
        }
    }
}

fun MavenPom.addLicences(){
    licenses {
        license {
            name.set("The Apache Software License, Version 2.0")
            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
        }
    }
}

fun MavenPom.addDeveloper(){
    developers {
        developer {
            id.set("id-user")
            name.set("user name")
            email.set("your-email@gmail.com")
        }
    }
}

fun Node.addCustomLibDependency(){
    // Note: project.findProject(":CustomButtonLib") aún se usa si es un módulo local
    // para obtener el groupId y artifactId.
    // La versión sí la obtenemos del catálogo.
    val customButtonLibProject = project.findProject(":CustomButtonLib")
    if (customButtonLibProject != null) {
        appendNode("dependency").apply {
            appendNode("groupId", customButtonLibProject.group.toString())
            appendNode("artifactId", customButtonLibProject.name.toString())
            appendNode("version", libs.versions.customButtonLib.get())
        }
    } else {
        // Si CustomButtonLib NO es un módulo local,
        // lo defines directamente con el catálogo
        val customButtonLibRef = libs.custom.button.lib.get()
        appendNode("dependency").apply {
            appendNode("groupId", customButtonLibRef.group)
            appendNode("artifactId", customButtonLibRef.name)
            appendNode("version", libs.versions.customButtonLib.get())
        }
    }
}

fun Node.addMaterialExternalDependency(){
    val materialRef = libs.google.android.material.get()
    appendNode("dependency").apply {
        appendNode("groupId", materialRef.group)
        appendNode("artifactId", materialRef.name)
        appendNode("version", libs.versions.material.get())
    }
}
