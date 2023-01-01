package com.utkarsh.githubkotlin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailedActivity extends AppCompatActivity {

    private TextView loginTv,nameTv,descrTv,starTv,forkTv;
    private ImageView avatarImgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        loginTv = findViewById(R.id.ownerName);
        nameTv = findViewById(R.id.repoName);
        descrTv = findViewById(R.id.repoDesc);
        starTv = findViewById(R.id.repoStarCNt);
        forkTv = findViewById(R.id.repoForkCNt);
        avatarImgView = findViewById(R.id.avtarIv);

//        intent.putExtra("name",item.getName());
//        intent.putExtra("description",item.getDescription());
//        intent.putExtra("stargazers_count",item.getStargazers_count());
//        intent.putExtra("forks_count",item.getForks_count());

        String username = getIntent().getExtras().getString("login");
        String reponame = getIntent().getExtras().getString("name");
        String rdesc = getIntent().getExtras().getString("description");
        String strcnt = getIntent().getExtras().getString("stargazers_count");
        String frkcnt = getIntent().getExtras().getString("forks_count");
        String avatarUrl = getIntent().getExtras().getString("avatar_url");
//        String link = getIntent().getExtras().getString("html_url");

        loginTv.setText("Author: "+username);
        nameTv.setText("Repository Name: "+reponame);
        descrTv.setText("Description: "+rdesc);
        starTv.setText("Stars: "+strcnt);
        forkTv.setText("Forks: "+frkcnt);

//        Load avatar img
        Glide.with(this)
                .load(avatarUrl)
                .placeholder(R.drawable.loading)
                .into(avatarImgView);

//        Web view for readme file
//        https://github.com/JetBrains/.github/tree/main/profile/README.md
        WebView webView = findViewById(R.id.mywebView);

        String readmeUrl = "https://github.com/"+username+".github/tree/main/profile/README.md";
        // loading http://www.google.com url in the WebView.
//        webView.loadUrl(readmeUrl);
        webView.loadUrl("https://github.com/"+username+"/"+reponame);

        // this will enable the javascript.
        webView.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.setWebViewClient(new WebViewClient());

        getSupportActionBar().setTitle("Details Screen");

    }
}