package controller;

import Entity.Film;

import java.util.HashMap;
import java.util.List;

/**
 * Class for passing request attributes via POST
 */
public class TemporaryAttributes {
    //Login error
    private boolean emailError;
    //Main page
    private Integer mainNextPage;
    private Integer mainPreviousPage;
    //SignUp errors
    private boolean signUpDataError;
    private boolean signUpEmailError;
    //User errors
    private boolean errorUpdateSum;
    //Session errors
    private boolean errorLogInSession;
    private boolean errorBalance;
    private boolean operationError;
    //SessionAdd
    private List<Film> sessionAddPage;
    private boolean successfulAdd;
    private boolean errorDB;

    private HashMap<Integer, Integer> places;

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

    public boolean getErrorUpdateSum() {
        return errorUpdateSum;
    }

    public void setErrorUpdateSum(boolean errorUpdateSum) {
        this.errorUpdateSum = errorUpdateSum;
    }

    public HashMap<Integer, Integer> getPlaces() {
        return places;
    }

    public void setPlaces(HashMap<Integer, Integer> places) {
        this.places = places;
    }

    public boolean getErrorBalance() {
        return errorBalance;
    }

    public void setErrorBalance(boolean errorBalance) {
        this.errorBalance = errorBalance;
    }

    public boolean getErrorLogInSession() {
        return errorLogInSession;
    }

    public void setErrorLogInSession(boolean errorLogInSession) {
        this.errorLogInSession = errorLogInSession;
    }

    public boolean isOperationError() {
        return operationError;
    }

    public void setOperationError(boolean operationError) {
        this.operationError = operationError;
    }

    public List<Film> getSessionAddPage() {
        return sessionAddPage;
    }

    public void setSessionAddPage(List<Film> sessionAddPage) {
        this.sessionAddPage = sessionAddPage;
    }

    public boolean getSuccessfulAdd() {
        return successfulAdd;
    }

    public void setSuccessfulAdd(boolean successfulAdd) {
        this.successfulAdd = successfulAdd;
    }

    public boolean getErrorDB() {
        return errorDB;
    }

    public void setErrorDB(boolean errorDB) {
        this.errorDB = errorDB;
    }
}
