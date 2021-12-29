import kotlin.system.exitProcess
import java.io.File

fun handleArgs(args: Array<String>) {

    val argsList = args.toMutableList()

    eliminateEvaluateFlag(argsList)

    parseArgs(argsList)

}

fun eliminateEvaluateFlag(argsList: MutableList<String>) {

    // --config | -c 
    if (argsList.contains("--config") || argsList.contains("-c")) {
        val indexConfig = argsList.indexOf("--config")
        val indexC = argsList.indexOf("-c")
        val index = if (indexConfig != -1) indexConfig else indexC
        Resource.configFolderPath = argsList[index + 1]
        argsList.removeAt(index)
        argsList.removeAt(index)
    }
    Resource.configFolder = File(Resource.configFolderPath)

    // --question | -q 
    if (argsList.contains("--question") || argsList.contains("-q")) {
        val indexQuestion = argsList.indexOf("--question")
        val indexQ = argsList.indexOf("-q")
        val index = if (indexQuestion != -1) indexQuestion else indexQ
        argsList.removeAt(index)
        Resource.question_mode = true
    }

}


fun parseArgs(argsList: MutableList<String>) {

    // Init config template file //
    if (argsList.size == 1 && argsList[0] == "init") {
        initFile()
        exitProcess(exitCode_init)
    }

}


