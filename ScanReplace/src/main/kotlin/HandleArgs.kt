import kotlin.system.exitProcess
import java.io.File



fun handleArgs(args: Array<String>) {

    val argsList = args.toMutableList()

    var configFolderPath = ".scan-replace"
    if (argsList.contains("--config") || argsList.contains("-c")) {
        val index_config = argsList.indexOf("--config")
        val index_c = argsList.indexOf("-c")
        val index = if(index_config != -1) index_config else index_c
        configFolderPath = argsList[index + 1]
        argsList.removeAt(index)
        argsList.removeAt(index)
    }

    Resource.configFolder = File(configFolderPath)
    if(!Resource.configFolder.isDirectory()){
        println()
        println("[Error] $configFolderPath not a directory (--config <configFolderPath>) ")
        println()
        exitProcess(exitCode_configFolderPath_not_a_directory)
    }

    if(argsList.contains("--question") || argsList.contains("-q")){
        Resource.question_mode = true
        println()
        println("Question mode ON")
        println()
    }

    // Init config template file //
    if (args.size == 1 && args[0] == "init") {
        initFile()
        exitProcess(exitCode_init)
    }


    // Wrong number of arguments //
    if (args.size >= 2) {
        printUsage()
        exitProcess(exitCode_wrong_number_of_args)
    }

}
