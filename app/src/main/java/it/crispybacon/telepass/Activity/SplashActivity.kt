package it.crispybacon.telepass.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import it.crispybacon.telepass.R
import it.crispybacon.telepass.telepass

/**
 * Search Weather View
 */
class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        telepass.shared.getPokemons(this, 0)
    }
}
