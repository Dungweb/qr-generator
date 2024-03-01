package com.qr.qrcode

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE
import com.qr.qrcode.fragments.EmailFragment
import com.qr.qrcode.fragments.ImageFragment
import com.qr.qrcode.fragments.SmsFragment
import com.qr.qrcode.fragments.TextFragment
import com.qr.qrcode.fragments.UrlFragment
import com.qr.qrcode.fragments.WifiFragment
import android.Manifest


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE)
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, TextFragment(), "FragmentA")
                .commit()
        }
        initView();
    }

    fun initView() {
        val spinner = findViewById<View>(R.id.spinner) as Spinner
        val adapterSpinner = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterSpinner.add("Text")
        adapterSpinner.add("Url")
        adapterSpinner.add("Sms")
        adapterSpinner.add("Email")
        adapterSpinner.add("Wifi")
        adapterSpinner.add("Image")
        spinner.adapter = adapterSpinner


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, TextFragment())
                            .addToBackStack(null)
                            .commit()
                    }
                    1 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, UrlFragment())
                            .addToBackStack(null)
                            .commit()
                    }
                    2 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, SmsFragment())
                            .addToBackStack(null)
                            .commit()
                    }
                    3 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, EmailFragment())
                            .addToBackStack(null)
                            .commit()
                    }
                    4 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, WifiFragment())
                            .addToBackStack(null)
                            .commit()
                    }
                    5 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, ImageFragment())
                            .addToBackStack(null)
                            .commit()
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
}