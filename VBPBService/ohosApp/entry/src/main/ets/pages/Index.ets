import hilog from '@ohos.hilog';
import { invoke } from "knoi"

@Entry
@Component
struct Index {
  @State message: string = 'Hello World';

  build() {
    Row() {
      Column() {
        Text(this.message)
          .fontSize(50)
          .fontWeight(FontWeight.Bold)
          .onClick(() => {
            hilog.debug(1, "Null", "test")
            invoke("testPBNetwork")
          })
      }
      .width('100%')
    }
    .height('100%')
  }
}