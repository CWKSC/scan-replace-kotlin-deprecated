# ScanReplace.kt

A tool for scan and replace file content

Instead of global search and replace, it can specify replace scope

## Usage

```
java -jar ScanReplace-<version>.jar [init]
```

Example:

```
java -jar ScanReplace-1.2.0.jar
java -jar ScanReplace-1.2.0.jar init
```

## Get Started

Download latest version on [Release](https://github.com/CWKSC/ScanReplace.kt/releases), type

```
java -jar ScanReplace-<version>.jar init
```

Following message show in screen

```
file_key.json                  created
key_value.json                 created
scan_replace_config.json       created
```

Change `file_key.json` content to

```
{
    "test.txt": ["key1", "key2"]
}
```

Change `key_value.json` content to

```
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

## Note

Format of `file_key.json` is

```json
{
    "filePath": ["key1", "key2", ...],
    ...
}
```

Format of `keyValue.txt` is

```json
{
    "key": "value",
    ...
}
```

___

`filePath` in `file_key.json` can refer under directory

e.g. `dir1/dir2/test.txt` 

___

Only the key declare in `file_key.json` to corresponding file will be scan and replace

For example

```
{
    "test.txt": ["key1", "key2"]
}
```

```
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

___

`output` in `scan-replace-config.json` set output folder

All output file with `output` as parent directory

For example:

```
{
    "file_key": "file_key.json",
    "key_value": "key_value.json",
    "output": "output"
}
```

Leave it blank will replace file in-place

___

