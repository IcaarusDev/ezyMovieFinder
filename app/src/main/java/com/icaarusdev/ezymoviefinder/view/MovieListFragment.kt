package com.icaarusdev.ezymoviefinder.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.icaarusdev.ezymoviefinder.R
import com.icaarusdev.ezymoviefinder.model.Movie
import com.icaarusdev.ezymoviefinder.view.MovieDetailFragmentDirections.actionMovieListFragment
import com.icaarusdev.ezymoviefinder.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val moviesListAdapter = MoviesListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refreshData()

        movieRcv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moviesListAdapter
        }
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.movies.observe(this, Observer { movies: List<Movie> ->
            movies.let {
                movieRcv.visibility = View.VISIBLE
                moviesListAdapter.updateMovieList(movies)
            }
        })

        viewModel.moviesLoadError.observe(this, Observer { isError ->
            isError?.let {
                txvlistError.visibility = if(it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let{
                progressBarView.visibility = if(it) View.VISIBLE else View.GONE
                if (it){
                    txvlistError.visibility = View.GONE
                    movieRcv.visibility = View.GONE
                }
            }
        })
    }
}