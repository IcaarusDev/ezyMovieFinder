package com.icaarusdev.ezymoviefinder.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.icaarusdev.ezymoviefinder.R
import com.icaarusdev.ezymoviefinder.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesListAdapter(val moviesList: ArrayList<Movie>) :
    RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder>() {

    fun updateMovieList(newMoviesList: List<Movie>) {
        moviesList.clear()
        moviesList.addAll(newMoviesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.view.txMovieTitle.text = moviesList[position].title
        holder.view.txMovieDescription.text = moviesList[position].overview
        holder.view.setOnClickListener{
            Navigation.findNavController(it).navigate(MovieListFragmentDirections.actionMovieDetailFragment())
        }
    }

    override fun getItemCount() = moviesList.size

    class MovieViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}