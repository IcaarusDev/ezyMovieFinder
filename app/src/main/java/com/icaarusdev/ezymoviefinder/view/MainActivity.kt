package com.icaarusdev.ezymoviefinder.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.icaarusdev.ezymoviefinder.R
import com.icaarusdev.ezymoviefinder.util.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(myToolbar)

        navController = Navigation.findNavController(this,R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this,navController)

        appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//
//        menuInflater.inflate(R.menu.menu_search, menu)
//
//        val menuItem = menu!!.findItem(R.id.action_search)
//
//        if (menuItem !=null){
//            val searchView = menuItem.actionView as SearchView
//
//            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//                override fun onQueryTextSubmit(query: String?): Boolean {
//
//                    val action = MovieListFragmentDirections.actionMovieListFragmentToSearchMovieFragment()
//                    navController.navigate(action)
//                    hideKeyboard(window.decorView.rootView)
//                    searchView.visibility = View.GONE
//                    return true
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//
//                    if(newText!!.isNotEmpty()){
//
//                    }
//
//                    return true
//                }
//
//            })
//        }
//
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return super.onOptionsItemSelected(item)
//    }

    override fun onSupportNavigateUp(): Boolean {
        //return NavigationUI.navigateUp(navController,null)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}