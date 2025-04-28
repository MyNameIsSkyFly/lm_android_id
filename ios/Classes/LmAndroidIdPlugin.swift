import Flutter
import UIKit

public class LmAndroidIdPlugin: NSObject, FlutterPlugin {
    public static func register(with registrar: FlutterPluginRegistrar) {
        let channel = FlutterMethodChannel(name: "lm_android_id", binaryMessenger: registrar.messenger())
        let instance = LmAndroidIdPlugin()
        registrar.addMethodCallDelegate(instance, channel: channel)
    }
    
    public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
        switch call.method {
        case "getDeviceId":
            let applicationUUID = (UIDevice.current.identifierForVendor?.uuidString)!
            result(applicationUUID)
        default:
            result(FlutterMethodNotImplemented)
        }
    }
 
}
