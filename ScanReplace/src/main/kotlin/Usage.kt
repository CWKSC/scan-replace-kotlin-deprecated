
const val usage = """
Usage: java -jar ScanReplace-<version>.jar [init]

java -jar ScanReplace-<version>.jar
  - Load config file under current directory
    - scan_replace_config.json

java -jar ScanReplace-<version>.jar init
  - Initialize template config file
    - file_key.json
    - key_value.json
    - scan_replace_config.json

"""

fun printUsage() = print(usage)