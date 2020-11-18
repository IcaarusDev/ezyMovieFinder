package com.icaarusdev.ezymoviefinder.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.icaarusdev.ezymoviefinder.R
import com.icaarusdev.ezymoviefinder.model.Movie
import com.icaarusdev.ezymoviefinder.util.getImageProgress
import com.icaarusdev.ezymoviefinder.util.setImage
import com.icaarusdev.ezymoviefinder.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.item_movie.*


class MovieDetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private var movieId = 0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            movieId = MovieDetailFragmentArgs.fromBundle(it).id
        }

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetchData(movieId)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer { movie: Movie ->
            movie?.let {
                movieTitle.text = movie.title
                movieDescription.text = movie.overview
                context?.let{
                    movie.backDropPath?.let { it1 ->
                        backDropPath.setImage(
                            it1,
                            getImageProgress(it))
                    }
                }
            }
        })
    }
}