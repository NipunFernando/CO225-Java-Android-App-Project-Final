package com.ishanadeeparidma.bidcoin.CurrentAuctions;

import androidx.appcompat.app.AppCompatActivity;

import com.ishanadeeparidma.bidcoin.Bidding.BiddingActivity;
import com.ishanadeeparidma.bidcoin.Models.RunningBidResponse;
import com.ishanadeeparidma.bidcoin.Net.BidCoinAPIAccess;
import com.ishanadeeparidma.bidcoin.R;
import com.ishanadeeparidma.bidcoin.Repository.API_Repository;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCurrentAuctions extends AppCompatActivity {

    LinearLayout  LinearLayoutBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_current_auctions);

        //        get api reference
        BidCoinAPIAccess api = API_Repository.api;
        LinearLayoutBody = findViewById(R.id.LinearLayoutBody);

        api.getCurrentRunningBids().enqueue(new Callback<List<RunningBidResponse>>() {
            @Override
            public void onResponse(Call<List<RunningBidResponse>> call, Response<List<RunningBidResponse>> response) {
                assert response.body() != null;
                if (response.body().size() == 0)
                    Toast.makeText(AllCurrentAuctions.this, "No currently running bids", Toast.LENGTH_SHORT).show();
                else {
                    for (RunningBidResponse eachBid : response.body()) {
                        TextView auctionName = new TextView(AllCurrentAuctions.this);
                        auctionName.setText("AUCTION " + eachBid.getCryptoName()); // Just number
                        LinearLayoutBody.addView(auctionName);

                        TextView startAt = new TextView(AllCurrentAuctions.this);
                        startAt.setText("START " + eachBid.getStartDate());  // Add Starting time from db
                        LinearLayoutBody.addView(startAt);

                        TextView endAt = new TextView(AllCurrentAuctions.this);
                        endAt.setText("END " + eachBid.getEndDate());  // Add Ending time from db
                        LinearLayoutBody.addView(endAt);

                        // Button also
                        Button SelectButton = new Button(AllCurrentAuctions.this);
                        SelectButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i = new Intent(AllCurrentAuctions.this, BiddingActivity.class);
                                i.putExtra("cryptoName",eachBid.getCryptoName());
                                startActivity(i);
                            }
                        });
                        SelectButton.setText("SELECT");
                        LinearLayoutBody.addView(SelectButton);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RunningBidResponse>> call, Throwable t) {
                Toast.makeText(AllCurrentAuctions.this,"Connection to server lost", Toast.LENGTH_SHORT).show();
                Log.d("CONNECTION LOST", t.toString());
            }
        });

//
//        //Adding 2 TextViews
//        for (int i = 1; i <= 5; i++) {
//            TextView auctionName = new TextView(this);
//            auctionName.setText("AUCTION " + String.valueOf(i)); // Just number
//            LinearLayoutBody.addView(auctionName);
//
//            TextView startAt = new TextView(this);
//            startAt.setText("START " + String.valueOf(i));  // Add Starting time from db
//            LinearLayoutBody.addView(startAt);
//
//            TextView endAt = new TextView(this);
//            endAt.setText("END " + String.valueOf(i));  // Add Ending time from db
//            LinearLayoutBody.addView(endAt);
//
//            // Button also
//            Button SelectButton = new Button(this);
//            SelectButton.setText("SELECT");
//            LinearLayoutBody.addView(SelectButton);
//
//        }
    }
}