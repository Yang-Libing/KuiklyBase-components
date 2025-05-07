import SwiftUI
import shared_template

let logTag = "[iOS PBService]"
let useCurl = false

struct ContentView: View {
    let greet = "hello world"

    var body: some View {
        VStack(content: {
            Button("测试 PB", action: {
                VBPBServiceTest().testSendRequest(logTag: logTag, useCurl: useCurl)
            })
            Button("测试 Post", action: {
                VBTransportServiceTest().testSendPostRequest(logTag: logTag, useCurl: useCurl)
            })
            Button("测试 sendByte", action: {
                VBTransportServiceTest().testSendByteRequest(logTag: logTag, useCurl: useCurl)
            })
            Button("测试 sendString", action: {
                VBTransportServiceTest().testSendStringRequest(logTag: logTag, useCurl: useCurl)
            })
            Button("测试 Get", action: {
                VBTransportServiceTest().testSendGetRequest(logTag: logTag, useCurl: useCurl, isByteContentType: false)
            })
            Button("测试 Cancel", action: {
                VBTransportServiceTest().testCancelRequest()
            })
            Button("测试 SSE", action: {
                VBSSEServiceTest().testSSEService()
            })
            Button("初始化", action: {
                VBPBServiceTest().testServiceInit() // 初始化
            })
        })
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
