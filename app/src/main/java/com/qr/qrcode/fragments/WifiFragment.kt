package com.qr.qrcode.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.qr.qrcode.QRCodeActivity
import com.qr.qrcode.R

class WifiFragment : Fragment() {
    private lateinit var textWifi: EditText;
    private lateinit var passWord: EditText;
    private lateinit var btnTextQrCode: Button;
    private lateinit var radioGroup: RadioGroup
    private lateinit var selectHidden: RadioButton
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_wifi, container, false)
        textWifi = view.findViewById(R.id.nameWifi)
        passWord = view.findViewById(R.id.passWord)
        radioGroup = view.findViewById(R.id.radioGroup)
        selectHidden = view.findViewById(R.id.selectHidden);
        btnTextQrCode = view.findViewById(R.id.btnQrcode)
        btnTextQrCode.setOnClickListener {
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            var selectedText = "";
            var hidden = "";
            if (selectHidden.isChecked) hidden = selectHidden.text.toString()
            if (selectedRadioButtonId != -1) {
                val selectedRadioButton = view.findViewById<RadioButton>(selectedRadioButtonId)
                selectedText = selectedRadioButton.text.toString()
            }
            val wifi = "WIFI:T:$selectedText;S:" + textWifi.text.toString() + ";P:" + passWord.text.toString() + ";H:$hidden;;"
            val intent = Intent(activity, QRCodeActivity::class.java)
            intent.putExtra("text", wifi)
            startActivity(intent)
        }
        return view
    }
}