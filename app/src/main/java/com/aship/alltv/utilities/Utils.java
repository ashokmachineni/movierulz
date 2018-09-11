package com.aship.alltv.utilities;


import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.aship.alltv.R;


public class Utils {
    public static int ADS_COUNTER = 0;

    public static int NEW_ADS_COUNTER = 0;


    public static void showInterstitialAds(Context context) {
        final InterstitialAd interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(context.getString(R.string.interstitial_ad_unit_id));
        interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
        interstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                interstitialAd.show();
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();

            }
        });
    }
    public static void showInterstitialIfNeed(Context context) {

            showrewardedAds(context);

    }



    public static void showrewardedAds(final Context context)
    {
        final RewardedVideoAd mAd = MobileAds.getRewardedVideoAdInstance(context);
        mAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                try {
                    if (mAd.isLoaded()) {
                        mAd.show();
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {

            }

            @Override
            public void onRewarded(RewardItem rewardItem) {

            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

                showInterstitialAds(context);
            }
        });



        mAd.loadAd(context.getString(R.string.rewarded_ad_unit_id),
                new AdRequest.Builder().build());

    }
}