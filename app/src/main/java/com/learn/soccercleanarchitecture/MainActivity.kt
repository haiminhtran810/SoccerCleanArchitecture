package com.learn.soccercleanarchitecture

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import com.google.android.gms.ads.formats.NativeAdOptions
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


/*
* https://developers.google.com/admob/android/quick-start#kotlin
* https://developers.google.com/admob/android/native/templates
* */

class MainActivity : AppCompatActivity() {

    private lateinit var mInterstitialAd: InterstitialAd
    private lateinit var rewardedAd: RewardedAd
    lateinit var adLoader: AdLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(
            this
        ) {}
        adsBanner()
        //adsInterstitial()
        adsNative()
        //adsRewarded()
    }

    private fun adsBanner() {
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

    private fun adsInterstitial() {
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        mInterstitialAd.loadAd(AdRequest.Builder().build())
        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            override fun onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
            }
        }
        buttonAds.setOnClickListener {
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            } else {
                Timber.d("The interstitial wasn't loaded yet.")
            }
        }
    }

    private fun adsNative() {
        adLoader = AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
            .forUnifiedNativeAd { ad: UnifiedNativeAd ->
                // Show the ad.
                if (adLoader.isLoading) {
                    // The AdLoader is still loading ads.
                    // Expect more adLoaded or onAdFailedToLoad callbacks.
                } else {
                    // The AdLoader has finished loading ads.
                }

                // If this callback occurs after the activity is destroyed, you
                // must call destroy and return or you may get a memory leak.
                // Note `isDestroyed` is a method on Activity.
                if (isDestroyed) {
                    ad.destroy()
                    return@forUnifiedNativeAd
                }
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    // Handle the failure by logging, altering the UI, and so on.
                }
            })
            .withNativeAdOptions(
                NativeAdOptions.Builder()
                    // Methods in the NativeAdOptions.Builder class can be
                    // used here to specify individual options settings.
                    .build()
            )
            .build()

        adLoader.loadAds(AdRequest.Builder().build(), 3)
    }

    private fun adsRewarded() {
        rewardedAd = RewardedAd(
            this,
            "ca-app-pub-3940256099942544/5224354917"
        )
        val adLoadCallback = object : RewardedAdLoadCallback() {

        }
        rewardedAd.loadAd(AdRequest.Builder().build(), adLoadCallback)

        buttonAds.setOnClickListener {
            if (rewardedAd.isLoaded) {
                val activityContext: Activity = this@MainActivity
                val adCallback = object : RewardedAdCallback() {
                    override fun onUserEarnedReward(p0: RewardItem) {

                    }

                    override fun onRewardedAdClosed() {
                        super.onRewardedAdClosed()
                    }

                    override fun onRewardedAdFailedToShow(p0: Int) {
                        super.onRewardedAdFailedToShow(p0)
                    }

                    override fun onRewardedAdOpened() {
                        super.onRewardedAdOpened()
                    }

                }
                rewardedAd.show(activityContext, adCallback)
            } else {
                Timber.d("The rewarded ad wasn't loaded yet.")
            }
        }
    }

    override fun onPause() {
        adView.pause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        adView.resume()
    }

    override fun onDestroy() {
        adView.destroy()
        super.onDestroy()
    }
}
