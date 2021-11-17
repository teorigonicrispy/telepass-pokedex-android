package it.crispybacon.telepass.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.crispybacon.telepass.Adapter.PokemonAdapter
import it.crispybacon.telepass.R
import it.crispybacon.telepass.telepass
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Search Weather View
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pokemonAdapter = PokemonAdapter(this, telepass.shared.pokemons)
        recyclerView.adapter = pokemonAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    val offset = telepass.shared.pokemons.size

                    telepass.shared.getPokemons(this@MainActivity, offset)
                }
            }
        })

        refresh()
    }

    fun refresh() {
        pokemonAdapter.notifyDataSetChanged()
    }
}
