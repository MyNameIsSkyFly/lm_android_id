import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'lm_android_id_platform_interface.dart';

/// An implementation of [LmAndroidIdPlatform] that uses method channels.
class MethodChannelLmAndroidId extends LmAndroidIdPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('lm_android_id');

  @override
  Future<String?> getDeviceId() async {
    final version = await methodChannel.invokeMethod<String>('getDeviceId');
    return version;
  }
}
