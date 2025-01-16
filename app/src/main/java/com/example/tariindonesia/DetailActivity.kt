package com.example.tariindonesia

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity_main) // Pastikan ini sesuai dengan nama file XML Anda

        // Mengambil data yang dikirim dari MainActivity
        val danceName = intent.getStringExtra("dance_name") ?: "Nama Tari Tidak Diketahui"
        val danceDescription =
            intent.getStringExtra("dance_description") ?: "Deskripsi Tidak Diketahui"
        val dancePhoto = intent.getIntExtra("dance_photo", -1)

        // Mengambil referensi untuk TextView dan ImageView dari layout
        val textTitle: TextView = findViewById(R.id.text_title)
        val textDescription: TextView = findViewById(R.id.text_description)
        val imageView: ImageView = findViewById(R.id.image_phone)

        // Mengatur data ke dalam tampilan
        textTitle.text = danceName
        textDescription.text = danceDescription

        // Mengatur gambar jika ID gambar valid
        if (dancePhoto != -1) {
            imageView.setImageResource(dancePhoto)
        } else {
            // Opsional: mengatur gambar default jika tidak ada gambar yang valid
            imageView.setImageResource(R.drawable.aceh) // Ganti dengan gambar default yang ada
        }
    }

}
