package be.xplore.recruitment.domain.prospect;

public class ImportProspectsFailure {
    private String reason;
    private String input;

    public ImportProspectsFailure(String reason, String input) {
        this.reason = reason;
        this.input = input;
    }

    public String getReason() {
        return reason;
    }

    public String getInput() {
        return input;
    }
}
