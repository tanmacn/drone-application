package com.musala.api.base;

import com.musala.dto.base.DtoBase;
import com.musala.model.base.JpaModelBase;
import com.musala.service.base.JpaCrudService;
import com.musala.util.jpa.PagedContent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

public abstract class AbstractApi<A extends DtoBase, D extends JpaModelBase> extends AbstractMappingApi<A, D> implements BaseApi<A> {

	public AbstractApi() {

	}

	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") String id) {

		this.getService().delete(id);
		return this.ok();
	}

	@Transactional
	public ResponseEntity<PagedContent<A>> get() {

		PagedContent<D> domainPages = this.getService().get();
		PagedContent<A> apiPages = this.map(domainPages);
		return this.ok(apiPages);
	}

	@Transactional
	public ResponseEntity<A> get(@PathVariable("id") String id) {

		D domain = this.getService().get(id);
		if (domain == null) {
			throw new EntityNotFoundException(id);
		} else {
			A api = this.map(domain);
			return this.ok(api);
		}
	}

	@Transactional
	public ResponseEntity<A> post(@RequestBody A api) {

		D domain = this.map(api);
		domain = this.getService().post(domain);
		api = this.map(domain);
		return this.ok(api);
	}

	@Transactional
	public ResponseEntity<A> put(@PathVariable("id") String id, @RequestBody A api) {

		api.setId(id);
		D domain = this.map(api);
		domain = this.getService().put(domain);
		api = this.map(domain);
		return this.ok(api);
	}

	protected abstract JpaCrudService<D> getService();

	protected PagedContent<A> map(PagedContent<D> items) {

		return this.mapToApi(items);
	}

	protected abstract A map(D var1);

	protected abstract D map(A var1);

	protected A mapToApi(D item) {

		return this.map(item);
	}

	protected D mapToDomain(A item) {

		return this.map(item);
	}

	public ResponseEntity<?> ok() {

		return ResponseEntity.ok().build();
	}

	public <T> ResponseEntity<T> ok(T body) {

		return ResponseEntity.ok().body(body);
	}
}
