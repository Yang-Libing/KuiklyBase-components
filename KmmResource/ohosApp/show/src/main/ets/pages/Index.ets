import testNapi from 'libshow.so';
import { DrawContext, FrameNode, NodeController, NodeRenderType, RenderNode, XComponentNode } from "@ohos.arkui.node";
import { XComponentNodeController } from '../xcomponnet/XComponentNodeController';
import display from '@ohos.display';
import { UIContext } from '@ohos.arkui.UIContext';
import { inject } from './inject'
import hilog from '@ohos.hilog';

function randomColor(): number {
  return Math.floor(Math.random() * 0xFFFFFFFF);
}

let beginDate = (new Date()).getTime()

let times = new Array<number>();

function nativeWillDraw() {
  beginDate = (new Date()).getTime()
}

function nativeDidDraw() {
  const diff = ((new Date()).getTime()) - beginDate;
  times.push(diff)
}

let records = new Array<number>();
function skikobridge_compose_record_time(duration:number) {
  records.push(duration)
}

class MyRenderNode extends RenderNode {
  constructor() {
    super()
    inject(() => {
      this.invalidate()
    }, nativeWillDraw, nativeDidDraw, skikobridge_compose_record_time);
  }

  draw(context: DrawContext): void {
    this.invalidate()
    testNapi.drawFromRenderNode();
  }

  private offset: Boolean = true

  invalidate(): void {
    this.size = { width: this.size.width, height: this.size.height + (this.offset ? 1 : -1) }
    this.offset = !this.offset
    super.invalidate()
  }
}

class MyController extends NodeController {
  private rednerNode = new MyRenderNode()

  makeNode(uiContext: UIContext): FrameNode {
    const frameNode = new FrameNode(uiContext)
    const renderNode = frameNode.getRenderNode()
    if (renderNode) {
      renderNode.appendChild(this.rednerNode)
      this.rednerNode.backgroundColor = 0xffff0000
      this.rednerNode.size = { width: 60, height: 60 }
    }
    return frameNode
  }
}

@Entry
@Component
export struct OHShow {
  @State message: string = 'Hello World';
  private vc = new XComponentNodeController()
  private hasDraw = false;
  private invalidateVC = new MyController()
  private composeWidth: number = display.getDefaultDisplaySync().width / 3.25
  private composeHeight: number = display.getDefaultDisplaySync().height / 3.25

  build() {
    Row() {
      Row() {
        Column() {
          NodeContainer(this.vc)
          Row() {
            NodeContainer(this.invalidateVC)
            Row() {
              Text("ClearRecording")
            }.width("35%").height(40).backgroundColor('#ff00ff00').onClick(() => {
              times = []
              records = []
            })

            Row() {
              Text("DumpRecording")
            }.width("35%").height(40).backgroundColor('#ff0000ee').onClick(() => {
                times.forEach((it)=>{
                  console.log(`[Trace] 耗时: ${it} ms`);
                });

              records.forEach((it)=>{
                console.log(`[Trace]  glFlush 等 耗时:${it} ms`);
              });
            })

            Row() {
              Text("show test")
            }.width("35%").height(40).backgroundColor('#5555555').onClick(() => {
              hilog.info(0x0000, 'testShowTag', 'call intAdd 1 + 2 = %{public}d', testNapi.testShow(1, 2));
              testNapi.ktHandleMethodFromOH();

              const numTimes = (a: number): number => {
                const b = a * 10;
                hilog.info(0x0000, 'testTag', 'call cCallArkTS = %{public}d',b);
                return b;
              };
              testNapi.cCallArkTS(numTimes);
            })
          }
        }
      }
      .onAppear(() => {
        setTimeout(()=>{
          if (!this.hasDraw) {
            this.hasDraw = true;
            this.vc.drawCompose()
          }
        }, 100)
      })
      .onTouch((event?: TouchEvent) => {
        if (event) {
          // const endTime = new Date().getTime();
          // const timeInterval = endTime - this.start;
          // this.start = endTime;
          // console.log("[Touch] JS 间隔（毫秒）：", timeInterval);
          // event.touches.forEach((item)=>{
          //   console.log(`[Touch] JS touchItem.x:${item.x * 3.25} item.y:${item.y * 3.25} id:${item.id} timeStamp:${event.timestamp}`)
          // })
          //
          // event.getHistoricalPoints().forEach((item)=>{
          //   console.log(`[Touch] JS HistoricalPoints touchItem.x:${item.touchObject.x * 3.25} item.y:${item.touchObject.y * 3.25} timeStamp:${item.timestamp}`)
          // })
          // const t1 = new Date().getTime();
          this.vc.dispatchEventTouch(event)

          // const t2 = new Date().getTime();
          // console.log("[Touch] native 处理一帧（毫秒）：", (t2 - t1));
        }
      })
      .width(this.composeWidth)
      .height(this.composeHeight - 200)
      .backgroundColor('#ffff0000')
    }
    .height('100%')
  }
}
