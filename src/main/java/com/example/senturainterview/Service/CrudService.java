package com.example.senturainterview.Service;

import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;

@Service
public class CrudService {
    private static final String base_url = "https://8015b5dbc0724d38882ac90397c27649.weavy.io";
    private static final String token = "wys_hMWpXdekxcn9Gc8Ioah3azOllzUZ7l3HN9yB";
    private static final OkHttpClient client = new OkHttpClient();

    public String createUser(String uid,String name){

    }

}
