package com.thedancercodes.zammusicapollodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import javax.annotation.Nonnull;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {

            }
        });

    }
}
