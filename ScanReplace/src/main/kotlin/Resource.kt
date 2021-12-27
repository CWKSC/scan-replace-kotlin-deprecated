import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileNotFoundException
import kotlin.system.exitProcess

object Resource {
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

        println()

        Resource.config = Json.decodeFromString(File("scan_replace_config.json").readText())
        println("[Config]")
        println(jsonPrint.encodeToString(Resource.config))

        Resource.fileToKey = Json.decodeFromString(File(Resource.config.file_key).readText())
        println()
        println("[FileToKey]")
        println(jsonPrint.encodeToString(Resource.fileToKey))

        Resource.keyValue = Json.decodeFromString(File(Resource.config.key_value).readText())
        println()
        println("[keyValue]")
        println(jsonPrint.encodeToString(Resource.keyValue))

    } catch (e: FileNotFoundException) {

        println()
        println("File not found: ${e.message}")
        printUsage()
        exitProcess(exitCode_file_not_found)

    }

}
