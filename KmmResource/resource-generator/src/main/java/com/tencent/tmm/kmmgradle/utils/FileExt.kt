/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.utils

import com.tencent.tmm.kmmgradle.generator.AssetsGenerator
import java.io.File

internal val File.svg: Boolean
    get() =
        extension.equals("svg", ignoreCase = true)

internal val File.scale: String
    get() =
        nameWithoutExtension.substringAfter("@")

internal val File.nameWithoutScale: String
    get() =
        nameWithoutExtension.withoutScale


internal val File.pathRelative: String
    get() {
        val relativePathToAssets = this.path.substringAfterLast(AssetsGenerator.RES_ROOT)
        val fixedRelativePath = File(relativePathToAssets).path

        val result: String = if (fixedRelativePath.startsWith(File.separatorChar)) {
            fixedRelativePath.substring(1)
        } else {
            fixedRelativePath
        }

        return if (File.separatorChar == '/') result else result.replace(File.separatorChar, '/')

    }

internal fun File.copyAndMergeDirectory(
    dest: File,
    mergeFiles: (srcFile: File, destFile: File) -> Unit
) {
    if (this.isDirectory) {
        dest.mkdirs() // 确保目标目录存在
        this.listFiles()?.forEach { file ->
            val destFile = File(dest, file.name)
            file.copyAndMergeDirectory(destFile, mergeFiles)
        }
    } else {
        // 检查目标文件是否存在，如果存在，则合并内容
        if (dest.exists() && dest.isFile) {
            mergeFiles(this, dest)
        } else {
            // 如果目标文件不存在，则直接复制
            this.copyTo(dest, true)
        }
    }
}

internal fun File.copyDirectory(dest: File) {
    if (this.isDirectory) {
        dest.mkdirs() // 确保目标目录存在
        this.listFiles()?.forEach { file ->
            val destFile = File(dest, file.name)
            file.copyDirectory(destFile)
        }
    } else {
        // 检查目标文件是否存在，如果存在，则合并内容
        // 如果目标文件不存在，则直接复制
        this.copyTo(dest, true)
    }
}

fun mergeFiles(src: File, dest: File) {
    // 自定义合并逻辑，这里简单地将源文件内容追加到目标文件
    src.bufferedReader().use { reader ->
        dest.bufferedWriter().use { writer ->
            writer.append(reader.readText())
        }
    }
}

fun copyFile(src: File, dest: File) {
    src.inputStream().buffered().use { inputStream ->
        dest.outputStream().buffered().use { outputStream ->
            inputStream.copyTo(outputStream)
        }
    }
}

