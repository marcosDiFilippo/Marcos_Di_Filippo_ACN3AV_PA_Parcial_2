package session;

import model.User;

public class UserSession {
    private static User currentUser;
    
    public UserSession (User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static User getInstance() {
        return currentUser;
    }
    
    public static void closeSession() {
        currentUser = null;
    }
}
