package com.androidbridge

import android.content.Context
import android.content.SharedPreferences
import android.webkit.JavascriptInterface
import org.json.JSONObject

class NativeBridge(
    private val context: Context,
    private val prefs: SharedPreferences
) {

    @JavascriptInterface
    fun saveServerUrl(url: String) {
        prefs.edit().putString("server_url", url).apply()
    }

    @JavascriptInterface
    fun saveAuthToken(token: String) {
        prefs.edit().putString("auth_token", token).apply()
    }

    @JavascriptInterface
    fun getServerUrl(): String {
        return prefs.getString("server_url", "") ?: ""
    }

    @JavascriptInterface
    fun getAuthToken(): String {
        return prefs.getString("auth_token", "") ?: ""
    }

    @JavascriptInterface
    fun getDeviceInfo(): String {
        return JSONObject().apply {
            put("manufacturer", android.os.Build.MANUFACTURER)
            put("model", android.os.Build.MODEL)
            put("android", android.os.Build.VERSION.RELEASE)
            put("sdk", android.os.Build.VERSION.SDK_INT)
        }.toString()
    }

    @JavascriptInterface
    fun clearSettings() {
        prefs.edit().clear().apply()
    }

    @JavascriptInterface
    fun getAppVersion(): String = "1.0"
}
