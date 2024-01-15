package com.sku.myapplication.ui.login;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.sku.myapplication.MainActivity;
import com.sku.myapplication.R;
import com.sku.myapplication.network.ApiInterface;
import com.sku.myapplication.model.UserAccount;
import com.sku.myapplication.network.RetrofitClient;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(view -> performLogin());
    }

    private void performLogin() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<UserAccount> call = apiInterface.loginUser(new UserAccount(0, username, password, null, null));

        call.enqueue(new Callback<UserAccount>() {
            @Override
            public void onResponse(Call<UserAccount> call, Response<UserAccount> response) {
                if (response.isSuccessful()) {
                    // Login successful, redirect to MainActivity with user details
                    UserAccount userAccount = response.body();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("userId", userAccount.getUserId()); // Pass user ID to MainActivity
                    startActivity(intent);
                    finish(); // Finish LoginActivity
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<UserAccount> call, Throwable t) {
                // Handle failure
            }
        });
    }
}
