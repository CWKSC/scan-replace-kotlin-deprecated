import kotlinx.serialization.encodeToString
import java.io.File

const val default_file_key = """{
    "file_path": ["key"]
}"""

const val default_key_value = """{
    "key": "value"
}"""

const val format = "%-50s %s"

fun createFileIfNotExistsByDefault(filePath: String, default: String = default_file_key) {
    val file = File(filePath)
    if(file.exists()) {
        println(format.format(file, "already exists"))
        return
    }
    file.writeText(default)
    println(format.format(file, "created"))
}

fun initFile() {

    if (Resource.question_mode) {
        println("Question mode ON")
        println()
    }

    if (!Resource.configFolder.exists()) {
        Resource.configFolder.mkdir()
        println(format.format(Resource.configFolder, "created"))
    } else {
        println(format.format(Resource.configFolder, "already exists"))
    }
    println()

    createFileIfNotExistsByDefault("${Resource.configFolder}/file_key.json", default_file_key)
    createFileIfNotExistsByDefault("${Resource.configFolder}/key_value.json", default_key_value)
    createFileIfNotExistsByDefault(
        "${Resource.configFolder}/scan_replace_config.json",
        jsonPrint.encodeToString(Config())
    )
    println()
}


