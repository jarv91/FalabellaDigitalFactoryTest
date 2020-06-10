package com.falabella.test.presentation.ui.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.falabella.test.R
import com.falabella.test.databinding.ActivityMainBinding
import com.falabella.test.presentation.ui.auth.AuthActivity
import com.falabella.test.presentation.viewmodel.MainViewModel
import com.falabella.test.presentation.viewmodel.SignInViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.apply {
            navigationIcon = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_arrow_left)
            setNavigationOnClickListener { onBackPressed() }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                viewModel.logout()
                startActivity(AuthActivity.newIntent(this))
                finish()
            }
        }
        return true
    }
}