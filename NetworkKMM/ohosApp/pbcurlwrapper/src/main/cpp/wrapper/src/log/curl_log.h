//
//  curl_log.h
//  pbcurlwrapper
//
//  Created by berryyang on 2025/6/6.
//  Copyright © 2025 Tencent. All rights reserved.
//

#ifndef NETWORKKMM_OHOSAPP_PBCURLWRAPPER_MAIN_CPP_WRAPPER_LOG_CURL_LOG_H_
#define NETWORKKMM_OHOSAPP_PBCURLWRAPPER_MAIN_CPP_WRAPPER_LOG_CURL_LOG_H_
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

#include <string>

static const char *gDefaultTag = "NXNetwork_CurlWrapper";

void curlLogImpl(int level, const char *tag, const char *content);
void logD(const std::string &tag, const std::string &content);
void logI(const std::string &tag, const std::string &content);
void logW(const std::string &tag, const std::string &content);
void logE(const std::string &tag, const std::string &content);

#endif  // NETWORKKMM_OHOSAPP_PBCURLWRAPPER_MAIN_CPP_WRAPPER_LOG_CURL_LOG_H_
