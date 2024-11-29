package Model;

public class Session {
    private static Long loggedInUserID;

    public static Long getLoggedInUserID() {
        return loggedInUserID;
    }

    public static void setLoggedInUser(Long loggedInUserId) {
        loggedInUserID = loggedInUserId;
    }
}
