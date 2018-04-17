package com.thedancercodes.zammusicapollodemo;


import com.apollographql.apollo.ApolloClient;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by TheDancerCodes on 17/04/2018.
 */

public class ZamApolloClient {

    // TODO: Add URL from Graph.Cool
    private static final String BASE_URL =
            "https://api.graph.cool/simple/v1/cjg2bwy5h09jb012568hn1agy";

    // TODO: Create Apollo Client
    private static ApolloClient zamApolloClient;

    public static ApolloClient getZamApolloClient() {

        // Intercepts our request and logs it
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Build the OkHttp Client
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        // Build the Apollo Client
        zamApolloClient = ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttpClient)
                .build();

        return zamApolloClient;

    }


}
