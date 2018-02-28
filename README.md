![screen](../master/art/logo.png)

The Popular Movies Android app, was made as part of Udacity's [Android Developer Nanodegree Program](https://www.udacity.com/course/android-developer-nanodegree-by-google--nd801).
This app has adaptive UI for phone and tablet devices. It displays the Most Popular and Top Rated Movies.
User has the ability to save favourite movies locally and view them even when is offline.
Also can view movie details (rating, release date, duration, etc.), watch trailers, read reviews and share the movie.

**Download:**

You can download an APK build [on releases page](https://github.com/dnKaratzas/udacity-popular-movies/releases/).

## How to Work with the Source
The app fetches movie information using [The Movie Database (TMDb)](https://www.themoviedb.org/documentation/api) API.
You have to enter your own API key into `gradle.properties` file.

```gradle.properties
MOVIE_DB_API_KEY="Your Api Key"
```

If you don’t already have an account, you will need to create one in order to request an [API Key](https://www.themoviedb.org/documentation/api) .

Libraries
---------
* [Jackson](https://github.com/FasterXML/jacksont)
* [Retrofit](https://github.com/square/retrofit)
* [Picasso](https://github.com/square/picasso)
* [Logger](https://github.com/orhanobut/logger)
* [FlowLayout](https://github.com/nex3z/FlowLayout)
* [MaterialRatingBar](https://github.com/DreaminginCodeZH/MaterialRatingBar)
* [SwipyRefreshLayout](https://github.com/omadahealth/SwipyRefreshLayout)

Icon credits
---------
* Popcorn by [Wissawa Khamsriwath](https://www.flaticon.com/authors/wissawa-khamsriwath)
* Heart by [Smashicons](https://www.flaticon.com/authors/smashicons)
* Broken Heart by [Swifticons](https://www.flaticon.com/authors/swifticons)
* Sort Down by [Freepik](http://www.freepik.com)

License
-------
Copyright 2018 Dionysios Karatzas

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
