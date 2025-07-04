/*
 * Copyright (c) 2023 Huawei Device Co., Ltd.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
#ifndef EGL_CORE_H
#define EGL_CORE_H

#include <EGL/egl.h>
#include <EGL/eglext.h>
#include <GLES3/gl3.h>
#include "libs/shared/libkn_api.h"
#include "napi/native_api.h"

typedef bool (*InitializeComposeControllerHandler)(int, int);

typedef void (*TouchEventToComposeControllerHandler)(napi_value);

typedef bool (*DidReceiveRenderNodeDrawHandler)(void);

class EGLCore {
public:
    explicit EGLCore() {};

    ~EGLCore() {}

    bool EglContextInit(void *window, int width, int height,
                        TouchEventToComposeControllerHandler touchEventToComposeControllerHandler,
                        InitializeComposeControllerHandler initializeComposeControllerHandler,
                        DidReceiveRenderNodeDrawHandler didReceiveRenderNodeDrawHandler);

    bool CreateEnvironment();

    void Draw();

    void Background();

    void ChangeColor();

    void Release();

    void drawSkia();

    bool FinishDraw();

    void dispatchTouchEvent(napi_value touchEvent);

    void didReceiveRenderNodeDraw();

private:
    GLuint LoadShader(GLenum type, const char *shaderSrc);

    GLuint CreateProgram(const char *vertexShader, const char *fragShader);

    GLint PrepareDraw();

    bool ExecuteDraw(GLint position, const GLfloat *color, const GLfloat rectangleVertices[],
                     unsigned long vertSize);


private:
    EGLNativeWindowType m_eglWindow;
    EGLDisplay m_eglDisplay = EGL_NO_DISPLAY;
    EGLConfig m_eglConfig = EGL_NO_CONFIG_KHR;
    EGLSurface m_eglSurface = EGL_NO_SURFACE;
    EGLContext m_eglContext = EGL_NO_CONTEXT;
    GLuint m_program;
    bool m_flag = false;
    int m_width;
    int m_height;
    GLfloat m_widthPercent;
    bool m_hasRenderCompose;
    TouchEventToComposeControllerHandler m_touchEventToComposeControllerHandler;
    InitializeComposeControllerHandler m_initializeComposeControllerHandler;
    DidReceiveRenderNodeDrawHandler m_didReceiveRenderNodeDrawHandler;
};

#endif
