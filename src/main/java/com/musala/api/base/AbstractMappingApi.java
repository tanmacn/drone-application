package com.musala.api.base;

import com.musala.util.Util;
import com.musala.util.jpa.PagedContent;

public abstract class AbstractMappingApi<A, D> {

	public AbstractMappingApi() {

	}

	protected PagedContent<A> mapToApi(PagedContent<D> items) {

		return Util.toPagedContent(items, (i) -> this.mapToApi(i));
	}

	protected abstract A mapToApi(D var1);

	protected abstract D mapToDomain(A var1);

}