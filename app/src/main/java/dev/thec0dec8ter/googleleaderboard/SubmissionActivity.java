package dev.thec0dec8ter.googleleaderboard;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity {

    private ImageView backBtn;
    private EditText editFirstName;
    private EditText editLastName;
    private EditText editEmail;
    private EditText editProjectLink;
    private Button submitBtn;

    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        backBtn = findViewById(R.id.back_btn);
        editFirstName = findViewById(R.id.edit_firstname);
        editLastName = findViewById(R.id.edit_lastname);
        editEmail = findViewById(R.id.edit_email);
        editProjectLink = findViewById(R.id.edit_projectlink);
        submitBtn = findViewById(R.id.submit_btn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });


    }

    private void showCustomDialog(){
        alertDialog = new AlertDialog.Builder(SubmissionActivity.this).create();
        View submissionView = getLayoutInflater().inflate(R.layout.submission_alert, null, false);
        ImageButton cancelBtn = submissionView.findViewById(R.id.cancel_btn);
        Button confirmBtn = submissionView.findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitProject();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(submissionView);
        alertDialog.show();
    }

    private void submitProject(){
        String firstName = editFirstName.getText().toString();
        String lastName = editLastName.getText().toString();
        String emailAddress = editEmail.getText().toString();
        String projectLink = editProjectLink.getText().toString();

        SubmissionService submissionService = RetrofitClientInstance.getRetroSubmissionInstance().create(SubmissionService.class);
        Call<Void> call = submissionService.submitForm(firstName, lastName, emailAddress, projectLink);
        call.enqueue(new Callback<Void>() {
            View responseView;
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    responseView = getLayoutInflater().inflate(R.layout.submission_success, null, false);
                    alertDialog.setContentView(responseView);
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                responseView = getLayoutInflater().inflate(R.layout.submission_failed, null, false);
                alertDialog.setContentView(responseView);
            }
        });
    }

}