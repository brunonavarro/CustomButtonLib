plugins {
    `java-library`
    `maven-publish`
}

group = "com.github.brunonavarro"
version = "2.2025-07-16"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

publishing {
    publications {
        create<MavenPublication>("release") {
            // Configura tu artefacto para que sea un BOM
            // Esto es crucial: el packaging debe ser "pom"
            // Y el componente debe ser un "bom"
            artifact(file("src/main/resources/bom.pom")) {
                extension = "pom"
                classifier = ""
            }

            pom {
                // Nombre descriptivo de tu BOM
                name.set("CustomButtonLib BOM")
                // Descripción
                description.set("A Bill of Materials for CustomButtonLib")
                // URL de tu repositorio
                url.set("https://github.com/brunonavarro/CustomButtonLib")
                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("25406162")
                        name.set("Bruno Navarro")
                        email.set("bnavarrodev@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:github.com/brunonavarro/CustomButtonLib.git")
                    developerConnection.set("scm:git:ssh://github.com/brunonavarro/CustomButtonLib.git")
                    url.set("https://github.com/brunonavarro/CustomButtonLib")
                }

                // Aquí es donde defines tus dependencias gestionadas
                // Por ejemplo, si tienes un módulo llamado "core" y "ui"
                withXml {
                    asNode().appendNode("dependencyManagement").appendNode("dependencies").apply {
                        // Si el módulo 'core' existe en este proyecto:
                        val customButtonLibProject = project.findProject(":CustomButtonLib")
                        if (customButtonLibProject != null) {
                            // Reemplaza "yourcompany.yourapp" con tu groupId
                            // Reemplaza ":core", ":ui" con los nombres de tus otros módulos
                            // Si estos módulos ya están publicados en JitPack o Maven Central,
                            // usa su group y artifact ID completo con su versión.
                            // Si son módulos de tu mismo proyecto, usa project.group y project.name
                            appendNode("dependency").apply {
                                appendNode("groupId", customButtonLibProject.group.toString())
                                appendNode("artifactId", customButtonLibProject.name.toString())
                                appendNode("version", customButtonLibProject.version.toString())
                            }
                        }

                        // Reemplaza "yourcompany.yourapp" con tu groupId
                        // Reemplaza ":core", ":ui" con los nombres de tus otros módulos
                        // Si estos módulos ya están publicados en JitPack o Maven Central,
                        // usa su group y artifact ID completo con su versión.
                        // Si son módulos de tu mismo proyecto, usa project.group y project.name
//                        project(":CustomButtonLib").afterEvaluate {
//                            appendNode("dependency").apply {
//                                appendNode("groupId", project.group.toString())
//                                appendNode("artifactId", project.name.toString())
//                                appendNode("version", project.version.toString())
//                            }
//                        }
                        /*project(":ui").afterEvaluate {
                            appendNode("dependency").apply {
                                appendNode("groupId", project.group.toString())
                                appendNode("artifactId", project.name.toString())
                                appendNode("version", project.version.toString())
                            }
                        }*/

                        // Ejemplo de dependencia externa que podrías gestionar con tu BOM

                        appendNode("dependency").apply {
                            appendNode("groupId", "com.google.android.material")
                            appendNode("artifactId", "material")
                            appendNode("version", "1.12.0") // Versión que tu BOM recomienda
                        }
                    }
                }
            }
        }
    }
}
