package com.mlb.rxplayground.traditional;

import rx.Observable;

/**
 * Created by vincekennedy on 2/24/17.
 */

public interface LoginView {
    void enableLogin(boolean enableLogin);

    Observable<String> username();

    Observable<String> password();

    void loginSuccessful();
}
