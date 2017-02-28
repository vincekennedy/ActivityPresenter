package com.mlb.rxplayground.traditional;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEvent;
import com.mlb.rxplayground.MainActivity;
import com.mlb.rxplayground.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;

public class LoginActivity extends AppCompatActivity implements LoginView {

    //Pretend this is dagger
    LoginPresenter presenter = new LoginPresenter();

    @BindView(R.id.edit_password)
    EditText password;

    @BindView(R.id.edit_username)
    EditText username;

    @BindView(R.id.btn_login)
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.startPresenting();
    }

    public Observable<String> password() {
        return RxTextView.afterTextChangeEvents(password)
                .map(TextViewAfterTextChangeEvent::toString);

    }

    public Observable<String> username() {
        return RxTextView.afterTextChangeEvents(username)
                .map(TextViewAfterTextChangeEvent::toString);
    }

    @OnClick(R.id.btn_login)
    public void loginClick(){
       presenter.onLoginClick();
    }

    @Override
    public void loginSuccessful() {
       startActivity(MainActivity.intent(this));
    }

    public void enableLogin(boolean enableLogin) {
        this.login.setEnabled(enableLogin);
    }
}
