// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2017/12/22 星期五 18:56:38
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   LoginJson.java

package com.job.json;


public class LoginJson
{

    private String loginEmail;
    private String loginPassword;
    private String loginRemenber[];

    public LoginJson(String loginEmail, String loginPassword, String loginRemenber[])
    {
        this.loginEmail = loginEmail;
        this.loginPassword = loginPassword;
        this.loginRemenber = loginRemenber;
    }

    public LoginJson()
    {
    }

    public String getLoginEmail()
    {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail)
    {
        this.loginEmail = loginEmail;
    }

    public String getLoginPassword()
    {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword)
    {
        this.loginPassword = loginPassword;
    }

    public String[] getLoginRemenber()
    {
        return loginRemenber;
    }

    public void setLoginRemenber(String loginRemenber[])
    {
        this.loginRemenber = loginRemenber;
    }
}
