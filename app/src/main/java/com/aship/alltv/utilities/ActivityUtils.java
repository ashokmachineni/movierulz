package com.aship.alltv.utilities;

import android.app.Activity;
import android.content.Intent;

import com.aship.alltv.activity.MainActivity;
import com.aship.alltv.activity.VideoViewBuffer;
import com.aship.alltv.activity.YoutubePlayerViewActivity;
import com.aship.alltv.model.Channel;

import java.util.ArrayList;


public class ActivityUtils {
    private static ActivityUtils sActivityUtils = null;

    public static ActivityUtils getInstance() {
        if (sActivityUtils == null) {
            sActivityUtils = new ActivityUtils();
        }
        return sActivityUtils;
    }

    public void invokeActivity(Activity activity, Class<?> tClass, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }
    public void invokeYoutubePlayerViewActivity(Activity activity, Channel channelData, ArrayList<Channel> channelList) {
        Intent  intent = new Intent(activity, YoutubePlayerViewActivity.class);
        intent.putExtra("channel_data", channelData);
        intent.putExtra("related_channel_list", channelList);
        activity.startActivity(intent);
    }
 public void invokeExoPlayerViewActivity(Activity activity, Channel channelData, ArrayList<Channel> channelList) {
/*        Intent  intent = new Intent(activity, ExoPlayerViewActivity.class);
        intent.putExtra("channel_data", channelData);
        intent.putExtra("related_channel_list", channelList);
        activity.startActivity(intent);*/

     Utils.showInterstitialIfNeed(activity);


     Intent  intent = new Intent(activity, VideoViewBuffer.class);
     intent.putExtra("channel_data", channelData);
     intent.putExtra("stringurl",channelData.getStreamUrl());
     intent.putExtra("related_channel_list", channelList);
     activity.startActivity(intent);

    }


}
