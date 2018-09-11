package com.aship.alltv.utilities;

import com.aship.alltv.data.constants.AppConstant;
import com.aship.alltv.model.Channel;

import java.util.ArrayList;

public class ChannelUtils {

    public static ArrayList<Channel> getTvChannelList(ArrayList<Channel> channelArrayList, int categorId) {
        ArrayList<Channel> allChannelList = new ArrayList<>();
        for (int j = 0; j < channelArrayList.size(); j++) {
            Channel channel = channelArrayList.get(j);
            int category_id = channel.getCategoryId();
            if (category_id == categorId) {
                int channelId = channel.getChannelId();
                int is_live = channel.getIsLive();
                String channel_name = channel.getChannelName();
                String channel_Logo = channel.getChannelLogoUrl();
                String stream_url = channel.getStreamUrl();
                Channel single_channel = new Channel(channelId, category_id, channel_name, is_live, channel_Logo, stream_url);
                allChannelList.add(single_channel);
            } else if (categorId == AppConstant.All_CATEGORY) {
                int channelId = channel.getChannelId();
                int is_live = channel.getIsLive();
                String channel_name = channel.getChannelName();
                String channel_Logo = channel.getChannelLogoUrl();
                String stream_url = channel.getStreamUrl();
                Channel single_channel = new Channel(channelId, category_id, channel_name, is_live, channel_Logo, stream_url);
                allChannelList.add(single_channel);
            }

        }

        return allChannelList;
    }

}
