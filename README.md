# ScanReplace.kt

A tool for scan and replace file content

Instead of global search and replace, it can specify replace scope

## Usage

```
java -jar ScanReplace-<version>.jar [init]
```

Example:

```
java -jar ScanReplace-0.1.0.jar
java -jar ScanReplace-0.1.0.jar init
```

## Get Started

Download latest version on [Release](https://github.com/CWKSC/ScanReplace.kt/releases), type

```
java -jar ScanReplace-<version>.jar init
```

Following message will show in screen

```
file_key.json                  created
key_value.json                 created
scan_replace_config.json       created
```

Change `file_key.json` content to

```json
{
    "test.txt": ["key1", "key2"]
}
```

Change `key_value.json` content to

```json
{
    "key1": "[It is key1]",
    "key2": "[It is key2]"
}
```

Create `test.txt`

```
key1
key2
```

Type

```
java -jar ScanReplace-<version>.jar
```

Content of `test.txt` will change to be

```
[It is key1]
[It is key2]
```

## Document

### `file_key.json`

```json
{
    "filePath": ["key1", "key2"],
    "...": ["..."]
}
```

#### filePath

`filePath` in `file_key.json` can refer under directory

e.g. `dir1/dir2/test.txt` 

#### key

Only the key declare in `file_key.json` to corresponding file will be scan and replace

```json
{
    "test.txt": ["key1", "key2"]
}
```

```json
{
    "key1": "[It is key1]",
    "key2": "[It is key2]"
}
```

```
key1
key2
```

`key2` in `test.txt` will not be replace, because `key2` not declare in `file_key.json` to corresponding file

You can check out a example in `example/key2-not-replace/`

### `key_value.json`

```json
{
    "key": "value",
    "...": "..."
}
```

### `scan-replace-config.json`

```json
{
    "file_key": "file_key.json",
    "key_value": "key_value.json",
    "output": "",
    "file_prefix": "",
    "file_suffix": "",
    "key_prefix": "",
    "key_suffix": ""
}
```

#### file_key

Define a path of file_key json, default is `file_key.json`

#### key_value

Define a path of key_value json, default is `key_value.json`

#### output

Define a path of output folder, default is empty `""`

```json
{
    "..." : "...",
    "output": "output/",
    "..." : "..."
}
```

Leave it blank `""` will replace file in-place

You can check out a example in `example/output-folder/`

#### file_prefix, file_suffix

It is a global setting of file path with prefix and suffix

A common setting is prefix with some folder, to change the root of reference in `file_key.json`

`file_suffix` is uncommon to use but provided

```json
{
    "..." : "...",
    "file_prefix": "directory/",
    "file_suffix": ""
}
```

All `filePath` in `file_key.json` will append prefix and suffix

```json
{
    "filePath1": ["key1", "key2"],
    "filePath2": ["key1", "key2"]
}
```

equivalent to

```json
{
    "directory/filePath1": ["key1", "key2"],
    "directory/filePath2": ["key1", "key2"]
}
```

#### key_prefix key_suffix

It is a global setting of searching key with prefix and suffix

A common setting is prefix with `<` and suffix with `>` (or `{{` and `}}` )

```json
{
    "..." : "...",
    "key_prefix": "<",
    "key_suffix": ">"
}
```

All key in `key_value.json` will append prefix and suffix

```json
{
    "key1" : "value",
    "key2" : "value"
}
```

equivalent to

```json
{
    "<key1>" : "value",
    "<key2>" : "value"
}
```

### Build jar

```
.\gradlew jar
```

