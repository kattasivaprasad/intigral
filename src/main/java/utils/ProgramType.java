package utils;

public enum ProgramType {
    EPISODE("episode"),
    MOVIE("movie"),
    SERIES("series"),
    SEASON("season");

    private final String programType;

    private ProgramType(String programType) {
        this.programType = programType;
    }
}
