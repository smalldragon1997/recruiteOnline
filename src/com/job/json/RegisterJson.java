// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2017/12/22 星期五 18:57:58
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   RegisterJson.java

package com.job.json;


public class RegisterJson
{
    private String registerUsername;
    private String registerEmail;
    private String registerPassword;
    private String registerPasswordAgain;
    
    public RegisterJson(String registerUsername, String registerEmail, String registerPassword, String registerPasswordAgain)
    {
        this.registerUsername = registerUsername;
        this.registerEmail = registerEmail;
        this.registerPassword = registerPassword;
        this.registerPasswordAgain = registerPasswordAgain;
    }

    public RegisterJson()
    {
    }

    public String getRegisterUsername()
    {
        return registerUsername;
    }

    public void setRegisterUsername(String registerUsername)
    {
        this.registerUsername = registerUsername;
    }

    public String getRegisterEmail()
    {
        return registerEmail;
    }

    public void setRegisterEmail(String registerEmail)
    {
        this.registerEmail = registerEmail;
    }

    public String getRegisterPassword()
    {
        return registerPassword;
    }

    public void setRegisterPassword(String registerPassword)
    {
        this.registerPassword = registerPassword;
    }

    public String getRegisterPasswordAgain()
    {
        return registerPasswordAgain;
    }

    public void setRegisterPasswordAgain(String registerPasswordAgain)
    {
        this.registerPasswordAgain = registerPasswordAgain;
    }
}
