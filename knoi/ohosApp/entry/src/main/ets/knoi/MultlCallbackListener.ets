import { MultiCallbackListener } from './callback';

export class MultiCallbackListenerImpl implements MultiCallbackListener {
  onStart() {
    console.log("qizhengchen MultiCallbackListener#onStart");
  }

  onEnd() {
    console.log("qizhengchen MultiCallbackListener#onEnd");
  }

  onSuccess() {
    console.log("qizhengchen MultiCallbackListener#onSuccess");
  }

  onFail() {
    console.log("qizhengchen MultiCallbackListener#onFail");
  }
}