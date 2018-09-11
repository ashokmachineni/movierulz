/*
 * Copyright (C) 2013 yixia.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aship.alltv.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aship.alltv.R;
import com.aship.alltv.data.constants.AppConstant;
import com.aship.alltv.model.Channel;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.utils.Log;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoViewBuffer extends Activity implements OnInfoListener, OnBufferingUpdateListener {

  /**
   * TODO: Set the path variable to a streaming video URL or a local media file
   * path.
   */
  private String path = "";
  private Uri uri;
  private VideoView mVideoView;
  private ProgressBar pb;


  private  Channel channelData;

  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    if (!LibsChecker.checkVitamioLibs(this))
      return;
    setContentView(R.layout.videobuffer);
    mVideoView = (VideoView) findViewById(R.id.buffer);
    pb = (ProgressBar) findViewById(R.id.probar);




    initFunctionality();


    if (path == "") {
      // Tell the user to provide a media file URL/path.
      Toast.makeText(
          VideoViewBuffer.this,
          "Please edit VideoBuffer Activity, and set path"
              + " variable to your media file URL/path", Toast.LENGTH_LONG).show();
      return;
    } else {
      /*
       * Alternatively,for streaming media you can use
       * mVideoView.setVideoURI(Uri.parse(URLstring));
       */
      uri = Uri.parse(path);
      mVideoView.setVideoURI(uri);
      mVideoView.setMediaController(new MediaController(this));
      mVideoView.requestFocus();
      mVideoView.setOnInfoListener(this);
      mVideoView.setOnBufferingUpdateListener(this);
      mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
          // optional need Vitamio 4.0
          mediaPlayer.setPlaybackSpeed(1.0f);
        }
      });
    }

  }



  public void initFunctionality() {

    Intent intent = getIntent();
    if (intent != null) {
      channelData = (Channel) intent.getSerializableExtra(AppConstant.CHANNEL_DATA);


      mVideoView.setVideoURI(Uri.parse(channelData.getStreamUrl().toString()));

      path= String.valueOf(Uri.parse(channelData.getStreamUrl().toString()));



    }
  }


  @Override
  public boolean onInfo(MediaPlayer mp, int what, int extra) {
    switch (what) {
    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
      if (mVideoView.isPlaying()) {
        mVideoView.pause();
        pb.setVisibility(View.VISIBLE);



      }
      break;
    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
      mVideoView.start();
      pb.setVisibility(View.GONE);


      break;
    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:


      break;
    }
    return true;
  }

  @Override
  public void onBufferingUpdate(MediaPlayer mp, int percent) {
    if (percent==0)
    {
      pb.setVisibility(View.VISIBLE);

    }
    else {
      pb.setVisibility(View.GONE);
    }

  }

}
