package com.icaarusdev.ezymoviefinder.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.icaarusdev.ezymoviefinder.R
import com.icaarusdev.ezymoviefinder.databinding.FragmentMovieDetailBinding
import com.icaarusdev.ezymoviefinder.util.getImageProgress
import com.icaarusdev.ezymoviefinder.util.setImage
import com.icaarusdev.ezymoviefinder.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_movie_detail.*


class MovieDetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private var movieId = 0
    private lateinit var dataBinding: FragmentMovieDetailBinding
    private lateinit var optionsMenu: Menu

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        return dataBinding.root
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
        viewModel.favoriteImage

        observeViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        this.optionsMenu = menu
        inflater.inflate(R.menu.menu_favorite, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                viewModel.updateFavorite(movieId)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
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
            backDropPath.setImage(backDropString, getImageProgress(requireContext()))
        })

        viewModel.favoriteImage.observe(viewLifecycleOwner, Observer {
            val favorite: Boolean? = viewModel.favoriteImage.value
            if (favorite!!) {
                optionsMenu.getItem(0).icon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite);
            } else {
                optionsMenu.getItem(0).icon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_bordered);
            }
        })
    }
}