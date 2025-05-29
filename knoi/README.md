# KNOI

[中文文档](./README-zh.md)

KNOI (Kotlin Native Ohos Interaction)

### Introduction

Provides mutual calling capability between Kotlin Native & ArkTS without writing C/C++ bridging code.

### Important Notes

1. Since definitions from knoi (Kotlin) are required, you need to pre-configure the Kotlin Native shared library name (default is libkn.so. If manual configuration is needed, call setup("custom_so_name"))!!!

2. knoi uses dlopen to load libkn.so, which will also load the so files linked by libkn.so. However, if the passively loaded so files also provide napi interfaces (i.e., call napi_module_register), it may cause "not callable" errors when used in ArkTS. The solution is to import the module that the so file belongs to before calling knoi.

3. To achieve automatic registration capability. Currently, the sharedLib configuration is used to identify whether it's the final shared library, thereby generating the complete registration table. If you encounter "initBridge symbol not found" errors, check if other modules have configured sharedLib.

### Integration

- Kotlin Integration
```kotlin
   // Add ksp and knoi plugins in root build.gradle.kts
   plugins {
     id("com.google.devtools.ksp") version "2.0.21-1.0.28" apply false 
     // For latest knoi version, see changelog
     id("com.tencent.kuiklybase.knoi.plugin") version("0.0.4") apply false
   }

  // Add in the gradle file of the module where you want to use knoi
  plugins {
    id("com.tencent.kuiklybase.knoi.plugin")
  }
  
  // To configure TypeScript file output path
  knoi {
      // Default generation path is under current module's build/ts-api/
      tsGenDir = projectDir.absolutePath + "/ts-api/"
  }
  // For reference, see example/ integration
```

- ArkTS Integration

```bash
// Add dependency (in entry/oh-package.json5, not root oh-package.json5)
// ArkTS side has slow version caching, if new APIs cannot be called, directly use latest version
  "dependencies": {
    "@kuiklybase/knoi":"0.0.4"
  }

// Initialization
import { setup, init } from "knoi"
setup("libkn.so", BuildProfile.DEBUG) // KMP generated dynamic library name
init()

// For reference, see ohosApp/entry integration
```

- Obfuscation Configuration

If ArkTS code obfuscation is enabled on HarmonyOS side, the following scenarios require keeping certain code:

1. Need to keep non-exported ArkTS side ServiceProvider
2. Need to keep non-exported ArkTS side JSValue accessed properties and methods
3. Add -keep-global-name in obfuscation-rules.txt

```JavaScript
-keep-global-name
knoi
```

### Usage Guide

[Type Conversion](./docs/types.md)

[Service Calling](./docs/service.md)

[Service Discovery](./docs/serviceDiscover-en.md)

[Method Calling](./docs/function-en.md)

[JSValue](./docs/jsvalue-en.md)

[ArrayBuffer](./docs/arraybuffer-en.md)

[Exception Handling](./docs/execption-en.md)

[Type Export](./docs/declare-en.md)

[MainHandler](./docs/handler-en.md)

[Performance Optimization](./docs/pref-en.md)

### Benchmark

|API|Call Count|Node-API (ms)|KNOI(ms)
|--|--|--|--|
bool (*)()|10000|0.0031|0.0030(cached Service) 0.0036(uncached Service)
string (*)(string)|10000|0.0057|0.0034(size 10) 0.015(size1000)
void (*)( std::function )|10000|0.0176|0.0072(one-way) 0.010(two-way)
unit|1000||0.0025
number|1000||0.0030
ArrayBuffer 2.4M|10||0.4943
ArrayBuffer 4.5K|10||0.0061

*node api data reference from Huawei [aki gitee](https://gitee.com/openharmony-sig/aki/tree/master)*

### Known Issues

1. Cannot use Array<JSValue?> as ArkTS return type, please use JSValue and check with JSValue.isArrayType and convert with JSValue.toList.

2. When converting Kotlin closures to ArkTS Function using ktValueToJSValue, the closure parameters need to be Array<JSValue?> and return value should be JSValue?. Example:

```Kotlin
// Recommended to explicitly declare closure parameters
ktValueToJSValue(getEnv(), { it: Array<JSValue?> ->
    return@ktValueToJSValue ktValueToJSValue(getEnv(), null, String::class)
}, Function::class)
```

### ChangeLog

[Version History](./docs/changelog.md)