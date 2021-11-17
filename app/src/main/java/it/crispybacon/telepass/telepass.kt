package it.crispybacon.telepass

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.gson.Gson
import it.crispybacon.telepass.Activity.MainActivity
import it.crispybacon.telepass.Activity.SplashActivity
import it.crispybacon.telepass.dto.NamedAPIResourceDTO
import it.crispybacon.telepass.dto.PokemonDTO
import it.crispybacon.telepass.dto.PokemonListDTO
import okhttp3.*
import org.jetbrains.anko.runOnUiThread
import java.io.IOException


class telepass : Application() {

    val pokemonBaseUrl = "https://pokeapi.co/api/v2"
    val spriteBaseUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites"

    var pokemons = ArrayList<NamedAPIResourceDTO>()

    fun showAlert(
        context: Context,
        title: String,
        message: String,
        button1: String?,
        button1Completion: (() -> Unit)?,
        button2: String?,
        button2Completion: (() -> Unit)?
    ) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(false)

        if (button1 != null) {
            builder.setPositiveButton(button1) { dialog, id ->
                if (button1Completion != null) {
                    button1Completion()
                } else {
                    dialog.cancel()
                }
            }
        }

        if (button2 != null) {
            builder.setNegativeButton(button2) { dialog, id ->
                if (button2Completion != null) {
                    button2Completion()
                    dialog.cancel()
                }
            }
        }

        val alert = builder.create()
        alert.show()
    }

    fun getPokemons(context: Context, offset: Int) {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .method("GET", null)
            .url(pokemonBaseUrl + "/pokemon?limit=20&offset=$offset")
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    val bodyResponse = response.body()!!.string()

                    val gson = Gson()
                    val newPokemons = gson.fromJson(bodyResponse, PokemonListDTO::class.java)

                    pokemons.addAll(newPokemons.results!!)

                    if (context is SplashActivity) {
                        context.startActivity(Intent(context, MainActivity::class.java))
                        context.finishAffinity()
                    } else if (context is MainActivity) {

                        context.refresh()
                    }
                }
            }
        })
    }

    fun getPokemon(context: Context, name: String?, imageView: ImageView) {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .method("GET", null)
            .url(pokemonBaseUrl + "/pokemon/$name")
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    val bodyResponse = response.body()!!.string()

                    val gson = Gson()
                    val pokemon = gson.fromJson(bodyResponse, PokemonDTO::class.java)

                    Glide.with(context)
                        .load("$spriteBaseUrl/pokemon/${pokemon.id}.png")
                        .centerCrop()
                        .placeholder(R.drawable.ic_stat_name)
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                ImageViewCompat.setImageTintList(imageView, null)
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                ImageViewCompat.setImageTintList(imageView, null)
                                return false
                            }
                        })
                        .into(imageView)
                }
            }
        })
    }

    init {
        println("Init Singleton!")
    }

    private object GetInstance {
        val INSTANCE = telepass()
    }

    companion object {
        val shared: telepass by lazy { GetInstance.INSTANCE }
    }

    override fun onCreate() {
        super.onCreate()
    }
}