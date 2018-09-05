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
    public static final String USER_INDEX = "/user/index";
    public static final String USER_LOGOUT = "/user/logout";
    public static final String REDIRECT_USER_LOGIN = "redirect:" + USER_LOGIN;
    public static final String USER_PROFILE = "/user/profile";

    // ## Post Controller ##
    public static final String POST_INDEX = "/post/index";
    public static final String POST_ADD = "/post/add";
    public static final String POST_SHOW = "/post/show";
    public static final String POST_EDIT = "/post/edit";
    public static final String POST_DELETE = "/post/delete";
    public static final String REDIRECT_POST_INDEX = "redirect:" + POST_INDEX;
}
