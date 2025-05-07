#ifndef CURLHM_CURL_LOG_H
#define CURLHM_CURL_LOG_H

#include <string>

static const char *gDefaultTag = "NXNetwork_CurlWrapper";

void curlLogImpl(int level, const char *tag, const char *content);
void logD(const std::string &tag, const std::string &content);
void logI(const std::string &tag, const std::string &content);
void logW(const std::string &tag, const std::string &content);
void logE(const std::string &tag, const std::string &content);

#endif  // CURLHM_CURL_LOG_H
