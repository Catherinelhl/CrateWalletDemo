package io.catherine.cratewallet.tool;
/*
+--------------+---------------------------------
+ author       +   Catherine Liu
+--------------+---------------------------------
+ since        +   2019-05-07 15:54
+--------------+---------------------------------
+ projectName  +   CrateWalletDemo
+--------------+---------------------------------
+ packageName  +   io.catherine.cratewallet.tool
+--------------+---------------------------------
+ description  +  
+--------------+---------------------------------
+ version      +  
+--------------+---------------------------------
*/

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTool {
    public static final String PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9!@#$%^&*_]{8,16}$";

    public static boolean isValidatePassword(String password) {

        Pattern pattern = getPattern(PASSWORD);

        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    private static Pattern getPattern(String regex) {

        Pattern pattern = Pattern.compile(regex);

        return pattern;
    }


}
