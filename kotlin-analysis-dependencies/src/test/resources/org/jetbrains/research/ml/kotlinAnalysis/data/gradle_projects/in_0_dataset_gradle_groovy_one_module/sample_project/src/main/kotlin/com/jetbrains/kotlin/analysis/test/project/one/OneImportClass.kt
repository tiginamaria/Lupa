package com.jetbrains.kotlin.analysis.test.project.one

import kotlinx.coroutines.delay

class OneImportClass {

    suspend fun importUsage(): Int {
        delay(1000L)
        return 29
    }

}
