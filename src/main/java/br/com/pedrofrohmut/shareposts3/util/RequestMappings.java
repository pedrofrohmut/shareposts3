package br.com.pedrofrohmut.shareposts3.util;

public final class RequestMappings
{
    private RequestMappings() { }

    // ## Home Controller ##
    public static final String HOME_INDEX = "/";
    public static final String HOME_ABOUT = "/about";
    public static final String REDIRECT_HOME_INDEX = "redirect:" + HOME_INDEX;
    
    // ## User Controller ##
    public static final String USER_REGISTER = "/user/register";
    public static final String USER_LOGIN = "/user/login";
}
