package com.qr.qrcode.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.qr.qrcode.QRCodeActivity
import com.qr.qrcode.R

class SmsFragment : Fragment(){
    private lateinit var phoneNumber: EditText;
    private lateinit var message: EditText;
    private lateinit var btnTextQrCode: Button;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sms, container, false)
        phoneNumber = view.findViewById(R.id.phoneNumber)
        message = view.findViewById(R.id.textMessage)
        btnTextQrCode = view.findViewById(R.id.btnQrcode)
        btnTextQrCode.setOnClickListener {
            val sms = "SMSTO:" + phoneNumber.text.toString() + ":" + message.text.toString()
            val intent = Intent(activity, QRCodeActivity::class.java)
            intent.putExtra("text",sms)
            startActivity(intent)
        }
        return view
    }
}