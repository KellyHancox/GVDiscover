package com.example.gvdiscoverapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

/**
 * A login screen that offers login via email/password.
 *
 * @author Jesse David
 */
public class LoginActivity extends AppCompatActivity {
    // UI references.
    /**
     * mEmailView is the view associated with the autocomplete text field.
     */
    private AutoCompleteTextView mEmailView;
    /**
     * mPasswordView is the view associated with the password.
     */
    private EditText mPasswordView;

    /**
     * mProgressView is the view associated with showing the progress.
     */
    private View mProgressView;

    /**
    * this is used for log 
    */
    private static final String TAG = "LoginActivity";

    /**
     * mLoginFormView is the view associated with the login form.
     */
    private View mLoginFormView;

    /**
     * This simply creates the page.
     *
     * @param savedInstanceState see AppCompatActivity.
     * */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(final TextView textView, final int id,
                                          final KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE
                        || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button)
                findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;

        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login    attempt.
            showProgress(true);

            //Loading model
            try {
               File directory = LoginActivity.this.getFilesDir();
               Model.getInstance().load(directory, email);
               System.out.println(directory.getAbsolutePath());
               Log.d(TAG, "Model successfully loaded");
            }
            catch (ClassNotFoundException e){
                Log.d(TAG, "ClassNotFoundException has occured...");
                e.printStackTrace(); }
            catch (IOException e) {
                Log.d(TAG,"IOException has occured...");
                e.printStackTrace();
            }
            catch (NumberFormatException e){
                Log.d(TAG,"Model does not exist yet.");
            }
            catch (Exception e) {
                Log.d(TAG,"Unknown Exception has occured...");
                e.printStackTrace();
            }
            startActivity(new Intent(LoginActivity.this, HomeScreen.class));
            finish();
        }
    }

    /**
     * @param email inputed the email
     * @return sends the boolean value if the email is valid.
     */
    private boolean isEmailValid(final String email) {
        //determines if the inputed email is one of the following
        return email.contains("@mail.gvsu.edu");
    }

    /**
     *
     * @param password inputed password.
     * @return Returns true if the password is longer than 7 characters.
     */
    private boolean isPasswordValid(final String password) {
        return password.length() > 7;
    }

    /**
     * Shows the progress UI and hides the login form.
     *
     * @param show set to true to show the progress bar
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(
                    android.R.integer.config_shortAnimTime);

            if (show) {
                mLoginFormView.setVisibility(View.GONE);
            } else {
                mLoginFormView.setVisibility(View.VISIBLE);
            }
            if (show) {
                mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                        0).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(final Animator animation) {
                        if (show) {
                            mLoginFormView.setVisibility(View.GONE);
                        } else {
                            mLoginFormView.setVisibility(View.VISIBLE);
                        }
                    }
                });
            } else {
                mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                        1).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(final Animator animation) {
                        if (show) {
                            mLoginFormView.setVisibility(View.GONE);
                        } else {
                            mLoginFormView.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }

            if (show) {
                mProgressView.setVisibility(View.VISIBLE);
            } else {
                mProgressView.setVisibility(View.GONE);
            }
            if (show) {
                mProgressView.animate().setDuration(shortAnimTime).alpha(
                        1).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(final Animator animation) {
                        if (show) {
                            mProgressView.setVisibility(View.VISIBLE);
                        } else {
                            mProgressView.setVisibility(View.GONE);
                        }
                    }
                });
            } else {
                mProgressView.animate().setDuration(shortAnimTime).alpha(
                        0).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(final Animator animation) {
                        if (show) {
                            mProgressView.setVisibility(View.VISIBLE);
                        } else {
                            mProgressView.setVisibility(View.GONE);
                        }
                    }
                });
            }
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            if (show) {
                mProgressView.setVisibility(View.VISIBLE);
            } else {
                mProgressView.setVisibility(View.GONE);
            }
            if (show) {
                mLoginFormView.setVisibility(View.GONE);
            } else {
                mLoginFormView.setVisibility(View.VISIBLE);
            }
        }
    }
}

