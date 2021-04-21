package com.hmsmd.huawei.ads;

import android.content.Context;


import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.InterstitialAd;
import com.smaato.soma.ErrorCode;
import com.smaato.soma.bannerutilities.constant.Values;
import com.smaato.soma.debug.DebugCategory;
import com.smaato.soma.debug.Debugger;
import com.smaato.soma.debug.LogMessage;
import com.smaato.soma.mediation.MediationEventInterstitial;

import java.util.Map;

public class CustomMediationIntersitial extends MediationEventInterstitial {


    /**
     * KEY_STRING needs to be updated and to be matched with SPX PORTAL inputs
     */
    private static final String ID_KEY = "AD_UNIT_ID";


    /**
     * TAG used for Log.
     */
    private static final String TAG = "CustomMediationBannerAdapterSample";


    private MediationEventInterstitialListener mIntersitialListener;

    // consider to have static and single instance based on the Adapter requirement
    private InterstitialAd mGoogleAdView;
    int width = 0;
    int height = 0;

    /*
     * The Method name could be changed as per the name given in the Smaato SPX portal, but the params should be fixed.
     */
    public void loadCustomIntersitial(Context context, MediationEventInterstitialListener mediationEventBannerListener, Map<String, String> serverBundle) {

        mIntersitialListener = mediationEventBannerListener;

        if (!mediationInputsAreValid(serverBundle)) {
            mIntersitialListener.onInterstitialFailed(ErrorCode.ADAPTER_CONFIGURATION_ERROR);
            return;
        }

        try {

            mGoogleAdView = new InterstitialAd(context);
            mGoogleAdView.setAdId(serverBundle.get(ID_KEY));
            mGoogleAdView.setAdListener(new AdViewListener());
            //  mGoogleAdView.setAdUnitId(serverBundle.get(ID_KEY));



            AdParam adParam = new AdParam.Builder().build();
            mGoogleAdView.loadAd(adParam);


        } catch (NoClassDefFoundError e) {
            notifyMediationConfigIssues();
            return;
        } catch (Exception e) {
            notifyMediationException();
            return;
        }


    }


    @Override
    public void showInterstitial() {
        if(mGoogleAdView.isLoaded()){
            mGoogleAdView.show();
        }
    }

    @Override
    public void onInvalidate() {
        try {
          //  Views.removeFromParent(mGoogleAdView.getAdMetadata());
            Debugger.showLog(new LogMessage(TAG,
                    "hata invalidate",
                    Debugger.Level_1,
                    DebugCategory.DEBUG));
            destroy();


        } catch (NoClassDefFoundError e) {
            notifyMediationConfigIssues();
            return;
        } catch (Exception e) {
            notifyMediationException();
            return;
        }

    }


    public void destroy() {
        try {

            if (mGoogleAdView != null) {
                //mGoogleAdView.;
                Debugger.showLog(new LogMessage(TAG,
                        "hata destroy",
                        Debugger.Level_1,
                        DebugCategory.DEBUG));
            }

        } catch (NoClassDefFoundError e) {
            return;
        } catch (Exception e) {
            return;
        }

    }

    private boolean mediationInputsAreValid(Map<String,String> serverBundle) {
        try {

            if (serverBundle == null)
                return false;

            try{
                // Check and update whether widht and Height are needed for your custom Adapter
                if(serverBundle.get(Values.MEDIATION_WIDTH)!=null && serverBundle.get(Values.MEDIATION_HEIGHT) !=null){
                    width = Integer.valueOf(serverBundle.get(Values.MEDIATION_WIDTH));
                    height = Integer.valueOf(serverBundle.get(Values.MEDIATION_HEIGHT));
                }
            } catch (Exception ex) { // check if width ht params are mandatory return false;
            }

            // ### Needs to be updated as per Custom Network Mandatory Fields
            if (serverBundle != null && !serverBundle.get(ID_KEY).isEmpty() )
                return true;


        } catch (Exception e) {
            return false;
        }
        return false;
    }




    public class AdViewListener extends AdListener {
        @Override
        public void onAdClosed() {

        }

        @Override
        public void onAdFailed(int errorCode) {

            try {
                Debugger.showLog(new LogMessage(TAG,
                        "Google Play Services banner ad failed to load.",
                        Debugger.Level_1,
                        DebugCategory.DEBUG));

                if (mIntersitialListener != null) {
                    mIntersitialListener.onInterstitialFailed(ErrorCode.NETWORK_NO_FILL);
                }

                if (mGoogleAdView != null) {
                 //   mGoogleAdView.();
                }

                onInvalidate();

            } catch (NoClassDefFoundError e) {
                notifyMediationConfigIssues();
                return;
            } catch (Exception e) {
                notifyMediationException();
                return;
            }

        }

        @Override
        public void onAdLeave() {

            // cleanup
            onInvalidate();
        }

        @Override
        public void onAdLoaded() {

            try {
                Debugger.showLog(new LogMessage(TAG,
                        "Google Play banner ad loaded successfully",
                        Debugger.Level_1,
                        DebugCategory.DEBUG));

                if (mIntersitialListener != null) {
               //     mIntersitialListener.onWillShow();
                    mIntersitialListener.onInterstitialLoaded();
                }

            } catch (NoClassDefFoundError e) {
                notifyMediationConfigIssues();
                return;
            } catch (Exception e) {
                notifyMediationException();
                return;
            }

        }

        @Override
        public void onAdOpened() {

            Debugger.showLog(new LogMessage(TAG,
                    "Google Play Services banner ad clicked.",
                    Debugger.Level_1,
                    DebugCategory.DEBUG));

            if (mIntersitialListener != null ) {
                mIntersitialListener.onInterstitialClicked();
            }
        }
    }


    private void notifyMediationConfigIssues() {

        Debugger.showLog(new LogMessage(TAG,
                "Dependencies missing. Check configurations of " + TAG,
                Debugger.Level_1,
                DebugCategory.ERROR));

        mIntersitialListener.onInterstitialFailed(ErrorCode.ADAPTER_CONFIGURATION_ERROR);
        onInvalidate();
    }

    private void notifyMediationException() {

        Debugger.showLog(new LogMessage(TAG,
                "Exception happened with Mediation inputs. Check in "+TAG,
                Debugger.Level_1,
                DebugCategory.ERROR));

        mIntersitialListener.onInterstitialFailed(ErrorCode.GENERAL_ERROR);

        onInvalidate();
    }

}