package com.mlb.rxplayground.traditional;


import android.support.annotation.NonNull;

import rx.Observable;

public class LoginPresenter {

    LoginView loginView;

    public void initializePresenter(LoginView loginView){
        this.loginView = loginView;
    }

    public void startPresenting() {
        connectLoginView();
    }

    void connectLoginView() {
        getValidCredentialsStream()
                .distinctUntilChanged()
                .subscribe(validCredentials -> {
                    loginView.enableLogin(validCredentials);
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

    public void onLoginClick() {
        loginView.loginSuccessful();
    }
}
