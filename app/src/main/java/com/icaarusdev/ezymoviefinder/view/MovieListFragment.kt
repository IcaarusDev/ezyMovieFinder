package com.icaarusdev.ezymoviefinder.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
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

        refreshlayout.setOnRefreshListener {
            movieRcv.visibility = View.GONE
            txvlistError.visibility = View.GONE
            progressBarView.visibility = View.VISIBLE
            viewModel.refreshFromCache()
            refreshlayout.isRefreshing = false
        }
        observeViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu_search, menu)
        val menuItem = menu!!.findItem(R.id.action_search)

        if (menuItem != null) {
            val searchView = menuItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isNotEmpty()) {

                    }

                    return true
                }

            })
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }


    fun observeViewModel() {
        viewModel.movies.observe(viewLifecycleOwner, Observer { movies: List<Movie> ->
            movies.let {
                movieRcv.visibility = View.VISIBLE
                moviesListAdapter.updateMovieList(movies)
            }
        })

        viewModel.moviesLoadError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let {
                txvlistError.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                progressBarView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    txvlistError.visibility = View.GONE
                    movieRcv.visibility = View.GONE
                }
            }
        })
    }
}