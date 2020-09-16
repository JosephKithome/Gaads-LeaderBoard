package com.sejjoh.gads_leaderboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.sejjoh.gads_leaderboard.Common.SubmitApi;
import com.sejjoh.gads_leaderboard.Common.RetrofitClient;
import com.sejjoh.gads_leaderboard.Models.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SubmitActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
       final EditText mFirstName = findViewById(R.id.txt_FirstName);
       final EditText mLastName = findViewById(R.id.txt_LastName);
       final EditText mEmail = findViewById(R.id.txt_email);
       final EditText mGithubLink = findViewById(R.id.txt_github);
       final Button btnSubmit = findViewById(R.id.submit_area);
       btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String firstName = mFirstName.getText().toString().trim();
                final String lastName = mLastName.getText().toString().trim();
                final String eMail = mEmail.getText().toString().trim();
                final  String projectLink = mGithubLink.getText().toString().trim();

                if(!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) &&
                        !TextUtils.isEmpty(eMail) && !TextUtils.isEmpty(projectLink)){

                    final AlertDialog.Builder dialog = new AlertDialog.Builder(SubmitActivity.this);
                    LayoutInflater inflater = getLayoutInflater();

                    View sview = inflater.inflate(R.layout.alert, null);
                    Button btnYes = sview.findViewById(R.id.btn_yes);
                    dialog.setView(sview);
                    ImageView imgClose = sview.findViewById(R.id.img_cancel);
                    final Dialog dialogs = dialog.create();

                    dialogs.show();
                    btnYes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sendPost(firstName,lastName,eMail,projectLink);
                            dialogs.cancel();
                        }
                    });
                    imgClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogs.cancel();
                        }
                    });
                }
                else{
                    Toast.makeText(SubmitActivity.this,"Please fill all required fields",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    public void sendPost(String firstName,String lastName,String eMail,String linkToProject){
        SubmitApi submitApi = RetrofitClient.getRetrofitInstance()
                .create(SubmitApi.class);
        Call<Void> call = submitApi.postSubmission(firstName,lastName,eMail,linkToProject);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful())
                    showSuccessDialog();

                else
                    showFailureDialog();
                System.out.println(response.toString());


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    private void showFailureDialog() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.create();
        View layout = getLayoutInflater().inflate(R.layout.fail,null);
        builder.setView(layout);
        AlertDialog dialog= builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void showSuccessDialog() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.create();
        View layout = getLayoutInflater().inflate(R.layout.success,null);
        builder.setView(layout);
        AlertDialog dialog= builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    }
