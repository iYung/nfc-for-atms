package com.example.iyung.atmtap

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_deposit.*

class Deposit : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deposit)
        amount.setSelection(amount.text.length)
        var myIntent = intent.extras
        if (myIntent != null) {
            account.text = myIntent.getString("account")
            action.text = myIntent.getString("action")
        }
    }
}
