package com.freedom.notey.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.freedom.notey.R
import com.freedom.notey.utils.toast
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),NavHost {
    override fun getNavController(): NavController {
        toast("get nav controller ")
        return navController
    }


    companion object{
        /** Key for an int extra defining the initial navigation target. */
        const val EXTRA_NAVIGATION_ID = "extra.NAVIGATION_ID"
        private const val NAV_ID_NONE = -1

        private val TOP_LEVEL_DESTINATIONS = setOf(
            R.id.navigation_home,
            R.id.navigation_secured,
            R.id.navigation_settings,
            R.id.navigation_archive,
            R.id.navigation_supportDevelopment,
            R.id.navigation_trash,
            R.id.navigation_add
        )
    }

    private lateinit var navController: NavController
    private lateinit var navigation: NavigationView
    private var currentNavId =NAV_ID_NONE
    private var navHostFragment: NavHostFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment?

        navigation = findViewById(R.id.nav_view)
        navController=findNavController(R.id.fragment)
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            currentNavId = destination.id
//            val isTopLevelDestination = TOP_LEVEL_DESTINATIONS.contains(destination.id)
//            val lockMode = if (isTopLevelDestination) {
//                DrawerLayout.LOCK_MODE_UNLOCKED
//            } else {
//                DrawerLayout.LOCK_MODE_LOCKED_CLOSED
//            }
//            drawerlayout.setDrawerLockMode(lockMode)
//        }

        navigation.apply {
            setupWithNavController(navController)
        }

//        if (savedInstanceState == null) {
//            // default to showing Home
//            val initialNavId = intent.getIntExtra(EXTRA_NAVIGATION_ID, R.id.navigation_home)
//            navigation.setCheckedItem(initialNavId) // doesn't trigger listener
//            navigateTo(initialNavId)
//        }

    }

    private fun closeDrawer() {
        drawerlayout.closeDrawer(GravityCompat.START)
    }

//    override fun onUserInteraction() {
//        super.onUserInteraction()
//        getCurrentFragment()?.onUserInteraction()
//    }

//    private fun getCurrentFragment(): MainNavigationFragment? {
//        return navHostFragment
//            ?.childFragmentManager
//            ?.primaryNavigationFragment as? MainNavigationFragment
//    }

//    private fun navigateTo(navId: Int) {
//        if (navId == currentNavId) {
//            toast("user click the same nav")
//            return // user tapped the current item
//        }
//        navController.navigate(navId)
//    }
    override fun onBackPressed() {
        if (drawerlayout.isDrawerOpen(GravityCompat.START)) {
            drawerlayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


}
