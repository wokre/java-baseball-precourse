package baseball;

/**
 * Created By bohyun on 2021/10/01
 */
public enum BaseBallResult {
    NOTHING("Nothing"),
    BALL("볼"),
    STRIKE("스트라이크"),
    PASS("");

    private String value;

    public String getValue() {
        return value;
    }

    BaseBallResult(String value) {
        this.value = value;
    }
}
