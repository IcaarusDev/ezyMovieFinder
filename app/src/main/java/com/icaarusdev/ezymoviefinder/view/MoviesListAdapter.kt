package com.icaarusdev.ezymoviefinder.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.ListFragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.icaarusdev.ezymoviefinder.R
import com.icaarusdev.ezymoviefinder.model.Movie
import com.icaarusdev.ezymoviefinder.util.getImageProgress
import com.icaarusdev.ezymoviefinder.util.setImage
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
            //Toast.makeText(holder.view.context,"${moviesList[position].id}",Toast.LENGTH_SHORT).show()
            //Navigation.findNavController(it).navigate(MovieListFragmentDirections.actionMovieDetailFragment())

            val action = MovieListFragmentDirections.actionMovieDetailFragment()
            moviesList[position].id?.toInt()?.let { it1 -> action.setId(it1) }
            holder.view.findNavController().navigate(action)

        }
        moviesList[position].backDropPath?.let {
            holder.view.movieImage.setImage(it, getImageProgress(holder.view.movieImage.context))
        }
    }

    override fun getItemCount() = moviesList.size

    class MovieViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}