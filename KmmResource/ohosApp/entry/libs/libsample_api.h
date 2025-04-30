#ifndef KONAN_LIBSAMPLE_H
#define KONAN_LIBSAMPLE_H
#ifdef __cplusplus
extern "C" {
#endif
#ifdef __cplusplus
typedef bool            libsample_KBoolean;
#else
typedef _Bool libsample_KBoolean;
#endif
typedef unsigned short libsample_KChar;
typedef signed char libsample_KByte;
typedef short libsample_KShort;
typedef int libsample_KInt;
typedef long long libsample_KLong;
typedef unsigned char libsample_KUByte;
typedef unsigned short libsample_KUShort;
typedef unsigned int libsample_KUInt;
typedef unsigned long long libsample_KULong;
typedef float libsample_KFloat;
typedef double libsample_KDouble;
typedef float __attribute__ ((__vector_size__ (16))) libsample_KVector128;
typedef void *libsample_KNativePtr;
struct libsample_KType;
typedef struct libsample_KType libsample_KType;

typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_Byte;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_Short;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_Int;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_Long;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_Float;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_Double;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_Char;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_Boolean;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_Unit;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_UByte;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_UShort;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_UInt;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_ULong;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_type_ArrayBuffer;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_Array;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_Function1;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_type_JSValue;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_collections_List;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_collections_Map;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_collections_MutableMap;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_collections_SafeHashMap;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_reflect_KClass;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_converter_TypeConverter;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_Any;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_converter_ArrayBufferTypeConverter;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_platform_ohos_napi_valuetype;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_converter_ArrayTypeConverter;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_converter_BooleanTypeConverter;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_converter_DoubleTypeConverter;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_converter_IntTypeConverter;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_converter_JSCallbackTypeConverter;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_converter_JSValueTypeConverter;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_converter_ListTypeConverter;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_converter_LongTypeConverter;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_converter_MapTypeConverter;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_converter_StringTypeConverter;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_converter_UnitTypeConverter;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_exception_FunctionNotRegisterException;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_exception_ReturnTypeErrorException;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_exception_UnSupportTypeException;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_jsbind_JSFunction;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_kotlin_Function0;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_register_FunctionBindInfo;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_register_FunctionRegister;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_register_JSFunctionRegister;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_platform_ohos_napi_typedarray_type;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_type_JSValue_Companion;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_MR;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_knoi_MR_strings;
typedef struct {
    libsample_KNativePtr pinned;
} libsample_kref_com_tencent_tmm_kmmresource_resource_StringResource;

extern void com_tencent_tmm_knoi_initEnv(void *env, void *export_, libsample_KBoolean debug);

extern void com_tencent_tmm_knoi_initTestFunc();

typedef struct {
    /* Service functions. */
    void (*DisposeStablePointer)(libsample_KNativePtr ptr);

    void (*DisposeString)(const char *string);

    libsample_KBoolean (*IsInstance)(libsample_KNativePtr ref, const libsample_KType *type);

    libsample_kref_kotlin_Byte (*createNullableByte)(libsample_KByte);

    libsample_KByte (*getNonNullValueOfByte)(libsample_kref_kotlin_Byte);

    libsample_kref_kotlin_Short (*createNullableShort)(libsample_KShort);

    libsample_KShort (*getNonNullValueOfShort)(libsample_kref_kotlin_Short);

    libsample_kref_kotlin_Int (*createNullableInt)(libsample_KInt);

    libsample_KInt (*getNonNullValueOfInt)(libsample_kref_kotlin_Int);

    libsample_kref_kotlin_Long (*createNullableLong)(libsample_KLong);

    libsample_KLong (*getNonNullValueOfLong)(libsample_kref_kotlin_Long);

    libsample_kref_kotlin_Float (*createNullableFloat)(libsample_KFloat);

    libsample_KFloat (*getNonNullValueOfFloat)(libsample_kref_kotlin_Float);

    libsample_kref_kotlin_Double (*createNullableDouble)(libsample_KDouble);

    libsample_KDouble (*getNonNullValueOfDouble)(libsample_kref_kotlin_Double);

    libsample_kref_kotlin_Char (*createNullableChar)(libsample_KChar);

    libsample_KChar (*getNonNullValueOfChar)(libsample_kref_kotlin_Char);

    libsample_kref_kotlin_Boolean (*createNullableBoolean)(libsample_KBoolean);

    libsample_KBoolean (*getNonNullValueOfBoolean)(libsample_kref_kotlin_Boolean);

    libsample_kref_kotlin_Unit (*createNullableUnit)(void);

    libsample_kref_kotlin_UByte (*createNullableUByte)(libsample_KUByte);

    libsample_KUByte (*getNonNullValueOfUByte)(libsample_kref_kotlin_UByte);

    libsample_kref_kotlin_UShort (*createNullableUShort)(libsample_KUShort);

    libsample_KUShort (*getNonNullValueOfUShort)(libsample_kref_kotlin_UShort);

    libsample_kref_kotlin_UInt (*createNullableUInt)(libsample_KUInt);

    libsample_KUInt (*getNonNullValueOfUInt)(libsample_kref_kotlin_UInt);

    libsample_kref_kotlin_ULong (*createNullableULong)(libsample_KULong);

    libsample_KULong (*getNonNullValueOfULong)(libsample_kref_kotlin_ULong);

    /* User functions. */
    struct {
        struct {
            struct {
                struct {
                    struct {
                        struct {
                            struct {
                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_converter_ArrayBufferTypeConverter
                                    (*ArrayBufferTypeConverter)();

                                    libsample_kref_com_tencent_tmm_knoi_type_ArrayBuffer
                                    (*convertJSValueToKotlinValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_ArrayBufferTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_ArrayBufferTypeConverter thiz,
                                            void *env,
                                            libsample_kref_com_tencent_tmm_knoi_type_ArrayBuffer value);

                                    libsample_kref_kotlin_reflect_KClass (*getKType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_ArrayBufferTypeConverter thiz);

                                    libsample_KBoolean (*isSupportJSType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_ArrayBufferTypeConverter thiz,
                                            void *env,
                                            libsample_kref_platform_ohos_napi_valuetype type,
                                            void *value);

                                    libsample_KBoolean (*isSupportKType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_ArrayBufferTypeConverter thiz,
                                            libsample_kref_kotlin_reflect_KClass type);
                                } ArrayBufferTypeConverter;

                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_converter_ArrayTypeConverter
                                    (*ArrayTypeConverter)();

                                    libsample_kref_kotlin_Array (*convertJSValueToKotlinValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_ArrayTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_ArrayTypeConverter thiz,
                                            void *env, libsample_kref_kotlin_Array value);

                                    libsample_kref_kotlin_reflect_KClass (*getKType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_ArrayTypeConverter thiz);

                                    libsample_KBoolean (*isSupportKType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_ArrayTypeConverter thiz,
                                            libsample_kref_kotlin_reflect_KClass type);
                                } ArrayTypeConverter;

                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_converter_BooleanTypeConverter
                                    (*BooleanTypeConverter)();

                                    libsample_KBoolean (*convertJSValueToKotlinValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_BooleanTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_BooleanTypeConverter thiz,
                                            void *env, libsample_kref_kotlin_Boolean value);

                                    libsample_kref_platform_ohos_napi_valuetype (*getJSType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_BooleanTypeConverter thiz);

                                    libsample_kref_kotlin_reflect_KClass (*getKType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_BooleanTypeConverter thiz);
                                } BooleanTypeConverter;

                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_converter_DoubleTypeConverter
                                    (*DoubleTypeConverter)();

                                    libsample_kref_kotlin_Double (*convertJSValueToKotlinValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_DoubleTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_DoubleTypeConverter thiz,
                                            void *env, libsample_kref_kotlin_Double value);

                                    libsample_kref_platform_ohos_napi_valuetype (*getJSType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_DoubleTypeConverter thiz);

                                    libsample_kref_kotlin_reflect_KClass (*getKType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_DoubleTypeConverter thiz);
                                } DoubleTypeConverter;

                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_converter_IntTypeConverter
                                    (*IntTypeConverter)();

                                    libsample_KInt (*convertJSValueToKotlinValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_IntTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_IntTypeConverter thiz,
                                            void *env, libsample_kref_kotlin_Int value);

                                    libsample_kref_kotlin_reflect_KClass (*getKType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_IntTypeConverter thiz);
                                } IntTypeConverter;

                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_converter_JSCallbackTypeConverter
                                    (*JSCallbackTypeConverter)();

                                    libsample_kref_kotlin_Function1 (*convertJSValueToKotlinValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_JSCallbackTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_JSCallbackTypeConverter thiz,
                                            void *env, libsample_kref_kotlin_Function1 value);

                                    libsample_kref_platform_ohos_napi_valuetype (*getJSType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_JSCallbackTypeConverter thiz);

                                    libsample_kref_kotlin_reflect_KClass (*getKType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_JSCallbackTypeConverter thiz);
                                } JSCallbackTypeConverter;

                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_converter_JSValueTypeConverter
                                    (*JSValueTypeConverter)();

                                    libsample_kref_com_tencent_tmm_knoi_type_JSValue
                                    (*convertJSValueToKotlinValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_JSValueTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_JSValueTypeConverter thiz,
                                            void *env,
                                            libsample_kref_com_tencent_tmm_knoi_type_JSValue value);

                                    libsample_kref_kotlin_reflect_KClass (*getKType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_JSValueTypeConverter thiz);
                                } JSValueTypeConverter;

                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_converter_ListTypeConverter
                                    (*ListTypeConverter)();

                                    libsample_kref_kotlin_collections_List
                                    (*convertJSValueToKotlinValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_ListTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_ListTypeConverter thiz,
                                            void *env,
                                            libsample_kref_kotlin_collections_List value);

                                    libsample_kref_kotlin_reflect_KClass (*getKType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_ListTypeConverter thiz);

                                    libsample_KBoolean (*isSupportJSType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_ListTypeConverter thiz,
                                            void *env,
                                            libsample_kref_platform_ohos_napi_valuetype type,
                                            void *value);

                                    libsample_KBoolean (*isSupportKType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_ListTypeConverter thiz,
                                            libsample_kref_kotlin_reflect_KClass type);
                                } ListTypeConverter;

                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_converter_LongTypeConverter
                                    (*LongTypeConverter)();

                                    libsample_KLong (*convertJSValueToKotlinValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_LongTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_LongTypeConverter thiz,
                                            void *env, libsample_kref_kotlin_Long value);

                                    libsample_kref_kotlin_reflect_KClass (*getKType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_LongTypeConverter thiz);
                                } LongTypeConverter;

                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_converter_MapTypeConverter
                                    (*MapTypeConverter)();

                                    libsample_kref_kotlin_collections_Map
                                    (*convertJSValueToKotlinValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_MapTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_MapTypeConverter thiz,
                                            void *env, libsample_kref_kotlin_collections_Map value);

                                    libsample_kref_platform_ohos_napi_valuetype (*getJSType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_MapTypeConverter thiz);

                                    libsample_kref_kotlin_reflect_KClass (*getKType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_MapTypeConverter thiz);

                                    libsample_KBoolean (*isSupportKType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_MapTypeConverter thiz,
                                            libsample_kref_kotlin_reflect_KClass type);
                                } MapTypeConverter;

                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_converter_StringTypeConverter
                                    (*StringTypeConverter)();

                                    const char *(*convertJSValueToKotlinValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_StringTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_StringTypeConverter thiz,
                                            void *env, const char *value);

                                    libsample_kref_platform_ohos_napi_valuetype (*getJSType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_StringTypeConverter thiz);

                                    libsample_kref_kotlin_reflect_KClass (*getKType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_StringTypeConverter thiz);
                                } StringTypeConverter;

                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_converter_UnitTypeConverter
                                    (*UnitTypeConverter)();

                                    void (*convertJSValueToKotlinValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_UnitTypeConverter thiz,
                                            void *env, void *value);

                                    void *(*convertKotlinValueToJSValue)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_UnitTypeConverter thiz,
                                            void *env, libsample_kref_kotlin_Unit value);

                                    libsample_kref_kotlin_reflect_KClass (*getKType)(
                                            libsample_kref_com_tencent_tmm_knoi_converter_UnitTypeConverter thiz);
                                } UnitTypeConverter;

                                libsample_kref_kotlin_Array (*get_converters)();

                                libsample_kref_com_tencent_tmm_knoi_collections_SafeHashMap
                                (*get_ktFunctionMap)();

                                libsample_kref_com_tencent_tmm_knoi_converter_TypeConverter
                                (*getFirstSupportConverter)(
                                        libsample_kref_kotlin_reflect_KClass type);

                                libsample_kref_com_tencent_tmm_knoi_converter_TypeConverter
                                (*getFirstSupportConverter_)(void *env, void *value);

                                libsample_kref_kotlin_Any
                                (*jsValueToKTValue)(void *env, void *value,
                                                    libsample_kref_kotlin_reflect_KClass kType);

                                void *
                                (*ktValueToJSValue)(void *env, libsample_kref_kotlin_Any value,
                                                    libsample_kref_kotlin_reflect_KClass clazz);
                            } converter;

                            struct {
                                const char *(*get_METHOD_NAME_NAPI)();

                                void
                                (*bind)(const char *name, libsample_kref_kotlin_Function1 function,
                                        libsample_kref_kotlin_reflect_KClass returnType,
                                        libsample_kref_kotlin_Array paramsTypes);

                                void (*unBind)(const char *name);
                            } definder;

                            struct {
                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_exception_FunctionNotRegisterException
                                    (*FunctionNotRegisterException)(const char *methodName);
                                } FunctionNotRegisterException;

                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_exception_ReturnTypeErrorException
                                    (*ReturnTypeErrorException)();
                                } ReturnTypeErrorException;

                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_exception_UnSupportTypeException
                                    (*UnSupportTypeException)(const char *msg);
                                } UnSupportTypeException;
                            } exception;

                            struct {
                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_jsbind_JSFunction
                                    (*JSFunction)(void *env, const char *name, void *jsCallback);

                                    libsample_KInt (*get_bindTid)(
                                            libsample_kref_com_tencent_tmm_knoi_jsbind_JSFunction thiz);

                                    void *(*get_cbRef)(
                                            libsample_kref_com_tencent_tmm_knoi_jsbind_JSFunction thiz);

                                    void *(*get_env)(
                                            libsample_kref_com_tencent_tmm_knoi_jsbind_JSFunction thiz);

                                    const char *(*get_name)(
                                            libsample_kref_com_tencent_tmm_knoi_jsbind_JSFunction thiz);

                                    libsample_kref_kotlin_Any (*invokeDirect)(
                                            libsample_kref_com_tencent_tmm_knoi_jsbind_JSFunction thiz,
                                            libsample_kref_kotlin_Array params,
                                            libsample_kref_kotlin_reflect_KClass kType);

                                    libsample_kref_kotlin_Any (*invokeIndirect)(
                                            libsample_kref_com_tencent_tmm_knoi_jsbind_JSFunction thiz,
                                            libsample_KBoolean sync,
                                            libsample_kref_kotlin_Array params,
                                            libsample_kref_kotlin_reflect_KClass kType);

                                    void (*release)(
                                            libsample_kref_com_tencent_tmm_knoi_jsbind_JSFunction thiz);
                                } JSFunction;

                                const char *(*get_JS_BIND_METHOD_NAME)();

                                const char *(*get_JS_UNBIND_METHOD_NAME)();

                                libsample_kref_com_tencent_tmm_knoi_jsbind_JSFunction
                                (*getJSFunction)(const char *name);
                            } jsbind;

                            struct {
                                libsample_KUInt (*get_domain)();

                                libsample_KBoolean (*get_isDebug)();

                                void (*set_isDebug)(libsample_KBoolean set);

                                const char *(*get_tag)();

                                void (*check)(const char *message,
                                              libsample_kref_kotlin_Function0 condition);

                                void (*debug)(const char *message);

                                void (*error)(const char *message);

                                void (*info)(const char *message);

                                void (*log)(libsample_KUInt level, const char *message);
                            } logger;

                            struct {
                                void (*defineFunctionToExport)(void *env, void *export_,
                                                               const char *properties, void *func);

                                libsample_kref_kotlin_Any
                                (*safeCaseNumberType)(libsample_kref_kotlin_Any value,
                                                      libsample_kref_kotlin_reflect_KClass type);
                            } napi;

                            struct {
                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_register_FunctionBindInfo
                                    (*FunctionBindInfo)(const char *name,
                                                        libsample_kref_kotlin_Function1 originFun,
                                                        libsample_kref_kotlin_reflect_KClass returnType,
                                                        libsample_kref_kotlin_Array paramsType);

                                    const char *(*get_name)(
                                            libsample_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz);

                                    libsample_kref_kotlin_Function1 (*get_originFun)(
                                            libsample_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz);

                                    libsample_kref_kotlin_Array (*get_paramsType)(
                                            libsample_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz);

                                    libsample_kref_kotlin_reflect_KClass (*get_returnType)(
                                            libsample_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz);

                                    const char *(*component1)(
                                            libsample_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz);

                                    libsample_kref_kotlin_Function1 (*component2)(
                                            libsample_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz);

                                    libsample_kref_kotlin_reflect_KClass (*component3)(
                                            libsample_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz);

                                    libsample_kref_kotlin_Array (*component4)(
                                            libsample_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz);

                                    libsample_kref_com_tencent_tmm_knoi_register_FunctionBindInfo
                                    (*copy)(libsample_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz,
                                            const char *name,
                                            libsample_kref_kotlin_Function1 originFun,
                                            libsample_kref_kotlin_reflect_KClass returnType,
                                            libsample_kref_kotlin_Array paramsType);

                                    libsample_KBoolean (*equals)(
                                            libsample_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz,
                                            libsample_kref_kotlin_Any other);

                                    libsample_KInt (*hashCode)(
                                            libsample_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz);

                                    const char *(*toString)(
                                            libsample_kref_com_tencent_tmm_knoi_register_FunctionBindInfo thiz);
                                } FunctionBindInfo;

                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_register_FunctionRegister
                                    (*FunctionRegister)();

                                    libsample_kref_kotlin_Function1 (*getFunction)(
                                            libsample_kref_com_tencent_tmm_knoi_register_FunctionRegister thiz,
                                            const char *name);

                                    libsample_kref_kotlin_Array (*getParamTypes)(
                                            libsample_kref_com_tencent_tmm_knoi_register_FunctionRegister thiz,
                                            const char *name);

                                    libsample_kref_kotlin_reflect_KClass (*getReturnType)(
                                            libsample_kref_com_tencent_tmm_knoi_register_FunctionRegister thiz,
                                            const char *name);

                                    void (*register_)(
                                            libsample_kref_com_tencent_tmm_knoi_register_FunctionRegister thiz,
                                            libsample_kref_com_tencent_tmm_knoi_register_FunctionBindInfo bindInfo);

                                    void (*unRegister)(
                                            libsample_kref_com_tencent_tmm_knoi_register_FunctionRegister thiz,
                                            const char *name);
                                } FunctionRegister;

                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_register_JSFunctionRegister
                                    (*JSFunctionRegister)();

                                    libsample_kref_com_tencent_tmm_knoi_jsbind_JSFunction
                                    (*getJSFunction)(
                                            libsample_kref_com_tencent_tmm_knoi_register_JSFunctionRegister thiz,
                                            const char *name);

                                    void (*register_)(
                                            libsample_kref_com_tencent_tmm_knoi_register_JSFunctionRegister thiz,
                                            libsample_kref_com_tencent_tmm_knoi_jsbind_JSFunction jsFunction);

                                    void (*unregister)(
                                            libsample_kref_com_tencent_tmm_knoi_register_JSFunctionRegister thiz,
                                            const char *name);
                                } JSFunctionRegister;
                            } register_;
                            struct {
                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_type_ArrayBuffer
                                    (*ArrayBuffer)(void *data, libsample_KLong length,
                                                   libsample_kref_platform_ohos_napi_typedarray_type type);

                                    libsample_kref_com_tencent_tmm_knoi_type_ArrayBuffer
                                    (*ArrayBuffer_)(void *handle);

                                    void *(*get_handle)(
                                            libsample_kref_com_tencent_tmm_knoi_type_ArrayBuffer thiz);

                                    libsample_KLong (*getCount)(
                                            libsample_kref_com_tencent_tmm_knoi_type_ArrayBuffer thiz);
                                } ArrayBuffer;

                                struct {
                                    struct {
                                        libsample_KType *(*_type)(void);

                                        libsample_kref_com_tencent_tmm_knoi_type_JSValue_Companion
                                        (*_instance)();

                                        libsample_kref_com_tencent_tmm_knoi_type_JSValue
                                        (*createJSObject)(
                                                libsample_kref_com_tencent_tmm_knoi_type_JSValue_Companion thiz);

                                        libsample_kref_com_tencent_tmm_knoi_type_JSValue (*global)(
                                                libsample_kref_com_tencent_tmm_knoi_type_JSValue_Companion thiz);
                                    } Companion;

                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_type_JSValue
                                    (*JSValue)(void *handle);

                                    void *(*get_handle)(
                                            libsample_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libsample_kref_com_tencent_tmm_knoi_type_JSValue (*callMethod)(
                                            libsample_kref_com_tencent_tmm_knoi_type_JSValue thiz,
                                            const char *name, libsample_kref_kotlin_Array params);

                                    libsample_kref_com_tencent_tmm_knoi_type_JSValue
                                    (*get)(libsample_kref_com_tencent_tmm_knoi_type_JSValue thiz,
                                           libsample_KInt index);

                                    libsample_kref_com_tencent_tmm_knoi_type_JSValue
                                    (*get_)(libsample_kref_com_tencent_tmm_knoi_type_JSValue thiz,
                                            const char *key);

                                    libsample_KBoolean (*isArrayType)(
                                            libsample_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libsample_KBoolean (*isBoolean)(
                                            libsample_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libsample_KBoolean (*isFunction)(
                                            libsample_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libsample_KBoolean (*isNull)(
                                            libsample_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libsample_KBoolean (*isNumber)(
                                            libsample_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libsample_KBoolean (*isObject)(
                                            libsample_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libsample_KBoolean (*isString)(
                                            libsample_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libsample_KBoolean (*isUndefined)(
                                            libsample_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libsample_KBoolean
                                    (*set)(libsample_kref_com_tencent_tmm_knoi_type_JSValue thiz,
                                           libsample_KInt index,
                                           libsample_kref_com_tencent_tmm_knoi_type_JSValue value);

                                    libsample_KBoolean
                                    (*set_)(libsample_kref_com_tencent_tmm_knoi_type_JSValue thiz,
                                            const char *key,
                                            libsample_kref_com_tencent_tmm_knoi_type_JSValue value);

                                    libsample_kref_kotlin_Any (*toBoolean)(
                                            libsample_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libsample_kref_kotlin_Any (*toDouble)(
                                            libsample_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libsample_kref_kotlin_Any
                                    (*toInt)(libsample_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libsample_kref_kotlin_Any (*toKString)(
                                            libsample_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libsample_kref_kotlin_Any (*toLong)(
                                            libsample_kref_com_tencent_tmm_knoi_type_JSValue thiz);

                                    libsample_kref_kotlin_Any
                                    (*toMap)(libsample_kref_com_tencent_tmm_knoi_type_JSValue thiz);
                                } JSValue;
                            } type;

                            struct {
                                struct {
                                    libsample_KType *(*_type)(void);

                                    libsample_kref_com_tencent_tmm_knoi_MR_strings (*_instance)();

                                    libsample_kref_com_tencent_tmm_kmmresource_resource_StringResource
                                    (*get_hello_world)(
                                            libsample_kref_com_tencent_tmm_knoi_MR_strings thiz);
                                } strings;

                                libsample_KType *(*_type)(void);

                                libsample_kref_com_tencent_tmm_knoi_MR (*_instance)();
                            } MR;

                            void (*InitEnv)(void *env, void *export_, libsample_KBoolean debug);

                            void *(*getEnv)();

                            void (*setEnv)(void *env);

                            void (*registerTestFunction)();

                            libsample_kref_com_tencent_tmm_knoi_type_ArrayBuffer
                            (*testArrayBufferReturnArrayBuffer)(
                                    libsample_kref_com_tencent_tmm_knoi_type_ArrayBuffer buffer);

                            libsample_kref_com_tencent_tmm_knoi_type_ArrayBuffer
                            (*testArrayBufferReturnArrayBuffer_)(
                                    libsample_kref_kotlin_Array params);

                            libsample_kref_kotlin_Array
                            (*testArrayReturnArray)(libsample_kref_kotlin_Array array);

                            libsample_kref_kotlin_Array
                            (*testArrayReturnArray_)(libsample_kref_kotlin_Array params);

                            libsample_KBoolean (*testBoolReturnBool)(libsample_KBoolean result);

                            libsample_KBoolean
                            (*testBooleanReturnBoolean)(libsample_kref_kotlin_Array params);

                            libsample_KDouble
                            (*testDoubleReturnDouble)(libsample_kref_kotlin_Array params);

                            libsample_KDouble (*testDoubleReturnDouble_)(libsample_KDouble result);

                            libsample_KInt (*testIntReturnInt)(libsample_kref_kotlin_Array params);

                            libsample_KInt (*testIntReturnInt_)(libsample_KInt number);

                            libsample_kref_kotlin_Function1 (*testJSCallbackReturnJSCallback)(
                                    libsample_kref_kotlin_Function1 function);

                            libsample_kref_kotlin_Function1
                            (*testJSCallbackReturnJSCallback_)(libsample_kref_kotlin_Array params);

                            void
                            (*testJSCallbackReturnMap)(libsample_kref_kotlin_Function1 function);

                            void (*testJSCallbackReturnMap_)(libsample_kref_kotlin_Array params);

                            void
                            (*testJSCallbackReturnString)(libsample_kref_kotlin_Function1 function);

                            void (*testJSCallbackReturnString_)(libsample_kref_kotlin_Array params);

                            void
                            (*testJSCallbackReturnVoid)(libsample_kref_kotlin_Function1 function);

                            void (*testJSCallbackReturnVoid_)(libsample_kref_kotlin_Array params);

                            void (*testJSFunction)();

                            void (*testJSFunction_)(libsample_kref_kotlin_Array params);

                            void (*testJSFunctionSubThread)();

                            void (*testJSValue)(libsample_kref_kotlin_Array params);

                            libsample_kref_com_tencent_tmm_knoi_type_JSValue
                            (*testJSValueReturnJSValue)(
                                    libsample_kref_com_tencent_tmm_knoi_type_JSValue value);

                            libsample_kref_com_tencent_tmm_knoi_type_JSValue
                            (*testJSValueReturnJSValue_)(libsample_kref_kotlin_Array params);

                            libsample_kref_kotlin_collections_List
                            (*testListReturnList)(libsample_kref_kotlin_Array params);

                            libsample_kref_kotlin_collections_List
                            (*testListReturnList_)(libsample_kref_kotlin_collections_List array);

                            libsample_KLong
                            (*testLongReturnLong)(libsample_kref_kotlin_Array params);

                            libsample_KLong (*testLongReturnLong_)(libsample_KLong number);

                            libsample_kref_kotlin_collections_Map
                            (*testMapReturnMap)(libsample_kref_kotlin_Array params);

                            libsample_kref_kotlin_collections_Map (*testMapReturnMap_)(
                                    libsample_kref_kotlin_collections_MutableMap result);

                            const char *(*testStringFunction)(const char *name);

                            const char *
                            (*testStringReturnString)(libsample_kref_kotlin_Array params);

                            const char *(*testVoidReturnString)();

                            const char *
                            (*testVoidReturnString_)(libsample_kref_kotlin_Array params);

                            void (*testVoidReturnVoid)();

                            void (*testVoidReturnVoid_)(libsample_kref_kotlin_Array params);
                        } knoi;
                    } tmm;
                } tencent;
            } com;
        } root;
    } kotlin;
} libsample_ExportedSymbols;

extern libsample_ExportedSymbols *libsample_symbols(void);

#ifdef __cplusplus
}  /* extern "C" */
#endif
#endif  /* KONAN_LIBSAMPLE_H */
