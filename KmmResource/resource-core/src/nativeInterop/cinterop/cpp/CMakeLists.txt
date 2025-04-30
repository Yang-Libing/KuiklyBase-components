# 指定CMake的最小版本
CMAKE_MINIMUM_REQUIRED(VERSION 3.16)
PROJECT(napi_func)

add_definitions(-DOHOS_PLATFORM)

set(NAPI_BRIDGE_ROOT_PATH ${CMAKE_CURRENT_SOURCE_DIR})

set(TARGET_NAME resource_compose)

SET(NAPI_BRIDGE_SRC ${NAPI_BRIDGE_ROOT_PATH}/src)

include_directories(${NAPI_BRIDGE_ROOT_PATH}/include)

add_library(${TARGET_NAME} SHARED
        include/resource_compose.h
        src/resource_compose.cpp
)

set_target_properties(${TARGET_NAME} PROPERTIES IMPORTED_NO_SONAME TRUE)

SET(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -O2")

SET(CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} -Wl,--emit-relocs --verbose")

target_link_libraries(${TARGET_NAME} PUBLIC libace_napi.z.so)
target_link_libraries(${TARGET_NAME} PUBLIC libohresmgr.so)
target_link_libraries(${TARGET_NAME} PUBLIC libhilog_ndk.z.so)
target_link_libraries(${TARGET_NAME} PUBLIC librawfile.z.so)

