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

package eu.dkaratzas.popularmoviesapp.models.Movie;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie implements Parcelable {
    @JsonProperty("id")
    private int id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("overview")
    private String overview;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("runtime")
    private int runtime;
    @JsonProperty("vote_average")
    private double voteAverage;
    @JsonProperty("videos")
    private VideoResults videos;
    @JsonProperty("reviews")
    private Reviews reviews;
    @JsonProperty("genres")
    private List<Genre> genres;

    public Movie() {
        this.id = 0;
        this.title = "";
        this.overview = "";
        this.posterPath = "";
        this.backdropPath = "";
        this.releaseDate = "";
        this.runtime = 0;
        this.voteAverage = 0.0;
        this.videos = new VideoResults();
        this.reviews = new Reviews();
        this.genres = new ArrayList<>();
    }

    public Movie(int id, String title, String overview, String posterPath, String backdropPath, String releaseDate, int runtime, double voteAverage, VideoResults videos, Reviews reviews, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.voteAverage = voteAverage;
        this.videos = videos;
        this.reviews = reviews;
        this.genres = genres;
    }

    // Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.posterPath);
        dest.writeString(this.backdropPath);
        dest.writeString(this.releaseDate);
        dest.writeInt(this.runtime);
        dest.writeDouble(this.voteAverage);
        dest.writeParcelable(this.videos, flags);
        dest.writeParcelable(this.reviews, flags);
        dest.writeTypedList(this.genres);
    }

    protected Movie(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
        this.releaseDate = in.readString();
        this.runtime = in.readInt();
        this.voteAverage = in.readDouble();
        this.videos = in.readParcelable(VideoResults.class.getClassLoader());
        this.reviews = in.readParcelable(Reviews.class.getClassLoader());
        this.genres = in.createTypedArrayList(Genre.CREATOR);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getReleaseDateLocalized(Context context) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
        Date date = null;
        try {
            date = sdf.parse(releaseDate);
        } catch (ParseException e) {
            return releaseDate;
        }
        DateFormat dateFormat = android.text.format.DateFormat.getMediumDateFormat(context);
        return dateFormat.format(date);
    }

    public int getRuntime() {
        return runtime;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public VideoResults getVideos() {
        return videos;
    }

    public Reviews getReviews() {
        return reviews;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getDuration() {
        return String.valueOf(runtime) + " min";
    }
}