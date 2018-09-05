package br.com.pedrofrohmut.shareposts3.util;

public final class DBNames
{
    // ## User ##
    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_CREATED_AT = "created_at";

    // ## Post ##
    public static final String POST_ID = "id";
    public static final String POST_USER_ID = "user_id";
    public static final String POST_TITLE = "title";
    public static final String POST_BODY = "body";
    public static final String POST_CREATED_AT = "created_at";

    public static final String TABLE_POST = "shareposts.post";
    public static final String TABLE_USER = "shareposts.user";
}
