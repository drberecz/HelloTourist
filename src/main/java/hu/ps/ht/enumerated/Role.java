package hu.ps.ht.enumerated;

public class Role {

    public static final String NOT_AUTHORIZED = "guest";
    public static final String GUIDE = "guide";
    public static final String TRAVELER = "traveler";
    public static final String ADMIN = "admin";

    public static final String HTTP_SESSION_ATTR_USERROLE = "roleOfUser";
    public static final String HTTP_SESSION_ATTR_ANONYMOUS = "anonymousRole";
    public static final String HTTP_SESSION_ATTR_IMAGELINK = "imageLink";

    public static final String DEFAULT_AVATAR_URL = "./assets/avatar.png";
    public static final String DEFAULT_ADMIN_AVATAR_URL = "./assets/admin.png";
    public static final String UPLOAD_DIR = "img";

}
