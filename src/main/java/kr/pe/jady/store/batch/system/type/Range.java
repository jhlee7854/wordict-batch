package kr.pe.jady.store.batch.system.type;

/**
 * Created by jhlee7854 on 2016. 11. 9..
 */
public class Range<T> {
    private T from;
    private T to;

    public T getFrom() {
        return from;
    }

    public void setFrom(T from) {
        this.from = from;
    }

    public T getTo() {
        return to;
    }

    public void setTo(T to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Range{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
