package ph.gov.bbop.application.model;

public enum ApplicationType {
    RESIDENCY("R", "Certificate of Residency"),
    INDIGENCY("I", "Certificate of Indigency"),
    CLEARANCE("C", "Barangay Clearance");

    private final String code;
    private final String description;

    private ApplicationType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }
}
