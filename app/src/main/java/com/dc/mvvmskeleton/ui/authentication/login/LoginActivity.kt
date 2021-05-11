package com.dc.mvvmskeleton.ui.authentication.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dc.mvvmskeleton.R
import com.dc.mvvmskeleton.data.local.AppPrefrence
import com.dc.mvvmskeleton.data.model.User
import com.dc.mvvmskeleton.data.model.hb.Generics
import com.dc.mvvmskeleton.data.model.hb.WSGenericResponse
import com.dc.mvvmskeleton.data.remote.ApiClient
import com.dc.mvvmskeleton.databinding.ActivityLoginBinding
import com.dc.mvvmskeleton.ui.authentication.dashboard.DashboardActivity
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by HB on 20/8/19.
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.btnLogin.setOnClickListener {
            loginJoinPods()
        }
    }

    private fun loginJoinPods() {
        val map = HashMap<String, String>()
        map["mobile_no"] = "9662534851"
        map["country_code"] = "+91"
        map["password"] = "2Hkac1RDAbZFyfm5ixKI4Q=="
        map["device_type"] = "Android"
        map["device_token"] = "cJDNLG3_xSw:APA91bH-muWSmSWrQFEPpoC05ofYWGxgAGMNUxYYG6WiGsPPooSPsZuEhpZaMNW-4tJorTeGzjL990ZcPdLNHElVnfWYxAlX_W0g4lKnSpqmRjYFoc5-FcU149sIPL7fIqJYCK2gaqTv"
        map["device_token_voip"] = "cJDNLG3_xSw:APA91bH-muWSmSWrQFEPpoC05ofYWGxgAGMNUxYYG6WiGsPPooSPsZuEhpZaMNW-4tJorTeGzjL990ZcPdLNHElVnfWYxAlX_W0g4lKnSpqmRjYFoc5-FcU149sIPL7fIqJYCK2gaqTv"
        map["device_os"] = "24"
        map["device_name"] = "motorola Moto G (4) 7.0 M"
        map["app_version"] = "2.1"

        ApiClient.apiService.loginJoinPods(map).enqueue(object : Callback<WSGenericResponse<JsonElement>> {
            override fun onResponse(call: Call<WSGenericResponse<JsonElement>>, response: Response<WSGenericResponse<JsonElement>>) {
                /*Toast.makeText(this@LoginActivity, response.body()?.data!!.asString, Toast.LENGTH_SHORT).show()*/
                val body = response.body()
                val res = Generics.with(body?.data!!).getAsObject(User::class.java)
                AppPrefrence().putToken(this@LoginActivity, body.settings?.accessToken!!)
                startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                finish()
            }

            override fun onFailure(call: Call<WSGenericResponse<JsonElement>>, t: Throwable) {

            }
        })
    }

}
