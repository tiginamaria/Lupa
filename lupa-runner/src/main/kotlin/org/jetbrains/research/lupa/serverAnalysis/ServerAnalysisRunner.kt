package org.jetbrains.research.lupa.serverAnalysis

import org.jetbrains.research.lupa.AnalysisWatchingServer
import org.jetbrains.research.pluginUtilities.runners.BaseRunner
import org.jetbrains.research.pluginUtilities.runners.IORunnerArgs
import org.jetbrains.research.pluginUtilities.runners.IORunnerArgsParser

/** Runner for analysis server. */
object ServerAnalysisRunner : BaseRunner<IORunnerArgs, IORunnerArgsParser>
    ("server-analysis", IORunnerArgsParser) {
    override fun run(args: IORunnerArgs) {
        val server = AnalysisWatchingServer(args.inputDir)
        server.run()
    }
}
