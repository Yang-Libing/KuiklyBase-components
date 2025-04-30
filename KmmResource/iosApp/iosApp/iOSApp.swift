import SwiftUI
import shared_template

@main
struct iOSApp: App {

   init() {
        // 在应用程序启动时执行的逻辑
        print("App is starting!")
        Greeting().doInitPlatform()
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}