import java.io.File

var global_filePathToKey: Map<String, List<String>>? = null
var global_keyToFileName: Map<String, List<String>>? = null

fun createByFileNameToKey(vararg filePathToKey: Pair<String, List<String>>) {
    global_filePathToKey = filePathToKey.toMap()
    val keyToFileName = mutableMapOf<String, MutableList<String>>()
    filePathToKey.forEach { (key, value) ->
        value.forEach {
            keyToFileName.computeIfAbsent(it) { mutableListOf() }.add(key)
        }
    }
    global_keyToFileName = keyToFileName
}

@Suppress("unused")
fun createByKeyToFileName(vararg keyToFileName: Pair<String, List<String>>) {
    global_keyToFileName = keyToFileName.toMap()
    val filePathToKey = mutableMapOf<String, MutableList<String>>()
    keyToFileName.forEach { (key, value) ->
        value.forEach {
            filePathToKey.computeIfAbsent(it) { mutableListOf() }.add(key)
        }
    }
    global_filePathToKey = filePathToKey
}

fun main(args: Array<String>) {

    // Display help message //
    if (args.size == 1 && (args[0] == "--help" || args[0] == "help" || args[0] == "-?" || args[0] == "?")) {
        println("Usage: java -jar scanReplace.jar <filePathToKeyFilePath> <keyValueFilePath>")
        return
    }

    // Wrong arguments //
    if (args.size != 2) {
        println("Usage: java -jar scanReplace.jar <filePathToKeyFilePath> <keyValueFilePath>")
        return
    }

    println()

    // Check if filePathToKeyFilePath exists //
    val filePathToKeyFilePath = args[0]
    val filePathToKeyFile = File(filePathToKeyFilePath)
    if (!filePathToKeyFile.exists()) {
        println("filePathToKeyFile not found: $filePathToKeyFilePath")
        return
    }
    println("filePathToKeyFile absolute path: ${filePathToKeyFile.absoluteFile}")

    println()

    // Check if keyValueFilePath exists //
    val keyValueFilePath = args[1]
    val keyValueFile = File(keyValueFilePath)
    if (!keyValueFile.exists()) {
        println("keyValueFile not found: $keyValueFilePath")
        return
    }
    println("keyValueFile absolute path: ${filePathToKeyFile.absoluteFile}")

    // Read fileNameToKey //
    println()
    println("[Read filePathToKey file]")
    val filePathToKey = mutableListOf<Pair<String, List<String>>>()
    filePathToKeyFile.forEachLine { line ->
        if(line.trim() == "") return@forEachLine
        val (filePath, keyListString) = line.split("=").map { it.trim() }
        val keyList = keyListString.split(",").map { key -> key.trim() }
        filePathToKey.add(filePath to keyList)
        println("filePathToKey: $filePath -> $keyList")
    }
    createByFileNameToKey(*filePathToKey.toTypedArray())

    // Read keyValue //
    println()
    println("[Read keyValue file]")
    val keyValue = mutableMapOf<String, String>()
    keyValueFile.forEachLine { line ->
        if(line.trim() == "") return@forEachLine
        val (key, value) = line.split("=").map { it.trim() }
        keyValue[key] = value
        println("keyValue: $key -> $value")
    }

    // Scan - Replace //
    println()
    println("[Scan - Replace]")
    filePathToKey.forEach { (filePath, keyList) ->

        println()
        println("[$filePath] start scan - replace")

        // Check if file exists //
        val file = File(filePath)
        if(!file.exists()) {
            println("File not found: $filePath")
            return@forEach
        }

        // Read file and replace //
        var content = file.readText()
        keyList.forEach { key ->
            val value = keyValue[key]
            if (value == null) {
                println("[$filePath] Key not found: $key")
                return
            }
            content = content.replace(key, value)
            println("[$filePath] $key -> $value")
        }

        // Output file //
        val outputFile = File("output_ScanReplace/$filePath")
        println("[$filePath] -> $outputFile")
        outputFile.parentFile.mkdirs()
        outputFile.writeText(content)
    }

    println()
    println("[Done]")

}