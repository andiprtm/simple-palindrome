package com.example.palyndrome

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.kusu.loadingbutton.LoadingButton

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnCheck: LoadingButton
    private lateinit var edtTxtPalindrome: EditText
    private lateinit var imgAvatar: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgAvatar = findViewById(R.id.imageView)
        btnCheck = findViewById(R.id.button4)
        edtTxtPalindrome = findViewById(R.id.edtTextPalindrome)
        btnCheck.setOnClickListener(this)

        Glide.with(this)
            .load("https://is3.cloudhost.id/andiprtm/duck.jpg")
            .into(imgAvatar)
    }

    fun isPalindrome(input: String): String {
        val cleanInput = input.replace("\\s".toRegex(), "").lowercase()
        val length = cleanInput.length
        for (i in 0 until length / 2) {
            if (cleanInput[i] != cleanInput[length - i - 1]) {
                return "Bukan Palindrome"
            }
        }
        return "Adalah Palindrome"
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.button4) {
            btnCheck.showLoading()
            val input = edtTxtPalindrome.text.toString().trim()
            val output = isPalindrome(input)
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            var isEmptyFields = false

            if (input.isEmpty()) {
                isEmptyFields = true
                edtTxtPalindrome.error = "Field ini tidak boleh kosong"
                btnCheck.hideLoading()
            }
            if (!isEmptyFields) {
                Handler(Looper.getMainLooper()).postDelayed({
                    btnCheck.hideLoading()
                    builder
                        .setMessage(output)
                        .setTitle("Hasilnya")
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }, 3000)
            }

        }
    }
}