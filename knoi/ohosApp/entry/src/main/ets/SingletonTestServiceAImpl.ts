import { SingletonTestServiceA } from './knoi/consumer';

export default class SingletonTestServiceAImpl implements SingletonTestServiceA {
  number: Number = 0

  constructor() {
    this.number = 42
    console.log("qizhengchen SingletonTestServiceAImpl constructor...");
  }

  method1(str: String) {
    console.log("qizhengchen SingletonTestServiceAImpl method1 call... params :" + str);
    console.log("qizhengchen SingletonTestServiceAImpl method1 call... name : this.number" + this.number);
  }

  method2(str: String) {
    console.log("qizhengchen SingletonTestServiceAImpl method2 call... params :" + str);
    console.log("qizhengchen SingletonTestServiceAImpl method2 call... name : this.number" + this.number);
    return str + "In ArkTS"
  }
}