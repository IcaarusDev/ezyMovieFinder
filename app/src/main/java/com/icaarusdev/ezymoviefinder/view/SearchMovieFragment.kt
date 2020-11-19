package com.icaarusdev.ezymoviefinder.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.ui.navigateUp
import androidx.recyclerview.widget.LinearLayoutManager
import com.icaarusdev.ezymoviefinder.R
import com.icaarusdev.ezymoviefinder.model.Movie
import com.icaarusdev.ezymoviefinder.viewmodel.ListViewModel
import com.icaarusdev.ezymoviefinder.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.fragment_movie_list.txvlistError
import kotlinx.android.synthetic.main.fragment_search_movie.*

class SearchMovieFragment : Fragment() {


    private lateinit var viewModel: SearchViewModel
    private val moviesListAdapter = MoviesListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        viewModel.refreshData()

        movieSearchRcv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moviesListAdapter
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.movies.observe(viewLifecycleOwner, Observer { movies: List<Movie> ->
            movies.let {
                txvlistError.visibility = View.GONE
                movieSearchRcv.visibility = View.VISIBLE
                moviesListAdapter.updateMovieList(movies)
            }
        })
    }

}