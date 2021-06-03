package com.icaarusdev.ezymoviefinder.ui.movie

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.icaarusdev.ezymoviefinder.R
import com.icaarusdev.ezymoviefinder.viewmodel.ListViewModel


class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
    }
}