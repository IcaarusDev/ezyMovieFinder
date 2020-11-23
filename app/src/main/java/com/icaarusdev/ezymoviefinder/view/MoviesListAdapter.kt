package com.icaarusdev.ezymoviefinder.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.icaarusdev.ezymoviefinder.R
import com.icaarusdev.ezymoviefinder.databinding.ItemMovieBinding
import com.icaarusdev.ezymoviefinder.model.Movie
import com.icaarusdev.ezymoviefinder.util.getImageProgress
import com.icaarusdev.ezymoviefinder.util.setImage
import com.icaarusdev.ezymoviefinder.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.item_movie.view.*


class MoviesListAdapter(val moviesList: ArrayList<Movie>) :
    RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder>(), MovieClickListener {

    private lateinit var listViewModel: ListViewModel

    fun updateMovieList(newMoviesList: List<Movie>) {
        moviesList.clear()
        moviesList.addAll(newMoviesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemMovieBinding>(inflater, R.layout.item_movie, parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.view.movie = moviesList[position]
        holder.view.listener = this
    }

    override fun onMovieClicked(v: View) {
        super.onMovieClicked(v)
            val movieId = v.movieId.text.toString().toInt()
            val action = MovieListFragmentDirections.actionMovieDetailFragment()
            action.id = movieId
            Navigation.findNavController(v).navigate(action)
    }

    override fun getItemCount() = moviesList.size

    class MovieViewHolder(var view: ItemMovieBinding) : RecyclerView.ViewHolder(view.root)
}