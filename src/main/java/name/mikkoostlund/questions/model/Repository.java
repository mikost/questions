package name.mikkoostlund.questions.model;

import java.util.List;

public interface Repository<T, SPEC> {
    void add(T item);

    void update(T item);

    void remove(T item);

    List<T> query(SPEC specification);
}