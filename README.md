English | [简体中文](./README-zh_CN.md)

# KuiklyBase-component

# Introduction
Tencent Video is deeply involved in the Oteam collaboration project, jointly launching the KuiklyBase technical solution. Its Component module encapsulates commonly used reusable cross-platform components, currently including resource management, cross-language communication, network requests, and image loading modules. This helps developers reduce multi-platform adaptation costs. Future iterations will continuously expand the component library.

# Feature Overview
| Component | Description | Supported Platforms | Release Status | Documentation | 
|-------|-------|-------|-------|-------|
| knoi | Provides mutual invocation capability between Kotlin Native and ArkTS without writing C/C++ bridging code | HarmonyOS | Open sourced | [Details](knoi/README.md) |
| KmmResource | Cross-platform native resource management solution, offering developers an Android R-like resource access experience | Android / iOS / HarmonyOS | Open sourced | [Details](KmmResource/README.md) |
| Network | Delivers cross-platform network request capabilities without requiring attention to platform differences | Android / iOS / HarmonyOS | June release | / |
| Image | Image display capability based on Compose, supporting network image loading | Android / iOS / HarmonyOS | September release | / |

# License
KuiklyBase-component is released under the Apache 2.0 License. For details, see: [License](License.txt)