package com.example.madlevel4task2

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        setSupportActionBar(toolbar)
        supportActionBar?.title = "MAD Level 4 Task 2"

        navController = findNavController(R.id.nav_host_fragment)
//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        switchMenu()
        return true
    }

    private fun switchMenu() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in arrayOf(R.id.gameFragment)) {
                toolbar.title = "Mad Level 4 Task 2"
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                toolbar.menu.findItem(R.id.btHistory)?.isVisible = true
                toolbar.menu.findItem(R.id.btDelete)?.isVisible = false
            } else {
                toolbar.title = "Your Game History"
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                toolbar.menu.findItem(R.id.btHistory).isVisible = false
                toolbar.menu.findItem(R.id.btDelete)?.isVisible = true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        when (item.itemId) {
            R.id.btHistory -> {
                navController.navigate(R.id.action_gameFragment_to_gamesPlayedFragment2)
                return true
            }
            android.R.id.home -> {
                navController.navigate(R.id.action_gamesPlayedFragment2_to_gameFragment)
                return true
            }
            R.id.btDelete -> {
                return false
            }

            else -> super.onOptionsItemSelected(item)

        }
        return true
    }
}