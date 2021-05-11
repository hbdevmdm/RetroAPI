package com.dc.mvvmskeleton.data.local

import android.content.Context

/**
 * Created by HB on 21/8/19.
 */
class AppPrefrence {
    fun putToken(context: Context, token: String) {
        context.getSharedPreferences("appPref", Context.MODE_PRIVATE).edit().putString("token", token).apply()
    }

    fun getToken(context: Context): String {
        return context.getSharedPreferences("appPref", Context.MODE_PRIVATE).getString("token", "")
                ?: ""
    }
}
