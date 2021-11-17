package it.crispybacon.telepass.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.crispybacon.telepass.Activity.DetailActivity
import it.crispybacon.telepass.Activity.MainActivity
import it.crispybacon.telepass.R
import it.crispybacon.telepass.dto.NamedAPIResourceDTO
import it.crispybacon.telepass.telepass

class PokemonAdapter(val context: Context, private val dataSet: ArrayList<NamedAPIResourceDTO>) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.textView)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_pokemon, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val pokemon = dataSet[position]

        viewHolder.textView.text = pokemon.name

        telepass.shared.getPokemon(context, pokemon.name, viewHolder.imageView)

        viewHolder.imageView.setOnClickListener {
            context as MainActivity

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("name", pokemon.name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataSet.size
}
