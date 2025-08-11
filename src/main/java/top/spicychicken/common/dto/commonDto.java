package top.spicychicken.common.dto;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import lombok.Data;

@Data
public class commonDto<T> {
    private T entity;
    private Integer pageNumber;
    private Integer pageSize;
    private List<Order> orders;

    public Example<T> toExample() {
        return Example.of(entity);
    }

    public Pageable toPageRequest() {
        return PageRequest.of(pageNumber, pageSize, Sort.by(orders));
    }
}
