package com.yvkalume.hovertest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.hover.sdk.api.Hover
import com.hover.sdk.api.HoverParameters

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Hover.initialize(this)

        findViewById<Button>(R.id.send).setOnClickListener {
            val phone = findViewById<TextView>(R.id.edPhone).text.toString()
            val montant = findViewById<TextView>(R.id.edMontant).text.toString()
            sendMoney(phone,montant)
        }
    }

    private fun sendMoney(phone: String,montant: String) {
        val intent = HoverParameters.Builder(baseContext)
                .request("467432e3")
                .extra("phoneNumber",phone)
                .extra("montant",montant)
                .buildIntent()
        startActivityForResult(intent,0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            logResult(resultCode,data)
        }
    }

    fun logResult(resultCode: Int,data: Intent?) {
        if (resultCode == Activity.RESULT_OK){
            Log.d("MainActivity","Ok")
        }
    }
}