package com.qr.qrcode.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.qr.qrcode.QRCodeActivity
import com.qr.qrcode.R

class EmailFragment : Fragment() {
    private lateinit var email: EditText;
    private lateinit var subject: EditText;
    private lateinit var message: EditText;
    private lateinit var btnTextQrCode: Button;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_email, container, false)

        email = view.findViewById(R.id.emailText)
        subject = view.findViewById(R.id.subjectText)
        message = view.findViewById(R.id.messageEmail)
        btnTextQrCode = view.findViewById(R.id.btnQrcode)
        btnTextQrCode.setOnClickListener {
            var emailString = if(subject.text.equals("") && message.text.equals("")) "MAILTO:" + email.text.toString()
            else "MATMSG:TO:" + email.text.toString() + ";SUB:" + message.text.toString() + ";BODY:" + message.text.toString() + ";;"
            val intent = Intent(activity, QRCodeActivity::class.java)
            intent.putExtra("text", emailString)
            startActivity(intent)
        }
        return view
    }
}