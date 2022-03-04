package com.makentoshe.shuvi.buildscript.plugins

object ShadowJarPlugin : Plugin {
    override val version = "7.1.2"
    override val classpath = "com.github.jengelman.gradle.plugins:shadow:$version"
    override val id = "com.github.johnrengelman.shadow"
}