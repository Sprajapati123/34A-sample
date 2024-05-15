package com.example.topnavbar

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.topnavbar.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainBinding.root)

        setSupportActionBar(mainBinding.toolbar)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.lavender)
        }

        var toggle = ActionBarDrawerToggle(
            this, mainBinding.drawerLayout,
            mainBinding.toolbar, R.string.open_drawer, R.string.open_drawer
        )
        mainBinding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        mainBinding.navigationView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, HomeFragment())
                .commit()
        }

    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.home -> {
                title = "Home"
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout, HomeFragment())
                    .commit()
            }

            R.id.search -> supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, SearchFragment()).commit()

            R.id.favourite -> supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, FavouriteFragment()).commit()

            R.id.more -> supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, MoreFragment()).commit()
        }
        mainBinding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (mainBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mainBinding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed() // Move this line inside the else block
        }
    }

}