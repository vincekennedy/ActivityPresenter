package com.mlb.rxplayground.alt;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by vincekennedy on 2/24/17.
 */
public class AltLoginActivityTest {

    private AltLoginActivity presenter;

    @Mock
    private LoginView mockLoginView;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new AltLoginActivity();
        presenter.loginView = mockLoginView;
    }

    @Test
    public void testValidPasswordReturnsTrueForValidLength() throws Exception {
        Assert.assertTrue(presenter.validPassword("password"));
        Assert.assertFalse(presenter.validPassword("pas"));
    }

    @Test
    public void testLoginView() throws Exception {
        when(mockLoginView.password()).thenReturn(Observable.just("password"));
        when(mockLoginView.username()).thenReturn(Observable.just("username@mlb.com"));

        TestSubscriber testSubscriber = new TestSubscriber();
        presenter.getValidCredentialsStream().subscribe(testSubscriber);

        testSubscriber.assertValue(true);
        testSubscriber.assertCompleted();
    }

    @Test
    public void testSubscribe() throws Exception {
        when(mockLoginView.password()).thenReturn(Observable.just("pa", "s", "ssword"));
        when(mockLoginView.username()).thenReturn(Observable.just("username@mlb.com"));

        presenter.connectLoginView();

        verify(mockLoginView).enableLogin(false);
        verify(mockLoginView).enableLogin(true);
    }
}