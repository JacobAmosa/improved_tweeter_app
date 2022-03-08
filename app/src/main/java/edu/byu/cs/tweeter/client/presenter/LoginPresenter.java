package edu.byu.cs.tweeter.client.presenter;

import android.widget.EditText;

import edu.byu.cs.tweeter.client.model.service.UserService;

public class LoginPresenter extends AuthPresenter{
    private UserService userService;

    public LoginPresenter(View view) {
        super(view);
        this.userService = new UserService();
    }

    public void loginUser(String userAlias, String password) {
        userService.loginUserTask(userAlias, password, new GetAuthObserver());
    }

    public void validateLogin(EditText alias, EditText password) {
        if (alias.getText().charAt(0) != '@') {
            throw new IllegalArgumentException("Alias must begin with @.");
        }
        if (alias.getText().length() < 2) {
            throw new IllegalArgumentException("Alias must contain 1 or more characters after the @.");
        }
        if (password.getText().length() == 0) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
    }

}
