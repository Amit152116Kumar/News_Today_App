package com.example.news

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.preference.PreferenceManager
import com.example.news.databinding.ActivityMainBinding
import com.example.news.settings.SettingsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val theme = PreferenceManager.getDefaultSharedPreferences(applicationContext)
            .getString("Theme", null)
        theme?.let {
            when (theme) {
                "light" -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                "dark" -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                "default" -> {

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
            }
        }
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            binding.navigation.setCheckedItem(R.id.home_menu)
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = null
        //
        binding.drawerMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        //
        binding.navigation.setNavigationItemSelectedListener(this)
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this)


        val header = binding.navigation.getHeaderView(0)
        header.setOnClickListener {
            binding.bottomNavigation.selectedItemId = R.id.profile
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.drop_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.sort_by -> {
                Toast.makeText(this, "Sort button is clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home_menu -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment()).commit()
                binding.drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.source_menu -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SourcesFragment()).commit()
                binding.drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.profile -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ProfileFragment()).commit()
                return true
            }
            R.id.search -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SearchFragment()).commit()
                return true
            }

        }
        return false
    }
}