KmmResource (Kotlin Multiplatform Mobile Resource)

### 简介

KMM 鸿蒙测桥接接口支持插件

### Ohos工程(其他Platform 跳过)

#### project oh-package.json5添加

安装

```bash
ohpm install @kuiklybase/resource_core
```

```
"dependencies": {
    "@kuiklybase/knoi": "0.0.x"
    "@kuiklybase/resource_core": "0.0.x"
 }
```

#### project 最终build.gradle中添加

```
linkerOpts("-L${projectDir}/libs/", "-lresource_compose")
```

全路径参考

```
kotlin {
    ohosArm64 {
        binaries.sharedLib {
             linkerOpts("-L${projectDir}/libs/", "-lresource_compose")
        }
    }
}
```

#### 拷贝鸿蒙so到kmm中进行链接

在鸿蒙的 oh_modules/@qqlive/resource_compose/libs/arm64-v8a 中有一个 libresource_compose.so 的文件，拷贝到上一步配置的libs目录里 编译的时候进行链接

#### 启动任务手动初始化

```
import { initResourceCompose } from 'resource_compose';

initResourceCompose(this.context)
```
