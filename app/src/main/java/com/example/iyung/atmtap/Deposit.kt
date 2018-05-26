package com.example.iyung.atmtap

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.NfcEvent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_deposit.*
import org.json.JSONException
import org.json.JSONObject

class Deposit : AppCompatActivity() , NfcAdapter.CreateNdefMessageCallback, NfcAdapter.OnNdefPushCompleteCallback {
    override fun onNdefPushComplete(event: NfcEvent?) {
        val myIntent = Intent(this, MainActivity::class.java)
        startActivity(myIntent)
    }

    override fun createNdefMessage(event: NfcEvent?): NdefMessage {
        val type = "deposit"
        val dataToSend = JSONObject()
        try {
            dataToSend.put("type", type)
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
        setContentView(R.layout.activity_deposit)
        amount.setSelection(amount.text.length)
        var myIntent = intent.extras
        if (myIntent != null) {
            account.text = myIntent.getString("account")
            action.text = myIntent.getString("action")
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

    override fun onResume() {
        super.onResume()

        print("On resume")
    }
}
