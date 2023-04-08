package ph.gov.bbop.user.model;

public enum Role {
    ADMIN,
    USER;

    public static Role of (String role) {
        return role.equalsIgnoreCase(ADMIN.name()) ? ADMIN : USER;
    }
}
