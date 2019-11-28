package com.echwood.evie

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.kevalpatel.passcodeview.authenticator.PasscodeViewPinAuthenticator
import com.kevalpatel.passcodeview.indicators.CircleIndicator
import com.kevalpatel.passcodeview.interfaces.AuthenticationListener
import com.kevalpatel.passcodeview.keys.KeyNamesBuilder
import com.kevalpatel.passcodeview.keys.RoundKey
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.sign_in.*


class LoginActivity : AppCompatActivity() {

    private val ARG_CURRENT_PIN = "current_pin"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSignInPage()
    }

    private fun initSignInPage() {
        //Set Pin
        val correctPin = intArrayOf(1, 2, 3, 4, 5, 6)
        pinView.pinAuthenticator = PasscodeViewPinAuthenticator(correctPin)

        //Set length
        pinView.pinLength = 6

        //Config
        pinView.setKey(
            RoundKey.Builder(pinView)
                .setKeyPadding(resources.getDimension(R.dimen.key_padding))
                .setKeyStrokeColorResource(R.color.colorPrimary)
                .setKeyStrokeWidth(R.dimen.key_stroke_width)
                .setKeyTextColorResource(R.color.colorPrimary)
                .setKeyTextSize(R.dimen.key_text_size)
        )
        //Set indicator
            pinView.setIndicator( CircleIndicator.Builder(pinView)
                .setIndicatorRadius(R.dimen.lib_indicator_radius)
                .setIndicatorFilledColorResource(R.color.colorPrimary)
                .setIndicatorStrokeColorResource(R.color.colorPrimary)
                .setIndicatorStrokeWidth(R.dimen.indicator_stroke_width))

        //Set KeysName
        pinView.setKeyNames(
            KeyNamesBuilder()
                .setKeyOne(this, R.string.key_1)
                .setKeyTwo(this, R.string.key_2)
                .setKeyThree(this, R.string.key_3)
                .setKeyFour(this, R.string.key_4)
                .setKeyFive(this, R.string.key_5)
                .setKeySix(this, R.string.key_6)
                .setKeySeven(this, R.string.key_7)
                .setKeyEight(this, R.string.key_8)
                .setKeyNine(this, R.string.key_9)
                .setKeyZero(this, R.string.key_0)
        )

        pinView.title = "Enter the PIN"


        pinView.setAuthenticationListener(object : AuthenticationListener {
            override fun onAuthenticationSuccessful() {
                //User authenticated successfully.
                //Navigate to next screens.
                Log.d("EVIE WORKER", "SUCCESSFUL")
            }

            override fun onAuthenticationFailed() {
                //Calls whenever authentication is failed or user is unauthorized.
                //Do something if you want to handle unauthorized user.
                Log.d("EVIE WORKER", "NOT SUCCESSFUL")

            }
        })
    }


    fun signInAnimation(v: View) {
        signInPage.visibility = View.VISIBLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this@LoginActivity.window.statusBarColor = resources.getColor(R.color.offWhite)
            this@LoginActivity.window.navigationBarColor = resources.getColor(R.color.offWhite)
            var getXYSignInButton = IntArray(2)
            val  signInButtonLocation= signIn.getLocationInWindow(getXYSignInButton)
            ViewAnimationUtils.createCircularReveal(signInPage, getXYSignInButton[0], getXYSignInButton[1], 0f,  Math.hypot(mainPage.width.toDouble(), mainPage.height.toDouble()).toFloat() ).start()
        }
    }



    override fun onSaveInstanceState(outState: Bundle) {
        outState.putIntArray(ARG_CURRENT_PIN, pinView.currentTypedPin)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        pinView.currentTypedPin = savedInstanceState.getIntArray(ARG_CURRENT_PIN)
    }

    override fun onBackPressed() {
        if ()
        super.onBackPressed()
    }
}
