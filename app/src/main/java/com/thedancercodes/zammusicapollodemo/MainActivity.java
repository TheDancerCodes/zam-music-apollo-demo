package com.thedancercodes.zammusicapollodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import javax.annotation.Nonnull;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // Variables for the TextViews.
    private TextView title1, title2, description1, description2;

    // String variables for TextView titles and descriptions
    private String t1, t2, d1, d2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title1 = findViewById(R.id.title1);
        title2 = findViewById(R.id.title2);
        description1 = findViewById(R.id.description1);
        description2 = findViewById(R.id.description2);


        // Reference getPosts methods
        getPosts();
    }

    // TODO: Method for getting Posts
    private void getPosts() {

        // Access Apollo Client & make a Query
        ZamApolloClient.getZamApolloClient()
                .query(AllPostsQuery.builder().build())
                .enqueue(new ApolloCall.Callback<AllPostsQuery.Data>() {

            /**
             * The onResponse contains the data from Graph.Cool
              * @param response
             */
            @Override
            public void onResponse(@Nonnull Response<AllPostsQuery.Data> response) {

                Log.d(TAG, "onResponse: " + response.data().allPosts().get(0).title());

                // Pass Data to the TextView Variables
                t1 = response.data().allPosts().get(0).title();
                t2 = response.data().allPosts().get(1).title();

                d1 = response.data().allPosts.get(0).description();
                d2 = response.data().allPosts.get(1).description();

                /*
                    *onResponse runs on the background thread &
                    * this means that we cannot modify our UI on the background thread.
                    *
                    * What we do to overcome this, is that we run it on the UI thread.
                 */
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        title1.setText(t1);
                        title2.setText(t2);
                        description1.setText(d1);
                        description2.setText(d2);
                    }
                });

            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {

            }
        });

    }
}
