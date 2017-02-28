package com.mlb.rxplayground.alt;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEvent;
import com.mlb.rxplayground.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;


public class LoginView extends LinearLayout {

    @BindView(R.id.edit_password)
    EditText password;

    @BindView(R.id.edit_username)
    EditText username;

    @BindView(R.id.btn_login)
    Button login;

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_login, this);
        ButterKnife.bind(this);
    }

    public Observable<String> password() {
        return RxTextView.afterTextChangeEvents(password)
                .map(TextViewAfterTextChangeEvent::toString);

    }

    public Observable<String> username(){
        return RxTextView.afterTextChangeEvents(username)
                .map(TextViewAfterTextChangeEvent::toString);
    }

    public Observable<Void> loginClick(){
        return RxView.clicks(login);
    }

    public void enableLogin(boolean enableLogin){
        this.login.setEnabled(enableLogin);
    }

}
