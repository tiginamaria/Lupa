package org.jetbrains.research.lupa

import kotlinx.coroutines.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardWatchEventKinds.*

class AnalysisWatchingServer(private val projectsInfoPath: Path) {

    private val watchService = FileSystems.getDefault().newWatchService()
    private val watchKey = projectsInfoPath.register(watchService, ENTRY_CREATE)
    private val analysisTaskProcessor = AnalysisTaskProcessor()

    @OptIn(ExperimentalSerializationApi::class)
    fun run() = runBlocking {
        launch(Dispatchers.IO) {
            while (true) {
                watchKey.pollEvents().forEach {
                    val eventPath = projectsInfoPath.resolve(it.context() as Path)
                    val eventType = it.kind()

                    println("$eventType: $eventPath")
                    println("Start analysis $eventPath")
                    val analysisTask = Json.decodeFromStream<AnalysisTask>(Files.newInputStream(eventPath))
                    println(analysisTask)
                    analysisTaskProcessor.accept(analysisTask)
                    println("Finish analysis $eventPath")
                }
                delay(1000L)
            }
        }
    }
}
