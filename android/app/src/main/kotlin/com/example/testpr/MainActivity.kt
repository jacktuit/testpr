package com.example.testpr

import android.os.Build
import android.os.Handler
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.kollus.sdk.media.KollusStorage
import com.kollus.sdk.media.MediaPlayer
import com.kollus.sdk.media.content.FileManager
import com.kollus.sdk.media.content.KollusContent
import com.kollus.sdk.media.content.PallyconDownloader
import com.kollus.sdk.media.content.PallyconDownloader.Callback
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel


class MainActivity : FlutterActivity() {
    private val CHANNEL = "com.example.myapp/methodChannel"

    init {
        val fileManage = FileManager(1)
        fileManage.fileList


    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        val handler = Handler.createAsync(Handler().looper)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
                .setMethodCallHandler { call: MethodCall, result: MethodChannel.Result ->
                    if (call.method == "getMessageFromAndroid") {
                        val message: String = getData()

                        PallyconDownloader(this, KollusStorage(this), handler,
                                KollusContent(), object : Callback {
                            override fun onComplete(p0: KollusContent?) {
//                                Toast.makeText(this@MainActivity, "Co mplete", Toast.LENGTH_SHORT).show()
                            }

                            override fun onProgress(p0: KollusContent?) {
//                                result.success(message)
                            }

                            override fun onError(p0: KollusContent?, p1: Int) {
//                                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                            }
                        }, "https://v.kr.kollus.com/si?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHB0IjoxNjg0NDk2OTYyLCJjdWlkIjoiYXMxYXMiLCJtYyI6eyIwIjp7Im1ja2V5IjoiVGlvcWhMS3UifX19.w-QxoAVql4BtWwM2ZSJW_tB3l1pt5WVMbys7B4zXr5Y&custom_key=b58ff70f6bdc17470f604e2e19500c97faa3fd7c677d206f51db05858331221c&uservalue0=1111").download();

                        result.success(message)
                    } else {
                        val message: String = getDataSecond()
                        result.success(message)
                    }
                }
    }


    private fun getData(): String {
        return "Test Method"
    }

    private fun getDataSecond(): String {
        return "Test MethodSecond"
    }

}
