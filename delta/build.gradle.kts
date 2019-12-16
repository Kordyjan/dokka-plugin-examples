dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compileOnly("org.jetbrains.dokka:dokka-fatapi:0.11.0-SNAPSHOT")
    implementation(project(":alpha"))
    implementation(project(":bravo"))
    implementation(project(":charlie"))
}