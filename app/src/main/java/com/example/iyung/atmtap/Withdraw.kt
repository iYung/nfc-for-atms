package com.example.iyung.atmtap

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_withdraw.*

class Withdraw : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdraw)
        var myIntent = intent.extras
        if (myIntent != null) {
            account.text = myIntent.getString("account")
            action.text = myIntent.getString("action")
        }
        add5.setOnClickListener { amount.text = "$" + (amount.text.removePrefix("$").toString().toInt() + 5).toString() }
        add10.setOnClickListener { amount.text = "$" + (amount.text.removePrefix("$").toString().toInt() + 10).toString() }
        add20.setOnClickListener { amount.text = "$" + (amount.text.removePrefix("$").toString().toInt() + 20).toString() }
        sub5.setOnClickListener {
            var total = amount.text.removePrefix("$").toString().toInt()
            if (total >= 5) {
                amount.text = "$" + (total - 5).toString()
            }
        }
        sub10.setOnClickListener {
            var total = amount.text.removePrefix("$").toString().toInt()
            if (total >= 10) {
                amount.text = "$" + (total - 10).toString()
            }
        }
        sub20.setOnClickListener {
            var total = amount.text.removePrefix("$").toString().toInt()
            if (total >= 20) {
                amount.text = "$" + (total - 20).toString()
            }
        }
    }
}
