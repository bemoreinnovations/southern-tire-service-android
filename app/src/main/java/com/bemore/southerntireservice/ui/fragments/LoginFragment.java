package com.bemore.southerntireservice.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bemore.southerntireservice.MainActivity;
import com.bemore.southerntireservice.R;
import com.bemore.southerntireservice.model.User;
import com.bemore.southerntireservice.utils.Keyboard;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Cody on 5/11/16.
 */
public class LoginFragment extends BaseFragment {

    @Bind(R.id.loginView)
    protected View loginView;

    @Bind(R.id.progressBar)
    protected ProgressBar progressBar;

    @Bind(R.id.email)
    protected EditText emailView;

    @Bind(R.id.emailInputLayout)
    protected TextInputLayout emailInputLayout;

    @Bind(R.id.password)
    protected EditText passwordView;

    @Bind(R.id.passwordInputLayout)
    protected TextInputLayout passwordInputLayout;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @OnClick(R.id.loginButton)
    public void onLoginClick(Button view) {

        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        Keyboard.dismiss(getView());

        if (TextUtils.isEmpty(email)) {
            emailInputLayout.setError("Email field cannot be empty");
        }
        else if (TextUtils.isEmpty(password)) {
            passwordInputLayout.setError("Password field cannot be empty");
        }
        else {

            showProgress();
            User.logInInBackground(email, password, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (e == null) {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        hideProgress();
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        User user = new User();
    }

    private void showProgress() {
        loginView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        loginView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.createAccountButton)
    public void showCreateAccount(Button view) {
        showFragmentWithSlidingTransition(new CreateAccountFragment(), true);
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_login;
    }
}
