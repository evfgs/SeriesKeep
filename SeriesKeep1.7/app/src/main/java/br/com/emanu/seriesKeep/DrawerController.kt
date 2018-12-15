package br.com.emanu.seriesKeep

import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import br.com.emanu.seriesKeep.R
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.LibsBuilder
import kotlinx.android.synthetic.main.act_homepage.*

class DrawerController(var mActivity: AppCompatActivity) : NavigationView.OnNavigationItemSelectedListener {

    private val mDrawerLayout: DrawerLayout
    private val mNavigationView: NavigationView

    init {
        mDrawerLayout = mActivity.findViewById(R.id.drawer_layout) as DrawerLayout
        mNavigationView = mActivity.findViewById(R.id.nav_view) as NavigationView
    }

    fun init() {
        mNavigationView.setNavigationItemSelectedListener(this)

        val actionBarDrawerToggle = object : ActionBarDrawerToggle(
                mActivity,
                mDrawerLayout,
                mActivity.toolbar,
                R.string.drawer_open,
                R.string.drawer_close) {

            override fun onDrawerClosed(view: View?) {
                super.onDrawerClosed(view)
                mActivity.invalidateOptionsMenu()
                syncState()
            }

            override fun onDrawerOpened(drawerView: View?) {
                super.onDrawerOpened(drawerView)
                mActivity.invalidateOptionsMenu()
                syncState()
            }
        }

        mDrawerLayout.setDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        // TODO Ações para cada item do menu
        when (id) {
            R.id.action_seriesFilmes_seguidos -> {
            }
            R.id.actions_series -> {
            }
            R.id.action_filmes -> {

            }
        }

        val drawer = mActivity.findViewById(R.id.drawer_layout) as DrawerLayout?
        drawer!!.closeDrawer(GravityCompat.START)
        return true
    }
}
