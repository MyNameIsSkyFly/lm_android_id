
import 'lm_android_id_platform_interface.dart';

class LmAndroidId {
  Future<String?> getDeviceId() {
    return LmAndroidIdPlatform.instance.getDeviceId();
  }
}
