#ifndef KONAN_LIBKN_H
#define KONAN_LIBKN_H
#ifdef __cplusplus
extern "C" {
#endif
#ifdef __cplusplus
typedef bool            libkn_KBoolean;
#else
typedef _Bool           libkn_KBoolean;
#endif
typedef unsigned short     libkn_KChar;
typedef signed char        libkn_KByte;
typedef short              libkn_KShort;
typedef int                libkn_KInt;
typedef long long          libkn_KLong;
typedef unsigned char      libkn_KUByte;
typedef unsigned short     libkn_KUShort;
typedef unsigned int       libkn_KUInt;
typedef unsigned long long libkn_KULong;
typedef float              libkn_KFloat;
typedef double             libkn_KDouble;
typedef float __attribute__ ((__vector_size__ (16))) libkn_KVector128;
typedef void*              libkn_KNativePtr;
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
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_protocol_vb_pb_kmm_EnvInfo;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_collections_Map;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_collections_List;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_protocol_vb_pb_kmm_UserStatusInfo;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBLog;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Throwable;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBLoginInfo;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBPersonalize;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBBucketInfo;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBFlagInfo;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBReport;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBVersionInfo;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBNetworkState;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBNetworkState_NETWORK_STATE_DISCONNECT;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBNetworkState_NETWORK_STATE_UNKNOWN;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBNetworkState_NETWORK_STATE_2G;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBNetworkState_NETWORK_STATE_3G;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBNetworkState_NETWORK_STATE_4G;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBNetworkState_NETWORK_STATE_5G;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBNetworkState_NETWORK_STATE_WIFI;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Any;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBInitConfig;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBLoginInfo;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetwork;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetworkState;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetworkState_NETWORK_STATE_UNKNOWN;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetworkState_NETWORK_STATE_DISCONNECT;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetworkState_NETWORK_STATE_2G;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetworkState_NETWORK_STATE_3G;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetworkState_NETWORK_STATE_4G;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetworkState_NETWORK_STATE_5G;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetworkState_NETWORK_STATE_WIFI;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBPortraitInfo;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_protocol_vb_pb_kmm_ServerReportInfo;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBProtocolType;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig_Companion;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBWrapperLoginKey;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlinx_serialization_json_JsonArray;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlinx_serialization_json_JsonObject;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Number;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlinx_serialization_json_JsonPrimitive;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_IVBPBTaskListener;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBTask;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBConfig;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBFramePackage;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_ByteArray;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBFramePackage_Companion;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBHeaderConfig;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_protocol_vb_pb_kmm_RequestHead;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBHeaderConfig_Companion;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBLog;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBPackageMessageResult;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBPersonalize;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBProtocolType_QUIC;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBProtocolType_HTTP;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBProtocolType_FORCEQUIC;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReport;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_co_touchlab_stately_collections_ConcurrentMutableMap;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBTaskManager;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportState;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_protocol_vb_pb_kmm_ResponseHead;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBVersionInfo;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportManager;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTask;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportState_Create;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportState_Running;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportState_Canceled;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportState_Done;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportState_Unknown;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBytesRequest;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Function1;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportGetRequest;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportPostRequest;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportStringRequest;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTimeoutInfo;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTimeoutStrategy;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IVBTransportService;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IHMTransportService;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_HmTransportImpl;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Function2;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlinx_coroutines_CoroutineScope;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Function0;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlinx_coroutines_Job;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_HmTransportImpl_ResultArrayException;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IHMTransportServiceProxy;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_service_VBPBService;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_service_VBPBServiceInitHelper;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_service_VBPBServiceInitTask;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbpbservice_service_VBPBServiceTest;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBaseRequest;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_collections_MutableMap;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBaseResponse;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBytesResponse;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportContentType;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportContentType_JSON;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportContentType_BYTE;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportGetResponse;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportMethod;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportMethod_GET;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportMethod_POST;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportPostResponse;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportStringResponse;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_service_VBTransportService;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_service_VBTransportServiceTest;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Function3;

extern void com_tencent_tmm_knoi_initEnv(void* env, void* value, libkn_KBoolean debug);
extern void com_tencent_tmm_knoi_initBridge();
extern void platform_ohos_kotlin_stdlib_platform_ext_onLoad();
extern void test_2();

typedef struct {
  /* Service functions. */
  void (*DisposeStablePointer)(libkn_KNativePtr ptr);
  void (*DisposeString)(const char* string);
  libkn_KBoolean (*IsInstance)(libkn_KNativePtr ref, const libkn_KType* type);
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
                void (*testPBNetwork)();
                void (*testPBNetworkWithAny)(libkn_kref_kotlin_Array args);
                void (*testTransportBytesRequest)();
                void (*testTransportBytesRequestWithAny)(libkn_kref_kotlin_Array args);
                void (*testTransportGetRequest)();
                void (*testTransportGetRequestWithAny)(libkn_kref_kotlin_Array args);
                void (*testTransportPostRequest)();
                void (*testTransportPostRequestWithAny)(libkn_kref_kotlin_Array args);
                void (*testTransportStringRequest)();
                void (*testTransportStringRequestWithAny)(libkn_kref_kotlin_Array args);
              } sample;
            } component;
          } kmm;
          struct {
            struct {
              struct {
                struct {
                  struct {
                    libkn_KType* (*_type)(void);
                    const char* (*getDomain)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig thiz);
                    libkn_kref_com_tencent_qqlive_protocol_vb_pb_kmm_EnvInfo (*getEnvInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig thiz);
                    libkn_kref_kotlin_collections_Map (*getExtraRequestHeadMap)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig thiz);
                    const char* (*getGuid)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig thiz);
                    libkn_kref_kotlin_collections_List (*getLoginTokenList)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig thiz);
                    const char* (*getOmgId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig thiz);
                    const char* (*getOpenId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig thiz);
                    const char* (*getQIMEI)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig thiz);
                    const char* (*getQQAppId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig thiz);
                    libkn_KInt (*getQmfAppId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig thiz);
                    libkn_KByte (*getQmfPlatform)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig thiz);
                    libkn_kref_com_tencent_qqlive_protocol_vb_pb_kmm_UserStatusInfo (*getUserStatusInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig thiz);
                    libkn_KInt (*getVNVersion)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig thiz);
                    const char* (*getWxAppId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig thiz);
                    libkn_KBoolean (*isMainProcess)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig thiz);
                  } IVBPBConfig;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_KInt (*get_densityDpi)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo thiz);
                    void (*set_densityDpi)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo thiz, libkn_KInt set);
                    const char* (*get_deviceId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo thiz);
                    void (*set_deviceId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo thiz, const char* set);
                    const char* (*get_deviceModel)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo thiz);
                    void (*set_deviceModel)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo thiz, const char* set);
                    libkn_KInt (*get_deviceType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo thiz);
                    void (*set_deviceType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo thiz, libkn_KInt set);
                    const char* (*get_guid)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo thiz);
                    void (*set_guid)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo thiz, const char* set);
                    const char* (*get_manufacturer)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo thiz);
                    void (*set_manufacturer)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo thiz, const char* set);
                    const char* (*get_omgId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo thiz);
                    void (*set_omgId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo thiz, const char* set);
                    libkn_KInt (*get_screenHeight)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo thiz);
                    void (*set_screenHeight)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo thiz, libkn_KInt set);
                    libkn_KInt (*get_screenWidth)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo thiz);
                    void (*set_screenWidth)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo thiz, libkn_KInt set);
                    libkn_KInt (*getCurrentWindowUiSizeByUiSizeType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo thiz);
                    libkn_KInt (*getMaxUiSizeByUiSizeType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo thiz);
                  } IVBPBDeviceInfo;
                  struct {
                    libkn_KType* (*_type)(void);
                    void (*d)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBLog thiz, const char* tag, const char* content);
                    void (*e)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBLog thiz, const char* tag, const char* content, libkn_kref_kotlin_Throwable throwable);
                    void (*i)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBLog thiz, const char* tag, const char* content);
                  } IVBPBLog;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_KBoolean (*enableDefaultLoginToken)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBLoginInfo thiz);
                    libkn_kref_kotlin_collections_List (*getLoginToken)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBLoginInfo thiz);
                  } IVBPBLoginInfo;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBBucketInfo (*getBucketInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBPersonalize thiz);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBFlagInfo (*getFlagInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBPersonalize thiz);
                    libkn_kref_kotlin_Array (*getPortraitInfoList)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBPersonalize thiz);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo (*getUserStatusInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBPersonalize thiz);
                  } IVBPBPersonalize;
                  struct {
                    libkn_KType* (*_type)(void);
                    void (*report)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBReport thiz, libkn_kref_kotlin_collections_Map reportInfo);
                  } IVBPBReport;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_KInt (*getAppId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBVersionInfo thiz);
                    libkn_KInt (*getAppNameId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBVersionInfo thiz);
                    libkn_KInt (*getChannelId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBVersionInfo thiz);
                    libkn_KInt (*getPlatformId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBVersionInfo thiz);
                    const char* (*getPlatformVersion)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBVersionInfo thiz);
                    libkn_KInt (*getVersionCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBVersionInfo thiz);
                    const char* (*getVersionName)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBVersionInfo thiz);
                  } IVBPBVersionInfo;
                  struct {
                    struct {
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBNetworkState (*get)(); /* enum entry for NETWORK_STATE_DISCONNECT. */
                    } NETWORK_STATE_DISCONNECT;
                    struct {
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBNetworkState (*get)(); /* enum entry for NETWORK_STATE_UNKNOWN. */
                    } NETWORK_STATE_UNKNOWN;
                    struct {
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBNetworkState (*get)(); /* enum entry for NETWORK_STATE_2G. */
                    } NETWORK_STATE_2G;
                    struct {
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBNetworkState (*get)(); /* enum entry for NETWORK_STATE_3G. */
                    } NETWORK_STATE_3G;
                    struct {
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBNetworkState (*get)(); /* enum entry for NETWORK_STATE_4G. */
                    } NETWORK_STATE_4G;
                    struct {
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBNetworkState (*get)(); /* enum entry for NETWORK_STATE_5G. */
                    } NETWORK_STATE_5G;
                    struct {
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBNetworkState (*get)(); /* enum entry for NETWORK_STATE_WIFI. */
                    } NETWORK_STATE_WIFI;
                    libkn_KType* (*_type)(void);
                    libkn_KInt (*get)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBNetworkState thiz);
                  } VBNetworkState;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBBucketInfo (*VBPBBucketInfo)(libkn_KInt mBucketId, const char* mExtra);
                    libkn_KInt (*get_mBucketId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBBucketInfo thiz);
                    void (*set_mBucketId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBBucketInfo thiz, libkn_KInt set);
                    const char* (*get_mExtra)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBBucketInfo thiz);
                    void (*set_mExtra)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBBucketInfo thiz, const char* set);
                    libkn_KInt (*component1)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBBucketInfo thiz);
                    const char* (*component2)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBBucketInfo thiz);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBBucketInfo (*copy)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBBucketInfo thiz, libkn_KInt mBucketId, const char* mExtra);
                    libkn_KBoolean (*equals)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBBucketInfo thiz, libkn_kref_kotlin_Any other);
                    libkn_KInt (*hashCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBBucketInfo thiz);
                    const char* (*toString)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBBucketInfo thiz);
                  } VBPBBucketInfo;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBFlagInfo (*VBPBFlagInfo)(libkn_KBoolean mIsChecking, libkn_KBoolean mIsDebugEnabled);
                    libkn_KBoolean (*get_mIsChecking)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBFlagInfo thiz);
                    void (*set_mIsChecking)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBFlagInfo thiz, libkn_KBoolean set);
                    libkn_KBoolean (*get_mIsDebugEnabled)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBFlagInfo thiz);
                    void (*set_mIsDebugEnabled)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBFlagInfo thiz, libkn_KBoolean set);
                    libkn_KBoolean (*component1)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBFlagInfo thiz);
                    libkn_KBoolean (*component2)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBFlagInfo thiz);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBFlagInfo (*copy)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBFlagInfo thiz, libkn_KBoolean mIsChecking, libkn_KBoolean mIsDebugEnabled);
                    libkn_KBoolean (*equals)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBFlagInfo thiz, libkn_kref_kotlin_Any other);
                    libkn_KInt (*hashCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBFlagInfo thiz);
                    const char* (*toString)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBFlagInfo thiz);
                  } VBPBFlagInfo;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBInitConfig (*VBPBInitConfig)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBReport reportImpl, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBLog logImpl, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo deviceInfo, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBVersionInfo versionInfo, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig config, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBPersonalize personalizeInfo);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig (*get_config)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBInitConfig thiz);
                    void (*set_config)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBInitConfig thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig set);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo (*get_deviceInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBInitConfig thiz);
                    void (*set_deviceInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBInitConfig thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo set);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBLog (*get_logImpl)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBInitConfig thiz);
                    void (*set_logImpl)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBInitConfig thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBLog set);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBPersonalize (*get_personalizeInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBInitConfig thiz);
                    void (*set_personalizeInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBInitConfig thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBPersonalize set);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBReport (*get_reportImpl)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBInitConfig thiz);
                    void (*set_reportImpl)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBInitConfig thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBReport set);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBVersionInfo (*get_versionInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBInitConfig thiz);
                    void (*set_versionInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBInitConfig thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBVersionInfo set);
                  } VBPBInitConfig;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBLoginInfo (*_instance)();
                    libkn_KBoolean (*enableDefaultLoginToken)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBLoginInfo thiz);
                    libkn_kref_kotlin_collections_List (*getLoginToken)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBLoginInfo thiz);
                  } VBPBLoginInfo;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetwork (*_instance)();
                    const char* (*getClientV4Ip)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetwork thiz);
                    libkn_KInt (*getOperatorType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetwork thiz);
                  } VBPBNetwork;
                  struct {
                    struct {
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetworkState (*get)(); /* enum entry for NETWORK_STATE_UNKNOWN. */
                    } NETWORK_STATE_UNKNOWN;
                    struct {
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetworkState (*get)(); /* enum entry for NETWORK_STATE_DISCONNECT. */
                    } NETWORK_STATE_DISCONNECT;
                    struct {
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetworkState (*get)(); /* enum entry for NETWORK_STATE_2G. */
                    } NETWORK_STATE_2G;
                    struct {
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetworkState (*get)(); /* enum entry for NETWORK_STATE_3G. */
                    } NETWORK_STATE_3G;
                    struct {
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetworkState (*get)(); /* enum entry for NETWORK_STATE_4G. */
                    } NETWORK_STATE_4G;
                    struct {
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetworkState (*get)(); /* enum entry for NETWORK_STATE_5G. */
                    } NETWORK_STATE_5G;
                    struct {
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetworkState (*get)(); /* enum entry for NETWORK_STATE_WIFI. */
                    } NETWORK_STATE_WIFI;
                    libkn_KType* (*_type)(void);
                  } VBPBNetworkState;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBPortraitInfo (*VBPBPortraitInfo)(const char* mKey, libkn_kref_kotlin_Array mValueInfoList);
                    const char* (*get_mKey)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBPortraitInfo thiz);
                    void (*set_mKey)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBPortraitInfo thiz, const char* set);
                    libkn_kref_kotlin_Array (*get_mValueInfoList)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBPortraitInfo thiz);
                    void (*set_mValueInfoList)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBPortraitInfo thiz, libkn_kref_kotlin_Array set);
                    const char* (*component1)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBPortraitInfo thiz);
                    libkn_kref_kotlin_Array (*component2)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBPortraitInfo thiz);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBPortraitInfo (*copy)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBPortraitInfo thiz, const char* mKey, libkn_kref_kotlin_Array mValueInfoList);
                    libkn_KBoolean (*equals)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBPortraitInfo thiz, libkn_kref_kotlin_Any other);
                    libkn_KInt (*hashCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBPortraitInfo thiz);
                    const char* (*toString)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBPortraitInfo thiz);
                  } VBPBPortraitInfo;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo (*VBPBReportInfo)();
                    libkn_KInt (*get_autoRetryFlag)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_autoRetryFlag)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KInt set);
                    libkn_KInt (*get_bucketId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_bucketId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KInt set);
                    libkn_KInt (*get_busiDataType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_busiDataType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KInt set);
                    libkn_KInt (*get_businessErrCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_businessErrCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KInt set);
                    const char* (*get_businessErrType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_businessErrType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, const char* set);
                    const char* (*get_callee)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_callee)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, const char* set);
                    libkn_KInt (*get_dnsCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_dnsCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KInt set);
                    libkn_KLong (*get_downStreamCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_downStreamCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KLong set);
                    libkn_KInt (*get_errorCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_errorCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KInt set);
                    const char* (*get_errorMessage)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_errorMessage)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, const char* set);
                    const char* (*get_finishFlag)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_finishFlag)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, const char* set);
                    const char* (*get_func)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_func)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, const char* set);
                    libkn_kref_kotlin_collections_Map (*get_headExtras)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_headExtras)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_kref_kotlin_collections_Map set);
                    const char* (*get_httpVersion)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_httpVersion)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, const char* set);
                    libkn_KBoolean (*get_isHttps)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_isHttps)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KBoolean set);
                    libkn_KLong (*get_limitTime)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_limitTime)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KLong set);
                    libkn_KInt (*get_limitType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_limitType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KInt set);
                    const char* (*get_logTag)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_logTag)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, const char* set);
                    libkn_KBoolean (*get_needRetryFlag)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_needRetryFlag)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KBoolean set);
                    libkn_KLong (*get_packageCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_packageCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KLong set);
                    libkn_KLong (*get_packageHeaderCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_packageHeaderCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KLong set);
                    libkn_KLong (*get_packagePBFrameCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_packagePBFrameCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KLong set);
                    libkn_kref_kotlin_collections_Map (*get_pageParams)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_pageParams)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_kref_kotlin_collections_Map set);
                    const char* (*get_pbCmdId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_pbCmdId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, const char* set);
                    const char* (*get_qmfCmdId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_qmfCmdId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, const char* set);
                    libkn_KInt (*get_queueUpCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_queueUpCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KInt set);
                    libkn_kref_kotlin_collections_Map (*get_quicStatInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_quicStatInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_kref_kotlin_collections_Map set);
                    libkn_KInt (*get_requestCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_requestCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KInt set);
                    const char* (*get_requestDomain)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_requestDomain)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, const char* set);
                    libkn_KInt (*get_requestId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_requestId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KInt set);
                    const char* (*get_requestIp)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_requestIp)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, const char* set);
                    libkn_KLong (*get_requestPackageLength)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_requestPackageLength)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KLong set);
                    libkn_KInt (*get_responseCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_responseCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KInt set);
                    libkn_KLong (*get_responsePackageLength)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_responsePackageLength)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KLong set);
                    libkn_KInt (*get_retryTimes)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_retryTimes)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KInt set);
                    libkn_KInt (*get_rttCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_rttCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KInt set);
                    const char* (*get_scene)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_scene)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, const char* set);
                    libkn_kref_com_tencent_qqlive_protocol_vb_pb_kmm_ServerReportInfo (*get_serverReportInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_serverReportInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_kref_com_tencent_qqlive_protocol_vb_pb_kmm_ServerReportInfo set);
                    libkn_KLong (*get_serverRspCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_serverRspCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KLong set);
                    libkn_KInt (*get_socketConnCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_socketConnCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KInt set);
                    const char* (*get_srcDomain)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_srcDomain)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, const char* set);
                    libkn_KLong (*get_startTs)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_startTs)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KLong set);
                    libkn_KInt (*get_tlsConnCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_tlsConnCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KInt set);
                    libkn_KLong (*get_totalCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_totalCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KLong set);
                    libkn_KInt (*get_tranportCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_tranportCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KInt set);
                    libkn_KLong (*get_unpackageCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_unpackageCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KLong set);
                    libkn_KInt (*get_unpackageErrorCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_unpackageErrorCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KInt set);
                    libkn_KLong (*get_unpackageHeaderCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_unpackageHeaderCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KLong set);
                    libkn_KLong (*get_unpackagePBFrameCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_unpackagePBFrameCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KLong set);
                    libkn_KLong (*get_upStreamCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                    void (*set_upStreamCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz, libkn_KLong set);
                    const char* (*toString)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo thiz);
                  } VBPBReportInfo;
                  struct {
                    struct {
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig_Companion (*_instance)();
                      libkn_KInt (*get_AUTO_RETRY_FLAG_BUSSINESS_ERROR)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig_Companion thiz);
                      libkn_KInt (*get_AUTO_RETRY_FLAG_NOT_RETRY)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig_Companion thiz);
                      libkn_KInt (*get_AUTO_RETRY_FLAG_PLATFORM_ERROR)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig_Companion thiz);
                    } Companion;
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig (*VBPBRequestConfig)();
                    libkn_KLong (*get_cacheTimeStamp)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_cacheTimeStamp)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_KLong set);
                    libkn_KInt (*get_connTimeOut)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_connTimeOut)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_KInt set);
                    libkn_kref_kotlin_collections_Map (*get_customTraceMap)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_customTraceMap)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_kref_kotlin_collections_Map set);
                    libkn_KInt (*get_dnsTimeOut)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_dnsTimeOut)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_KInt set);
                    const char* (*get_domain)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_domain)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, const char* set);
                    libkn_KBoolean (*get_enableServerCurrentLimit)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_enableServerCurrentLimit)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_KBoolean set);
                    libkn_KBoolean (*get_enhanceThreadPriority)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_enhanceThreadPriority)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_KBoolean set);
                    libkn_kref_kotlin_collections_Map (*get_extraDataMap)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_extraDataMap)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_kref_kotlin_collections_Map set);
                    libkn_kref_kotlin_collections_Map (*get_httpHeaderMap)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_httpHeaderMap)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_kref_kotlin_collections_Map set);
                    libkn_KBoolean (*get_httpUseHttp1)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_httpUseHttp1)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_KBoolean set);
                    libkn_KBoolean (*get_ipv4First)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_ipv4First)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_KBoolean set);
                    libkn_KBoolean (*get_isHtts)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_isHtts)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_KBoolean set);
                    libkn_KBoolean (*get_isReportDisable)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_isReportDisable)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_KBoolean set);
                    libkn_KBoolean (*get_isResponseEmptyAllowed)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_isResponseEmptyAllowed)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_KBoolean set);
                    libkn_KBoolean (*get_isRetryEnable)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_isRetryEnable)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_KBoolean set);
                    libkn_KBoolean (*get_isTryUseCellularNetwork)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_isTryUseCellularNetwork)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_KBoolean set);
                    libkn_kref_kotlin_collections_Map (*get_pageParams)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_pageParams)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_kref_kotlin_collections_Map set);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBProtocolType (*get_protocolType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_protocolType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBProtocolType set);
                    libkn_KBoolean (*get_quicForceQuic)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_quicForceQuic)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_KBoolean set);
                    libkn_KBoolean (*get_quicUseConnAndSend)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_quicUseConnAndSend)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_KBoolean set);
                    libkn_KInt (*get_readTimeOut)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_readTimeOut)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_KInt set);
                    const char* (*get_scene)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_scene)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, const char* set);
                    const char* (*get_url)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_url)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, const char* set);
                    libkn_KInt (*get_writeTimeOut)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz);
                    void (*set_writeTimeOut)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBRequestConfig thiz, libkn_KInt set);
                  } VBPBRequestConfig;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode (*_instance)();
                    libkn_KInt (*get_CODE_CANCELED)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_CODE_HTTP_ENTITYNULL)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_CODE_OK)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_CODE_PBERR_EXCLUDE_QMF_DATA_EMPTY)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_CODE_PBERR_EXT_ERR)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_CODE_PBERR_NOGZIPERR)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_CODE_PBERR_PB_FRAME_MAGIC_DISMATCH)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_CODE_PBERR_QMFDATAERR)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_CODE_PBERR_QMF_CMD_MISMATCH)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_CODE_PBERR_QMF_LEN_MISMATCH_RECEIVED_LEN)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_CODE_PBERR_ROUTEERR)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_CODE_PB_ERR_BODY)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_CODE_REQUEST_DATA_EMPTY)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_CODE_REQUEST_LIMIT)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_CODE_UNZIPERR)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    const char* (*get_ERROR_CODE_TYPE_ACCESS_SVR_ERR)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    const char* (*get_ERROR_CODE_TYPE_BUSI_PACK_ERR)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    const char* (*get_ERROR_CODE_TYPE_BUSI_RS_ERR)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    const char* (*get_ERROR_CODE_TYPE_CLIENT_ERR)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    const char* (*get_ERROR_CODE_TYPE_FRAME_PACK_ERR)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    const char* (*get_ERROR_CODE_TYPE_NET_LIB_ERR)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    const char* (*get_ERROR_CODE_TYPE_QMF_PACK_ERR)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    const char* (*get_ERROR_CODE_TYPE_TRPC_PACK_ERR)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_ERR_RESPONSE_PARSE_DATA_EMPTY)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_ERR_RESPONSE_PARSE_EXCEPTION)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_ERR_RESPONSE_PARSE_PARSER_EMPTY)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_ERR_RESPONSE_QMF_APPID_MISMATCH)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_ERR_RESPONSE_QMF_MAGIC_MISMATCH)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_ERR_RESPONSE_REQUEST_ID_CHANGE)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_ERR_RESPONSE_REQUEST_ID_NULL)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_ERR_RESPONSE_UNCOMPRESS_FAIL)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_ERR_RESPONSE_UNSUPPORTED_COMPRESS_TYPE)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_NETWORK_DISCONNECT)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                    libkn_KInt (*get_REQUEST_ERROR_ID)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBResultCode thiz);
                  } VBPBResultCode;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo (*VBPBUserStatusInfo)(const char* mSessionCode, libkn_KBoolean mIsSpecialZone, libkn_KLong mExpiredTime, const char* mUserStatusKey);
                    libkn_KLong (*get_mExpiredTime)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo thiz);
                    void (*set_mExpiredTime)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo thiz, libkn_KLong set);
                    libkn_KBoolean (*get_mIsSpecialZone)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo thiz);
                    void (*set_mIsSpecialZone)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo thiz, libkn_KBoolean set);
                    const char* (*get_mSessionCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo thiz);
                    void (*set_mSessionCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo thiz, const char* set);
                    const char* (*get_mUserStatusKey)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo thiz);
                    void (*set_mUserStatusKey)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo thiz, const char* set);
                    const char* (*component1)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo thiz);
                    libkn_KBoolean (*component2)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo thiz);
                    libkn_KLong (*component3)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo thiz);
                    const char* (*component4)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo thiz);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo (*copy)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo thiz, const char* mSessionCode, libkn_KBoolean mIsSpecialZone, libkn_KLong mExpiredTime, const char* mUserStatusKey);
                    libkn_KBoolean (*equals)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo thiz, libkn_kref_kotlin_Any other);
                    libkn_KInt (*hashCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo thiz);
                    const char* (*toString)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo thiz);
                  } VBPBUserStatusInfo;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBWrapperLoginKey (*_instance)();
                    const char* (*get_KEY_ACCOUNT_PATTERN)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBWrapperLoginKey thiz);
                    const char* (*get_VB_WRAPPERLOGINSERVICE_ASSIST_CHANNELACCESSTOKEN)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBWrapperLoginKey thiz);
                    const char* (*get_VB_WRAPPERLOGINSERVICE_ASSIST_CHANNELUSERID)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBWrapperLoginKey thiz);
                    const char* (*get_VB_WRAPPERLOGINSERVICE_ASSIST_LOGGEDCHANNELAPPID)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBWrapperLoginKey thiz);
                    const char* (*get_VB_WRAPPERLOGINSERVICE_ATOMICINFO)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBWrapperLoginKey thiz);
                    const char* (*get_VB_WRAPPERLOGINSERVICE_CHANNELACCESSTOKEN)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBWrapperLoginKey thiz);
                    const char* (*get_VB_WRAPPERLOGINSERVICE_CHANNELUSERID)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBWrapperLoginKey thiz);
                    const char* (*get_VB_WRAPPERLOGINSERVICE_LOGGEDCHANNELAPPID)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBWrapperLoginKey thiz);
                    const char* (*get_VB_WRAPPERLOGINSERVICE_VIDEOSESSION)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBWrapperLoginKey thiz);
                    const char* (*get_VB_WRAPPERLOGINSERVICE_VIDEOUSERID)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBWrapperLoginKey thiz);
                  } VBPBWrapperLoginKey;
                } export_;
                struct {
                  struct {
                    struct {
                      libkn_KType* (*_type)(void);
                      void (*onTaskBegin)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_IVBPBTaskListener thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBTask task);
                      void (*onTaskFinish)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_IVBPBTaskListener thiz, libkn_KInt requestId);
                    } IVBPBTaskListener;
                    struct {
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBConfig (*_instance)();
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig (*get_config)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBConfig thiz);
                      void (*set_config)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBConfig thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBConfig set);
                      const char* (*getDomain)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBConfig thiz);
                      libkn_kref_com_tencent_qqlive_protocol_vb_pb_kmm_EnvInfo (*getEnvInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBConfig thiz);
                      libkn_kref_kotlin_collections_Map (*getExtraRequestHeadMap)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBConfig thiz);
                      const char* (*getGuid)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBConfig thiz);
                      libkn_kref_kotlin_collections_List (*getLoginTokenList)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBConfig thiz);
                      const char* (*getOmgId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBConfig thiz);
                      const char* (*getOpenId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBConfig thiz);
                      const char* (*getQIMEI)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBConfig thiz);
                      const char* (*getQQAppId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBConfig thiz);
                      libkn_KInt (*getQmfAppId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBConfig thiz);
                      libkn_KByte (*getQmfPlatform)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBConfig thiz);
                      libkn_kref_com_tencent_qqlive_protocol_vb_pb_kmm_UserStatusInfo (*getUserStatusInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBConfig thiz);
                      libkn_KInt (*getVNVersion)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBConfig thiz);
                      const char* (*getWxAppId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBConfig thiz);
                      libkn_KBoolean (*isMainProcess)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBConfig thiz);
                    } VBPBConfig;
                    struct {
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo (*_instance)();
                      libkn_KInt (*get_densityDpi)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz);
                      void (*set_densityDpi)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz, libkn_KInt value);
                      const char* (*get_deviceId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz);
                      void (*set_deviceId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz, const char* value);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo (*get_deviceInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz);
                      void (*set_deviceInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBDeviceInfo set);
                      const char* (*get_deviceModel)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz);
                      void (*set_deviceModel)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz, const char* value);
                      libkn_KInt (*get_deviceType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz);
                      void (*set_deviceType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz, libkn_KInt value);
                      const char* (*get_guid)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz);
                      void (*set_guid)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz, const char* value);
                      const char* (*get_manufacturer)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz);
                      void (*set_manufacturer)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz, const char* value);
                      const char* (*get_omgId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz);
                      void (*set_omgId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz, const char* value);
                      libkn_KInt (*get_screenHeight)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz);
                      void (*set_screenHeight)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz, libkn_KInt value);
                      libkn_KInt (*get_screenWidth)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz);
                      void (*set_screenWidth)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz, libkn_KInt value);
                      libkn_KInt (*getCurrentWindowUiSizeByUiSizeType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz);
                      libkn_KInt (*getMaxUiSizeByUiSizeType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBDeviceInfo thiz);
                    } VBPBDeviceInfo;
                    struct {
                      struct {
                        libkn_KType* (*_type)(void);
                        libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBFramePackage_Companion (*_instance)();
                        const char* (*get_MAGIC_PB_REQUEST_MAGIC)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBFramePackage_Companion thiz);
                        const char* (*get_MAGIC_PB_RESPONSE_MAGIC)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBFramePackage_Companion thiz);
                        libkn_KInt (*get_PB_FRAME_LEN)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBFramePackage_Companion thiz);
                      } Companion;
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBFramePackage (*VBPBFramePackage)(const char* logTag);
                      libkn_KInt (*get_responseBodyLen)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBFramePackage thiz);
                      void (*set_responseBodyLen)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBFramePackage thiz, libkn_KInt set);
                      libkn_KInt (*get_responseHeadLen)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBFramePackage thiz);
                      void (*set_responseHeadLen)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBFramePackage thiz, libkn_KInt set);
                      libkn_kref_kotlin_ByteArray (*packagePBRequest)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBFramePackage thiz, libkn_KShort headerLen, libkn_kref_kotlin_ByteArray requestDataBytes);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult (*unpackagePBResponse)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBFramePackage thiz, libkn_kref_kotlin_ByteArray responseBytes);
                    } VBPBFramePackage;
                    struct {
                      struct {
                        libkn_KType* (*_type)(void);
                        libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBHeaderConfig_Companion (*_instance)();
                        const char* (*get_TAG)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBHeaderConfig_Companion thiz);
                        libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetworkState (*convertToNetworkType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBHeaderConfig_Companion thiz, libkn_KInt networkType);
                        libkn_KInt (*getNetworkType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBHeaderConfig_Companion thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBNetworkState networkType);
                      } Companion;
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBHeaderConfig (*VBPBHeaderConfig)();
                      libkn_kref_com_tencent_qqlive_protocol_vb_pb_kmm_RequestHead (*createCommonRequestHead)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBHeaderConfig thiz, libkn_KInt requestId, const char* requestTag);
                      const char* (*createUniqueId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBHeaderConfig thiz, libkn_KInt requestId);
                    } VBPBHeaderConfig;
                    struct {
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBLog (*_instance)();
                      const char* (*get_HEADER_CONFIG)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBLog thiz);
                      const char* (*get_HEADER_PACKAGE)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBLog thiz);
                      const char* (*get_HMTRANSPORTIMPL)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBLog thiz);
                      const char* (*get_INIT_TASK)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBLog thiz);
                      const char* (*get_MESSAGE_PACKAGE)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBLog thiz);
                      const char* (*get_PACKAGE_IMPL)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBLog thiz);
                      const char* (*get_PACKAGE_TASK)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBLog thiz);
                      const char* (*get_PBFRAME_PACKAGE)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBLog thiz);
                      const char* (*get_SENDER)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBLog thiz);
                      const char* (*get_TASK_MANAGER)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBLog thiz);
                      const char* (*get_TIMEOUT)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBLog thiz);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBLog (*get_logImpl)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBLog thiz);
                      void (*set_logImpl)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBLog thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBLog set);
                      void (*e)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBLog thiz, const char* tag, const char* content, libkn_kref_kotlin_Throwable throwable);
                      void (*i)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBLog thiz, const char* tag, const char* content);
                    } VBPBLog;
                    struct {
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBPackageMessageResult (*VBPBPackageMessageResult)();
                      libkn_kref_kotlin_ByteArray (*get_packageBytes)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBPackageMessageResult thiz);
                      void (*set_packageBytes)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBPackageMessageResult thiz, libkn_kref_kotlin_ByteArray set);
                      libkn_kref_com_tencent_qqlive_protocol_vb_pb_kmm_RequestHead (*get_requestHead)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBPackageMessageResult thiz);
                      void (*set_requestHead)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBPackageMessageResult thiz, libkn_kref_com_tencent_qqlive_protocol_vb_pb_kmm_RequestHead set);
                      libkn_KBoolean (*get_useGzip)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBPackageMessageResult thiz);
                      void (*set_useGzip)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBPackageMessageResult thiz, libkn_KBoolean set);
                    } VBPBPackageMessageResult;
                    struct {
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBPersonalize (*_instance)();
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBPersonalize (*get_personalize)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBPersonalize thiz);
                      void (*set_personalize)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBPersonalize thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBPersonalize set);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBBucketInfo (*getBucketInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBPersonalize thiz);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBFlagInfo (*getFlagInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBPersonalize thiz);
                      libkn_kref_kotlin_Array (*getPortraitInfoList)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBPersonalize thiz);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBUserStatusInfo (*getUserStatusInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBPersonalize thiz);
                    } VBPBPersonalize;
                    struct {
                      struct {
                        libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBProtocolType (*get)(); /* enum entry for QUIC. */
                      } QUIC;
                      struct {
                        libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBProtocolType (*get)(); /* enum entry for HTTP. */
                      } HTTP;
                      struct {
                        libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBProtocolType (*get)(); /* enum entry for FORCEQUIC. */
                      } FORCEQUIC;
                      libkn_KType* (*_type)(void);
                    } VBPBProtocolType;
                    struct {
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReport (*_instance)();
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBReport (*get_reportImpl)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReport thiz);
                      void (*set_reportImpl)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReport thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBReport set);
                      libkn_kref_kotlin_collections_Map (*convert)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReport thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo reportInfo);
                      void (*report)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReport thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo reportInfo);
                      void (*syncUnpackageErrorCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReport thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo reportInfo);
                    } VBPBReport;
                    struct {
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager (*_instance)();
                      libkn_kref_co_touchlab_stately_collections_ConcurrentMutableMap (*get_reportInfoMap)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz);
                      void (*addReportInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, const char* logTag, libkn_KLong startTs);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBReportInfo (*getReportInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId);
                      void (*removeReportInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId);
                      void (*setAutoRetryFlag)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, libkn_KInt autoRetryFlag);
                      void (*setBucketId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, libkn_KInt bucketId);
                      void (*setBusiDataType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, libkn_KInt busiDataType);
                      void (*setBusinessErrCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, libkn_KInt businessErrorCode);
                      void (*setBusinessErrType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, const char* businessErrType);
                      void (*setCallee)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, const char* callee);
                      void (*setErrorMessage)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, const char* errorMessage);
                      void (*setFunc)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, const char* func);
                      void (*setHeadExtras)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, libkn_kref_kotlin_collections_Map headExtras);
                      void (*setLimitType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, libkn_KInt limitType);
                      void (*setNeedRetryFlag)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, libkn_KBoolean needRetryFlag);
                      void (*setPBCmdId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, const char* cmdId);
                      void (*setPackageHeaderTimeSpent)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, libkn_KLong startTs);
                      void (*setPackagePBFrameTimeSpent)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, libkn_KLong startTs);
                      void (*setPackageTimeSpent)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, libkn_KLong startTs);
                      void (*setPageParams)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, libkn_kref_kotlin_collections_Map pageParams);
                      void (*setQmfCmdId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, const char* cmdId);
                      void (*setQueueUpTimeSpent)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, libkn_KInt queueUpCost);
                      void (*setRequestPackageLength)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, libkn_KLong requestPackageLength);
                      void (*setResponsePackageLength)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, libkn_KLong responsePackageLength);
                      void (*setScene)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, const char* scene);
                      void (*setServerReportInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, libkn_kref_com_tencent_qqlive_protocol_vb_pb_kmm_ServerReportInfo serverReportInfo);
                      void (*setServerRspCost)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, libkn_KLong serverRspCost);
                      void (*setSrcDomain)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, const char* srcDomain);
                      void (*setTotalTimeSpent)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId);
                      void (*setUnpackageErrorCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, libkn_KInt unpackageErrorCode);
                      void (*setUnpackageHeaderTimeSpent)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, libkn_KLong startTs);
                      void (*setUnpackagePBFrameTimeSpent)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, libkn_KLong startTs);
                      void (*setUnpackageTimeSpent)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBReportManager thiz, libkn_KInt requestId, libkn_KLong startTs);
                    } VBPBReportManager;
                    struct {
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBTaskManager (*VBPBTaskManager)();
                      void (*cancel)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBTaskManager thiz, libkn_KInt requestId);
                      libkn_KInt (*execute)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBTaskManager thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBTask task);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportState (*getState)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBTaskManager thiz, libkn_KInt requestId);
                      void (*onTaskBegin)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBTaskManager thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBTask task);
                      void (*onTaskFinish)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBTaskManager thiz, libkn_KInt requestId);
                    } VBPBTaskManager;
                    struct {
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult (*VBPBUnPackageResult)();
                      libkn_KInt (*get_blockIntervalTime)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult thiz);
                      void (*set_blockIntervalTime)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult thiz, libkn_KInt set);
                      libkn_KInt (*get_businessCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult thiz);
                      void (*set_businessCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult thiz, libkn_KInt set);
                      libkn_KInt (*get_errorCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult thiz);
                      void (*set_errorCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult thiz, libkn_KInt set);
                      const char* (*get_errorCodeType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult thiz);
                      void (*set_errorCodeType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult thiz, const char* set);
                      const char* (*get_errorMessage)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult thiz);
                      void (*set_errorMessage)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult thiz, const char* set);
                      libkn_KBoolean (*get_needRetryFlag)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult thiz);
                      void (*set_needRetryFlag)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult thiz, libkn_KBoolean set);
                      libkn_kref_kotlin_ByteArray (*get_responseBytes)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult thiz);
                      void (*set_responseBytes)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult thiz, libkn_kref_kotlin_ByteArray set);
                      libkn_kref_com_tencent_qqlive_protocol_vb_pb_kmm_ResponseHead (*get_responseHead)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult thiz);
                      void (*set_responseHead)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult thiz, libkn_kref_com_tencent_qqlive_protocol_vb_pb_kmm_ResponseHead set);
                      libkn_kref_kotlin_collections_Map (*get_transInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult thiz);
                      void (*set_transInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBUnPackageResult thiz, libkn_kref_kotlin_collections_Map set);
                    } VBPBUnPackageResult;
                    struct {
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBVersionInfo (*_instance)();
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBVersionInfo (*get_versionInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBVersionInfo thiz);
                      void (*set_versionInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBVersionInfo thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBVersionInfo set);
                      libkn_KInt (*getAppId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBVersionInfo thiz);
                      libkn_KInt (*getAppNameId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBVersionInfo thiz);
                      libkn_KInt (*getChannelId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBVersionInfo thiz);
                      libkn_KInt (*getPlatformId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBVersionInfo thiz);
                      const char* (*getPlatformVersion)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBVersionInfo thiz);
                      libkn_KInt (*getVersionCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBVersionInfo thiz);
                      const char* (*getVersionName)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBPBVersionInfo thiz);
                    } VBPBVersionInfo;
                    struct {
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportManager (*VBTransportManager)();
                      void (*cancel)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportManager thiz, libkn_KInt requestId);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportState (*getState)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportManager thiz, libkn_KInt requestId);
                      void (*onTaskBegin)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportManager thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTask task);
                      void (*onTaskFinish)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportManager thiz, libkn_KInt requestId);
                    } VBTransportManager;
                    struct {
                      struct {
                        libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportState (*get)(); /* enum entry for Create. */
                      } Create;
                      struct {
                        libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportState (*get)(); /* enum entry for Running. */
                      } Running;
                      struct {
                        libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportState (*get)(); /* enum entry for Canceled. */
                      } Canceled;
                      struct {
                        libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportState (*get)(); /* enum entry for Done. */
                      } Done;
                      struct {
                        libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportState (*get)(); /* enum entry for Unknown. */
                      } Unknown;
                      libkn_KType* (*_type)(void);
                    } VBTransportState;
                    struct {
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTask (*VBTransportTask)(libkn_KInt requestId, const char* logTag, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportManager taskManager);
                      const char* (*get_logTag)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTask thiz);
                      libkn_KInt (*get_requestId)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTask thiz);
                      void (*cancel)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTask thiz);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportState (*getState)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTask thiz);
                      void (*sendBytesRequest)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTask thiz, libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBytesRequest request, libkn_kref_kotlin_Function1 handler);
                      void (*sendGetRequest)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTask thiz, libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportGetRequest request, libkn_kref_kotlin_Function1 handler);
                      void (*sendPostRequest)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTask thiz, libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportPostRequest request, libkn_kref_kotlin_Function1 handler);
                      void (*sendStringRequest)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTask thiz, libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportStringRequest request, libkn_kref_kotlin_Function1 handler);
                    } VBTransportTask;
                    struct {
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTimeoutInfo (*VBTransportTimeoutInfo)(libkn_KInt readWriteTimeout, libkn_KInt connTimeout);
                      libkn_KInt (*get_connTimeout)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTimeoutInfo thiz);
                      libkn_KInt (*get_readWriteTimeout)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTimeoutInfo thiz);
                      libkn_KInt (*component1)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTimeoutInfo thiz);
                      libkn_KInt (*component2)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTimeoutInfo thiz);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTimeoutInfo (*copy)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTimeoutInfo thiz, libkn_KInt readWriteTimeout, libkn_KInt connTimeout);
                      libkn_KBoolean (*equals)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTimeoutInfo thiz, libkn_kref_kotlin_Any other);
                      libkn_KInt (*hashCode)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTimeoutInfo thiz);
                      const char* (*toString)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTimeoutInfo thiz);
                    } VBTransportTimeoutInfo;
                    struct {
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTimeoutStrategy (*_instance)();
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTimeoutInfo (*getTimeoutInfo)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportTimeoutStrategy thiz, libkn_KInt networkState);
                    } VBTransportTimeoutStrategy;
                    const char* (*get_RELEASE_DOMAIN)();
                    libkn_KInt (*getRequestId)();
                    libkn_KLong (*getTimestamp)();
                    const char* (*encodeToJsonString)(libkn_kref_kotlin_collections_Map thiz);
                    libkn_KInt (*getInt)(libkn_kref_kotlinx_serialization_json_JsonArray thiz, libkn_KInt index);
                    libkn_kref_kotlinx_serialization_json_JsonObject (*getJSONObject)(libkn_kref_kotlinx_serialization_json_JsonArray thiz, libkn_KInt index);
                    const char* (*getString)(libkn_kref_kotlinx_serialization_json_JsonArray thiz, libkn_KInt index);
                    libkn_KInt (*optInt)(libkn_kref_kotlinx_serialization_json_JsonObject thiz, const char* key);
                    libkn_kref_kotlinx_serialization_json_JsonArray (*optJSONArray)(libkn_kref_kotlinx_serialization_json_JsonObject thiz, const char* key);
                    const char* (*optString)(libkn_kref_kotlinx_serialization_json_JsonObject thiz, const char* key);
                    libkn_kref_kotlinx_serialization_json_JsonObject (*parseToJsonObject)(const char* thiz);
                    libkn_kref_kotlinx_serialization_json_JsonPrimitive (*toJsonPrimitive)(libkn_kref_kotlin_Number thiz);
                    libkn_kref_kotlinx_serialization_json_JsonPrimitive (*toJsonPrimitive_)(const char* thiz);
                  } internal;
                  struct {
                    struct {
                      struct {
                        libkn_KType* (*_type)(void);
                        libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_HmTransportImpl_ResultArrayException (*ResultArrayException)(const char* message);
                      } ResultArrayException;
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_HmTransportImpl (*HmTransportImpl)();
                      void (*cancel)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_HmTransportImpl thiz, libkn_KInt requestId);
                      void (*get)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_HmTransportImpl thiz, libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportGetRequest kmmGetRequest, libkn_kref_kotlin_Function1 kmmGetResponseCallback);
                      const char* (*getClientV4Ip)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_HmTransportImpl thiz);
                      libkn_KInt (*getNetworkType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_HmTransportImpl thiz);
                      libkn_KInt (*getOperatorType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_HmTransportImpl thiz);
                      void (*post)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_HmTransportImpl thiz, libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportPostRequest kmmPostRequest, libkn_kref_kotlin_Function1 kmmPostResponseCallback);
                      void (*sendBytesRequest)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_HmTransportImpl thiz, libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBytesRequest kmmBytesRequest, libkn_kref_kotlin_Function1 kmmBytesResponseCallback);
                      void (*sendStringRequest)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_HmTransportImpl thiz, libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportStringRequest kmmStringRequest, libkn_kref_kotlin_Function1 kmmStringResponseCallback);
                      void (*setLogImpl)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_HmTransportImpl thiz, libkn_kref_kotlin_Function2 logImpl);
                      libkn_kref_kotlinx_coroutines_Job (*setupTimeoutJob)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_HmTransportImpl thiz, libkn_kref_kotlinx_coroutines_CoroutineScope scope, const char* logTag, libkn_kref_kotlin_Function0 callback);
                    } HmTransportImpl;
                    struct {
                      libkn_KType* (*_type)(void);
                      void (*cancel)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IHMTransportService thiz, libkn_KInt requestId);
                      libkn_kref_kotlin_Int (*getNetworkType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IHMTransportService thiz);
                      const char* (*getTimestamp)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IHMTransportService thiz);
                      void (*sendGetRequest)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IHMTransportService thiz, libkn_kref_kotlin_collections_Map requestMap, libkn_kref_kotlin_Function1 callback);
                      void (*sendPostRequest)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IHMTransportService thiz, libkn_kref_kotlin_collections_Map requestMap, libkn_kref_kotlin_Function1 callback);
                      void (*setLogImpl)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IHMTransportService thiz, libkn_kref_kotlin_Function1 logImpl);
                    } IHMTransportService;
                    struct {
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IHMTransportServiceProxy (*IHMTransportServiceProxy)();
                      void (*cancel)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IHMTransportServiceProxy thiz, libkn_KInt requestId);
                      libkn_kref_kotlin_Int (*getNetworkType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IHMTransportServiceProxy thiz);
                      const char* (*getTimestamp)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IHMTransportServiceProxy thiz);
                      void (*sendGetRequest)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IHMTransportServiceProxy thiz, libkn_kref_kotlin_collections_Map requestMap, libkn_kref_kotlin_Function1 callback);
                      void (*sendPostRequest)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IHMTransportServiceProxy thiz, libkn_kref_kotlin_collections_Map requestMap, libkn_kref_kotlin_Function1 callback);
                      void (*setLogImpl)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IHMTransportServiceProxy thiz, libkn_kref_kotlin_Function1 logImpl);
                    } IHMTransportServiceProxy;
                    struct {
                      libkn_KType* (*_type)(void);
                      void (*cancel)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IVBTransportService thiz, libkn_KInt requestId);
                      void (*get)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IVBTransportService thiz, libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportGetRequest kmmGetRequest, libkn_kref_kotlin_Function1 kmmGetResponseCallback);
                      const char* (*getClientV4Ip)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IVBTransportService thiz);
                      libkn_KInt (*getNetworkType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IVBTransportService thiz);
                      libkn_KInt (*getOperatorType)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IVBTransportService thiz);
                      void (*post)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IVBTransportService thiz, libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportPostRequest kmmPostRequest, libkn_kref_kotlin_Function1 kmmPostResponseCallback);
                      void (*sendBytesRequest)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IVBTransportService thiz, libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBytesRequest kmmBytesRequest, libkn_kref_kotlin_Function1 kmmBytesResponseCallback);
                      void (*sendStringRequest)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IVBTransportService thiz, libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportStringRequest kmmStringRequest, libkn_kref_kotlin_Function1 kmmStringResponseCallback);
                      void (*setLogImpl)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IVBTransportService thiz, libkn_kref_kotlin_Function2 logImpl);
                    } IVBTransportService;
                    const char* (*get_BYTE_RESPONSE)();
                    const char* (*get_GET_RESPONSE)();
                    const char* (*get_KMM_REQUEST_CONTENT_TYPE)();
                    libkn_KInt (*get_NETWORK_TYPE_NET_2G)();
                    libkn_KInt (*get_NETWORK_TYPE_NET_3G)();
                    libkn_KInt (*get_NETWORK_TYPE_NET_4G)();
                    libkn_KInt (*get_NETWORK_TYPE_NET_5G)();
                    libkn_KInt (*get_NETWORK_TYPE_NET_DISCONNECT)();
                    libkn_KInt (*get_NETWORK_TYPE_NET_UNKNOWN)();
                    libkn_KInt (*get_NETWORK_TYPE_NET_WIFI)();
                    const char* (*get_POST_RESPONSE)();
                    libkn_KInt (*get_PROTOCOL_TYPE_HTTP1)();
                    libkn_KInt (*get_PROTOCOL_TYPE_HTTP2)();
                    libkn_KInt (*get_PROTOCOL_TYPE_HTTP3)();
                    const char* (*get_REQUEST_ADDRESS)();
                    const char* (*get_REQUEST_CONN_TIMEOUT)();
                    const char* (*get_REQUEST_CONTENT_TYPE)();
                    const char* (*get_REQUEST_DATA)();
                    const char* (*get_REQUEST_DNS_TIMEOUT)();
                    const char* (*get_REQUEST_HEADER)();
                    const char* (*get_REQUEST_ID)();
                    const char* (*get_REQUEST_IP)();
                    const char* (*get_REQUEST_METHOD)();
                    const char* (*get_REQUEST_PROTOCOLTYPE)();
                    const char* (*get_REQUEST_READ_TIMEOUT)();
                    const char* (*get_REQUEST_TAG)();
                    const char* (*get_REQUEST_WRITE_TIMEOUT)();
                    const char* (*get_RESPONSE_ADDRESS)();
                    const char* (*get_RESPONSE_CODE)();
                    const char* (*get_RESPONSE_DATA)();
                    const char* (*get_RESPONSE_DNSTIMING)();
                    const char* (*get_RESPONSE_MESSAGE)();
                    const char* (*get_RESPONSE_SEND_TIME)();
                    const char* (*get_RESPONSE_TCPTIMING)();
                    const char* (*get_RESPONSE_TLSTIMING)();
                    const char* (*get_RESPONSE_TOTALTIMING)();
                    const char* (*get_STRING_RESPONSE)();
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IVBTransportService (*get_hmTransportImpl)();
                    void (*set_hmTransportImpl)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IVBTransportService set);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IHMTransportService (*getIHMTransportServiceApi)();
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_platform_IVBTransportService (*getIVBTransportService)();
                  } platform;
                } impl;
                struct {
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_service_VBPBService (*_instance)();
                    void (*cancel)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_service_VBPBService thiz, libkn_KInt requestId);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportState (*getState)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_service_VBPBService thiz, libkn_KInt requestId);
                  } VBPBService;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_service_VBPBServiceInitHelper (*_instance)();
                    void (*debugInit)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_service_VBPBServiceInitHelper thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_IVBPBLog logImpl);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBInitConfig (*generateDefaultInitConfig)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_service_VBPBServiceInitHelper thiz);
                    void (*init)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_service_VBPBServiceInitHelper thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBInitConfig initConfig);
                  } VBPBServiceInitHelper;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_service_VBPBServiceInitTask (*_instance)();
                    void (*init)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_service_VBPBServiceInitTask thiz, libkn_kref_com_tencent_qqlive_kmm_vbpbservice_export_VBPBInitConfig initConfig);
                  } VBPBServiceInitTask;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_service_VBPBServiceTest (*_instance)();
                    void (*testSendRequest)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_service_VBPBServiceTest thiz);
                    void (*testServiceInit)(libkn_kref_com_tencent_qqlive_kmm_vbpbservice_service_VBPBServiceTest thiz);
                  } VBPBServiceTest;
                } service;
              } vbpbservice;
              struct {
                struct {
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBaseRequest (*VBTransportBaseRequest)();
                    libkn_kref_kotlin_collections_MutableMap (*get_header)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBaseRequest thiz);
                    void (*set_header)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBaseRequest thiz, libkn_kref_kotlin_collections_MutableMap set);
                    const char* (*get_logTag)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBaseRequest thiz);
                    void (*set_logTag)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBaseRequest thiz, const char* set);
                    libkn_KInt (*get_requestId)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBaseRequest thiz);
                    void (*set_requestId)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBaseRequest thiz, libkn_KInt set);
                    const char* (*get_url)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBaseRequest thiz);
                    void (*set_url)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBaseRequest thiz, const char* set);
                  } VBTransportBaseRequest;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBaseResponse (*VBTransportBaseResponse)();
                    libkn_KInt (*get_errorCode)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBaseResponse thiz);
                    void (*set_errorCode)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBaseResponse thiz, libkn_KInt set);
                    const char* (*get_errorMessage)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBaseResponse thiz);
                    void (*set_errorMessage)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBaseResponse thiz, const char* set);
                    libkn_kref_kotlin_collections_Map (*get_header)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBaseResponse thiz);
                    void (*set_header)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBaseResponse thiz, libkn_kref_kotlin_collections_Map set);
                  } VBTransportBaseResponse;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBytesRequest (*VBTransportBytesRequest)();
                    libkn_kref_kotlin_ByteArray (*get_data)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBytesRequest thiz);
                    void (*set_data)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBytesRequest thiz, libkn_kref_kotlin_ByteArray set);
                  } VBTransportBytesRequest;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBytesResponse (*VBTransportBytesResponse)();
                    libkn_kref_kotlin_ByteArray (*get_data)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBytesResponse thiz);
                    void (*set_data)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBytesResponse thiz, libkn_kref_kotlin_ByteArray set);
                    libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBytesRequest (*get_request)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBytesResponse thiz);
                    void (*set_request)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBytesResponse thiz, libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBytesRequest set);
                  } VBTransportBytesResponse;
                  struct {
                    struct {
                      libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportContentType (*get)(); /* enum entry for JSON. */
                    } JSON;
                    struct {
                      libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportContentType (*get)(); /* enum entry for BYTE. */
                    } BYTE;
                    libkn_KType* (*_type)(void);
                    const char* (*toString)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportContentType thiz);
                  } VBTransportContentType;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportGetRequest (*VBTransportGetRequest)();
                  } VBTransportGetRequest;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportGetResponse (*VBTransportGetResponse)();
                    libkn_kref_kotlin_Any (*get_data)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportGetResponse thiz);
                    void (*set_data)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportGetResponse thiz, libkn_kref_kotlin_Any set);
                    libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportGetRequest (*get_request)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportGetResponse thiz);
                    void (*set_request)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportGetResponse thiz, libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportGetRequest set);
                  } VBTransportGetResponse;
                  struct {
                    struct {
                      libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportMethod (*get)(); /* enum entry for GET. */
                    } GET;
                    struct {
                      libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportMethod (*get)(); /* enum entry for POST. */
                    } POST;
                    libkn_KType* (*_type)(void);
                  } VBTransportMethod;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportPostRequest (*VBTransportPostRequest)();
                    libkn_kref_kotlin_Any (*get_data)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportPostRequest thiz);
                    void (*set_data)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportPostRequest thiz, libkn_kref_kotlin_Any set);
                    libkn_KBoolean (*isDataInitialize)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportPostRequest thiz);
                  } VBTransportPostRequest;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportPostResponse (*VBTransportPostResponse)();
                    libkn_kref_kotlin_Any (*get_data)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportPostResponse thiz);
                    void (*set_data)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportPostResponse thiz, libkn_kref_kotlin_Any set);
                    libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportPostRequest (*get_request)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportPostResponse thiz);
                    void (*set_request)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportPostResponse thiz, libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportPostRequest set);
                  } VBTransportPostResponse;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportStringRequest (*VBTransportStringRequest)();
                  } VBTransportStringRequest;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportStringResponse (*VBTransportStringResponse)();
                    const char* (*get_data)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportStringResponse thiz);
                    void (*set_data)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportStringResponse thiz, const char* set);
                    libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportStringRequest (*get_request)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportStringResponse thiz);
                    void (*set_request)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportStringResponse thiz, libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportStringRequest set);
                  } VBTransportStringResponse;
                } export_;
                struct {
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_service_VBTransportService (*_instance)();
                    void (*cancel)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_service_VBTransportService thiz, libkn_KInt requestId);
                    libkn_kref_com_tencent_qqlive_kmm_vbpbservice_impl_internal_VBTransportState (*getState)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_service_VBTransportService thiz, libkn_KInt requestId);
                    void (*sendBytesRequest)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_service_VBTransportService thiz, libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportBytesRequest request, libkn_kref_kotlin_Function1 handler);
                    void (*sendGetRequest)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_service_VBTransportService thiz, libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportGetRequest request, libkn_kref_kotlin_Function1 handler);
                    void (*sendPostRequest)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_service_VBTransportService thiz, libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportPostRequest request, libkn_kref_kotlin_Function1 handler);
                    void (*sendStringRequest)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_service_VBTransportService thiz, libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_export_VBTransportStringRequest request, libkn_kref_kotlin_Function1 handler);
                  } VBTransportService;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_service_VBTransportServiceTest (*_instance)();
                    void (*testCancelRequest)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_service_VBTransportServiceTest thiz);
                    void (*testSendByteRequest)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_service_VBTransportServiceTest thiz);
                    void (*testSendGetRequest)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_service_VBTransportServiceTest thiz);
                    void (*testSendPostRequest)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_service_VBTransportServiceTest thiz);
                    void (*testSendStringRequest)(libkn_kref_com_tencent_qqlive_kmm_vbtransportservice_service_VBTransportServiceTest thiz);
                  } VBTransportServiceTest;
                } service;
              } vbtransportservice;
            } kmm;
          } qqlive;
          struct {
            struct {
              struct {
                struct {
                  void (*bindTestPBNetwork)();
                  void (*bindTestTransportBytesRequest)();
                  void (*bindTestTransportGetRequest)();
                  void (*bindTestTransportPostRequest)();
                  void (*bindTestTransportStringRequest)();
                } sample;
                struct {
                  void (*registerIHMTransportServiceProxy)();
                } sharedtemplate;
                void (*initSharedtemplate)();
                void (*initSample)();
              } modules;
              void (*initBridge)();
              void (*initEnvExport)(void* env, void* value, libkn_KBoolean debug);
              void (*initialize)();
              void (*preInitEnv)(void* env, libkn_KBoolean debug);
            } knoi;
            struct {
              const char* (*get_TAG)();
              libkn_kref_kotlin_Function3 (*get_logProxy)();
              void (*set_logProxy)(libkn_kref_kotlin_Function3 set);
              void (*onLoad)();
              void (*setLogProxy)(libkn_kref_kotlin_Function3 logImpl);
              void (*test)();
              void (*test5)();
              void (*printStacks)(libkn_kref_kotlin_Throwable thiz);
              const char* (*stacksToString)(libkn_kref_kotlin_Throwable thiz);
            } kotlin_stdblit_platform_ext;
          } tmm;
        } tencent;
      } com;
    } root;
  } kotlin;
} libkn_ExportedSymbols;
extern libkn_ExportedSymbols* libkn_symbols(void);
#ifdef __cplusplus
}  /* extern "C" */
#endif
#endif  /* KONAN_LIBKN_H */
