package ru.progwards.java1.lessons.datetime;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    private Map<Integer, UserSession> sessions;
    private int sessionValid;

    public SessionManager(int sessionValid) {
        this.sessionValid = sessionValid;
        this.sessions = new HashMap<>();
    }

    public void add(UserSession userSession) {
        sessions.putIfAbsent(userSession.getSessionHandle(), userSession);
    }

    public UserSession find(String userName) {
        Collection<UserSession> userSessionsCollection = sessions.values();
        for (UserSession userSession : userSessionsCollection) {
            if (userName.equals(userSession.getUserName())) {
                if ((userSession.getLastAccess() + sessionValid) <= (int) Instant.now().getEpochSecond()) {
                    return null;
                } else {
                    return userSession;
                }
            }
        }
        return null;
    }

    public UserSession get(int sessionHandle) {
        if (!sessions.containsKey(sessionHandle))
            return null;
        UserSession userSession = sessions.get(sessionHandle);
        if ((userSession.getLastAccess() + sessionValid) <= (int) Instant.now().getEpochSecond())
            return null;
        return userSession;
    }

    public void delete(int sessionHandle) {
        sessions.remove(sessionHandle);
    }

    public void deleteExpired() {
        Collection<UserSession> userSessionsCollection = sessions.values();
        for (UserSession userSession : userSessionsCollection) {
            if ((userSession.getLastAccess() + sessionValid) <= (int) Instant.now().getEpochSecond()) {
                userSessionsCollection.remove(userSession);
            }
        }
    }
}