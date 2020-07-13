package com.learn.soccercleanarchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(
            this
        ) {}
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                Timber.d("Code to be executed when an ad finishes loading.")
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                when (errorCode) {
                    AdRequest.ERROR_CODE_INTERNAL_ERROR -> {
                        Timber.d("AdRequest.ERROR_CODE_INTERNAL_ERROR")
                    }
                    AdRequest.ERROR_CODE_INVALID_REQUEST -> {
                        Timber.d("AdRequest.ERROR_CODE_INVALID_REQUEST")
                    }
                    AdRequest.ERROR_CODE_NETWORK_ERROR -> {
                        Timber.d("AdRequest.ERROR_CODE_NETWORK_ERROR")
                    }
                    AdRequest.ERROR_CODE_INTERNAL_ERROR -> {
                        Timber.d("AdRequest.ERROR_CODE_INTERNAL_ERROR")
                    }
                    AdRequest.ERROR_CODE_NO_FILL -> {
                        Timber.d("AdRequest.ERROR_CODE_NO_FILL")
                    }
                }
            }

            override fun onAdOpened() {
                Timber.d("Code to be executed when an ad opens an overlay that\ncovers the screen.")
            }

            override fun onAdClicked() {
                Timber.d("Code to be executed when the user clicks on an ad.")
            }

            override fun onAdLeftApplication() {
                Timber.d("Code to be executed when the user has left the app.")
            }

            override fun onAdClosed() {
                Timber.d("Code to be executed when the user is about to return\nto the app after tapping on an ad.")
            }
        }
    }
}
