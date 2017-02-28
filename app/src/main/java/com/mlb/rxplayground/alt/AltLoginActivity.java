package com.mlb.rxplayground.alt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.mlb.rxplayground.MainActivity;
import com.mlb.rxplayground.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

public class AltLoginActivity extends AppCompatActivity {

    @BindView(R.id.view_login)
    LoginView loginView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        connectLoginView();
    }

    void connectLoginView() {
        getValidCredentialsStream()
                .distinctUntilChanged()
                .subscribe(validCredentials -> {
                    loginView.enableLogin(validCredentials);
                });

        loginView.loginClick()
                .subscribe(v -> {
                   startActivity(MainActivity.intent(AltLoginActivity.this));
                });
    }

    @NonNull
    Observable<Boolean> getValidCredentialsStream() {
        return Observable.combineLatest(
                loginView.username().map(this::validUserName),
                loginView.password().map(this::validPassword),
                (validEmail, validPassword) -> validEmail && validPassword
        );
    }

    boolean validPassword(String password) {
        return password != null && password.length() > 4;
    }

    boolean validUserName(String userName) {
        return userName != null && userName.length() > 5 && userName.contains("@");
    }

}
