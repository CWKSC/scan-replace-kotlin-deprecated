# ScanReplace.kt

A tool for scan and replace file content

## Usage

```
java -jar ScanReplace-<version>.jar <filePathToKeyFilePath> <keyValueFilePath>
```

Example:

```
java -jar ScanReplace-1.0.0.jar filePathToKey.txt keyValue.txt
```

## Get Started

Download latest version on [Release](https://github.com/CWKSC/ScanReplace.kt/releases)

Create `filePathToKey.txt`

```
test.txt = key1, key2
```

Create `keyValue.txt`

```
key1 = [it is key 1]
key2 = [key2 here]
```

Create `test.txt`

```
key1
meow 42
key2
```

Run script

```
java -jar ScanReplace-<version>.jar filePathToKey.txt keyValue.txt
```

It will generate a output folder `output_ScanReplace`, contain one file `test.txt`

Content of `output_ScanReplace/test.txt` will be

```
[it is key 1]
meow 42
[key2 here]
```

## Note

Format of `filePathToKey.txt` is

```
<path> = <key1>, <key2>, ...
<path> = <key1>, <key2>, ...
...
```

Format of `keyValue.txt` is

```
<key> = <value>
<key> = <value>
...
```

White space ` `  in left and right will be trim

___

`<path>` can refer under directory

e.g. `dir1/test.txt` 

___

Only the key declare in `filePathToKey.txt` to corresponding file will be scan and replace

For example

```
// filePathToKey.txt
test.txt = key1

// keyValue.txt
key1 = [it is key1]
key2 = [it is key2]

// test.txt
key1
key2
```

`key2` in `test.txt` will not be replace, because it not declare in `filePathToKey.txt` to corresponding file
