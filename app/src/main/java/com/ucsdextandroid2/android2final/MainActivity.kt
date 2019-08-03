package com.ucsdextandroid2.android2final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpBottomNavBar();
    }

    private fun setUpBottomNavBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.ai_bottom_nav_view);
        NavigationUI.setupWithNavController(bottomNavigationView, findNavController(this, R.id.ai_nav_host));
    }
}
