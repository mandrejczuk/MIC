package pz1.MIC.interfaces;

import org.springframework.data.domain.Page;

import java.util.List;

public interface ManagementInterface<T> {
    T add(T entity) ;

    void delete(Long id) ;

    T show(Long id) ;

    Page<T> showAll(int page, int size);

    List<T> getAll();
}
