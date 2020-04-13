package eltech.DM2020.BigNum;

public class MyResult {
    private BigPolinom first;
    private BigPolinom second;

    public MyResult(BigPolinom first, BigPolinom second) {
        this.first = first;
        this.second = second;
    }

    public BigPolinom getFirst() {
        return first;
    }

    public BigPolinom getSecond() {
        return second;
    }
}
