package com.qr.qrcode

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

class QRCodeActivity : AppCompatActivity() {

    private lateinit var imageViewQRCode : ImageView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr)
        val textReceived = intent.getStringExtra("text")
        imageViewQRCode = findViewById(R.id.imageQrcode);
        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(textReceived, BarcodeFormat.QR_CODE, 240, 260)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bmp.setPixel(x, y, if (bitMatrix[x, y]) 0xFF000000.toInt() else 0xFF00BFFF.toInt())
            }
        }
        imageViewQRCode.setImageBitmap(bmp)
    }
}