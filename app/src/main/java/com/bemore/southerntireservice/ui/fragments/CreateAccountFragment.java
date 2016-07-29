package com.bemore.southerntireservice.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bemore.southerntireservice.MainActivity;
import com.bemore.southerntireservice.R;
import com.bemore.southerntireservice.model.User;
import com.parse.ParseException;
import com.parse.SignUpCallback;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Cody on 5/11/16.
 */
public class CreateAccountFragment extends BaseFragment {

    @Bind(R.id.email)
    protected EditText emailView;

//    @Bind(R.id.emailInputLayout)
//    protected TextInputLayout emailInputLayout;

    @Bind(R.id.password)
    protected EditText passwordView;

//    @Bind(R.id.passwordInputLayout)
//    protected TextInputLayout passwordInputLayout;

    @Bind(R.id.firstName)
    protected EditText firstNameView;

    @Bind(R.id.lastName)
    protected EditText lastNameView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.createAccountButton)
    public void onCreateAccountClick(Button view) {

        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        if (TextUtils.isEmpty(email)) {
//            emailInputLayout.setError("Email field cannot be empty");
        }
        else if (TextUtils.isEmpty(password)) {
//            passwordInputLayout.setError("Password field cannot be empty");
        }
        else {
            User user = new User();

            user.setEmail(email);
            user.setPassword(password);
            user.setUsername(email);
            user.setFirstName(firstNameView.getText().toString());
            user.setLastName(lastNameView.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_create_account;
    }
}
