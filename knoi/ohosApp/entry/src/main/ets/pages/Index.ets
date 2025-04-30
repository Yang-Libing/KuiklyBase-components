import { bind, getService, invoke, registerServiceProvider, registerSingleServiceProvider, setup, unBind } from "knoi"
import { MultiCallbackListenerImpl } from '../knoi/MultlCallbackListener'
import {
  SingletonTestServiceB,
  TestServiceB,
  getSingletonTestServiceB,
  getTestServiceB,
  getMultiCallbackSample,
  TestModuleLoad
} from '../knoi/provider'
import SingletonTestServiceAImpl from '../SingletonTestServiceAImpl'
import TestServiceAImpl from '../TestServiceAImpl'
import { process, worker } from '@kit.ArkTS'
import { DeclareTest_testString, testString } from '../knoi/declare'
import { Rect } from '../knoi/rect'
import router from '@ohos.router';
import { display } from '@kit.ArkUI'
import TestServiceAInWorkerImpl from '../TestServiceAInWorkerImpl'

@Entry
@Component
struct Index {
  // ！！！测试 JSCallback 泄露，请不要这样使用！！！
  private func: Function | undefined
  onPageShow(): void {
    registerServiceProvider("TestServiceAInWorker", true, TestServiceAInWorkerImpl)
    bind("testStringReturnStringForKMM", (name: string) => {
      console.log("qizhengchen testStringReturnStringForKMM")
    })
    bind("testNumberReturnNumberForKMM", (age: number) => {
      console.log("qizhengchen testNumberReturnNumberForKMM")
      return age + 1
    })
    bind("testObjectReturnObjectForKMM", (obj: object) => {
      console.log("qizhengchen testObjectReturnObjectForKMM")
      return obj
    })
    bind("testBooleanReturnBooleanForKMM", (result: Boolean) => {
      console.log("qizhengchen testBooleanReturnBooleanForKMM")
      return !result
    })

    bind("testVoidReturnVoidForKMM", () => {
      console.log("qizhengchen testVoidReturnVoidForKMM")
    })

    bind("testUint8ArrayReturnVoidForKMM", () => {
      console.log("qizhengchen testUint8ArrayReturnVoidForKMM")
      return new Uint8Array(0)
    })

    bind("testJsCallbackReturnVoid", (func: Function) => {
      console.log("qizhengchen testJsCallbackReturnVoid")
      func("testJsCallbackReturnVoidCallback")
    })

    bind("testArrayBufferReturnArrayBufferForKMM", (buffer: ArrayBuffer): ArrayBuffer => {
      let inputBuffer: Int8Array = new Int8Array(buffer);
      console.log(`qizhengchen testArrayBufferReturnArrayBufferForKMM ${inputBuffer[0]} ${inputBuffer[1]} ${inputBuffer[2]} ${inputBuffer[3]}`)
      inputBuffer[4] = 4
      inputBuffer[5] = 5
      inputBuffer[6] = 6
      inputBuffer[7] = 7
      return inputBuffer
    })

    bind("testArrayBufferInt32ReturnArrayBufferInt32ForKMM", (buffer: Int32Array): Int32Array => {
      // let inputBuffer: Int8Array = new Int8Array(buffer);
      console.log(`qizhengchen testArrayBufferInt32ReturnArrayBufferInt32ForKMM ${buffer[0]} ${buffer[1]} ${buffer[2]} ${buffer[3]}`)
      buffer[4] = 4
      buffer[5] = 5
      buffer[6] = 6
      buffer[7] = 7
      return buffer
    })

    registerServiceProvider("TestServiceA", false, TestServiceAImpl)
    registerSingleServiceProvider("SingletonTestServiceA", new SingletonTestServiceAImpl())
  }

  build() {
    Row() {
      Column() {
        Text("BenchMark")
          .fontSize(40)
          .fontWeight(FontWeight.Bold)
          .onClick(() => {
            onJumpClick()
          })
        Text("LoadBalance")
          .fontSize(40)
          .fontWeight(FontWeight.Bold)
          .onClick(() => {
            invoke("testLoadBalance")
          })
        Text("ArkTS call KN")
          .fontSize(40)
          .fontWeight(FontWeight.Bold)
          .onClick(() => {
            console.log("ohos tid:"+process.tid + "pid:"+process.pid)
            let result: String = invoke<String>("testStringReturnString", "input")
            console.log("qizhengchen invoke testStringReturnString result " + result);
            result = invoke<String>("testVoidReturnString",)
            console.log("qizhengchen invoke testVoidReturnString result " + result);
            invoke<void>("testVoidReturnVoid",)
            console.log("qizhengchen invoke testVoidReturnVoid result void");
            let numberResult: Number = invoke<number>("testIntReturnInt", 42)
            console.log("qizhengchen invoke testIntReturnInt result " + numberResult);
            numberResult = invoke<number>("testLongReturnLong", 42)
            console.log("qizhengchen invoke testLongReturnLong result " + numberResult);

            numberResult = invoke<number>("testDoubleReturnDouble", 42.1)
            console.log("qizhengchen invoke testDoubleReturnDouble result " + numberResult);

            let boolResult: Boolean = invoke<Boolean>("testBooleanReturnBoolean", true)
            console.log("qizhengchen invoke testBooleanReturnBoolean result " + boolResult);

            let objResult: Object = invoke<Object>("testMapReturnMap", {
              name: "ArkTs", age: 42, children: {
                name: "ArkTS", age: 18
              }
            })
            console.log("qizhengchen invoke testMapReturnMap result " + JSON.stringify(objResult));

            invoke("testJSCallbackReturnVoid", (result: string) => {
              console.log("qizhengchen invoke testJSCallbackReturnVoid log in ArkTs result = " + result)
            })

            invoke("testJSCallbackReturnString", (result: string) => {
              return result + "ArkTS"
            })
            invoke("testJSCallbackReturnMap", (result: Object) => {
              console.log("qizhengchen invoke testJSCallbackReturnMap result " + JSON.stringify(result));
              return result
            })

            this.func = invoke<Function>("testJSCallbackReturnJSCallback", (result: Object) => {
              console.log("qizhengchen invoke testJSCallbackReturnJSCallback result " + JSON.stringify(result));
              return result
            })
            console.log("qizhengchen invoke testJSCallbackReturnJSCallback func " + this.func);
            this.func("testJSCallbackReturnJSCallback invoke")
            let arrayResult: Array<String> =
              invoke<Array<String>>("testArrayReturnArray", ["ArkTsArray1", "ArkTsArray2"])
            console.log("qizhengchen invoke testArrayReturnArray result " + JSON.stringify(arrayResult));

            arrayResult = invoke<Array<String>>("testListReturnList", ["ArkTsArray1", "ArkTsArray2"])
            console.log("qizhengchen invoke testListReturnList result " + JSON.stringify(arrayResult));

            let buff: ArrayBuffer = new ArrayBuffer(8);
            let int8Buff: Int8Array = new Int8Array(buff);
            int8Buff[0] = 0;
            int8Buff[1] = 1;
            int8Buff[2] = 2;
            int8Buff[3] = 3;
            let bufferResult: ArrayBuffer = invoke<ArrayBuffer>("testArrayBufferReturnArrayBuffer", buff)
            let resultInt8Buff: Int8Array = new Int8Array(bufferResult);
            console.log(`qizhengchen invoke testArrayBufferReturnArrayBuffer result:${resultInt8Buff[4]} ${resultInt8Buff[5]} ${resultInt8Buff[6]} ${resultInt8Buff[7]}`)

            let obj: Map<String, ArrayBuffer> = invoke<Map<String, ArrayBuffer>>("testVoidReturnArrayBufferInMap")
            let data: ArrayBuffer = obj["data"]
            let dataInt32Buff: Int32Array = new Int32Array(data);
            console.log(`qizhengchen invoke testVoidReturnArrayBufferInMap result = ${dataInt32Buff[0]} ${dataInt32Buff[1]} ${dataInt32Buff[2]} ${dataInt32Buff[3]}`);
          })
        Text("KN call ArkTS")
          .fontSize(40)
          .fontWeight(FontWeight.Bold)
          .onClick(() => {
            invoke("testJSFunction")
            // unBind("testVoidReturnVoidForKMM")
          })

        Text("JSValue Test")
          .fontSize(40)
          .fontWeight(FontWeight.Bold)
          .onClick(() => {
            invoke("testJSValue")
          })

        Text("MainHandle Test")
          .fontSize(40)
          .fontWeight(FontWeight.Bold)
          .onClick(() => {
            invoke("testMainHandler")
          })

        Text("Coroutines Test")
          .fontSize(40)
          .fontWeight(FontWeight.Bold)
          .onClick(() => {
            invoke("testCoroutines")
          })

        Text("Service Test")
          .fontSize(40)
          .fontWeight(FontWeight.Bold)
          .onClick(() => {
            invoke("testService")
            let serviceB = getTestServiceB()
            serviceB.methodWithStringReturnString("method1 from ArkTs")
            let result = serviceB.methodWithStringReturnString("method2 from ArkTs")
            console.log("qizhengchen TestServiceB method2 result = " + result)

            let intResult = serviceB.methodWithIntReturnInt(42)
            console.log("qizhengchen TestServiceB methodWithIntReturnInt result = " + intResult)
            let strResult = serviceB.methodWithStringReturnString("param in ArkTS")
            console.log("qizhengchen TestServiceB methodWithStringReturnString result = " + strResult)
            let longResult = serviceB.methodWithLongReturnLong(42)
            console.log("qizhengchen TestServiceB methodWithLongReturnLong result = " + longResult)
            let doubleResult = serviceB.methodWithDoubleReturnDouble(42.0)
            console.log("qizhengchen TestServiceB methodWithDoubleReturnDouble result = " + doubleResult)
            let booleanResult = serviceB.methodWithBooleanReturnBoolean(true)
            console.log("qizhengchen TestServiceB methodWithBooleanReturnBoolean result = " + booleanResult)
            let arrayResult = serviceB.methodWithArrayStringReturnArrayString(["1", "2"])
            console.log("qizhengchen TestServiceB methodWithArrayStringReturnArrayString result = " + arrayResult)
            let map : Map<string,string | undefined> = new Map()
            map.set("key1","value1")
            map.set("key2","value2")
            map.set("key3","value3")
            map.set("key4","value4")
            map.set("key5",undefined)
            let objResult = serviceB.methodWithMapReturnMap(map)
            console.log("qizhengchen TestServiceB methodWithMapReturnMap result = " + objResult)
            serviceB.methodWithUnit()
            console.log("qizhengchen TestServiceB methodWithUnit result")
            let func: Function = serviceB.methodWithCallbackReturnCallback((a: string) => {
              console.log("qizhengchen TestServiceB methodWithCallbackReturnCallback result = " + a)
            })
            let callbackResult: string = func()
            console.log("qizhengchen TestServiceB methodWithCallbackReturnCallback callback result = " + callbackResult)

            // let func1: Function = serviceB.methodWithCallbackReturnCallback2((a: string, b: Number) => {
            //   console.log(`qizhengchen TestServiceB methodWithCallbackReturnCallback2 result a = ${a} , b = ${b}`)
            // }, "string", 42)
            // let func1Result: string = func1()
            // console.log("qizhengchen TestServiceB methodWithCallbackReturnCallback2 callback result = " + func1Result)

            serviceB.methodWithCallbackReturnCallback3(() => {
              console.log(`qizhengchen TestServiceB methodWithCallbackReturnCallback3 callback1`)
            }, () => {
              console.log(`qizhengchen TestServiceB methodWithCallbackReturnCallback3 callback2`)
            })

            let buff: ArrayBuffer = new ArrayBuffer(8);
            let int8Buff: Int8Array = new Int8Array(buff);

            let multiTypeMap: Record<string, Function | ArrayBuffer | Object> = {
              "callback": (result: string) => {
                console.log("qizhengchen multiTypeMap callback result " + result);
              },
              "arrayBuffer": buff,
              "typedBuffer": int8Buff,
              "object": this.getUIContext()
            }
            serviceB.methodWithDefaultValue()
            serviceB.methodWithDefaultValueInSubType()

            let multiTypeObject = serviceB.methodWithMultiTypeMap(multiTypeMap)
            let jsMap = new Map<string,string>()
            jsMap.set("key", "value")
            let map1: Record<string, number | string | Array<String> | Map<string,string>> = {
              "name": "arkTS",
              "arg": 42,
              "json": "jsonValue",
              "map" : jsMap
            }
            map1["array"] = ["element1", "element2", "element3"]
            let jsValueResult: Object = serviceB.methodWithJSValueReturnJSValue(map1)
            console.log("qizhengchen TestServiceB methodWithJSValueReturnJSValue result = " + jsValueResult)

            let singletonB = getSingletonTestServiceB()
            getSingletonTestServiceB().method1("method1 from ArkTs")
            singletonB.method1("method1 from ArkTs")
            result = singletonB.method2("method2 from ArkTs")
            console.log("qizhengchen SingletonTestServiceB method2 result = " + result)
          })

        Text("MultiCallbackTest")
          .fontSize(40)
          .fontWeight(FontWeight.Bold)
          .onClick(() => {
            getMultiCallbackSample().init(new MultiCallbackListenerImpl())
            getMultiCallbackSample().callListener()
          })

        Text("JSValue In Sub")
          .fontSize(40)
          .fontWeight(FontWeight.Bold)
          .onClick(() => {
            invoke("testJSValueInSubThread")
          })
        Text("Service In Sub")
          .fontSize(40)
          .fontWeight(FontWeight.Bold)
          .onClick(() => {
            invoke("testServiceInSubThread")
          })
        Text("Service In JSWorker")
          .fontSize(40)
          .fontWeight(FontWeight.Bold)
          .onClick(() => {
            invoke("testServiceInJSWorker")
          })
        Text("Custom Class Wrap")
          .fontSize(40)
          .fontWeight(FontWeight.Bold)
          .onClick(() => {
            let result = invoke<Rect>("testCustomClassWrap")
            console.log("qizhengchen result.getLeft = " + result.getLeft(42))
            console.log("qizhengchen result.getLeft = " + result.getLeft(42))
          })
        Text("Declare Test")
          .fontSize(40)
          .fontWeight(FontWeight.Bold)
          .onClick(() => {
            let result = testString
            console.log("qizhengchen Test Declare result = " + result)
            result = DeclareTest_testString
            console.log("qizhengchen Test Declare result = " + result)
          })
        Text("测试 JSValue 引用问题")
          .fontSize(40)
          .fontWeight(FontWeight.Bold)
          .onClick(() => {
            let logProxy: Record<string,object> = {};
            logProxy["impl"] = new LogImpl()
            getTestServiceB().testJSValueRef(logProxy)
          })

        Text("Test Module Load:"+display.getDefaultDisplaySync().height)
          .fontSize(40)
          .fontWeight(FontWeight.Bold)
          .onClick(() => {
            getService<TestModuleLoad>("TestModuleLoad").testModule()
          })

      }
      .width('100%')
    }
    .height('100%')
  }


}

// 在Home页面中
function onJumpClick(): void {
  router.pushUrl({
    url: 'pages/benchmark' // 目标url
  }, router.RouterMode.Standard, (err) => {
    if (err) {
      console.error(`Invoke pushUrl failed, code is ${err.code}, message is ${err.message}`);
      return;
    }
    console.info('Invoke pushUrl succeeded.');
  });
}

class LogImpl {
  info(msg: string) {
    console.log("qizhengchenjsvalue" + msg)
  }
}