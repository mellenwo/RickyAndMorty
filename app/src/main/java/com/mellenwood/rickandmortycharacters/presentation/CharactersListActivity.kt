package com.mellenwood.rickandmortycharacters.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.mellenwood.rickandmortycharacters.CharacterDetailActivity
import com.mellenwood.rickandmortycharacters.CharacterDetailFragment
import com.mellenwood.rickandmortycharacters.R

import com.mellenwood.rickandmortycharacters.remote.model.CharacterModel
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list_content.view.*
import kotlinx.android.synthetic.main.item_list.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [CharacterDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class CharactersListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private val viewModel = CharactersListViewModel()
    private val adapter = SimpleItemRecyclerViewAdapter(
        this,
        arrayListOf()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        progressBar.visibility = View.VISIBLE
        viewModel.loadCharacters()

        viewModel.characters.observe(this,
            Observer<List<CharacterModel>> { it?.let{
                adapter.replaceData(it)
                progressBar.visibility = View.GONE
            } })

        setupRecyclerView(item_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = adapter

    }

    class SimpleItemRecyclerViewAdapter(
        private val parentActivity: CharactersListActivity,
        private var values: List<CharacterModel>
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as CharacterModel
                val intent = Intent(v.context, CharacterDetailActivity::class.java).apply {
                    putExtra(CharacterDetailFragment.ARG_CHARACTER_NAME, item.name)
                    putExtra(CharacterDetailFragment.ARG_CHARACTER_IMAGE, item.image)
                }
                v.context.startActivity(intent)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.idView.text = item.name

            Glide.with(parentActivity).load(item.image).into(holder.imageView)

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }

        fun replaceData(newItems: List<CharacterModel>){
            values = newItems

            notifyDataSetChanged()

        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.id_text
            val imageView: ImageView = view.character_image
        }
    }
}
