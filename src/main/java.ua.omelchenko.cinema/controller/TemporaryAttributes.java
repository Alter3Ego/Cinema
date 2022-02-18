package controller;

public class TemporaryAttributes {
    //Login error
    private boolean emailError;
    //Main page
    private Integer mainNextPage;
    private Integer mainPreviousPage;
    //SignUp errors
    private boolean signUpDataError;
    private boolean signUpEmailError;

    public TemporaryAttributes() {
    }

    public boolean getEmailError() {
        return emailError;
    }

    public void setEmailError(boolean emailError) {
        this.emailError = emailError;
    }

    public Integer getMainNextPage() {
        return mainNextPage;
    }

    public void setMainNextPage(Integer mainNextPage) {
        this.mainNextPage = mainNextPage;
    }

    public Integer getMainPreviousPage() {
        return mainPreviousPage;
    }

    public void setMainPreviousPage(Integer mainPreviousPage) {
        this.mainPreviousPage = mainPreviousPage;
    }

    public boolean getSignUpDataError() {
        return signUpDataError;
    }

    public void setSignUpDataError(boolean signUpDataError) {
        this.signUpDataError = signUpDataError;
    }

    public boolean getSignUpEmailError() {
        return signUpEmailError;
    }

    public void setSignUpEmailError(boolean signUpEmailError) {
        this.signUpEmailError = signUpEmailError;
    }
}
