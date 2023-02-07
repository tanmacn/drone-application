package com.musala.service.base;


import com.musala.exception.ValidationException;
import com.musala.model.base.JpaModelBase;
import com.musala.repository.base.JpaCrudRepository;
import com.musala.util.ThreadContext;
import com.musala.util.jpa.PagedContent;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.util.Collection;

import static com.musala.service.base.JpaCrudService.ServiceMethod.*;
import static org.springframework.util.CollectionUtils.isEmpty;


public abstract class AbstractJpaCrudService<T extends JpaModelBase> implements JpaCrudService<T> {

	@Override
	public void delete(String id) {

		try {
			jpaOperation(DELETE);
			T item = get(id);
			if (item == null) {
				throw new EntityNotFoundException(id);
			}

			item.setUpdated(ZonedDateTime.now());
			item.setDeleted(ZonedDateTime.now());
			save(item);
		} finally {
			jpaOperation();
		}
	}

	public boolean exists(T item) {

		return exists(item.getId());
	}

	public boolean exists(String id) {

		if (StringUtils.isEmpty(id)) {
			return false;
		}

		return getRepository().existsById(id);
	}

	@Override
	public PagedContent<T> get() {

		try {
			jpaOperation(GET);
			return new PagedContent<>(getRepository().findAll());
		} finally {
			jpaOperation();
		}

	}

	@Override
	public T get(String id) {

		if (id == null) {
			return null;
		}

		return getRepository().findById(id).get();
	}

	@Override
	public abstract JpaCrudRepository<T> getRepository();

	private void jpaOperation(ServiceMethod method) {

		ThreadContext.add(JpaCrudService.JPA_ACTIVE_OPERATION, method);
	}

	private void jpaOperation() {

		ThreadContext.delete(JpaCrudService.JPA_ACTIVE_OPERATION);
	}

	@Override
	public void merge(Collection<T> from) {

		merge(from, null);
	}

	@Override
	public void merge(Collection<T> from, Collection<T> into) {

		if (!isEmpty(from)) {
			for (T item : from) {
				if ((item.getId() == null) || (!exists(item.getId()))) {
					T updated = post(item);
					item.setId(updated.getId());
					item.setCreated(updated.getCreated());
					item.setUpdated(updated.getUpdated());
				}
			}
		}

		if (!isEmpty(into)) {
			for (T item : into) {
				if (from.stream().noneMatch(i -> i.getId().equals(item.getId()))) {
					if (exists(item.getId())) {
						delete(item.getId());
						item.setDeleted(ZonedDateTime.now());
					}
				}
			}
		}
	}

	@Override
	public T post(T item) {

		try {
			jpaOperation(POST);
			validate(item);
			if (item.getCreated() == null) {
				item.setCreated(ZonedDateTime.now());
			}
			item.setUpdated(ZonedDateTime.now());
			return save(item);
		} finally {
			jpaOperation();
		}
	}

	@Override
	public T put(T item) {

		try {
			jpaOperation(PUT);
			validate(item);
			item.setUpdated(ZonedDateTime.now());
			return save(item);
		} finally {
			jpaOperation();
		}
	}

	private T save(T item) {

		return getRepository().save(item);
	}

	private void validate(T item) {

		switch (JpaCrudService.getJpaOperation()) {
			case PUT:
				if (item.getDeleted() != null) {
					throw new ValidationException("Error");
				}
				break;
		}
	}
}
