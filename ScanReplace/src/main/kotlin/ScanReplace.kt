import java.io.File
import kotlin.system.exitProcess

fun scanReplace() {

    println()
    println("[Scan - Replace]")
    Resource.fileToKey.forEach { (_filePath, keyList) ->

        val filePath = Resource.config.file_prefix + _filePath + Resource.config.file_suffix

        println()
        println("[$filePath] Start scan - replace")

        // Check if file exists //
        val file = File(filePath)
        if (!file.exists()) {
            println("[Error] File not found: $filePath")
            return@forEach
        }

        // Read file and replace //
        var content = file.readText()
        keyList.forEach { key ->
            val value = Resource.keyValue[key]
            if (value == null) {
                println("[$filePath] Key not found: $key")
                return
            }
            val replaceKey = Resource.config.key_prefix + key + Resource.config.key_suffix
            content = content.replace(replaceKey, value)
            println("[$filePath] $replaceKey -> $value")
        }

        // Output file //
        val outputFile = File("${Resource.config.output}$filePath")
        println("[$filePath] -> $outputFile")
        outputFile.parentFile?.mkdirs()
        outputFile.writeText(content)
    }

    println()
    println("[Done]")
    exitProcess(exitCode_success)

}
