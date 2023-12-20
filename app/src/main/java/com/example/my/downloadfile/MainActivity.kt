package com.example.my.downloadfile

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val field = findViewById<EditText>(R.id.field)
        val download = findViewById<TextView>(R.id.download)

        download.setOnClickListener {
            if (field.text.toString() == "") {
                Toast.makeText(this, R.string.hint, Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            Thread {
                if (!Utils.isFileExists(field.text.toString())) {
                    runOnUiThread { Toast.makeText(this, R.string.hint, Toast.LENGTH_LONG).show() }
                    return@Thread
                }
            }.start()
            val path = field.text.toString().split("/")
            Utils.downloadFile(
                path[path.size - 1], field.text.toString(), this
            )
            { Toast.makeText(this, R.string.downloaded, Toast.LENGTH_LONG).show() }
        }
    }

}