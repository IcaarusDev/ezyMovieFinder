package com.icaarusdev.ezymoviefinder.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
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
    RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder>() {


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
        //holder.view.txMovieTitle.text = moviesList[position].title
        //holder.view.txMovieDescription.text = moviesList[position].overview
//        holder.view.setOnClickListener {
//            //Toast.makeText(holder.view.context,"${moviesList[position].id}",Toast.LENGTH_SHORT).show()
//            //Navigation.findNavController(it).navigate(MovieListFragmentDirections.actionMovieDetailFragment())
//            val action = MovieListFragmentDirections.actionMovieDetailFragment()
//            moviesList[position].id?.toInt()?.let { it1 -> action.setId(it1) }
//            holder.view.findNavController().navigate(action)
//        }

//        holder.view.favoriteIcon.setOnClickListener {
//            moviesList[position].id?.toInt()?.let { it1 -> listViewModel.updateFavorite(it1) }
//        }

        moviesList[position].backDropPath?.let {
            holder.view.movieImage.setImage(it, getImageProgress(holder.view.movieImage.context))
        }
    }

    override fun getItemCount() = moviesList.size

    class MovieViewHolder(var view: ItemMovieBinding) : RecyclerView.ViewHolder(view.root)
}