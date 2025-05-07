# the minimum version of CMake.
cmake_minimum_required(VERSION 3.5.1)
project(CurlDemo)

set(NATIVERENDER_ROOT_PATH ${CMAKE_CURRENT_SOURCE_DIR})

include_directories(${NATIVERENDER_ROOT_PATH}
                    ${NATIVERENDER_ROOT_PATH}/wrapper/include)

# 添加 -fPIC 编译选项
set(CMAKE_POSITION_INDEPENDENT_CODE ON)

find_library(
    hilog-lib
    hilog_ndk.z
    libz.so
)

add_library(pbcurlwrapper SHARED
    wrapper/src/curl_wrapper.cpp
    wrapper/include/curl_wrapper.h
    wrapper/src/log/curl_log.cpp
    wrapper/src/log/curl_log.h
    wrapper/src/utils/curl_utils.cpp
    wrapper/src/utils/curl_utils.h
    )
# 下方的 libcurl.a 来自tvk工程
target_link_libraries(pbcurlwrapper PUBLIC
        ${CMAKE_CURRENT_SOURCE_DIR}/../../../libs/arm64-v8a/libopenssl.so
        libhilog_ndk.z.so
        libz.so
        "-Wl,-Bsymbolic,--gc-sections,--whole-archive"
        ${CMAKE_CURRENT_SOURCE_DIR}/../../../libs/arm64-v8a/libcurl.a
        "-Wl,--no-whole-archive"
)