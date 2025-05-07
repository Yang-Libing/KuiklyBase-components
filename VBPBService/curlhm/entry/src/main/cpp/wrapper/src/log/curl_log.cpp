#include "curl_log.h"
#include "../../include/curl_wrapper.h"

curlLog gCurlLog = nullptr;

static const std::string gPrefixTag = "[" + std::string(gDefaultTag) + "] ";

static const int gCurlLogLevelDebug = 0;
static const int gCurlLogLevelInfo = 1;
static const int gCurlLogLevelWarn = 2;
static const int gCurlLogLevelError = 3;

void setCurlLogImpl(curlLog logImpl) {
    gCurlLog = logImpl;
}

void curlLogImpl(int level, const char *tag, const char *content) {
    if (gCurlLog == nullptr) {
        return;
    }
    gCurlLog(level, tag, content);
}

void logD(const std::string &tag, const std::string &content) {
    curlLogImpl(gCurlLogLevelDebug, (std::string(gPrefixTag) + tag).c_str(), content.c_str());
}

void logI(const std::string &tag, const std::string &content) {
    curlLogImpl(gCurlLogLevelInfo, (std::string(gPrefixTag) + tag).c_str(), content.c_str());
}

void logW(const std::string &tag, const std::string &content) {
    curlLogImpl(gCurlLogLevelWarn, (std::string(gPrefixTag) + tag).c_str(), content.c_str());
}

void logE(const std::string &tag, const std::string &content) {
    curlLogImpl(gCurlLogLevelError, (std::string(gPrefixTag) + tag).c_str(), content.c_str());
}
