# the minimum version of CMake.
cmake_minimum_required(VERSION 3.4.1)
project(SkikoBridge)

set(NATIVERENDER_ROOT_PATH ${CMAKE_CURRENT_SOURCE_DIR})

if (DEFINED PACKAGE_FIND_FILE)
    include(${PACKAGE_FIND_FILE})
endif ()

include_directories(${NATIVERENDER_ROOT_PATH}
        ${NATIVERENDER_ROOT_PATH}/include)

add_definitions(-std=c++17)

# 将 ${NATIVERENDER_ROOT_PATH}/**/*.cpp 所有的文件路径保存在 SOURCES 这个变量中
file(
        GLOB_RECURSE SOURCES
        ${NATIVERENDER_ROOT_PATH}/nativerender/*.cpp
)

find_library(
        # Sets the name of the path variable.
        EGL-lib
        # Specifies the name of the NDK library that
        # you want CMake to locate.
        EGL
)

find_library(
        # Sets the name of the path variable.
        GLES-lib
        # Specifies the name of the NDK library that
        # you want CMake to locate.
        GLESv3
)

find_library(
        # Sets the name of the path variable.
        hilog-lib
        # Specifies the name of the NDK library that
        # you want CMake to locate.
        hilog_ndk.z
)

find_library(
        # Sets the name of the path variable.
        libace-lib
        # Specifies the name of the NDK library that
        # you want CMake to locate.
        ace_ndk.z
)

find_library(
        # Sets the name of the path variable.
        libnapi-lib
        # Specifies the name of the NDK library that
        # you want CMake to locate.
        ace_napi.z
)

find_library(
        # Sets the name of the path variable.
        libuv-lib
        # Specifies the name of the NDK library that
        # you want CMake to locate.
        uv
)

find_library(
        rawfile-lib
        librawfile.z.so
)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fno-exceptions")

add_library(show SHARED napi_init.cpp ${SOURCES})
target_link_libraries(show PUBLIC libace_napi.z.so)
target_link_libraries(show PUBLIC libace_ndk.z.so)
target_link_libraries(show PUBLIC libnative_window.so)
#target_link_libraries(show PUBLIC libnative_drawing.so) // 导致连接错误 crash
target_link_libraries(show PUBLIC libnative_vsync.so)

target_link_libraries(show PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/../../../libs/arm64-v8a/libskiac++.so)
target_link_libraries(show PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/../../../libs/arm64-v8a/libkn.so)
target_link_libraries(show PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/../../../libs/arm64-v8a/libskikobridge.so)
target_link_libraries(show PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/../../../libs/arm64-v8a/libknxcomponent.so)
target_link_libraries(show PUBLIC hilog_ndk.z.so)
target_link_libraries(show PUBLIC
        ${EGL-lib} ${GLES-lib} ${hilog-lib} ${rawfile-lib} ${libace-lib} ${libnapi-lib} ${libuv-lib} libc++.a)