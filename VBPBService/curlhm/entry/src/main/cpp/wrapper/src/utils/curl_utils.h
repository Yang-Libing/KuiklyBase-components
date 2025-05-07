#ifndef CURLHM_CURL_UTILS_H
#define CURLHM_CURL_UTILS_H

#include "../log/curl_log.h"
#include <string>

int GzipDecompress(const std::string &input, std::string &output, const std::string &tag = gDefaultTag);

bool FileExists(const std::string& path);

std::string ReadFileToString(const char *filename);

#endif  // CURLHM_CURL_UTILS_H
