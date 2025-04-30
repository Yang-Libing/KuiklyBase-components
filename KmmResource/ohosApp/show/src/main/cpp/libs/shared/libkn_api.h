#ifndef KONAN_LIBKN_H
#define KONAN_LIBKN_H
#ifdef __cplusplus
extern "C" {
#endif
#ifdef __cplusplus
typedef bool            libkn_KBoolean;
#else
typedef _Bool libkn_KBoolean;
#endif
typedef unsigned short libkn_KChar;
typedef signed char libkn_KByte;
typedef short libkn_KShort;
typedef int libkn_KInt;
typedef long long libkn_KLong;
typedef unsigned char libkn_KUByte;
typedef unsigned short libkn_KUShort;
typedef unsigned int libkn_KUInt;
typedef unsigned long long libkn_KULong;
typedef float libkn_KFloat;
typedef double libkn_KDouble;
typedef float __attribute__ ((__vector_size__ (16))) libkn_KVector128;
typedef void *libkn_KNativePtr;
struct libkn_KType;
typedef struct libkn_KType libkn_KType;

typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_Byte;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_Short;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_Int;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_Long;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_Float;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_Double;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_Char;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_Boolean;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_Unit;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_UByte;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_UShort;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_UInt;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_ULong;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_Array;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_collections_SafeHashMap;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_reflect_KClass;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_converter_TypeConverter;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_Any;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_converter_ArrayBufferTypeConverter;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_type_ArrayBuffer;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_platform_ohos_napi_valuetype;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_converter_ArrayTypeConverter;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_converter_BooleanTypeConverter;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_converter_DoubleTypeConverter;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_converter_IntTypeConverter;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_converter_JSCallbackTypeConverter;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_Function1;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_converter_JSValueTypeConverter;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_type_JSValue;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_converter_ListTypeConverter;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_collections_List;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_converter_LongTypeConverter;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_converter_MapTypeConverter;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_collections_Map;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_converter_StringTypeConverter;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_converter_UnitTypeConverter;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_register_ServiceProviderRegister;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_register_ServiceProxyRegister;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_Function0;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_exception_FunctionNotRegisterException;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_exception_ReturnTypeErrorException;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_exception_ServiceFactoryFailException;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_exception_ServiceNotRegisterException;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_exception_ServiceProxyNotFoundException;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_exception_UnSupportTypeException;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_jsbind_JSFunction;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_register_FunctionBindInfo;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_register_FunctionRegister;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_register_JSFunctionRegister;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_register_ServiceProvider;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_service_Invokable;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_register_ServiceWrapper;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_platform_ohos_napi_typedarray_type;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_type_JSValue_Companion;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tmm_demo_ComposeController;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_kotlin_ByteArray;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tmm_examples_fallingballs_Game;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_androidx_compose_runtime_snapshots_SnapshotStateList;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tmm_examples_fallingballs_PieceData;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tmm_examples_visualeffects_DoubleRocket;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tmm_examples_visualeffects_Particle;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tmm_examples_visualeffects_Rocket;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tmm_examples_visualeffects_SnowFlake;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tmm_examples_visualeffects_Star;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_net_novate_kotlin_MR;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_net_novate_kotlin_MR_assets;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_kmmresource_resource_AssetResource;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_net_novate_kotlin_MR_colors;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_kmmresource_resource_ColorResource;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_net_novate_kotlin_MR_files;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_net_novate_kotlin_MR_fonts;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_net_novate_kotlin_MR_images;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_net_novate_kotlin_MR_plurals;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_kmmresource_resource_PluralsResource;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_net_novate_kotlin_MR_strings;
typedef struct {
    libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_kmmresource_resource_StringResource;

extern void com_tencent_tmm_knoi_initEnv(void *env, void *export_, libkn_KBoolean debug);

extern void com_tencent_tmm_knoi_initBridge();

extern libkn_kref_com_tmm_demo_ComposeController
OhComposeMainDraw(libkn_KInt componentWidth, libkn_KInt componentHeight);

extern void didReceiveRenderNodeDrawFromComposeController(
        libkn_kref_com_tmm_demo_ComposeController composeController);

extern void initNativeResourceManager(void *manager);

extern void
sendPointerEvent2ComposeController(libkn_kref_com_tmm_demo_ComposeController composeController,
                                   void *nativeTouchEvent);

extern void setSize2ComposeController(libkn_kref_com_tmm_demo_ComposeController composeController,
                                      libkn_KInt sceneWidth, libkn_KInt sceneHeight);

extern void InitEnv(void *env, void *export_);

extern libkn_KInt testSum(libkn_KInt a, libkn_KInt b);

typedef struct {
    /* Service functions. */
    void (*DisposeStablePointer)(libkn_KNativePtr ptr);

    void (*DisposeString)(const char *string);

    libkn_KBoolean (*IsInstance)(libkn_KNativePtr ref, const libkn_KType *type);

    libkn_kref_kotlin_Byte (*createNullableByte)(libkn_KByte);

    libkn_KByte (*getNonNullValueOfByte)(libkn_kref_kotlin_Byte);

    libkn_kref_kotlin_Short (*createNullableShort)(libkn_KShort);

    libkn_KShort (*getNonNullValueOfShort)(libkn_kref_kotlin_Short);

    libkn_kref_kotlin_Int (*createNullableInt)(libkn_KInt);

    libkn_KInt (*getNonNullValueOfInt)(libkn_kref_kotlin_Int);

    libkn_kref_kotlin_Long (*createNullableLong)(libkn_KLong);

    libkn_KLong (*getNonNullValueOfLong)(libkn_kref_kotlin_Long);

    libkn_kref_kotlin_Float (*createNullableFloat)(libkn_KFloat);

    libkn_KFloat (*getNonNullValueOfFloat)(libkn_kref_kotlin_Float);

    libkn_kref_kotlin_Double (*createNullableDouble)(libkn_KDouble);

    libkn_KDouble (*getNonNullValueOfDouble)(libkn_kref_kotlin_Double);

    libkn_kref_kotlin_Char (*createNullableChar)(libkn_KChar);

    libkn_KChar (*getNonNullValueOfChar)(libkn_kref_kotlin_Char);

    libkn_kref_kotlin_Boolean (*createNullableBoolean)(libkn_KBoolean);

    libkn_KBoolean (*getNonNullValueOfBoolean)(libkn_kref_kotlin_Boolean);

    libkn_kref_kotlin_Unit (*createNullableUnit)(void);

    libkn_kref_kotlin_UByte (*createNullableUByte)(libkn_KUByte);

    libkn_KUByte (*getNonNullValueOfUByte)(libkn_kref_kotlin_UByte);

    libkn_kref_kotlin_UShort (*createNullableUShort)(libkn_KUShort);

    libkn_KUShort (*getNonNullValueOfUShort)(libkn_kref_kotlin_UShort);

    libkn_kref_kotlin_UInt (*createNullableUInt)(libkn_KUInt);

    libkn_KUInt (*getNonNullValueOfUInt)(libkn_kref_kotlin_UInt);

    libkn_kref_kotlin_ULong (*createNullableULong)(libkn_KULong);

    libkn_KULong (*getNonNullValueOfULong)(libkn_kref_kotlin_ULong);

    /* User functions. */
    struct {
        struct {
            struct {
                struct {
                    struct {
                        struct {
                            struct {
                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_converter_ArrayBufferTypeConverter
                                    (*ArrayBufferTypeConverter)();

                                    libkn_kref_com_tencent_tmm_knoi_type_ArrayBuffer
                                    (*convertJSValueToKotlinValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_ArrayBufferTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_ArrayBufferTypeConverter thiz,
                                            void *env,
                                            libkn_kref_com_tencent_tmm_knoi_type_ArrayBuffer value);

                                    libkn_kref_kotlin_reflect_KClass (*getKType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_ArrayBufferTypeConverter thiz);

                                    libkn_KBoolean (*isSupportJSType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_ArrayBufferTypeConverter thiz,
                                            void *env, libkn_kref_platform_ohos_napi_valuetype type,
                                            void *value);

                                    libkn_KBoolean (*isSupportKType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_ArrayBufferTypeConverter thiz,
                                            libkn_kref_kotlin_reflect_KClass type);
                                } ArrayBufferTypeConverter;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_converter_ArrayTypeConverter
                                    (*ArrayTypeConverter)();

                                    libkn_kref_kotlin_Array (*convertJSValueToKotlinValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_ArrayTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_ArrayTypeConverter thiz,
                                            void *env, libkn_kref_kotlin_Array value);

                                    libkn_kref_kotlin_reflect_KClass (*getKType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_ArrayTypeConverter thiz);

                                    libkn_KBoolean (*isSupportKType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_ArrayTypeConverter thiz,
                                            libkn_kref_kotlin_reflect_KClass type);
                                } ArrayTypeConverter;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_converter_BooleanTypeConverter
                                    (*BooleanTypeConverter)();

                                    libkn_KBoolean (*convertJSValueToKotlinValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_BooleanTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_BooleanTypeConverter thiz,
                                            void *env, libkn_kref_kotlin_Boolean value);

                                    libkn_kref_platform_ohos_napi_valuetype (*getJSType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_BooleanTypeConverter thiz);

                                    libkn_kref_kotlin_reflect_KClass (*getKType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_BooleanTypeConverter thiz);
                                } BooleanTypeConverter;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_converter_DoubleTypeConverter
                                    (*DoubleTypeConverter)();

                                    libkn_kref_kotlin_Double (*convertJSValueToKotlinValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_DoubleTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_DoubleTypeConverter thiz,
                                            void *env, libkn_kref_kotlin_Double value);

                                    libkn_kref_platform_ohos_napi_valuetype (*getJSType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_DoubleTypeConverter thiz);

                                    libkn_kref_kotlin_reflect_KClass (*getKType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_DoubleTypeConverter thiz);
                                } DoubleTypeConverter;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_converter_IntTypeConverter
                                    (*IntTypeConverter)();

                                    libkn_KInt (*convertJSValueToKotlinValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_IntTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_IntTypeConverter thiz,
                                            void *env, libkn_kref_kotlin_Int value);

                                    libkn_kref_kotlin_reflect_KClass (*getKType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_IntTypeConverter thiz);
                                } IntTypeConverter;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_converter_JSCallbackTypeConverter
                                    (*JSCallbackTypeConverter)();

                                    libkn_kref_kotlin_Function1 (*convertJSValueToKotlinValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_JSCallbackTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_JSCallbackTypeConverter thiz,
                                            void *env, libkn_kref_kotlin_Function1 value);

                                    libkn_kref_platform_ohos_napi_valuetype (*getJSType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_JSCallbackTypeConverter thiz);

                                    libkn_kref_kotlin_reflect_KClass (*getKType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_JSCallbackTypeConverter thiz);
                                } JSCallbackTypeConverter;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_converter_JSValueTypeConverter
                                    (*JSValueTypeConverter)();

                                    libkn_kref_com_tencent_tmm_knoi_type_JSValue
                                    (*convertJSValueToKotlinValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_JSValueTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_JSValueTypeConverter thiz,
                                            void *env,
                                            libkn_kref_com_tencent_tmm_knoi_type_JSValue value);

                                    libkn_kref_kotlin_reflect_KClass (*getKType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_JSValueTypeConverter thiz);
                                } JSValueTypeConverter;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_converter_ListTypeConverter
                                    (*ListTypeConverter)();

                                    libkn_kref_kotlin_collections_List
                                    (*convertJSValueToKotlinValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_ListTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_ListTypeConverter thiz,
                                            void *env, libkn_kref_kotlin_collections_List value);

                                    libkn_kref_kotlin_reflect_KClass (*getKType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_ListTypeConverter thiz);

                                    libkn_KBoolean (*isSupportJSType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_ListTypeConverter thiz,
                                            void *env, libkn_kref_platform_ohos_napi_valuetype type,
                                            void *value);

                                    libkn_KBoolean (*isSupportKType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_ListTypeConverter thiz,
                                            libkn_kref_kotlin_reflect_KClass type);
                                } ListTypeConverter;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_converter_LongTypeConverter
                                    (*LongTypeConverter)();

                                    libkn_KLong (*convertJSValueToKotlinValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_LongTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_LongTypeConverter thiz,
                                            void *env, libkn_kref_kotlin_Long value);

                                    libkn_kref_kotlin_reflect_KClass (*getKType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_LongTypeConverter thiz);
                                } LongTypeConverter;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_converter_MapTypeConverter
                                    (*MapTypeConverter)();

                                    libkn_kref_kotlin_collections_Map
                                    (*convertJSValueToKotlinValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_MapTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_MapTypeConverter thiz,
                                            void *env, libkn_kref_kotlin_collections_Map value);

                                    libkn_kref_platform_ohos_napi_valuetype (*getJSType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_MapTypeConverter thiz);

                                    libkn_kref_kotlin_reflect_KClass (*getKType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_MapTypeConverter thiz);

                                    libkn_KBoolean (*isSupportKType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_MapTypeConverter thiz,
                                            libkn_kref_kotlin_reflect_KClass type);
                                } MapTypeConverter;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_converter_StringTypeConverter
                                    (*StringTypeConverter)();

                                    const char *(*convertJSValueToKotlinValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_StringTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_StringTypeConverter thiz,
                                            void *env, const char *value);

                                    libkn_kref_platform_ohos_napi_valuetype (*getJSType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_StringTypeConverter thiz);

                                    libkn_kref_kotlin_reflect_KClass (*getKType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_StringTypeConverter thiz);
                                } StringTypeConverter;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_converter_UnitTypeConverter
                                    (*UnitTypeConverter)();

                                    void (*convertJSValueToKotlinValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_UnitTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_UnitTypeConverter thiz,
                                            void *env, libkn_kref_kotlin_Unit value);

                                    libkn_kref_kotlin_reflect_KClass (*getKType)(
                                            libkn_kref_com_tencent_tmm_knoi_converter_UnitTypeConverter thiz);
                                } UnitTypeConverter;

                                libkn_kref_kotlin_Array (*get_converters)();

                                libkn_kref_com_tencent_tmm_knoi_collections_SafeHashMap
                                (*get_ktFunctionMap)();

                                void (*finalizeJSCallback)(void *env, void *data, void *hint);

                                libkn_kref_com_tencent_tmm_knoi_converter_TypeConverter
                                (*getFirstSupportConverter)(libkn_kref_kotlin_reflect_KClass type);

                                libkn_kref_com_tencent_tmm_knoi_converter_TypeConverter
                                (*getFirstSupportConverter_)(void *env, void *value);

                                libkn_kref_kotlin_Any (*jsValueToKTValue)(void *env, void *value,
                                                                          libkn_kref_kotlin_reflect_KClass kType);

                                void *(*ktValueToJSValue)(void *env, libkn_kref_kotlin_Any value,
                                                          libkn_kref_kotlin_reflect_KClass clazz);
                            } converter;

                            struct {
                                const char *(*get_JS_CALL_SERVICE_METHOD_NAME)();

                                const char *(*get_JS_REGISTER_SERVICE_METHOD_NAME)();

                                const char *(*get_METHOD_NAME_NAPI)();

                                libkn_kref_com_tencent_tmm_knoi_register_ServiceProviderRegister
                                (*get_serviceProviderRegister)();

                                libkn_kref_com_tencent_tmm_knoi_register_ServiceProxyRegister
                                (*get_serviceProxyRegister)();

                                void (*bind)(const char *name, libkn_kref_kotlin_Function1 function,
                                             libkn_kref_kotlin_reflect_KClass returnType,
                                             libkn_kref_kotlin_Array paramsTypes);

                                void (*registerServiceExport)(void *env, void *export_);

                                void (*registerServiceProvider)(const char *name,
                                                                libkn_KBoolean singleton,
                                                                libkn_kref_kotlin_Function0 factory);

                                void (*unBind)(const char *name);
                            } definder;

                            struct {
                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_exception_FunctionNotRegisterException
                                    (*FunctionNotRegisterException)(const char *methodName);
                                } FunctionNotRegisterException;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_exception_ReturnTypeErrorException
                                    (*ReturnTypeErrorException)();
                                } ReturnTypeErrorException;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_exception_ServiceFactoryFailException
                                    (*ServiceFactoryFailException)(const char *service);
                                } ServiceFactoryFailException;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_exception_ServiceNotRegisterException
                                    (*ServiceNotRegisterException)(const char *service);
                                } ServiceNotRegisterException;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_exception_ServiceProxyNotFoundException
                                    (*ServiceProxyNotFoundException)(const char *service);
                                } ServiceProxyNotFoundException;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_exception_UnSupportTypeException
                                    (*UnSupportTypeException)(const char *msg);
                                } UnSupportTypeException;
                            } exception;

                            struct {
                                void (*cancelBlock)(libkn_kref_kotlin_Function0 block);

                                libkn_KBoolean (*isMainThread)();

                                void (*runOnMainThread)(libkn_kref_kotlin_Function0 block);

                                void (*runOnMainThread_)(libkn_kref_kotlin_Function0 block,
                                                         libkn_KLong delayMs);
                            } handler;

                            struct {
                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_jsbind_JSFunction
                                    (*JSFunction)(void *env, const char *name, void *jsCallback);

                                    libkn_KInt (*get_bindTid)(
                                            libkn_kref_com_tencent_tmm_knoi_jsbind_JSFunction thiz);

                                    void *(*get_env)(
                                            libkn_kref_com_tencent_tmm_knoi_jsbind_JSFunction thiz);

                                    const char *(*get_name)(
                                            libkn_kref_com_tencent_tmm_knoi_jsbind_JSFunction thiz);

                                    void
                                    (*bind)(libkn_kref_com_tencent_tmm_knoi_jsbind_JSFunction thiz,
                                            void *env, void *recv);

                                    libkn_kref_kotlin_Any (*invokeDirect)(
                                            libkn_kref_com_tencent_tmm_knoi_jsbind_JSFunction thiz,
                                            libkn_kref_kotlin_Array params,
                                            libkn_kref_kotlin_reflect_KClass kType);

                                    libkn_kref_kotlin_Any (*invokeIndirect)(
                                            libkn_kref_com_tencent_tmm_knoi_jsbind_JSFunction thiz,
                                            libkn_KBoolean sync, libkn_kref_kotlin_Array params,
                                            libkn_kref_kotlin_reflect_KClass kType);

                                    void (*release)(
                                            libkn_kref_com_tencent_tmm_knoi_jsbind_JSFunction thiz);
                                } JSFunction;

                                const char *(*get_JS_BIND_METHOD_NAME)();

                                const char *(*get_JS_UNBIND_METHOD_NAME)();

                                libkn_kref_com_tencent_tmm_knoi_jsbind_JSFunction
                                (*getJSFunction)(const char *name);
                            } jsbind;

                            struct {
                                libkn_KUInt (*get_domain)();

                                libkn_KBoolean (*get_isDebug)();

                                void (*set_isDebug)(libkn_KBoolean set);

                                const char *(*get_tag)();

                                void (*check)(const char *message,
                                              libkn_kref_kotlin_Function0 condition);

                                void (*debug)(const char *message);

                                void (*error)(const char *message);

                                void (*info)(const char *message);

                                void (*log)(libkn_KUInt level, const char *message);
                            } logger;

                            struct {
                                void (*defineFunctionToExport)(void *env, void *export_,
                                                               const char *properties, void *func);

                                libkn_kref_kotlin_Any
                                (*safeCaseNumberType)(libkn_kref_kotlin_Any value,
                                                      libkn_kref_kotlin_reflect_KClass type);
                            } napi;

                            struct {
                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_register_FunctionBindInfo
                                    (*FunctionBindInfo)(const char *name,
                                                        libkn_kref_kotlin_Function1 originFun,
                                                        libkn_kref_kotlin_reflect_KClass returnType,
                                                        libkn_kref_kotlin_Array paramsType);

                                    const char *(*get_name)(
                                            libkn_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz);

                                    libkn_kref_kotlin_Function1 (*get_originFun)(
                                            libkn_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz);

                                    libkn_kref_kotlin_Array (*get_paramsType)(
                                            libkn_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz);

                                    libkn_kref_kotlin_reflect_KClass (*get_returnType)(
                                            libkn_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz);

                                    const char *(*component1)(
                                            libkn_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz);

                                    libkn_kref_kotlin_Function1 (*component2)(
                                            libkn_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz);

                                    libkn_kref_kotlin_reflect_KClass (*component3)(
                                            libkn_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz);

                                    libkn_kref_kotlin_Array (*component4)(
                                            libkn_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz);

                                    libkn_kref_com_tencent_tmm_knoi_register_FunctionBindInfo
                                    (*copy)(libkn_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz,
                                            const char *name, libkn_kref_kotlin_Function1 originFun,
                                            libkn_kref_kotlin_reflect_KClass returnType,
                                            libkn_kref_kotlin_Array paramsType);

                                    libkn_KBoolean (*equals)(
                                            libkn_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz,
                                            libkn_kref_kotlin_Any other);

                                    libkn_KInt (*hashCode)(
                                            libkn_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz);

                                    const char *(*toString)(
                                            libkn_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz);
                                } FunctionBindInfo;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_register_FunctionRegister
                                    (*FunctionRegister)();

                                    libkn_kref_kotlin_Function1 (*getFunction)(
                                            libkn_kref_com_tencent_tmm_knoi_register_FunctionRegister thiz,
                                            const char *name);

                                    libkn_kref_kotlin_Array (*getParamTypes)(
                                            libkn_kref_com_tencent_tmm_knoi_register_FunctionRegister thiz,
                                            const char *name);

                                    libkn_kref_kotlin_reflect_KClass (*getReturnType)(
                                            libkn_kref_com_tencent_tmm_knoi_register_FunctionRegister thiz,
                                            const char *name);

                                    void (*register_)(
                                            libkn_kref_com_tencent_tmm_knoi_register_FunctionRegister thiz,
                                            libkn_kref_com_tencent_tmm_knoi_register_FunctionBindInfo bindInfo);

                                    void (*unRegister)(
                                            libkn_kref_com_tencent_tmm_knoi_register_FunctionRegister thiz,
                                            const char *name);
                                } FunctionRegister;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_register_JSFunctionRegister
                                    (*JSFunctionRegister)();

                                    libkn_kref_com_tencent_tmm_knoi_jsbind_JSFunction
                                    (*getJSFunction)(
                                            libkn_kref_com_tencent_tmm_knoi_register_JSFunctionRegister thiz,
                                            const char *name);

                                    void (*register_)(
                                            libkn_kref_com_tencent_tmm_knoi_register_JSFunctionRegister thiz,
                                            libkn_kref_com_tencent_tmm_knoi_jsbind_JSFunction jsFunction);

                                    void (*unregister)(
                                            libkn_kref_com_tencent_tmm_knoi_register_JSFunctionRegister thiz,
                                            const char *name);
                                } JSFunctionRegister;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_register_ServiceProvider
                                    (*ServiceProvider)(const char *name, libkn_KBoolean singleton,
                                                       libkn_kref_kotlin_Function0 factory);

                                    libkn_kref_kotlin_Function0 (*get_factory)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceProvider thiz);

                                    const char *(*get_name)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceProvider thiz);

                                    libkn_KBoolean (*get_singleton)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceProvider thiz);

                                    const char *(*component1)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceProvider thiz);

                                    libkn_KBoolean (*component2)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceProvider thiz);

                                    libkn_kref_kotlin_Function0 (*component3)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceProvider thiz);

                                    libkn_kref_com_tencent_tmm_knoi_register_ServiceProvider
                                    (*copy)(libkn_kref_com_tencent_tmm_knoi_register_ServiceProvider thiz,
                                            const char *name, libkn_KBoolean singleton,
                                            libkn_kref_kotlin_Function0 factory);

                                    libkn_KBoolean (*equals)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceProvider thiz,
                                            libkn_kref_kotlin_Any other);

                                    libkn_KInt (*hashCode)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceProvider thiz);

                                    const char *(*toString)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceProvider thiz);
                                } ServiceProvider;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_register_ServiceProviderRegister
                                    (*ServiceProviderRegister)();

                                    libkn_kref_com_tencent_tmm_knoi_service_Invokable
                                    (*getInvokable)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceProviderRegister thiz,
                                            libkn_kref_com_tencent_tmm_knoi_type_JSValue proxyJSValue,
                                            const char *name);

                                    void (*registerServiceProvider)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceProviderRegister thiz,
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceProvider provider);

                                    void (*releaseInvokable)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceProviderRegister thiz,
                                            libkn_kref_com_tencent_tmm_knoi_service_Invokable invokable);
                                } ServiceProviderRegister;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_register_ServiceProxyRegister
                                    (*ServiceProxyRegister)();

                                    libkn_kref_com_tencent_tmm_knoi_type_JSValue
                                    (*getServiceRealImpl)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceProxyRegister thiz,
                                            const char *name);

                                    void (*register_)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceProxyRegister thiz,
                                            const char *name, libkn_KBoolean isSingleton,
                                            void *jsConstructor);
                                } ServiceProxyRegister;

                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_register_ServiceWrapper
                                    (*ServiceWrapper)(const char *name, libkn_KBoolean isSingleton,
                                                      void *constructorRef);

                                    void *(*get_constructorRef)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceWrapper thiz);

                                    libkn_KBoolean (*get_isSingleton)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceWrapper thiz);

                                    const char *(*get_name)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceWrapper thiz);

                                    const char *(*component1)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceWrapper thiz);

                                    libkn_KBoolean (*component2)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceWrapper thiz);

                                    void *(*component3)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceWrapper thiz);

                                    libkn_kref_com_tencent_tmm_knoi_register_ServiceWrapper
                                    (*copy)(libkn_kref_com_tencent_tmm_knoi_register_ServiceWrapper thiz,
                                            const char *name, libkn_KBoolean isSingleton,
                                            void *constructorRef);

                                    libkn_KBoolean (*equals)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceWrapper thiz,
                                            libkn_kref_kotlin_Any other);

                                    libkn_KInt (*hashCode)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceWrapper thiz);

                                    const char *(*toString)(
                                            libkn_kref_com_tencent_tmm_knoi_register_ServiceWrapper thiz);
                                } ServiceWrapper;

                                void (*finalizeInvokableProxy)(void *env, void *data, void *hint);
                            } register_;

                            struct {
                                struct {
                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_type_ArrayBuffer
                                    (*ArrayBuffer)(void *data, libkn_KLong length,
                                                   libkn_kref_platform_ohos_napi_typedarray_type type);

                                    libkn_kref_com_tencent_tmm_knoi_type_ArrayBuffer
                                    (*ArrayBuffer_)(void *handle);

                                    void *(*get_handle)(
                                            libkn_kref_com_tencent_tmm_knoi_type_ArrayBuffer thiz);

                                    libkn_KLong (*getCount)(
                                            libkn_kref_com_tencent_tmm_knoi_type_ArrayBuffer thiz);
                                } ArrayBuffer;

                                struct {
                                    struct {
                                        libkn_KType *(*_type)(void);

                                        libkn_kref_com_tencent_tmm_knoi_type_JSValue_Companion
                                        (*_instance)();

                                        libkn_kref_com_tencent_tmm_knoi_type_JSValue
                                        (*createJSObject)(
                                                libkn_kref_com_tencent_tmm_knoi_type_JSValue_Companion thiz);

                                        libkn_kref_com_tencent_tmm_knoi_type_JSValue (*global)(
                                                libkn_kref_com_tencent_tmm_knoi_type_JSValue_Companion thiz);
                                    } Companion;

                                    libkn_KType *(*_type)(void);

                                    libkn_kref_com_tencent_tmm_knoi_type_JSValue
                                    (*JSValue)(void *handle);

                                    void *(*get_handle)(
                                            libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libkn_kref_com_tencent_tmm_knoi_type_JSValue
                                    (*get)(libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz,
                                           libkn_KInt index);

                                    libkn_kref_com_tencent_tmm_knoi_type_JSValue
                                    (*get_)(libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz,
                                            const char *key);

                                    libkn_KBoolean (*isArrayType)(
                                            libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libkn_KBoolean
                                    (*isBoolean)(libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libkn_KBoolean (*isFunction)(
                                            libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libkn_KBoolean
                                    (*isNull)(libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libkn_KBoolean
                                    (*isNumber)(libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libkn_KBoolean
                                    (*isObject)(libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libkn_KBoolean (*isStrictEquals)(
                                            libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz,
                                            libkn_kref_kotlin_Any other);

                                    libkn_KBoolean
                                    (*isString)(libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libkn_KBoolean (*isUndefined)(
                                            libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libkn_KBoolean
                                    (*set)(libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz,
                                           libkn_KInt index,
                                           libkn_kref_com_tencent_tmm_knoi_type_JSValue value);

                                    libkn_KBoolean
                                    (*set_)(libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz,
                                            const char *key,
                                            libkn_kref_com_tencent_tmm_knoi_type_JSValue value);

                                    libkn_KBoolean
                                    (*toBoolean)(libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libkn_KDouble
                                    (*toDouble)(libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libkn_KInt
                                    (*toInt)(libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    const char *
                                    (*toKString)(libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libkn_KLong
                                    (*toLong)(libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libkn_kref_kotlin_collections_Map
                                    (*toMap)(libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz);
                                } JSValue;
                            } type;

                            void (*InitEnv)(void *env, void *export_, libkn_KBoolean debug);

                            void *(*getEnv)();

                            void (*setEnv)(void *env);

                            void (*initBridge)();

                            void (*initialize)();
                        } knoi;
                    } tmm;
                } tencent;
                struct {
                    struct {
                        libkn_KBoolean (*get_EnableLogger)();

                        libkn_kref_com_tmm_demo_ComposeController
                        (*OhComposeMainDraw_)(libkn_KInt componentWidth,
                                              libkn_KInt componentHeight);

                        void (*didReceiveRenderNodeDrawFromComposeController_)(
                                libkn_kref_com_tmm_demo_ComposeController composeController);

                        void (*initNativeResourceManager_)(void *manager);

                        libkn_kref_kotlin_ByteArray (*readFile)();

                        void (*sendPointerEvent2ComposeController_)(
                                libkn_kref_com_tmm_demo_ComposeController composeController,
                                void *nativeTouchEvent);

                        void (*setSize2ComposeController_)(
                                libkn_kref_com_tmm_demo_ComposeController composeController,
                                libkn_KInt sceneWidth, libkn_KInt sceneHeight);
                    } demo;

                    struct {
                        struct {
                            struct {
                                libkn_KType *(*_type)(void);

                                libkn_kref_com_tmm_examples_fallingballs_Game (*Game)();

                                libkn_KLong
                                (*get_elapsed)(libkn_kref_com_tmm_examples_fallingballs_Game thiz);

                                void
                                (*set_elapsed)(libkn_kref_com_tmm_examples_fallingballs_Game thiz,
                                               libkn_KLong set);

                                libkn_KBoolean
                                (*get_finished)(libkn_kref_com_tmm_examples_fallingballs_Game thiz);

                                void
                                (*set_finished)(libkn_kref_com_tmm_examples_fallingballs_Game thiz,
                                                libkn_KBoolean set);

                                libkn_KFloat
                                (*get_height)(libkn_kref_com_tmm_examples_fallingballs_Game thiz);

                                void
                                (*set_height)(libkn_kref_com_tmm_examples_fallingballs_Game thiz,
                                              libkn_KFloat set);

                                libkn_KFloat (*get_numBlocks)(
                                        libkn_kref_com_tmm_examples_fallingballs_Game thiz);

                                void
                                (*set_numBlocks)(libkn_kref_com_tmm_examples_fallingballs_Game thiz,
                                                 libkn_KFloat set);

                                libkn_KBoolean
                                (*get_paused)(libkn_kref_com_tmm_examples_fallingballs_Game thiz);

                                void
                                (*set_paused)(libkn_kref_com_tmm_examples_fallingballs_Game thiz,
                                              libkn_KBoolean set);

                                libkn_kref_androidx_compose_runtime_snapshots_SnapshotStateList
                                (*get_pieces)(libkn_kref_com_tmm_examples_fallingballs_Game thiz);

                                libkn_KInt
                                (*get_score)(libkn_kref_com_tmm_examples_fallingballs_Game thiz);

                                void
                                (*set_score)(libkn_kref_com_tmm_examples_fallingballs_Game thiz,
                                             libkn_KInt set);

                                libkn_KBoolean
                                (*get_started)(libkn_kref_com_tmm_examples_fallingballs_Game thiz);

                                void
                                (*set_started)(libkn_kref_com_tmm_examples_fallingballs_Game thiz,
                                               libkn_KBoolean set);

                                libkn_KFloat
                                (*get_width)(libkn_kref_com_tmm_examples_fallingballs_Game thiz);

                                void
                                (*set_width)(libkn_kref_com_tmm_examples_fallingballs_Game thiz,
                                             libkn_KFloat set);

                                void (*clicked)(libkn_kref_com_tmm_examples_fallingballs_Game thiz,
                                                libkn_kref_com_tmm_examples_fallingballs_PieceData piece);

                                void (*start)(libkn_kref_com_tmm_examples_fallingballs_Game thiz);

                                void (*update)(libkn_kref_com_tmm_examples_fallingballs_Game thiz,
                                               libkn_KLong deltaTimeNanos);
                            } Game;

                            struct {
                                libkn_KType *(*_type)(void);

                                libkn_kref_com_tmm_examples_fallingballs_PieceData
                                (*PieceData)(libkn_kref_com_tmm_examples_fallingballs_Game game,
                                             libkn_KFloat velocity, libkn_KULong color);

                                libkn_KBoolean (*get_clicked)(
                                        libkn_kref_com_tmm_examples_fallingballs_PieceData thiz);

                                void (*set_clicked)(
                                        libkn_kref_com_tmm_examples_fallingballs_PieceData thiz,
                                        libkn_KBoolean set);

                                libkn_KULong (*get_color)(
                                        libkn_kref_com_tmm_examples_fallingballs_PieceData thiz);

                                libkn_kref_com_tmm_examples_fallingballs_Game (*get_game)(
                                        libkn_kref_com_tmm_examples_fallingballs_PieceData thiz);

                                libkn_KFloat (*get_position)(
                                        libkn_kref_com_tmm_examples_fallingballs_PieceData thiz);

                                void (*set_position)(
                                        libkn_kref_com_tmm_examples_fallingballs_PieceData thiz,
                                        libkn_KFloat set);

                                libkn_KFloat (*get_velocity)(
                                        libkn_kref_com_tmm_examples_fallingballs_PieceData thiz);

                                void
                                (*click)(libkn_kref_com_tmm_examples_fallingballs_PieceData thiz);

                                libkn_kref_com_tmm_examples_fallingballs_Game (*component1)(
                                        libkn_kref_com_tmm_examples_fallingballs_PieceData thiz);

                                libkn_KFloat (*component2)(
                                        libkn_kref_com_tmm_examples_fallingballs_PieceData thiz);

                                libkn_KULong (*component3)(
                                        libkn_kref_com_tmm_examples_fallingballs_PieceData thiz);

                                libkn_kref_com_tmm_examples_fallingballs_PieceData
                                (*copy)(libkn_kref_com_tmm_examples_fallingballs_PieceData thiz,
                                        libkn_kref_com_tmm_examples_fallingballs_Game game,
                                        libkn_KFloat velocity, libkn_KULong color);

                                libkn_KBoolean
                                (*equals)(libkn_kref_com_tmm_examples_fallingballs_PieceData thiz,
                                          libkn_kref_kotlin_Any other);

                                libkn_KInt (*hashCode)(
                                        libkn_kref_com_tmm_examples_fallingballs_PieceData thiz);

                                const char *(*toString)(
                                        libkn_kref_com_tmm_examples_fallingballs_PieceData thiz);

                                void
                                (*update)(libkn_kref_com_tmm_examples_fallingballs_PieceData thiz,
                                          libkn_KLong dt);
                            } PieceData;
                        } fallingballs;

                        struct {
                            struct {
                                libkn_KType *(*_type)(void);

                                libkn_kref_com_tmm_examples_visualeffects_DoubleRocket
                                (*DoubleRocket)(
                                        libkn_kref_com_tmm_examples_visualeffects_Particle particle);

                                libkn_kref_com_tmm_examples_visualeffects_Particle (*get_particle)(
                                        libkn_kref_com_tmm_examples_visualeffects_DoubleRocket thiz);

                                libkn_kref_kotlin_Array (*get_rockets)(
                                        libkn_kref_com_tmm_examples_visualeffects_DoubleRocket thiz);

                                void (*set_rockets)(
                                        libkn_kref_com_tmm_examples_visualeffects_DoubleRocket thiz,
                                        libkn_kref_kotlin_Array set);

                                libkn_KInt (*get_state)(
                                        libkn_kref_com_tmm_examples_visualeffects_DoubleRocket thiz);

                                void (*set_state)(
                                        libkn_kref_com_tmm_examples_visualeffects_DoubleRocket thiz,
                                        libkn_KInt set);

                                void
                                (*move)(libkn_kref_com_tmm_examples_visualeffects_DoubleRocket thiz,
                                        libkn_KLong timeElapsed, libkn_KLong deltaNanos);
                            } DoubleRocket;

                            struct {
                                libkn_KType *(*_type)(void);

                                libkn_kref_com_tmm_examples_visualeffects_Particle
                                (*Particle)(libkn_KDouble x, libkn_KDouble y, libkn_KDouble vx,
                                            libkn_KDouble vy, libkn_KULong color, libkn_KInt type);

                                libkn_KULong (*get_color)(
                                        libkn_kref_com_tmm_examples_visualeffects_Particle thiz);

                                libkn_KInt (*get_type)(
                                        libkn_kref_com_tmm_examples_visualeffects_Particle thiz);

                                libkn_KDouble
                                (*get_vx)(libkn_kref_com_tmm_examples_visualeffects_Particle thiz);

                                void
                                (*set_vx)(libkn_kref_com_tmm_examples_visualeffects_Particle thiz,
                                          libkn_KDouble set);

                                libkn_KDouble
                                (*get_vy)(libkn_kref_com_tmm_examples_visualeffects_Particle thiz);

                                void
                                (*set_vy)(libkn_kref_com_tmm_examples_visualeffects_Particle thiz,
                                          libkn_KDouble set);

                                libkn_KDouble
                                (*get_x)(libkn_kref_com_tmm_examples_visualeffects_Particle thiz);

                                void
                                (*set_x)(libkn_kref_com_tmm_examples_visualeffects_Particle thiz,
                                         libkn_KDouble set);

                                libkn_KDouble
                                (*get_y)(libkn_kref_com_tmm_examples_visualeffects_Particle thiz);

                                void
                                (*set_y)(libkn_kref_com_tmm_examples_visualeffects_Particle thiz,
                                         libkn_KDouble set);

                                void
                                (*gravity)(libkn_kref_com_tmm_examples_visualeffects_Particle thiz,
                                           libkn_KLong deltaNanos);

                                void
                                (*move)(libkn_kref_com_tmm_examples_visualeffects_Particle thiz,
                                        libkn_KLong deltaNanos);
                            } Particle;

                            struct {
                                libkn_KType *(*_type)(void);

                                libkn_kref_com_tmm_examples_visualeffects_Rocket (*Rocket)(
                                        libkn_kref_com_tmm_examples_visualeffects_Particle particle,
                                        libkn_KULong color, libkn_KLong startTime);

                                libkn_KULong
                                (*get_color)(libkn_kref_com_tmm_examples_visualeffects_Rocket thiz);

                                libkn_KBoolean (*get_exploded)(
                                        libkn_kref_com_tmm_examples_visualeffects_Rocket thiz);

                                void (*set_exploded)(
                                        libkn_kref_com_tmm_examples_visualeffects_Rocket thiz,
                                        libkn_KBoolean set);

                                libkn_kref_com_tmm_examples_visualeffects_Particle (*get_particle)(
                                        libkn_kref_com_tmm_examples_visualeffects_Rocket thiz);

                                libkn_kref_kotlin_Array
                                (*get_parts)(libkn_kref_com_tmm_examples_visualeffects_Rocket thiz);

                                void
                                (*set_parts)(libkn_kref_com_tmm_examples_visualeffects_Rocket thiz,
                                             libkn_kref_kotlin_Array set);

                                libkn_KLong (*get_startTime)(
                                        libkn_kref_com_tmm_examples_visualeffects_Rocket thiz);

                                libkn_KBoolean
                                (*checkDone)(libkn_kref_com_tmm_examples_visualeffects_Rocket thiz);

                                void (*checkExplode)(
                                        libkn_kref_com_tmm_examples_visualeffects_Rocket thiz,
                                        libkn_KLong timeElapsed);

                                void (*move)(libkn_kref_com_tmm_examples_visualeffects_Rocket thiz,
                                             libkn_KLong timeElapsed, libkn_KLong deltaNanos);
                            } Rocket;

                            struct {
                                libkn_KType *(*_type)(void);

                                libkn_kref_com_tmm_examples_visualeffects_SnowFlake
                                (*SnowFlake)(libkn_KFloat x, libkn_KFloat y, libkn_KFloat scale,
                                             libkn_KDouble v, libkn_KFloat alpha,
                                             libkn_KFloat angle, libkn_KInt rotate,
                                             libkn_KDouble phase);

                                libkn_KFloat (*get_alpha)(
                                        libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz);

                                void (*set_alpha)(
                                        libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz,
                                        libkn_KFloat set);

                                libkn_KFloat (*get_angle)(
                                        libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz);

                                void (*set_angle)(
                                        libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz,
                                        libkn_KFloat set);

                                libkn_KDouble (*get_phase)(
                                        libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz);

                                void (*set_phase)(
                                        libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz,
                                        libkn_KDouble set);

                                libkn_KInt (*get_rotate)(
                                        libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz);

                                void (*set_rotate)(
                                        libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz,
                                        libkn_KInt set);

                                libkn_KFloat (*get_scale)(
                                        libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz);

                                libkn_KDouble
                                (*get_v)(libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz);

                                void
                                (*set_v)(libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz,
                                         libkn_KDouble set);

                                libkn_KFloat
                                (*get_x)(libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz);

                                void
                                (*set_x)(libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz,
                                         libkn_KFloat set);

                                libkn_KFloat
                                (*get_y)(libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz);

                                void
                                (*set_y)(libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz,
                                         libkn_KFloat set);

                                libkn_KFloat (*component1)(
                                        libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz);

                                libkn_KFloat (*component2)(
                                        libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz);

                                libkn_KFloat (*component3)(
                                        libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz);

                                libkn_KDouble (*component4)(
                                        libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz);

                                libkn_KFloat (*component5)(
                                        libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz);

                                libkn_KFloat (*component6)(
                                        libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz);

                                libkn_KInt (*component7)(
                                        libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz);

                                libkn_KDouble (*component8)(
                                        libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz);

                                libkn_kref_com_tmm_examples_visualeffects_SnowFlake
                                (*copy)(libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz,
                                        libkn_KFloat x, libkn_KFloat y, libkn_KFloat scale,
                                        libkn_KDouble v, libkn_KFloat alpha, libkn_KFloat angle,
                                        libkn_KInt rotate, libkn_KDouble phase);

                                libkn_KBoolean
                                (*equals)(libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz,
                                          libkn_kref_kotlin_Any other);

                                libkn_KInt (*hashCode)(
                                        libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz);

                                const char *(*toString)(
                                        libkn_kref_com_tmm_examples_visualeffects_SnowFlake thiz);
                            } SnowFlake;

                            struct {
                                libkn_KType *(*_type)(void);

                                libkn_kref_com_tmm_examples_visualeffects_Star
                                (*Star)(libkn_KFloat x, libkn_KFloat y, libkn_KULong color,
                                        libkn_KFloat size);

                                libkn_KULong
                                (*get_color)(libkn_kref_com_tmm_examples_visualeffects_Star thiz);

                                libkn_KFloat
                                (*get_size)(libkn_kref_com_tmm_examples_visualeffects_Star thiz);

                                libkn_KFloat
                                (*get_x)(libkn_kref_com_tmm_examples_visualeffects_Star thiz);

                                libkn_KFloat
                                (*get_y)(libkn_kref_com_tmm_examples_visualeffects_Star thiz);

                                libkn_KFloat
                                (*component1)(libkn_kref_com_tmm_examples_visualeffects_Star thiz);

                                libkn_KFloat
                                (*component2)(libkn_kref_com_tmm_examples_visualeffects_Star thiz);

                                libkn_KULong
                                (*component3)(libkn_kref_com_tmm_examples_visualeffects_Star thiz);

                                libkn_KFloat
                                (*component4)(libkn_kref_com_tmm_examples_visualeffects_Star thiz);

                                libkn_kref_com_tmm_examples_visualeffects_Star
                                (*copy)(libkn_kref_com_tmm_examples_visualeffects_Star thiz,
                                        libkn_KFloat x, libkn_KFloat y, libkn_KULong color,
                                        libkn_KFloat size);

                                libkn_KBoolean
                                (*equals)(libkn_kref_com_tmm_examples_visualeffects_Star thiz,
                                          libkn_kref_kotlin_Any other);

                                libkn_KInt
                                (*hashCode)(libkn_kref_com_tmm_examples_visualeffects_Star thiz);

                                const char *
                                (*toString)(libkn_kref_com_tmm_examples_visualeffects_Star thiz);
                            } Star;

                            const char *(*get_HNYString)();

                            libkn_KInt (*get_height)();

                            libkn_kref_com_tmm_examples_visualeffects_DoubleRocket (*get_rocket)();

                            libkn_KInt (*get_rocketPartsCount)();

                            libkn_KInt (*get_snowCount)();

                            libkn_KInt (*get_starCount)();

                            libkn_KInt (*get_width)();

                            libkn_KFloat (*alphaHNY)(libkn_KInt i, libkn_KLong timeElapsed);

                            libkn_KULong (*blend)(libkn_KULong color1, libkn_KULong color2,
                                                  libkn_KFloat fraction);

                            libkn_KULong (*colorHNY)(libkn_KLong timeElapsed);

                            libkn_KFloat (*flickeringAlpha)(libkn_KLong time);

                            libkn_KInt (*period)(libkn_KLong timeElapsed, libkn_KInt periodLength,
                                                 libkn_KInt speed);

                            void (*prepareStarsAndSnowFlakes)(
                                    libkn_kref_androidx_compose_runtime_snapshots_SnapshotStateList stars,
                                    libkn_kref_androidx_compose_runtime_snapshots_SnapshotStateList snowFlakes);

                            libkn_KFloat (*random)();
                        } visualeffects;

                        const char *(*toFixed)(libkn_KDouble thiz, libkn_KInt digits);
                    } examples;
                } tmm;
            } com;
            struct {
                struct {
                    struct {
                        struct {
                            struct {
                                libkn_KType *(*_type)(void);

                                libkn_kref_net_novate_kotlin_MR_assets (*_instance)();

                                libkn_kref_com_tencent_tmm_kmmresource_resource_AssetResource
                                (*get_some_asset)(libkn_kref_net_novate_kotlin_MR_assets thiz);
                            } assets;

                            struct {
                                libkn_KType *(*_type)(void);

                                libkn_kref_net_novate_kotlin_MR_colors (*_instance)();

                                libkn_kref_com_tencent_tmm_kmmresource_resource_ColorResource
                                (*get_textColor)(libkn_kref_net_novate_kotlin_MR_colors thiz);
                            } colors;

                            struct {
                                libkn_KType *(*_type)(void);

                                libkn_kref_net_novate_kotlin_MR_files (*_instance)();
                            } files;

                            struct {
                                libkn_KType *(*_type)(void);

                                libkn_kref_net_novate_kotlin_MR_fonts (*_instance)();
                            } fonts;

                            struct {
                                libkn_KType *(*_type)(void);

                                libkn_kref_net_novate_kotlin_MR_images (*_instance)();
                            } images;

                            struct {
                                libkn_KType *(*_type)(void);

                                libkn_kref_net_novate_kotlin_MR_plurals (*_instance)();

                                libkn_kref_com_tencent_tmm_kmmresource_resource_PluralsResource
                                (*get_chars_count)(libkn_kref_net_novate_kotlin_MR_plurals thiz);
                            } plurals;

                            struct {
                                libkn_KType *(*_type)(void);

                                libkn_kref_net_novate_kotlin_MR_strings (*_instance)();

                                libkn_kref_com_tencent_tmm_kmmresource_resource_StringResource
                                (*get_hello_ohos)(libkn_kref_net_novate_kotlin_MR_strings thiz);

                                libkn_kref_com_tencent_tmm_kmmresource_resource_StringResource
                                (*get_hello_world)(libkn_kref_net_novate_kotlin_MR_strings thiz);
                            } strings;

                            libkn_KType *(*_type)(void);

                            libkn_kref_net_novate_kotlin_MR (*_instance)();
                        } MR;

                        void (*InitEnv_)(void *env, void *export_);

                        void *(*ktHandleMethodFromOH)(void *env, void *napiCallbackInfo);

                        libkn_KInt (*testSum_)(libkn_KInt a, libkn_KInt b);
                    } kotlin;
                } novate;
            } net;
        } root;
    } kotlin;
} libkn_ExportedSymbols;

extern libkn_ExportedSymbols *libkn_symbols(void);

#ifdef __cplusplus
}  /* extern "C" */
#endif
#endif  /* KONAN_LIBKN_H */
