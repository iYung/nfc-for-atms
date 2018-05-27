package com.example.iyung.atmtap

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_receipt.*
import java.util.*

class Receipt : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt)

        var myIntent = intent.extras
        if (myIntent != null) {
            accounttext.text = myIntent.getString("account")
            transactiontext.text = myIntent.getString("action")
            amounttext.text = myIntent.getString("amount")
        }

        backbutton.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }
    }
}
