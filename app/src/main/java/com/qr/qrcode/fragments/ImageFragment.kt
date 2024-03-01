package com.qr.qrcode.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import com.qr.qrcode.QRCodeActivity
import com.qr.qrcode.R
import java.util.UUID


class ImageFragment : Fragment() {
    private lateinit var btnImage: Button;
    private lateinit var btnTextQrCode: Button;
    private lateinit var image: ImageView;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_image, container, false)
        btnImage = view.findViewById(R.id.button)
        btnTextQrCode = view.findViewById(R.id.btnQRcode)
        image = view.findViewById(R.id.image)
        btnImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            someActivityResultLauncher.launch(intent)
        }

        return view
    }
    private val someActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = result.data?.data
            context?.let {
                Glide.with(it)
                    .load(imageUri)
                    .into(image)
            }
            val storage = Firebase.storage
            val storageRef = storage.reference
            val imageRef = storageRef.child("images/${UUID.randomUUID()}")
            if (imageUri != null) {
                imageRef.putFile(imageUri).continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            throw it
                        }
                    }
                    imageRef.downloadUrl
                }.addOnSuccessListener { downloadUri ->
                    var imageUrl = downloadUri.toString()
                    btnTextQrCode.setOnClickListener {
                        val intent = Intent(activity, QRCodeActivity::class.java)
                        intent.putExtra("text", imageUrl)
                        startActivity(intent)
                    }
                }.addOnFailureListener {exception ->
                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show()
                    Log.i("com","Lỗi: ${exception.message}" )
                }
            }
        }
    }

}