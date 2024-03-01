package com.qr.qrcode.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.qr.qrcode.QRCodeActivity
import com.qr.qrcode.R

class UrlFragment : Fragment() {
    private lateinit var textView: EditText;
    private lateinit var btnTextQrCode: Button;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_url, container, false)
        textView = view.findViewById(R.id.urlText)
        btnTextQrCode = view.findViewById(R.id.btnQrcode)
        btnTextQrCode.setOnClickListener {
            val intent = Intent(activity, QRCodeActivity::class.java)
            intent.putExtra("text", textView.text.toString())
            startActivity(intent)
        }
        return view
    }
}