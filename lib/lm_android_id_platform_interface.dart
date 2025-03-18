import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'lm_android_id_method_channel.dart';

abstract class LmAndroidIdPlatform extends PlatformInterface {
  /// Constructs a LmAndroidIdPlatform.
  LmAndroidIdPlatform() : super(token: _token);

  static final Object _token = Object();

  static LmAndroidIdPlatform _instance = MethodChannelLmAndroidId();

  /// The default instance of [LmAndroidIdPlatform] to use.
  ///
  /// Defaults to [MethodChannelLmAndroidId].
  static LmAndroidIdPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [LmAndroidIdPlatform] when
  /// they register themselves.
  static set instance(LmAndroidIdPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getDeviceId() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
