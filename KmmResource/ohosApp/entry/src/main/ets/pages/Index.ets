import { bind } from "knoi"
import { invoke } from "knoi"
import { registerServiceProvider } from "knoi"
import { font } from '@kit.ArkUI';

//TestService 为自行定义的 Ark class

/*const OHOS_FUN_GET_STRING = "getStringResourceByName"
const OHOS_FUN_GET_STRING_ARGS = "getStringResourceByNameArgs"
const OHOS_FUN_GET_PLURAL = "getPluralStringResourceByName"
const OHOS_FUN_GET_COLOR = "getColorResourceByName"
const OHOS_FUN_GET_IMAGE = "getImageResourceBytearrayByName"
const OHOS_FUN_GET_IMAGE_BASE64 = "getImageResourceBase64ByName"
const OHOS_FUN_GET_FILE = "getRawfileResourceBytearrayByName"

bind(OHOS_FUN_GET_STRING, (resName: string) => {
  return getContext().resourceManager.getStringByNameSync(resName)
})
bind(OHOS_FUN_GET_STRING_ARGS, (resName: string, args: Array<string | number>) => {
  return getContext().resourceManager.getStringByNameSync(resName,...args)
})
bind(OHOS_FUN_GET_PLURAL, (resName: string, args: number) => {
  return getContext().resourceManager.getPluralStringByNameSync(resName, args)
})
bind(OHOS_FUN_GET_COLOR, (resName: string) => {
  return getContext().resourceManager.getColorByNameSync(resName)
})
bind(OHOS_FUN_GET_IMAGE, (resName: string) => {
  return getContext().resourceManager.getMediaByNameSync(resName)
})
bind(OHOS_FUN_GET_IMAGE_BASE64, (resName: string) => {
  return getContext().resourceManager.getMediaBase64ByNameSync(resName)
})
bind(OHOS_FUN_GET_FILE, (resName: string) => {
  return getContext().resourceManager.getRawFileContentSync(resName)
})*/
@Entry
@Component
struct Index {
  @State message: string = 'Hello World';

  onPageShow(): void {
    //invoke<void>("testJSFunction")
    font.registerFont({
      familyName: 'cormorant-italic',
      familySrc: $rawfile('cormorant-italic.otf')
    })
  }
  build() {
    Row() {
      Column() {
        Text(this.message)
          .fontSize(50)
          .fontWeight(FontWeight.Bold)
          .onClick(()=>{
            invoke("JSBridge")
            console.log("JSBridge arkTS color textColor" +getContext().resourceManager.getColorByNameSync("textColor"))
          })
          .fontFamily($r("sys.string.ohos_id_text_font_family_regular"))

      }
      .width('100%')
    }
    .height('100%')
  }
}