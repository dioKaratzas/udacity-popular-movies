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

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import eu.dkaratzas.popularmoviesapp.R;
import eu.dkaratzas.popularmoviesapp.api.MoviesApiCallback;
import eu.dkaratzas.popularmoviesapp.api.MoviesApiManager;
import eu.dkaratzas.popularmoviesapp.controllers.MovieDetailActivity;
import eu.dkaratzas.popularmoviesapp.controllers.MovieDetailFragment;
import eu.dkaratzas.popularmoviesapp.controllers.MovieListActivity;
import eu.dkaratzas.popularmoviesapp.data.MoviesContract;
import eu.dkaratzas.popularmoviesapp.holders.MovieViewHolder;
import eu.dkaratzas.popularmoviesapp.models.Movie.Genre;
import eu.dkaratzas.popularmoviesapp.models.Movie.Movie;
import eu.dkaratzas.popularmoviesapp.models.Movie.Reviews;
import eu.dkaratzas.popularmoviesapp.models.Movie.VideoResults;
import eu.dkaratzas.popularmoviesapp.utils.ImageUtils;
import eu.dkaratzas.popularmoviesapp.utils.Misc;
import retrofit2.Call;

public class FavouriteMoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private MovieListActivity mParentActivity;
    private boolean mTwoPane;

    private List<Movie> mFavouriteMovies;
    private Call<Movie> callRequest;


    public FavouriteMoviesAdapter(MovieListActivity parent, Cursor cursor, boolean twoPane) {
        this.mParentActivity = parent;
        this.mTwoPane = twoPane;

        mFavouriteMovies = new ArrayList<>();
        loadMoviesFromCursor(cursor);

    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {
        final int pos = position;
        holder.mIvMovie.post(new Runnable() {
            @Override
            public void run() {
                Picasso.with(mParentActivity.getApplicationContext())
                        .load(ImageUtils.buildPosterImageUrl(mFavouriteMovies.get(pos).getPosterPath(), holder.mIvMovie.getWidth()))
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground)
                        .into(holder.mIvMovie);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callRequest != null) {
                    callRequest.cancel();
                }
                getMovieAndShowDetails(pos, holder);
            }
        });

        if (position == 0 && mTwoPane) {
            holder.itemView.callOnClick();
        }
    }

    @Override
    public int getItemCount() {
        return mFavouriteMovies.size();
    }


    private void getMovieAndShowDetails(final int position, final MovieViewHolder movieViewHolder) {
        if (Misc.isNetworkAvailable(mParentActivity)) {
            movieViewHolder.showProgress(true);

            callRequest = MoviesApiManager.getInstance().getMovie(mFavouriteMovies.get(position).getId(), new MoviesApiCallback<Movie>() {
                @Override
                public void onResponse(Movie result) {
                    if (result != null) {
                        showMovieDetails(result);
                    }
                    callRequest = null;
                    movieViewHolder.showProgress(false);
                }

                @Override
                public void onCancel() {
                    callRequest = null;
                    movieViewHolder.showProgress(false);
                }
            });
        } else {
            showMovieDetails(mFavouriteMovies.get(position));
        }


    }

    private void showMovieDetails(Movie movie) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(MovieDetailFragment.EXTRA_MOVIE_KEY, movie);
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(arguments);
            mParentActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movieDetailContainer, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(mParentActivity, MovieDetailActivity.class);
            intent.putExtra(MovieDetailFragment.EXTRA_MOVIE_KEY, movie);

            mParentActivity.startActivity(intent);
        }
    }

    private void loadMoviesFromCursor(Cursor cursor) {
        for (int i = 0; i < cursor.getCount(); i++) {
            int movieIdIndex = cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_ID);
            int titleIndex = cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_TITLE);
            int overviewIndex = cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_OVERVIEW);
            int posterPathIndex = cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_POSTER_PATH);
            int backdropPathIndex = cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_BACKDROP_PATH);
            int releaseDateIndex = cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_RELEASE_DATE);
            int runtimeIndex = cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_RUNTIME);
            int voteAverageIndex = cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_VOTE_AVERAGE);

            cursor.moveToPosition(i);

            mFavouriteMovies.add(new Movie(
                    cursor.getInt(movieIdIndex),
                    cursor.getString(titleIndex),
                    cursor.getString(overviewIndex),
                    cursor.getString(posterPathIndex),
                    cursor.getString(backdropPathIndex),
                    cursor.getString(releaseDateIndex),
                    cursor.getInt(runtimeIndex),
                    cursor.getInt(voteAverageIndex),
                    new VideoResults(),
                    new Reviews(),
                    new ArrayList<Genre>()
            ));
        }
    }
}
