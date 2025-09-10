package com.example.lab_week_03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
// import androidx.fragment.app.FragmentContainerView // No longer explicitly used here
// import androidx.fragment.app.ListFragment // No longer explicitly used here
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment // For finding NavController

class MainActivity : AppCompatActivity(), CoffeeListener {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragment_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // If you were manually adding ListFragment, remove that logic as NavHostFragment handles it.
        // if (savedInstanceState == null) {
        //     supportFragmentManager.beginTransaction()
        //         .replace(R.id.fragment_container, ListFragment.newInstance("","")) // Adjust newInstance if needed
        //         .commitNow()
        // }
    }

    override fun onSelected(coffeeId: Int, coffeeName: String) {
        val bundle = Bundle().apply {
            putString("COFFEE_NAME_KEY", coffeeName) // Use the same key as in DetailFragment
        }
        // Navigate using NavController.
        // Ensure you have an action defined in your nav_graph.xml from your ListFragment's
        // host (or the start destination) to DetailFragment, or a global action.
        // Example action ID from ListFragment to DetailFragment: R.id.action_listFragment_to_detailFragment
        // If your ListFragment is the start destination, and DetailFragment is reachable:
        navController.navigate(R.id.detailFragment, bundle) // Navigates to the destination ID

        // If you have a specific action from ListFragment to DetailFragment:
        // navController.navigate(R.id.action_listFragment_to_detailFragment, bundle)

        // Old way if not using Navigation Component directly for this transaction:
        // val detailFragment = DetailFragment.newInstance(coffeeName) // Use your modified newInstance
        // supportFragmentManager.beginTransaction()
        //     .replace(R.id.fragment_container, detailFragment)
        //     .addToBackStack(null) // Important for system back navigation
        //     .commit()
    }
}
