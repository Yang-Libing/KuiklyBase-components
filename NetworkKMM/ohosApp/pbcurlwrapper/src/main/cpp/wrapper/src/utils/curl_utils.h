//
//  curl_utils.h
//  pbcurlwrapper
//
//  Created by berryyang on 2025/6/6.
//  Copyright © 2025 Tencent. All rights reserved.
//

/*
 * Tencent is pleased to support the open source community by making KuiklyBase available.
 * Copyright (C) 2025 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#ifndef NETWORKKMM_OHOSAPP_PBCURLWRAPPER_MAIN_CPP_WRAPPER_UTILS_CURL_UTILS_H_
#define NETWORKKMM_OHOSAPP_PBCURLWRAPPER_MAIN_CPP_WRAPPER_UTILS_CURL_UTILS_H_

#include "../log/curl_log.h"
#include <string>

int GzipDecompress(const std::string &input, std::string &output, const std::string &tag = gDefaultTag);

bool FileExists(const std::string& path);

std::string ReadFileToString(const char *filename);

#endif  // NETWORKKMM_OHOSAPP_PBCURLWRAPPER_MAIN_CPP_WRAPPER_UTILS_CURL_UTILS_H_
