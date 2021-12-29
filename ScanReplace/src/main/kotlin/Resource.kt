import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileNotFoundException
import kotlin.system.exitProcess

object Resource {

    // Args flag value //
    var configFolderPath = ".scan-replace"
    var question_mode = false

    lateinit var configFolder: File
    lateinit var config: Config

    lateinit var fileToKey: Map<String, List<String>>
    lateinit var keyValue: Map<String, String>
}

val jsonPrint = Json {
    encodeDefaults = true
    prettyPrint = true
}

fun loadResource() {

    try {

        if (!Resource.configFolder.exists()) {
            println("[Error] ${Resource.configFolderPath} not exists")
            println("Please use 'init' to create config folder")
            println()
            exitProcess(exitCode_configFolder_not_exists)
        }

        if (!Resource.configFolder.isDirectory) {
            println("[Error] ${Resource.configFolderPath} not a directory")
            println("Please use '--config <configFolderPath>' to set config folder path")
            println()
            exitProcess(exitCode_configFolder_not_a_directory)
        }

        if (Resource.question_mode) {
            println("Question mode ON")
            println()
        }

        val configFile = File(Resource.configFolder.name + "/scan_replace_config.json")
        Resource.config = Json.decodeFromString(configFile.readText())
        println("[Config]")
        println(jsonPrint.encodeToString(Resource.config))
        println()

        val fileToKeyFile = File(Resource.configFolder.name + "/" + Resource.config.file_key)
        Resource.fileToKey = Json.decodeFromString(fileToKeyFile.readText())
        println("[FileToKey]")
        println(jsonPrint.encodeToString(Resource.fileToKey))
        println()

        val keyValueFile = File(Resource.configFolder.name + "/" + Resource.config.key_value)
        Resource.keyValue = Json.decodeFromString(keyValueFile.readText())
        println("[keyValue]")
        println(jsonPrint.encodeToString(Resource.keyValue))
        println()

    } catch (e: FileNotFoundException) {

        println("File not found: ${e.message}")
        printUsage()
        exitProcess(exitCode_file_not_found)

    }


}
