/*
 * Copyright 2018 Dionysios Karatzas
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.dkaratzas.popularmoviesapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import eu.dkaratzas.popularmoviesapp.R;
import eu.dkaratzas.popularmoviesapp.holders.VideoViewHolder;
import eu.dkaratzas.popularmoviesapp.models.Movie.Video;
import eu.dkaratzas.popularmoviesapp.models.Movie.VideoResults;

public class VideosAdapter extends RecyclerView.Adapter<VideoViewHolder> {
    private Context mContext;
    private VideoResults mVideoResults;

    public VideosAdapter(Context context, VideoResults videoResults) {
        this.mContext = context;
        this.mVideoResults = videoResults;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_list_item, parent, false);

        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        final Video video = mVideoResults.getResults().get(position);
        Picasso.with(mContext)
                .load(buildThumbnailUrl(video.getKey()))
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.mIvVideoThumb);

        holder.mTvVideoTitle.setText(video.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=".concat(video.getKey())));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mVideoResults.getResults().size();
    }

    private String buildThumbnailUrl(String videoId) {
        return "https://img.youtube.com/vi/" + videoId + "/hqdefault.jpg";
    }
}
