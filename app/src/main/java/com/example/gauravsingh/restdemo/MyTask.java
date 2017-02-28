package com.example.gauravsingh.restdemo;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.gauravsingh.restdemo.MovieDTO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MyTask extends AsyncTask<Void,Void,ArrayList<MovieDTO>> {
    public static final String LOG_TAG = "MY_TASK";
    public String jsonMoviesString;
    ArrayList<MovieDTO> movieArrayList = new ArrayList<>();
    String API_KEY = com.example.gauravsingh.restdemo.BuildConfig.Key;

    @Override
    protected ArrayList<MovieDTO> doInBackground(Void... s) {

        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;

        //"ff23b6d245aab103f984ed9562e26b05";
        int initialPage = 1;
        //String sortCriteria = params[0];
        String sortCriteria = "popular";

        // for loop to load two pages
        for (int j = initialPage; j < initialPage + 2; j++) {
            try {
                final String MOVIES_BASE_URL = "https://api.themoviedb.org/3/movie/" + sortCriteria;
                final String API_KEY_PARAM = "api_key";
                final String PAGE_PARAM = "page";

                //build uri
                Uri builtUri = Uri.parse(MOVIES_BASE_URL).buildUpon().appendQueryParameter(API_KEY_PARAM, API_KEY)
                        .appendQueryParameter(PAGE_PARAM, String.valueOf(j)).build();
                URL url = new URL(builtUri.toString());

                //get connection
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();


                InputStream inputStream = httpURLConnection.getInputStream();
                StringBuffer stringBuffer = new StringBuffer();
                // if stream is null do nothing
                if(inputStream == null) {
                    return null;
                }
                //read the json page
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line =bufferedReader.readLine()) != null) {
                    stringBuffer.append(line + "\n");
                }
                // if length of read data is 0 return null
                if(stringBuffer.length() == 0) {
                    return null;
                }

                jsonMoviesString = stringBuffer.toString();
                final String TMDB_RESULT_KEY = "results";
                final String POSTER_PATH_KEY = "poster_path";
                final String TITLE_KEY = "title";
                final String RELEASE_DATE_KEY = "release_date";
                final String VOTE_AVERAGE_KEY = "vote_average";
                final String OVERVIEW_KEY = "overview";
                final String ID_KEY = "id";

                //get the json string to be parsed
                JSONObject moviesJson = new JSONObject(jsonMoviesString);
                JSONArray moviesArray = moviesJson.getJSONArray(TMDB_RESULT_KEY);
                int len = moviesArray.length();

                // store all image urls in an ArrayList
                for (int i=0;i< len;i++) {
                    JSONObject movieInstance = moviesArray.getJSONObject(i);

                    //Initialize all the movie parameter required
                    String posterPath = movieInstance.optString(POSTER_PATH_KEY);
                    String title = movieInstance.getString(TITLE_KEY);
                    String release_date = movieInstance.getString(RELEASE_DATE_KEY);
                    String vote_average = movieInstance.getString(VOTE_AVERAGE_KEY);
                    String overview = movieInstance.getString(OVERVIEW_KEY);
                    String id = movieInstance.getString(ID_KEY);

                    //Initialize the movie instance and assign all parameters
                    MovieDTO movie = new MovieDTO();
                    movie.setDate(release_date);
                    movie.setPlot(overview);
                    movie.setPosterUrl(posterPath);
                    movie.setVoteAverage(vote_average);
                    movie.setTitle(title);
                    movie.setId(id);

                    //Add the movie to the arraylist of movies
                    movieArrayList.add(movie);

                  //  posterUrl.add(posterPath);

                }
            }
            catch (java.io.IOException e){
                Log.e(LOG_TAG,e.toString());
            }
            catch (org.json.JSONException e) {
                Log.e(LOG_TAG, e.toString());
            }

            //close the connections
            finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    }
                    catch (IOException e) {
                        Log.e(LOG_TAG,e.toString());
                    }
                }
            }
        }
        //return posterUrl;
        return movieArrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<MovieDTO> movies) {
        super.onPostExecute(movies);
       // mFragmentCallback.onTaskDone(movies);
    }


}
