package edu.byu.cs.tweeter.client.presenter;

public abstract class Presenter<T> {
    protected T view;

    public interface View {
        void displayErrorMessage(String message);
    }

}
