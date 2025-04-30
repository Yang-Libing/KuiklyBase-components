### ChangeLog

##### 0.3.0

- feature: 支持 Kotlin 2.0.21

##### 0.2.38

- bugfix: 修复 projectCacheDir 版本兼容问题

##### 0.2.37

- bugfix: 修复 gradle 多任务执行读取 config.ini 失败的 bug

##### 0.2.36

- opt: 重构 KSP 参数注入逻辑，兼容 Kotlin 2.0

##### 0.2.35

- bugfix: 回滚 2.0.20 KSP 兼容升级

##### 0.2.30

- opt：优化 jsValue Callback 的释放性能

##### 0.2.29

- opt：支持 qqlive Android java 版本
- 
##### 0.2.28

- opt：fun dispose增加额外逻辑，主动释放

##### 0.2.27

- opt：JSValue#to 方法增加 undefined 判断

##### 0.2.26

- opt：JSValue#handle 防止多线程调用，在 debug 环境主动抛异常

##### 0.2.25 

- bugfix：修复 ArrayBuffer 不支持 null 或 undefined 的问题

##### 0.2.24

- opt：新增 isDebugBinary 判断，减少字符串拼接

##### 0.2.23

- feature: 新增 JavaScript Map 类型支持

##### 0.2.22

- opt: 新增 JSCallback 泄露排查手段

##### 0.2.20

- opt: 删除 stdlib-ext 依赖

##### 0.2.19

- opt: 删除无用频繁日志

##### 0.2.18

- feature：新增 android application 统一生成 initialize 方法
- tips:从这个版本开始 Android 解决类冲突问题，才可以真正多模块使用
- tips:如基于旧版本 knoi 发布的组件需要重新发布

##### 0.2.17

- opt：新增 stdlib-ext 依赖，自动注册默认 UnhandledExceptionHook

##### 0.2.15

- feature：新增子线程调用子线程负载均衡

##### 0.2.14

- feature：修复空数组回传kotlin导致的 crash

##### 0.2.13

- feature：新增 Plugin 支持全平台 ksp

##### 0.2.12

- feature：新增 knoi metric 功能，支持 Trace 能力

##### 0.2.11

- bugfix：修复 wrap createJSFunction 无法重复调用的问题

##### 0.2.10

- opt: 优化 knoi 性能，对齐 napi
- feature：新增 Android & iOS 服务发现能力

##### 0.2.4

- opt: 日志可重定向，完善日志辅助排查混淆问题

##### 0.2.2

- bugfix: 支持超长 Service 名字
- bugfix：修复主线程调用 Worker consumer ANR 问题

##### 0.2.1

- opt: knoi 添加多个平台空实现，防止编译报错

##### 0.2.0

- opt: 优化 JSFunction 内存泄露问题
- bugfix: 修复 主线程调用 Worker consumer ANR 问题

##### 0.1.9

- bugfix: napi_bridge 链接 c++ shared

##### 0.1.8

- opt: 修复内存泄露问题

##### 0.1.7

- opt: 跨线程调用修改为高优先级队尾方式调用，提高响应速度
- bugfix：修复单例逻辑错误

##### 0.1.2

- opt: 补充日志，方便排查问题

##### 0.1.1

- feature: 重构 自动注册为 ksp 实现

##### 0.0.56

- bugfix: 修复闭包 undefined 类型报错

##### 0.0.54

- opt: 新增 混淆 keep 文件

##### 0.0.53

- bugfix: JSValue 引用错误回收问题

##### 0.0.49

- bugfix: 修复 GC 线程可能死锁的问题

##### 0.0.48

- feature: 支持自定义对象类型导出

##### 0.0.47

- opt: 优化 napi_bridge 为静态链接库

##### 0.0.46

- feature: TypeScript 接口生成支持可空类型
- bugfix: 修复 return undefined 时，字符串错乱问题

##### 0.0.45

- opt: 优化 Service Proxy 加锁逻辑，防止死锁

##### 0.0.44

- bugfix: 修复闭包 未 import JSValue 问题

##### 0.0.43

- bugfix: 修复路径问题

##### 0.0.42

- feature: 剥离对 libkn.so 强依赖，不再链接 libkn.so

##### 0.0.41

- feature: 支持以对象方式注入 ArkTS 服务，详见 registerSingleServiceProvider 

##### 0.0.40

- bugfix: 修复本地依赖偶现无法自动注册问题

##### 0.0.39

- opt: 优化 MainHandler#runMainThread 实现

##### 0.0.38

- opt: ArrayBuffer#toByteArray readBytes 优化 memcpy

##### 0.0.37

- opt: 主线程判断修改为 pid == tid 方式

##### 0.0.36

- feature: 支持常量导出

##### 0.0.35

- feature: 移除 export KNOI, 修改为 ksp 生成 CName

##### 0.0.34

- bugfix: 修复 Map 中 undefined 类型闪退问题

##### 0.0.33

- feature: 支持 commonMain 中使用 KNOI 注解

##### 0.0.32

- feature: ServiceProvider 支持带默认值参数

##### 0.0.31

- feature: 支持 Kotlin 调用 Kotlin 实现的 ServiceProvider
- bugfix: 修复回调类型自动包装能力

##### 0.0.30

- feature: 支持回调类型自动包装，即可直接定义回调，不再要求以 Array<JSValue> 作为入参
- feature: 支持 回调类型中使用 TypeAlias

##### 0.0.29

- feature: 支持 JS 异常处理
- feature: 支持子线程调用 Service & JSValue