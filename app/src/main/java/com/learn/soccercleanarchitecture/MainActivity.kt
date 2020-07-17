package com.learn.soccercleanarchitecture

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import com.google.android.gms.ads.formats.MediaView
import com.google.android.gms.ads.formats.NativeAdOptions
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.formats.UnifiedNativeAdView
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
    var currentNativeAd: UnifiedNativeAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(
            this
        ) {}
        adsBanner()
        //adsInterstitial()
        adsNative()
        adsRewarded()
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
        val adLoader = AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
            .forUnifiedNativeAd { unifiedNativeAd ->
                // Assumes that your ad layout is in a file call ad_unified.xml
                // in the res/layout folder
                val adView =
                    layoutInflater.inflate(R.layout.layout_native_ads, null) as UnifiedNativeAdView
                // This method sets the text, images and the native ad, etc into the ad
                // view.
                populateUnifiedNativeAdView(unifiedNativeAd, adView)
                // Assumes you have a placeholder FrameLayout in your View layout
                // (with id ad_frame) where the ad is to be placed.
                ad_frame.removeAllViews()
                ad_frame.addView(adView)
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
        adLoader.loadAds(AdRequest.Builder().build(), 5)
    }

    private fun populateUnifiedNativeAdView(
        nativeAd: UnifiedNativeAd,
        adView: UnifiedNativeAdView
    ) {
        // You must call destroy on old ads when you are done with them,
        // otherwise you will have a memory leak.
        currentNativeAd?.destroy()
        currentNativeAd = nativeAd

        // Set the media view.
        adView.mediaView = adView.findViewById<MediaView>(R.id.ad_media)

        // Set other ad assets.
        adView.headlineView = adView.findViewById(R.id.ad_headline)
        adView.bodyView = adView.findViewById(R.id.ad_body)
        adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)
        adView.iconView = adView.findViewById(R.id.ad_app_icon)
        adView.priceView = adView.findViewById(R.id.ad_price)
        adView.starRatingView = adView.findViewById(R.id.ad_stars)
        adView.storeView = adView.findViewById(R.id.ad_store)
        adView.advertiserView = adView.findViewById(R.id.ad_advertiser)

        // The headline and media content are guaranteed to be in every UnifiedNativeAd.
        (adView.headlineView as TextView).text = nativeAd.headline
        adView.mediaView.setMediaContent(nativeAd.mediaContent)

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.body == null) {
            adView.bodyView.visibility = View.INVISIBLE
        } else {
            adView.bodyView.visibility = View.VISIBLE
            (adView.bodyView as TextView).text = nativeAd.body
        }

        if (nativeAd.callToAction == null) {
            adView.callToActionView.visibility = View.INVISIBLE
        } else {
            adView.callToActionView.visibility = View.VISIBLE
            (adView.callToActionView as Button).text = nativeAd.callToAction
        }

        if (nativeAd.icon == null) {
            adView.iconView.visibility = View.GONE
        } else {
            (adView.iconView as ImageView).setImageDrawable(
                nativeAd.icon.drawable
            )
            adView.iconView.visibility = View.VISIBLE
        }

        if (nativeAd.price == null) {
            adView.priceView.visibility = View.INVISIBLE
        } else {
            adView.priceView.visibility = View.VISIBLE
            (adView.priceView as TextView).text = nativeAd.price
        }

        if (nativeAd.store == null) {
            adView.storeView.visibility = View.INVISIBLE
        } else {
            adView.storeView.visibility = View.VISIBLE
            (adView.storeView as TextView).text = nativeAd.store
        }

        if (nativeAd.starRating == null) {
            adView.starRatingView.visibility = View.INVISIBLE
        } else {
            (adView.starRatingView as RatingBar).rating = nativeAd.starRating!!.toFloat()
            adView.starRatingView.visibility = View.VISIBLE
        }

        if (nativeAd.advertiser == null) {
            adView.advertiserView.visibility = View.INVISIBLE
        } else {
            (adView.advertiserView as TextView).text = nativeAd.advertiser
            adView.advertiserView.visibility = View.VISIBLE
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView.setNativeAd(nativeAd)

        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
        // have a video asset.
        val vc = nativeAd.videoController

        // Updates the UI to say whether or not this ad has a video asset.
        if (vc.hasVideoContent()) {
//            videostatus_text.text = String.format(
//                Locale.getDefault(),
//                "Video status: Ad contains a %.2f:1 video asset.",
//                vc.aspectRatio)

            // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
            // VideoController will call methods on this object when events occur in the video
            // lifecycle.
            vc.videoLifecycleCallbacks = object : VideoController.VideoLifecycleCallbacks() {
                override fun onVideoEnd() {
                    // Publishers should allow native ads to complete video playback before
                    // refreshing or replacing them with another ad in the same UI location.
//                    refresh_button.isEnabled = true
//                    videostatus_text.text = "Video status: Video playback has ended."
                    super.onVideoEnd()
                }
            }
        } else {
//            videostatus_text.text = "Video status: Ad does not contain a video asset."
//            refresh_button.isEnabled = true
        }

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
