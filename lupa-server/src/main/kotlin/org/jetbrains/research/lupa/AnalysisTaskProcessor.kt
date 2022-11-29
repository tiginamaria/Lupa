package org.jetbrains.research.lupa

import org.jetbrains.research.lupa.kotlinAnalysis.AnalysisOrchestrator
import org.jetbrains.research.lupa.kotlinAnalysis.statistic.analysis.metrics.ProjectMetricsAnalysisExecutor

class AnalysisTaskProcessor {

    fun accept(analysisTask: AnalysisTask) {
        AnalysisOrchestrator(ProjectMetricsAnalysisExecutor(analysisTask.outputPath))
            .execute(analysisTask.inputPath, analysisTask.outputPath)
    }
}
