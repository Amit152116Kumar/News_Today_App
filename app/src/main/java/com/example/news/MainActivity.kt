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
import com.example.news.adapters.FragmentPagerAdapter
import com.example.news.databinding.ActivityMainBinding
import com.example.news.fragments.*
import com.example.news.settings.SettingsActivity
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private val fragmentList = listOf(
        BlankFragment(), EntertainmentFragment(), GeneralFragment(), HealthFragment(),
        ScienceFragment(), SportsFragment(), TechnologyFragment()
    )
    private val fragmentTitle =
        listOf("Business", "Entertainment", "General", "Health", "Science", "Sports", "Technology")

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

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = null

        val fragmentAdapter = FragmentPagerAdapter(supportFragmentManager, lifecycle, fragmentList)
        binding.viewPager.adapter = fragmentAdapter

        // TabLayout Mediator
        TabLayoutMediator(binding.tablayout, binding.viewPager) { tab: TabLayout.Tab, i: Int ->
            tab.text = fragmentTitle[i]
        }.attach()

        //
        binding.drawerMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        //
        binding.navigation.setNavigationItemSelectedListener(this)
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
            R.id.search->{
                Toast.makeText(this, "Search button is clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.sort_by->{
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
                Toast.makeText(this, "home button is clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.source_menu -> {
                Toast.makeText(this, "home button is clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.chat_menu -> {
                Toast.makeText(this, "chat menu is clicked", Toast.LENGTH_SHORT).show()
            }

            else -> return false
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}