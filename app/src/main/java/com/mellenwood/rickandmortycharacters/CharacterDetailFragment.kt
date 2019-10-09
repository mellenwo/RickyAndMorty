package com.mellenwood.rickandmortycharacters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [CharacterDetailActivity]
 * on handsets.
 */
class CharacterDetailFragment : Fragment() {

    private var characterName: String? = null
    private var characterImg: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_CHARACTER_NAME)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                characterName = it.getString(ARG_CHARACTER_NAME)
                activity?.toolbar_layout?.title = characterName
            }
            if (it.containsKey(ARG_CHARACTER_IMAGE)) {
                characterImg = it.getString(ARG_CHARACTER_IMAGE)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        characterImg?.let {
            characterImg?.let {
                Glide.with(this).load(characterImg).into(rootView.item_detail)
            }
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_CHARACTER_NAME = "character_name"
        const val ARG_CHARACTER_IMAGE = "character_image"
    }
}
