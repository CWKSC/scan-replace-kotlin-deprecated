import kotlin.system.exitProcess

fun handleArgs(args: Array<String>) {

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
