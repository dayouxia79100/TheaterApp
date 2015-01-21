package com.ming.dayouxia.theaterapp.model;

import org.json.simple.JSONObject;
/**
 * Created by Administrator on 2014/10/19.
 */
public class Helper {
    //Login Page
    public static JSONObject sendLoginAuthentication(String emailaddress, String password)
    {
        JSONObject obj = new JSONObject();
        return obj;
    }
    //Forgot Password Page
    public static JSONObject sendPasswordToEmail(String emailAddress)
    {
        JSONObject obj = new JSONObject();
        return obj;
    }
    //Sign Up Page
    public static JSONObject sendNewUser(String emailAddress, String firstName,String lastName, String password)
    {
        JSONObject obj = new JSONObject();
        return obj;
    }
    //Main Page
    public static JSONObject getMovies()
    {
        JSONObject obj = new JSONObject();
        return obj;
    }
    public static JSONObject getMovieDetail(String movieID)
    {
        JSONObject obj = new JSONObject();
        return obj;
    }
    public static JSONObject getQuestion(String image)
    {
        JSONObject obj = new JSONObject();
        return obj;
    }

    public static JSONObject sendAnswer(String questionID, String choice)
    {
        JSONObject obj = new JSONObject();
        return obj;
    }

    public static JSONObject updateUserPoints()
    {
        JSONObject obj = new JSONObject();
        return obj;
    }

    // News & Event Page
    public static JSONObject getNewsAndEvents()
    {
        JSONObject obj = new JSONObject();
        return obj;
    }


    // Profile Page
    public static JSONObject updateProfile()
    {
        JSONObject obj = new JSONObject();
        return obj;
    }

   // Redeem Page
    public static JSONObject getRedeemItemsXML()
    {
        JSONObject obj = new JSONObject();
        return obj;

    }

    public static JSONObject updateRedeem(String emailAddress, String firstName,String lastName, String password)
    {
        JSONObject obj = new JSONObject();
        return obj;
    }
}
