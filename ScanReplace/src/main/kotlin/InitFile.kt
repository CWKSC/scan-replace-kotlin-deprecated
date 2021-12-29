import kotlinx.serialization.encodeToString
import java.io.File

const val default_file_key = """{
    "file_path": ["key"]
}"""

const val default_key_value = """{
    "key": "value"
}"""

const val format = "%-40s %s"

fun createFileIfNotExistsByDefault(filePath: String, default: String = default_file_key) {
    val file = File(filePath)
    if (!file.exists()) {
        file.writeText(default)
        println(format.format(file, "created"))
    } else {
        println(format.format(file, "already exists"))
    }
}

fun initFile() {
    println()
    if (!Resource.configFolder.exists()) {
        Resource.configFolder.mkdir()
        println(format.format(Resource.configFolder, "created"))
    } else {
        println(format.format(Resource.configFolder, "already exists"))
    }
    println()
    createFileIfNotExistsByDefault("${Resource.configFolder}/file_key.json", default_file_key)
    createFileIfNotExistsByDefault("${Resource.configFolder}/key_value.json", default_key_value)
    createFileIfNotExistsByDefault("${Resource.configFolder}/scan_replace_config.json", jsonPrint.encodeToString(Config()))
    println()
}


