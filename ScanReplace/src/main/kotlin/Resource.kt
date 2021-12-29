import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileNotFoundException
import kotlin.system.exitProcess

object Resource {
    // Args flag value
    var configFolderPath =  ".scan-replace"
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
    
        if(Resource.question_mode){
            println("Question mode ON")
            println()
        }

        if(!Resource.configFolder.isDirectory()){
            println("[Error] ${Resource.configFolderPath} not a directory (--config <configFolderPath>) ")
            println()
            exitProcess(exitCode_configFolderPath_not_a_directory)
        }

        Resource.config = Json.decodeFromString(File(Resource.configFolder.name + "/scan_replace_config.json").readText())
        println("[Config]")
        println(jsonPrint.encodeToString(Resource.config))
        println()

        Resource.fileToKey = Json.decodeFromString(File(Resource.configFolder.name + Resource.config.file_key).readText())
        println("[FileToKey]")
        println(jsonPrint.encodeToString(Resource.fileToKey))
        println()

        Resource.keyValue = Json.decodeFromString(File(Resource.configFolder.name + Resource.config.key_value).readText())
        println("[keyValue]")
        println(jsonPrint.encodeToString(Resource.keyValue))
        println()

    } catch (e: FileNotFoundException) {

        println("File not found: ${e.message}")
        printUsage()
        exitProcess(exitCode_file_not_found)

    }



}
