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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.dkaratzas.popularmoviesapp.R;
import eu.dkaratzas.popularmoviesapp.holders.ReviewViewHolder;
import eu.dkaratzas.popularmoviesapp.models.Movie.Review;
import eu.dkaratzas.popularmoviesapp.models.Movie.Reviews;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {
    private Reviews mReviews;

    public ReviewAdapter(Reviews reviews) {
        this.mReviews = reviews;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_list_item, parent, false);

        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        final Review review = mReviews.getResults().get(position);

        holder.mTvReviewAuthor.setText(review.getAuthor());
        holder.mTvReviewContent.setText(review.getContent());
    }

    @Override
    public int getItemCount() {
        return mReviews.getResults().size();
    }

}
