package com.tencent.qqlive.kmm.imagefetchservice.service

import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBLog
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.buffer
import okio.use

// 测试图片数据保存到文件
actual fun testSaveByteArray(data: ByteArray, imgFilename: String) {
    // 跨端文件系统
    val fileSystem = FileSystem.SYSTEM
    val filename = if (imgFilename == "") {
        "image.bmp"
    } else {
        imgFilename
    }
    // 测试存储目录
    val filePath = "/data/storage/el2/base/files/imagedownload/$filename"
    // 父目录路径
    val folderPath = filePath.substring(0, filePath.lastIndexOf("/"))
    val isFolderExists = fileSystem.exists(folderPath.toPath())
    val isFileExists = fileSystem.exists(filePath.toPath())
    VBPBLog.i("[ImageFetchService]","fold path: ${folderPath}, fold exist: ${isFolderExists}, file exist: ${isFileExists}")
    // 检测父目录是否存在
    if (!isFolderExists) {
        fileSystem.createDirectories(folderPath.toPath(), false)
    }
    // 写入内容
    fileSystem.sink(filePath.toPath()).buffer().use { sink ->
        sink.write(data)
    }
}