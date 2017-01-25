package com.mlb.rxplayground;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEvent;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Func1;


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
                .map(new Func1<TextViewAfterTextChangeEvent, String>() {
                    @Override
                    public String call(TextViewAfterTextChangeEvent textChangeEvent) {
                        return textChangeEvent.toString();
                    }
                });

    }

    public Observable<String> username(){
        return RxTextView.afterTextChangeEvents(username)
                .map(new Func1<TextViewAfterTextChangeEvent, String>() {
                    @Override
                    public String call(TextViewAfterTextChangeEvent textChangeEvent) {
                        return textChangeEvent.toString();
                    }
                });
    }

    public void enableLogin(boolean enableLogin){
        this.login.setEnabled(enableLogin);
    }

}
