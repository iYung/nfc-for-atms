package com.example.iyung.atmtap

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.NfcEvent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_withdraw.*
import org.json.JSONException
import org.json.JSONObject

class Withdraw : AppCompatActivity(), NfcAdapter.CreateNdefMessageCallback, NfcAdapter.OnNdefPushCompleteCallback {
    override fun onNdefPushComplete(event: NfcEvent?) {
        val myIntent = Intent(this, Receipt::class.java)
        myIntent.putExtra("account", account.text)
        myIntent.putExtra("action", "Withdrawal")
        myIntent.putExtra("amount", amount.text.toString())
        startActivity(myIntent)
    }

    override fun createNdefMessage(event: NfcEvent?): NdefMessage {
        val withdrawText = "withdraw"

        val dataToSend = JSONObject()
        try {
            dataToSend.put("type", withdrawText)
            dataToSend.put("amount", amount.text)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val ndefRecord = NdefRecord.createMime("application/json", dataToSend.toString().toByteArray())
        val ndefMessage = NdefMessage(ndefRecord)
        return ndefMessage
    }

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

        val mAdapter = NfcAdapter.getDefaultAdapter(this)
        if (mAdapter == null) {
            return
        }

        if (!mAdapter.isEnabled) {
            Toast.makeText(this, "Please enable NFC via Settings.", Toast.LENGTH_LONG).show()
        }

        mAdapter.setNdefPushMessageCallback(this, this)
        mAdapter.setOnNdefPushCompleteCallback(this,  this)
    }
}
