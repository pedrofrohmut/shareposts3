package br.com.pedrofrohmut.shareposts3.util;

public final class DBNames
{
    // ## User ##
    public static final String USER_ID = "user.id";
    public static final String USER_NAME = "user.name";
    public static final String USER_EMAIL = "user.email";
    public static final String USER_PASSWORD = "user.password";
    public static final String USER_CREATED_AT = "user.created_at";

    // ## Post ##
    public static final String POST_ID = "post.id";
    public static final String POST_USER_ID = "post.user_id";
    public static final String POST_TITLE = "post.title";
    public static final String POST_BODY = "post.body";
    public static final String POST_CREATED_AT = "post.created_at";

    public static final String TABLE_POST = "shareposts.post";
    public static final String TABLE_USER = "shareposts.user";
}
