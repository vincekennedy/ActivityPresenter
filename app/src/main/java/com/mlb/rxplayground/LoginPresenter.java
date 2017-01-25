package com.mlb.rxplayground;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.subjects.PublishSubject;

public class LoginPresenter extends AppCompatActivity {

    @BindView(R.id.view_logi)
    LoginView view;

    Observable<Boolean> validCredentials = PublishSubject.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        super.onStart();

        validCredentials = Observable.combineLatest(
                view.username().map(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s != null && s.length() > 5 && s.contains("@");
                    }
                }),
                view.password().map(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s != null && s.length() > 4;
                    }
                }),
                new Func2<Boolean, Boolean, Boolean>() {
                    @Override
                    public Boolean call(Boolean validEmail, Boolean validPassword) {
                        return validEmail && validPassword;
                    }
                }
        );


        validCredentials.subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean validCredentials) {
                view.enableLogin(validCredentials);
            }
        });
    }
}
