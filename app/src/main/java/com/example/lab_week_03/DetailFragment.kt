package com.example.lab_week_03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class DetailFragment : Fragment() {

    private var coffeeName: String? = null
    private var coffeeDescription: String? = null // Variabel untuk menyimpan deskripsi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            coffeeName = it.getString("COFFEE_NAME_KEY") // Key dari ListFragment

            // Menentukan deskripsi berdasarkan nama kopi
            coffeeDescription = getCoffeeDescription(coffeeName)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        val coffeeNameTextView: TextView = view.findViewById(R.id.coffee_name_text_view) // Ubah ID jika perlu
        val coffeeDescriptionTextView: TextView = view.findViewById(R.id.coffee_description_text_view) // TextView baru untuk deskripsi
        val backButton: Button = view.findViewById(R.id.back_button)

        coffeeName?.let {
            coffeeNameTextView.text = it
        }

        coffeeDescription?.let {
            coffeeDescriptionTextView.text = it
        }

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return view
    }

    // Fungsi untuk mendapatkan deskripsi kopi
    private fun getCoffeeDescription(name: String?): String {
        return when (name) {
            "Affogato" -> "Affogato adalah hidangan penutup Italia berbasis kopi. Biasanya terdiri dari satu sendok es krim vanila yang 'ditenggelamkan' atau disiram dengan satu shot espresso panas."
            "Americano" -> "Caffè Americano adalah jenis minuman kopi yang dibuat dengan menyeduh espresso dengan tambahan air panas, memberikan kekuatan serupa, tetapi rasa berbeda dari kopi seduh biasa."
            "Latte" -> "Caffè latte adalah minuman kopi yang terbuat dari espresso dan susu kukus. Umumnya memiliki lapisan busa susu tipis di atasnya."
            "Cappuccino" -> "Cappuccino adalah minuman kopi berbasis espresso asal Italia yang secara tradisional disiapkan dengan busa susu kukus (microfoam). Variasi minuman ini biasanya terdiri dari espresso, susu panas, dan busa susu kukus."
            "Espresso" -> "Espresso adalah minuman kopi pekat yang diseduh dengan memaksa sejumlah kecil air hampir mendidih di bawah tekanan melalui biji kopi yang digiling halus."
            else -> "Deskripsi tidak tersedia."
        }
    }

    companion object {
        // newInstance method bisa tetap sama atau disesuaikan jika perlu
        @JvmStatic
        fun newInstance(coffeeName: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString("COFFEE_NAME_KEY", coffeeName)
                }
            }
    }
}
