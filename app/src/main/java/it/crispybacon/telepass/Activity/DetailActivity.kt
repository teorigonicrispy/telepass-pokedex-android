package it.crispybacon.telepass.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import it.crispybacon.telepass.R
import it.crispybacon.telepass.telepass
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Search Weather View
 */
class DetailActivity : AppCompatActivity(R.layout.activity_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imageViewBack.setOnClickListener {
            onBackPressed()
        }

        val name = intent.getStringExtra("name")

        telepass.shared.getPokemon(this, name, imageView)

        textView.text = name
    }
}
