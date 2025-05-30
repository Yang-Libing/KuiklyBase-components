[English](./README.md) | 简体中文

# KuiklyBase-component

# 简介
腾讯视频深度参与 Oteam 协同项目，共同推出 KuiklyBase 技术方案。其 Component 模块封装了常用的可复用跨端组件，目前包含资源管理、跨语言通信、网络请求、图片加载四个模块，帮助开发者降低多平台适配成本。未来将持续迭代扩展组件库。

# 功能概述
| 组件名   | 简介   | 支持平台 | 开源时间  | 使用文档 | 
|-------|-------|-------|-------|-------|
| knoi | 提供 Kotlin Native 与 ArkTS 的互相调用能力，无需编写 C/C++ 桥接代码 | HarmonyOS | 已开源 | [查看详情](knoi/README.md) |
| KmmResource | 跨平台原生资源管理方案，为开发者带来类 Android R 的资源调用体验。 | Android / iOS / HarmonyOS | 已开源 | [查看详情](KmmResource/README.md) |
| Network | 	提供跨平台的网络请求能力，无需关注平台差异。 | Android / iOS / HarmonyOS | 6月发布 | / |
| Image | 基于 Compose 的图片展示能力，支持加载网络图片。 | Android / iOS / HarmonyOS | 9月发布 | / |

# License
KuiklyBase-component 基于 Apache 2.0 协议发布，详见：[License](License.txt)