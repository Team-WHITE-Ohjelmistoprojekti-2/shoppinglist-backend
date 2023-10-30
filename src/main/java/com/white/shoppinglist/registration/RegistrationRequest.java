package com.white.shoppinglist.registration;

public class RegistrationRequest {
    private final String username;
    private final String passwordHash;
    private final String description;

    public RegistrationRequest(String username, String passwordHash, String description) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationRequest that = (RegistrationRequest) o;
        return username.equals(that.username) &&
                passwordHash.equals(that.passwordHash) &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + passwordHash.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
