package com.example.newscontroller.ui

import android.os.Bundle
import android.view.*
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.newscontroller.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        setContentView(R.layout.activity_main)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolBar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            toolBar.title = destination.label
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navBusiness -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.businessFragment)
            }
            R.id.navSport -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.sportsFragment)
            }

            R.id.navPolitics -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.politicsFragment)
            }

            R.id.navVisited -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.visitedFragment)
            }
        }
        drawerLayout.closeDrawers()
        return true
    }
}
