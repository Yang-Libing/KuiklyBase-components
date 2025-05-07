#!/bin/sh

# 入参个数
PARAM_COUNT=$#
# 入参列表
PARAM_LIST=$@
# AutoPush版本信息
AP_VERSION="v2.1.5"

# 获取当前路径的跟路径, 方便读写git hook里的文件
GIT_PATH=$(git rev-parse --show-toplevel)

# 文件夹名称
AUTO_TMP_NAME="autopushremote"
# 主干开发和feature分支开发脚本存储的地方
AUTO_TMP="${GIT_PATH}/${AUTO_TMP_NAME}"
# feature分支开发脚本
AUTO_PATH="${AUTO_TMP}/autopush_remote.sh"
# 主干开发脚本路径
AUTO_MASTER_PATH="${AUTO_TMP}/autopush_master.sh"

export TAPD_TOKEN="auto_ovb:698854E8-30BF-7E66-6BC1-F742E3FF510F"

export TAPD_PROJECT_ID="10078431"

function main()
{
    echo "当前版本:$AP_VERSION"
    # 检查入参判定是主干开发还是feature开发
    checkParam
}


# 检查入参
function checkParam()
{
    # 通过判断参数个数决定执行的脚本
    if [[ $PARAM_COUNT -lt 1 ]] ;then
        # 非主干开发,使用旧的feature分支开发.
        runAutopushRemote
    else
        # 此处执行新的主干开发逻辑
        runAutopushMaster

    fi
}

# 下载主干开发脚本
function runAutopushMaster()
{
    # 下载主干开发脚本 并执行
    local url="http://dldir1.qq.com/qqmi/script/autopush/1.0.1/autopush_master.sh"
    runRemote "autopush_master.sh" "${AUTO_MASTER_PATH}" "${url}" ${PARAM_LIST}
}

# 下载feature分支开发脚本
function runAutopushRemote()
{
    # 下载脚本 并执行
    url="http://dldir1.qq.com/qqmi/script/autopush/1.0.0/autopush_remote.sh"
    runRemote "autopush_remote.sh" "${AUTO_PATH}" "${url}" ${PARAM_LIST}
}

# 执行远端脚本
function runRemote()
{
    # 脚本名称
    local sh_name=${1}
    # 脚本路径
    local path=${2}
    # 脚本的url
    local url=${3}
    # 脚本需要的参数
    local params=${4}
    # 下载脚本
    downloadScript "${sh_name}" "${path}" "${url}"
    # 执行脚本
    excuteScript "${path}" ${params}
}

# 下载脚本
# 为了避免网络问题引起的下载文件见不全的问题,这里下载之后判断如果文件内容不为空则替换,否则使用缓存文件
function downloadScript()
{
    # 脚本名称
    local sh_name=${1}
    # 脚本路径
    local path=${2}
    # 脚本的url
    local url=${3}

    # 创建存储文件夹
    newDirIfNeed ${AUTO_TMP}

    # 创建临时存储文件夹
    local download_tmp="tmp_download"
    newDirIfNeed $download_tmp

    # 下载文件到临时文件夹
    download "$sh_name" "$download_tmp/$sh_name" "$url"

    # 判断下载的脚本文件是否为空
    check_file_null "$download_tmp/$sh_name"

    local file_exist=$?
    if [[ $file_exist -eq 0 ]];then
        echo "文件下载成功,删除旧文件:$path"
        rm -rf $path
        echo "移动文件到制定目录:${AUTO_TMP}"
        mv -f $download_tmp/$sh_name ${AUTO_TMP}
    fi

    echo "删除临时文件夹:$download_tmp"
    rm -rf $download_tmp
}

# 执行脚本
function excuteScript()
{
    # 脚本路径
    local path=${1}
    # 脚本参数
    local params=${2}

    # 判断脚本路径是否存在
    if [[ ! -f ${path} ]]; then
        echo "${path} 不存在"
        exit 1
    fi
    echo "开始执行autopush脚本"
    sh $path $PARAM_LIST
}

# 检查文件是否为空
function check_file_null()
{
    # 文件路径
    local file_path=${1}
    if [[ -z $file_path ]];then
        echo "下载路径不存在"
        return 1
    fi
    # 获得当前路径
    local cur_path=$(PWD)
    # 得到文件所在的文件夹
    local dir_path=${file_path%/*}
    # 跳转到文件所在的文件夹
    if [[ ! -d $dir_path ]];then
        echo "文件夹不存在"
        return 1
    fi
    # 跳转到文件所在目录下
    cd $dir_path
    # 获得文件size
    local file_size=$(ls -l ${file_path##*/} | awk '{print $5}')
    echo "下载文件的大小:$file_size"
    min_size=$((1024*1))
    # 判断文件大小,小于1k则认为脚本下载失败
    if [[ $file_size -lt $min_size ]]; then
        cd $cur_path
        return 1
    fi
    cd $cur_path
    return 0
}


# 下载二进制文件，用来执行后面的命令
# downName - 文件的名称
# downFile - 文件的存储位置
# reqUrl - 远端地址
function download()
{
    local downName="${1}"
    local downFile="${2}"
    local reqUrl="${3}"
    echo "下载${downName}..."
    curl -so "${downFile}" "${reqUrl}" -L
    chmod +x "${downFile}"
    # echoFile ""
}

# 新创建文件夹，如果文件夹已存在什么也不做
# dir - 想要创建的文件夹
function newDirIfNeed()
{
    local dir="${1}"
    if [[ ! -d "${dir}" ]]; then
        mkdir -p "${dir}"
    fi
}

main
