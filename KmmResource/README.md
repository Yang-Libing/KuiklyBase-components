# KMM Multiplatform Resource Management Solution

[中文README](./README中文.md)

This project implements a cross-platform native resource management solution using **Kotlin Multiplatform (KMP)** technology, supporting Android, iOS, and HarmonyOS platforms. It generates type-safe resource access classes (e.g., `MR` class) during compilation, providing compile-time validation and unified resource management across platforms through standardized encapsulation mechanisms (Kotlin/Native klib & Kotlin/JVM aar).

## Core Concept
**What does the resource manager actually manage?**  
The KMM resource manager fundamentally exposes platform-specific resource identifiers:
- Android: Resource IDs from R class
- iOS: Bundle+name combinations
- HarmonyOS: Resource names

## Version Matrix

| Component | Stable | Beta |
|-----------|--------|------|
| com.tencent.kuiklybase.resource.generator | 0.0.1 | 0.0.1 |
| resource-core | 0.0.1 | 0.0.1 |
| resource-compose | 0.0.1 | 0.0.1 |
| @kuiklybase/resource_compose | 0.0.1 | 0.0.1 |

## Gradle Integration

### KMM Project Setup

**Root `build.gradle.kts`:**
```kotlin
plugins {
    id("com.tencent.kuiklybase.resource.generator").version("x.x.x-version").apply(false)
}
```

**Module `build.gradle.kts`:**
```kotlin
plugins {
    id("com.tencent.kuiklybase.resource.generator")
}

commonMain.dependencies {
    implementation("com.tencent.kuiklybase:resource-core:x.x.x-version")
    implementation("com.tencent.kuiklybase:resource-compose:x.x.x-version")
}

multiplatformResources {
    multiplatformResourcesPackage = "net.novate.kotlin" // Required
    multiplatformResourcesPrefix = "ohshow"             // Required
    multiplatformResourcesClassName = "MR"              // Optional (default: MR)
    iosBaseLocalizationRegion = "en"                    // Optional (default: "en") 
    multiplatformResourcesSourceSet = "commonMain"      // Optional (default: "commonMain")
    multiplatformResourcesVisibility = MRVisibility.Internal // Optional
}
```

| Parameter | Description |
|-----------|-------------|
| `multiplatformResourcesPackage` | Package name for generated MR class |
| `multiplatformResourcesPrefix` | Unique resource prefix to avoid conflicts |
| `multiplatformResourcesClassName` | Generated class name (default: `MR`) |
| `iosBaseLocalizationRegion` | Base localization region for iOS bundles |
| `multiplatformResourcesSourceSet` | Target source set for resource processing |
| `multiplatformResourcesVisibility` | Visibility modifier for generated classes |

### HarmonyOS Configuration

**1. Add dependencies in `oh-package.json5`:**
```json
"dependencies": {
    "@kuiklybase/knoi": "0.0.x",
    "@kuiklybase/resource_compose": "0.0.x"
}
```

**2. Configure linker options:**
```kotlin
kotlin {
    ohosArm64 {
        binaries.sharedLib {
            linkerOpts("-L${projectDir}/libs/", "-lresource_compose")
        }
    }
}
```

**3. Copy HarmonyOS SO library:**  
Copy `libresource_compose.so` from `oh_modules/@qqlive/resource_compose/libs/arm64-v8a` to your project's `libs` directory.

**4. Initialize resource compose:**
```typescript
import { initResourceCompose } from 'resource_compose';
initResourceCompose(this.context)
```

### iOS Configuration

**1. Set resource bundle path in `gradle.properties`:**
```
resource.targetCopyDir=../Assets/
```

**2. Configure podspec:**
```ruby
spec.resource_bundles = { spec.name => ['Assets/tmm-resources-apple/*.{xcassets,bundle}'] }
```

**3. Initialize pod name:**
```kotlin
com.tencent.tmm.kmmresource.resource.utils.podName = "YourPodName" 
com.tencent.tmm.kmmresource.resource.utils.isDebug = true
```

## Resource Organization

```
commonMain
└── resources
    └── MR
        ├── base
        │   ├── strings.xml
        │   └── plurals.xml
        ├── colors
        │   └── colors.xml
        ├── images
        │   ├── home_back@1x.webp
        │   └── home_back@2x.webp
        ├── fonts
        │   └── qqlive_font.otf
        ├── files
        │   └── file_name.txt
        └── assets
            └── file_name.txt
```

## Usage Examples

### String Resources
**XML Definition:**
```xml
<resources>
    <string name="hello_world">Hello World</string>
    <string name="my_string_formatted">Formatted: %s</string>
</resources>
```

**Code Access:**
```kotlin
// Common
val basicString = MR.strings.hello_world
val formattedString = MR.strings.my_string_formatted

// Compose
Text(stringResource(MR.strings.hello_world))
Text(stringResource(MR.strings.my_string_formatted, "value"))
```

### Image Resources
**Asset Placement:**
```
commonMain/resources/MR/images/home_black_18@1x.webp
commonMain/resources/MR/images/home_black_18@2x.webp
```

**Code Access:**
```kotlin
// Common
val imageRef = MR.images.home_black_18

// Compose
Image(painterResource(MR.images.home_black_18), "icon")
```

### Color Resources
**XML Definition:**
```xml
<resources>
    <color name="primary">#B02743FF</color>
</resources>
```

**Code Access:**
```kotlin
// Common
val colorRef = MR.colors.primary

// Compose
Box(modifier = Modifier.background(colorResource(MR.colors.primary)))
```

## Implementation Architecture

### MR Class Generation
```kotlin
// COMMON
expect object MR {
    object strings : ResourceContainer<StringResource> {
        val hello_world: StringResource
    }
}

// ANDROID
actual object MR {
    actual object strings : ResourceContainer<StringResource> {
        actual val hello_world = StringResource(R.string.hello_world)
    }
}

// HARMONYOS
actual object MR {
    actual object strings : ResourceContainer<StringResource> {
        actual val hello_world = StringResource("module_hello_world")
    }
}
```

### Platform-Specific Implementations
| Resource Type | Android | HarmonyOS | iOS |
|---------------|---------|-----------|-----|
| `StringResource` | R.id | resName | bundle+resourceId |
| `ColorResource` | R.color | resName | bundle+name |
| `ImageResource` | R.drawable | resName | bundle+assetName |
| `FontResource` | R.font | path | bundle+fontName |

## HarmonyOS Bridge Implementation

**Service Interface:**
```kotlin
@ServiceConsumer
interface OhosResourceService {
    fun getString(resName: String): String?
    fun getString(resName: String, vararg args: Any): String?
    fun getPlural(resName: String, args: Number): String?
    fun getImage(resName: String): ArrayBuffer?
    fun getColor(resName: String): Int?
    fun getImageBase64(resName: String): String?
    fun getFile(resName: String): ArrayBuffer?
}
```

**Usage Example:**
```kotlin
object OhosKmmResourceManager {
    fun getString(resName: String): String? {
        return getOhosResourceServiceApi().getString(resName)
    }
    
    fun getColor(resName: String): Int? {
        return getOhosResourceServiceApi().getColor(resName)
    }
}
```

## Troubleshooting

**Sync Error:**
```
Caused by: java.lang.RuntimeException: Configure tmm-resources multiplatformResourcesPackage
```
**Solution:** Ensure required parameters are set:
```kotlin
multiplatformResources {
    multiplatformResourcesPackage = "com.your.package" 
    multiplatformResourcesPrefix = "your_prefix"
}
```