package com.dominic.protobuf

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dominic.protobuf.protobuf.PersonInfo
import okhttp3.*
import java.io.IOException

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val personByte = byteArrayOf(0xff.toByte(), 0x0f.toByte());
        val person = PersonInfo.Person.parseFrom(personByte);
        val account = person.account
        val password = person.password


        var newPerson =
            PersonInfo.Person.newBuilder().setAccount("dominic").setPassword("123456").build()
        val requestBody =
            FormBody.create(MediaType.get("application/octet-stream"), newPerson.toByteArray())
        val request =
            Request.Builder().url("http://192.168.20.206:8080/index/login").post(requestBody)
                .build()

        val newCall = OkHttpClient().newCall(request)
        newCall.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

            }

            override fun onResponse(call: Call, response: Response) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                val responseByte = response.body()?.bytes()
                val responsePerson = PersonInfo.Person.parseFrom(responseByte)
            }

        })

    }
}
