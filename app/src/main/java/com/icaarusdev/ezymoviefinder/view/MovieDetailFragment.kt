package com.icaarusdev.ezymoviefinder.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
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
import kotlinx.android.synthetic.main.item_movie.view.*


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

        (activity as AppCompatActivity).supportActionBar?.title = "EzyMovieFinder"
        (activity as AppCompatActivity).supportActionBar?.subtitle = "Movie Details"

        arguments?.let {
            movieId = MovieDetailFragmentArgs.fromBundle(it).id
        }

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.refreshData(movieId)

        observeViewModel()
    }


    private fun observeViewModel() {
        viewModel.titleTxt.observe(viewLifecycleOwner, Observer { titletxt: String ->
            movieTitle.text = titletxt
        })
        viewModel.overviewTxt.observe(viewLifecycleOwner, Observer { overview: String ->
            movieDescription.text = overview
        })
        viewModel.releaseDataTxt.observe(viewLifecycleOwner, Observer { release_date: String ->
            movieReleaseDate.text = release_date
        })
        viewModel.voteAverageTxt.observe(viewLifecycleOwner, Observer { vote_average: String ->
            movieVoteAverage.text = vote_average
        })
        viewModel.backdropPathTxt.observe(viewLifecycleOwner, Observer { backDropString: String ->
            backDropPath.setImage(backDropString,getImageProgress(requireContext()))

        })
    }
}