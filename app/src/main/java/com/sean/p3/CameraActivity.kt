package com.sean.p3

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class CameraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val cameraButton = findViewById<Button>(R.id.cameraButton)
        val backToMainButton = findViewById<Button>(R.id.backToMainButton)

        backToMainButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val vaccinationActivityButton = findViewById<Button>(R.id.CovidStatusActivityButton)

        vaccinationActivityButton.setOnClickListener {
            val intent = Intent(this, CovidCardActivity::class.java)
            startActivity(intent)
        }

        cameraButton.setOnClickListener {
            val cameraCheckPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

            if (cameraCheckPermission != PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
                    val builder = AlertDialog.Builder(this)

                    val message = getString(R.string.permission_message)
                    builder.setTitle(R.string.permission_title)
                        .setMessage(message)
                        .setPositiveButton("OK") { _, _ ->
                            requestPermission()
                        }

                    val dialog = builder.create()
                    dialog.show()
                }
                else{
                    Log.d("BSU", "Should not show rationale")
                    requestPermission() // Will not display the request
                }
            }
            else{
                launchCamera()
            }
        }
    }

    private fun launchCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 9090)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 123)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        for ((index, permission) in permissions.withIndex()){
            if( permission == Manifest.permission.CAMERA){
                if( grantResults[index] == PackageManager.PERMISSION_GRANTED){
                    launchCamera()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val cameraButton = findViewById<Button>(R.id.cameraButton)

        if( requestCode == 9090){

            if( data != null ) {
                val imageData: Bitmap = data.extras!!.get("data") as Bitmap

                val imageView = findViewById<ImageView>(R.id.imageView)
                imageView.setImageBitmap(imageData)
                cameraButton.setText(R.string.retake_picture)

            }
        }
    }
}
