package com.linhtetko.themoviebookingapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModel
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModelImpl
import com.linhtetko.themoviebookingapp.data.model.MovieModel
import com.linhtetko.themoviebookingapp.data.model.MovieModelImpl
import com.linhtetko.themoviebookingapp.data.vos.moviedb.GenreVO
import com.linhtetko.themoviebookingapp.delegates.DrawerMenuDelegate
import com.linhtetko.themoviebookingapp.delegates.MovieItemDelegate
import com.linhtetko.themoviebookingapp.util.loadImageFromMBA
import com.linhtetko.themoviebookingapp.util.showSnackBar
import com.linhtetko.themoviebookingapp.viewpods.DrawerMainItemViewPod
import com.linhtetko.themoviebookingapp.viewpods.MovieViewPod
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.view.*

class MainActivity : AppCompatActivity(R.layout.activity_main), MovieItemDelegate,
    DrawerMenuDelegate {

    private lateinit var appBarDrawerToggle: ActionBarDrawerToggle
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    private val mMovieDBModel: MovieModel = MovieModelImpl

    private lateinit var mVpNowShowing: MovieViewPod
    private lateinit var mVpUpComing: MovieViewPod

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpActionbar()
        setDrawerItems()
        setUpNowShowingMovies()
        setUpComingSoonMovies()

        getToken()
    }

    private fun getToken() {
        getGenres()
        getMainProfile()
    }

    private fun getGenres() {
        mMovieDBModel.getGenreList(
            onSuccess = {
                getNowPlayingMovies(it)
                getUpComingMovies(it)
            },
            onFailure = {
                showErrorMessage(it)
            }
        )
    }

    private fun getUpComingMovies(items: List<GenreVO>) {
        mMovieDBModel.getUpComingMovies(
            onSuccess = {
                mVpUpComing.setNewData(items, it)
            },
            onFailure = {
                showErrorMessage(it)
            }
        )
    }

    private fun getNowPlayingMovies(items: List<GenreVO>) {
        mMovieDBModel.getNowPlayingMovies(
            onSuccess = {
                mVpNowShowing.setNewData(items, it)
            },
            onFailure = {
                showErrorMessage(it)
            }
        )
    }

    //Get Profile
    private fun getMainProfile() {

        mMovieBookingModel.getDbProfile { authVO ->
            authVO?.apply {
                if (profileImage != null) {
                    ivMainProfile.loadImageFromMBA(profileImage)
                    ivDmProfile.loadImageFromMBA(profileImage)
                    tvDmName.text = name ?: ""
                    tvDmEmail.text = email ?: ""
                }
                if (name != null) {
                    tvMainProfile.text = getString(R.string.lbl_profile_name, name)
                }
            }
        }
    }

    private fun showErrorMessage(message: String) {
        clMain.showSnackBar(message)
    }

    private fun setDrawerItems() {

        (vpDmPromoCode as DrawerMainItemViewPod).setItem(
            R.drawable.ic_baseline_local_offer_24,
            R.string.lbl_promo_code,
        )
        (vpDmLanguage as DrawerMainItemViewPod).setItem(
            R.drawable.ic_baseline_g_translate_24,
            R.string.lbl_translate
        )
        (vpDmTermAndService as DrawerMainItemViewPod).setItem(
            R.drawable.ic_baseline_assignment_24,
            R.string.lbl_terms_of_service
        )
        (vpDmHelp as DrawerMainItemViewPod).setItem(
            R.drawable.ic_baseline_help_24,
            R.string.lbl_help
        )
        (vpDmRateUs as DrawerMainItemViewPod).setItem(
            R.drawable.ic_baseline_stars_24,
            R.string.lbl_rate_us
        )

        val vpLogout = vpDmLogout as DrawerMainItemViewPod
        vpLogout.setItem(
            R.drawable.ic_baseline_logout_24,
            R.string.lbl_logout
        ) {
            logout()
        }


    }

    private fun logout() {
        mMovieBookingModel.logout(
            onSuccess = {
                if (dlMain.isDrawerOpen(GravityCompat.START)) {
                    dlMain.closeDrawer(GravityCompat.START)
                }
                finishAffinity()
                startActivity(AuthActivity.newIntent(this))
            },
            onFailure = {
                if (dlMain.isDrawerOpen(GravityCompat.START)) {
                    dlMain.closeDrawer(GravityCompat.START)
                }
                showErrorMessage(it)
            }
        )
    }

    private fun setUpNowShowingMovies() {
        mVpNowShowing = vpMovieNowShowing as MovieViewPod
        mVpNowShowing.setUpViewPod(getString(R.string.lbl_now_showing), this)
    }

    private fun setUpComingSoonMovies() {
        mVpUpComing = vpMovieComingSoon as MovieViewPod
        mVpUpComing.setUpViewPod(getString(R.string.lbl_coming_soon), this)
    }

    private fun setUpActionbar() {
        setSupportActionBar(abMain.tbMain)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_left_alt_svgrepo_com)

        appBarDrawerToggle =
            ActionBarDrawerToggle(this, dlMain, R.string.lbl_open, R.string.lbl_close)
        appBarDrawerToggle.syncState()

        dlMain.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                if (dlMain.isDrawerOpen(GravityCompat.START)) {
                    dlMain.closeDrawer(GravityCompat.START)
                }
            }

            override fun onDrawerOpened(drawerView: View) {

            }

            override fun onDrawerClosed(drawerView: View) {

            }

            override fun onDrawerStateChanged(newState: Int) {

            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            dlMain.openDrawer(GravityCompat.START)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClickItem(movieId: Int) {
        startActivity(MovieDetailActivity.newIntent(this, movieId))
    }

    override fun onBackPressed() {
        if (dlMain.isDrawerOpen(GravityCompat.START)) {
            dlMain.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onItemClick(menuName: String) {
        when (menuName) {
            "logout" -> {
                dlMain.closeDrawer(GravityCompat.START)
                logout()
            }
        }
    }


}