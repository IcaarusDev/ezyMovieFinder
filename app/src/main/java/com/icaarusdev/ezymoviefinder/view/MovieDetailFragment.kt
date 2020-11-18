package com.icaarusdev.ezymoviefinder.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.icaarusdev.ezymoviefinder.R
import com.icaarusdev.ezymoviefinder.model.Movie
import com.icaarusdev.ezymoviefinder.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_movie_detail.*


class MovieDetailFragment : Fragment() {

    private var movieId = 0;
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetchData()

        arguments?.let {
            movieId = MovieDetailFragmentArgs.fromBundle(it).movieId
        }

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.movieLiveData.observe(this, Observer { movie: Movie ->
            movie.let{
                movieTitle.text = movie.title
                movieDescription.text = movie.overview
            }
        })
    }
}