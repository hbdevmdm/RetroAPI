package com.dc.mvvmskeleton.ui.authentication.dashboard

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dc.mvvmskeleton.R
import com.dc.mvvmskeleton.data.remote.PlainApiClient
import com.dc.mvvmskeleton.databinding.ActivityDashboardBinding
import com.google.gson.JsonElement
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
            editProfileJoinPods()
        }

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
