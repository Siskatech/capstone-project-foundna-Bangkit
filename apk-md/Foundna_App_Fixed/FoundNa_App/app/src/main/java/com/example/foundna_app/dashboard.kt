package com.example.foundna_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.foundna_app.databinding.ActivityDashboardBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class dashboard : AppCompatActivity() {

    lateinit var binding: ActivityDashboardBinding
    lateinit var imageView: ImageView
    lateinit var dialogmonstergila: BottomSheetDialog
    lateinit var dialogkatakberacun: BottomSheetDialog
    lateinit var dialogchamelon: BottomSheetDialog
    lateinit var dialogostrich: BottomSheetDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        imageView = findViewById(R.id.monstergila)
        imageView.setOnClickListener{
            showbottomsheetmonstergila()
        }

        imageView = findViewById(R.id.katakberacun)
        imageView.setOnClickListener {
            showbottomsheetkatak()
        }

        imageView = findViewById(R.id.dashboardchamelon)
        imageView.setOnClickListener {
            showbottomsheetchamelon()
        }

        imageView = findViewById(R.id.ostrich)
        imageView.setOnClickListener{
            showbottomsheetostrich()
        }

        val klikSearch = findViewById<ImageView>(R.id.search)
        klikSearch.setOnClickListener {
            val klik = Intent(this, CameraActivity::class.java)
            startActivity(klik)
        }
        val klikhome = findViewById<ImageView>(R.id.home)
        klikhome.setOnClickListener {
            val klik2 = Intent(this, dashboard::class.java)
            startActivity(klik2)
        }

        val klikprofile = findViewById<ImageView>(R.id.profile)
        klikprofile.setOnClickListener {
            val klik3 = Intent(this, profile::class.java)
            startActivity(klik3)
        }

        val klikcollection = findViewById<ImageView>(R.id.collection)
        klikcollection.setOnClickListener {
            val klik4 = Intent(this, collection::class.java)
            startActivity(klik4)
        }


    }

    private fun showbottomsheetchamelon() {
        val dialogView = layoutInflater.inflate(R.layout.bottom_sheet_3, null)
        dialogchamelon = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        dialogchamelon.setContentView(dialogView)
        dialogchamelon.show()
    }

    private fun showbottomsheetkatak() {
        val dialogView = layoutInflater.inflate(R.layout.bottom_sheet_2, null)
        dialogkatakberacun = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        dialogkatakberacun.setContentView(dialogView)
        dialogkatakberacun.show()
    }

    private fun showbottomsheetmonstergila() {
        val dialogView = layoutInflater.inflate(R.layout.bottom_sheet_1, null)
        dialogmonstergila = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        dialogmonstergila.setContentView(dialogView)
        dialogmonstergila.show()
    }

    private fun showbottomsheetostrich() {
        val dialogView = layoutInflater.inflate(R.layout.bottom_sheet_4, null)
        dialogostrich = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        dialogostrich.setContentView(dialogView)
        dialogostrich.show()
    }

}
