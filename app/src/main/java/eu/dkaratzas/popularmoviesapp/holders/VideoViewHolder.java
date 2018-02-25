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

package eu.dkaratzas.popularmoviesapp.holders;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import eu.dkaratzas.popularmoviesapp.R;

public class VideoViewHolder extends RecyclerView.ViewHolder {
    public ImageView mIvVideoThumb;
    public TextView mTvVideoTitle;

    public VideoViewHolder(View itemView) {
        super(itemView);

        mIvVideoThumb = itemView.findViewById(R.id.cvVideo);
        mTvVideoTitle = itemView.findViewById(R.id.tvVideoTitle);
    }
}
