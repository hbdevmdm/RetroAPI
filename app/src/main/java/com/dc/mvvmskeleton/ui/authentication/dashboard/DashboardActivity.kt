package com.dc.mvvmskeleton.ui.authentication.dashboard

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dc.mvvmskeleton.R
import com.dc.mvvmskeleton.data.remote.ApiClient
import com.dc.mvvmskeleton.data.remote.PlainApiClient
import com.dc.mvvmskeleton.databinding.ActivityDashboardBinding
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by HB on 3/9/19.
 */
class DashboardActivity : AppCompatActivity() {

    /*private val logger = Logger(this)*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*logger.dumpCustomEvent("OnCreate", "OnCreate Called From Dashboard screen")*/

        val binding = DataBindingUtil.setContentView<ActivityDashboardBinding>(this, R.layout.activity_dashboard)
        binding.btnEditProfile.setOnClickListener {
            sample()
        }

    }


    private fun sample(){
        val json = JsonObject()
        json.addProperty("flag",1)
        json.addProperty("id",15)
        json.addProperty("start_time","2021-12-14T08:23:42Z")
        json.addProperty("end_time","2021-12-14T14:50:15Z")
        json.addProperty("calories_burnt",1500)
        json.addProperty("effort_level",2)
        json.addProperty("input_date","2021-12-14")
        val act=Activity()
        ApiClient.apiService.addUserActivityLogX( act).enqueue(object:Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {

            }

            override fun onFailure(call: Call<String>, t: Throwable) {

            }
        })
    }
    private fun editProfileJoinPods() {
        /*ApiClient.apiService.editProfileJoinPods(WebServiceUtils.getStringRequestBody("666"),
                null)
                .enqueue(object : Callback<JsonElement> {
                    override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {

                    }

                    override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                    }
                })
                *//*WebServiceUtils.getStringMultipartBodyPart("profile_image",
                        File("/storage/emulated/0/Download/p.jpg").absolutePath)*//**//*)*/

        PlainApiClient().getService().createUser("abc", "xyz").enqueue(object : Callback<JsonElement> {
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {

            }

            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Toast.makeText(this@DashboardActivity, response.body().toString(), Toast.LENGTH_LONG).show()
            }
        })


    }

}
