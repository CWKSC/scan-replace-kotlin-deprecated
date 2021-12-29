import java.io.File
import kotlin.system.exitProcess

fun scanReplace() {

    println("[Scan - Replace]")
    println()

    Resource.fileToKey.forEach { (_filePath, keyList) ->

        val filePath = Resource.config.file_prefix + _filePath + Resource.config.file_suffix

        println("[$filePath] Start scan - replace")
        println()

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
            val replaceValue = Resource.config.value_prefix + value + Resource.config.value_suffix
            content = content.replace(replaceKey, replaceValue)
            println("[$filePath] $replaceKey -> $replaceValue")
        }

        // Output file //
        val outputFile = File("${Resource.config.output_prefix}$filePath${Resource.config.output_suffix}")
        println("[$filePath] -> $outputFile")
        outputFile.parentFile?.mkdirs()
        outputFile.writeText(content)
    }

    println("[Done]")
    println()
    exitProcess(exitCode_success)

}
