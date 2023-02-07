package com.musala.service.base;

import com.musala.model.base.JpaModelBase;
import com.musala.util.ThreadContext;
import com.musala.util.jpa.PagedContent;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

public interface JpaCrudService<T extends JpaModelBase> {

	String JPA_ACTIVE_OPERATION = "JPA_ACTIVE_OPERATION";

	static ServiceMethod getJpaOperation() {

		if (!ThreadContext.contains(JPA_ACTIVE_OPERATION)) {
			return null;
		}

		return (ServiceMethod) ThreadContext.get(JPA_ACTIVE_OPERATION);
	}

	void delete(String id);

	boolean exists(T item);

	boolean exists(String id);

	PagedContent<T> get();

	T get(String id);

	PagingAndSortingRepository<T, String> getRepository();

	void merge(Collection<T> from);

	void merge(Collection<T> from, Collection<T> into);

	T post(T item);

	T put(T item);

	enum ServiceMethod {
		POST,
		PUT,
		GET,
		DELETE
	}
}
