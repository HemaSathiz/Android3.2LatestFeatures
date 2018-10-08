package com.example.hemapalanisamy.smartbasecode

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.hemapalanisamy.smartbasecode.ui.main.MainFragment
import com.example.hemapalanisamy.smartbasecode.utils.AppConstant
import com.example.hemapalanisamy.smartbasecode.utils.PreferenceHelper.defaultPrefs
import com.example.hemapalanisamy.smartbasecode.utils.PreferenceHelper.get
import com.example.hemapalanisamy.smartbasecode.utils.PreferenceHelper.set
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val prefs = defaultPrefs(this)
        prefs[AppConstant.defaultValue] = "any_type_of_value" //setter
        val value: String? = prefs[AppConstant.defaultValue] //getter
        Log.e("value",value.toString())

        if (savedInstanceState == null) {
            addFragment(AppConstant.linearRecyclerView)
        }
    }

    private fun addFragment(linearRecyclerView: String) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance(linearRecyclerView))
                    .commitNow()
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.linear -> {
                addFragment(AppConstant.linearRecyclerView)
                true
            }
            R.id.grid -> {
                addFragment(AppConstant.gridRecyclerView)
                true
            }
            R.id.staggered -> {
                addFragment(AppConstant.staggeredRecyclerView)
                true
            }
            R.id.carousel -> {
                addFragment(AppConstant.carouselRecyclerView)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}








