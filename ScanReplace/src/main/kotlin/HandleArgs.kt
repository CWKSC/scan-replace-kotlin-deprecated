import kotlin.system.exitProcess
import java.io.File

fun handleArgs(args: Array<String>) {

    val argsList = args.toMutableList()

    eliminateEvaluteFlag(argsList)
    
    parseArgs(argsList)

}

fun eliminateEvaluteFlag(argsList: MutableList<String>) {

    // --config | -c 
    if (argsList.contains("--config") || argsList.contains("-c")) {
        val index_config = argsList.indexOf("--config")
        val index_c = argsList.indexOf("-c")
        val index = if(index_config != -1) index_config else index_c
        Resource.configFolderPath = argsList[index + 1]
        argsList.removeAt(index)
        argsList.removeAt(index)
    }
    Resource.configFolder = File(Resource.configFolderPath)

    // --question | -q 
    if(argsList.contains("--question") || argsList.contains("-q")){
        val index_question = argsList.indexOf("--question")
        val index_q = argsList.indexOf("-q")
        val index = if(index_question != -1) index_question else index_q
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


