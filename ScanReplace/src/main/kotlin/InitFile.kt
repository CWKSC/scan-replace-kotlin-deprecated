import kotlinx.serialization.encodeToString
import java.io.File

const val default_file_key = """{
    "file_path": ["key"]
}"""

const val default_key_value = """{
    "key": "value"
}"""

fun createFileIfNotExistsByDefault(filePath: String, default: String = default_file_key) {
    val format = "%-30s %s"
    val file = File(filePath)
    if (!file.exists()) {
        file.writeText(default)
        println(format.format(file, "created"))
    } else {
        println(format.format(file, "already exists"))
    }
}

fun initFile() {
    createFileIfNotExistsByDefault("file_key.json", default_file_key)
    createFileIfNotExistsByDefault("key_value.json", default_key_value)
    createFileIfNotExistsByDefault("scan_replace_config.json", jsonPrint.encodeToString(Config()))
}


