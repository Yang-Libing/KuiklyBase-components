import { display, FrameNode, NodeController, NodeRenderType, XComponentNode } from '@kit.ArkUI';

let globalContext:Record<string, () => void>|null = null

export class ComposeNode extends XComponentNode {

  private context:Record<string, () => void>|null = null

  onCreate(event: Object) {
    // do something when XComponentNode has created
    globalContext = event as Record<string, () => void>
  }

  onDestroy() {
    // do something when XComponentNode is destroying
  }
}

export class XComponentNodeController extends NodeController {
  private xComponentNode: ComposeNode | null = null;
  private soName: string = "show" // 该 so 由开发者通过 NAPI 编写并生成
  private didRender = false

  makeNode(context: UIContext): FrameNode | null {
    const size = display.getDefaultDisplaySync()
    const composeWidth = size.width / 3.25
    const composeHeight  = (size.height / 3.25) -200


    this.xComponentNode = new ComposeNode(context, {
      selfIdealSize: { width: composeWidth, height: composeHeight }
    }, "opengl_xcomponent", XComponentType.SURFACE, this.soName);
    // setTimeout(()=>{
      this.didRender = true;
    // }, 1000)
    return this.xComponentNode;
  }

  changeRenderType(renderType: NodeRenderType): void {
    if (this.xComponentNode) {
      this.xComponentNode.changeRenderType(renderType);
    }
  }

  drawCompose() {
    globalContext?.drawCompose()
  }

  dispatchEventTouch(event:TouchEvent) {
    if (!this.didRender) { return }
    const dispatch = globalContext?.dispatchEventTouch
    if (dispatch) {
      const func = dispatch as (event:TouchEvent) => void
      func(event)
    }
  }
}