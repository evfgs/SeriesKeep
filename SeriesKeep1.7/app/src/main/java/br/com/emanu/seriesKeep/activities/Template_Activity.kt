package br.com.emanu.seriesKeep.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.transition.Slide
import android.view.Gravity
import android.view.Menu
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import br.com.emanu.kotlin_support.service.log
import br.com.emanu.seriesKeep.R
import com.squareup.picasso.Picasso


abstract class Template_Activity : AppCompatActivity() {

    private var isRunning: Boolean? = true
    private var mSetup: ActivitySetup? = null

    abstract fun setupActivity(): ActivitySetup

    abstract fun getToolbar(): Toolbar?

    open fun onNavigationClicked() {
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startResources()
    }

    open fun startResources() {
        mSetup = setupActivity()
        if (mSetup != null) {
            try {
                setContentView(mSetup!!.contentView!!)
                initToolbarBehavior()
            } catch (e: Exception) {
                log("Could not start activity resources: ${e.message}")
                e.printStackTrace()
                startActivity(Intent(this, Homepage_Activity::class.java))
            }
        }
    }

    fun loadBackgroundImage(wrapper: ImageView?) {
        if (null != wrapper) {
            Picasso.with(this).load(R.drawable.img_background).fit().centerCrop().into(wrapper)
        }
    }
    //LIFECYCLE das activities
    override fun onPause() {
        super.onPause()
        isRunning = false
    }

    override fun onResume() {
        super.onResume()
        isRunning = true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        if (mSetup!!.menuFile != null) {
            menuInflater.inflate(mSetup!!.menuFile!!, menu)
        }
        return true
    }

    fun isRunning(): Boolean {
        return this.isRunning!!
    }

    fun setLeftSlideAnimationForCalled() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            val slide = Slide()
            slide.interpolator = LinearInterpolator()
            slide.slideEdge = Gravity.RIGHT
            slide.excludeTarget(android.R.id.statusBarBackground, true)
            slide.excludeTarget(android.R.id.navigationBarBackground, true)
            window.enterTransition = slide
            window.returnTransition = slide
            window.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        }
    }


    fun setLeftSlideAnimationForCaller() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val slide = Slide()
            slide.interpolator = LinearInterpolator()
            slide.slideEdge = Gravity.START
            slide.excludeTarget(android.R.id.statusBarBackground, true)
            slide.excludeTarget(android.R.id.navigationBarBackground, true)
            window.exitTransition = slide
            window.reenterTransition = slide
            window.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        }
    }

    private fun initToolbarBehavior() {
        val toolbar = getToolbar()
        if (null != toolbar) {
            setSupportActionBar(toolbar)
            toolbar.setTitleTextColor(resources.getColor(R.color.colorTextLight))
            supportActionBar!!.title = mSetup!!.title
            supportActionBar!!.setHomeButtonEnabled(true)
            if (-1 != mSetup!!.toolbarIcon) toolbar.setNavigationIcon(mSetup!!.toolbarIcon)
            toolbar.setNavigationOnClickListener {
                onNavigationClicked()
            }
        } else {
            log("toolbar is null")
        }
    }
}
