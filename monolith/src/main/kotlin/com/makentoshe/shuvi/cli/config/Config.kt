package com.makentoshe.shuvi.cli.config

import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.Option

internal fun CommandLine.option(option: Option, default: () -> String): String = try {
    getOptionValue(option)
} catch (exception: Exception) {
    default()
}

internal fun environment(name: String, default: String): String = try {
    System.getenv(name) ?: default
} catch (se: SecurityException) {
    default
}
