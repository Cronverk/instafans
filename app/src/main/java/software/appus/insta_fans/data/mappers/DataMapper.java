package software.appus.insta_fans.data.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class DataMapper<T, E> {
    public abstract T transform(E entity);

    public List<T> transform(Collection<E> entityCollection) {
        final List<T> list = new ArrayList<>();
        for (E entity : entityCollection) {
            final T model = transform(entity);
            if (model != null) {
                list.add(model);
            }
        }
        return list;
    }
}