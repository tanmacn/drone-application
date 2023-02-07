package com.musala.api.base;

import com.musala.dto.base.DtoBase;
import com.musala.util.jpa.PagedContent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface BaseApi<A extends DtoBase> {

	@DeleteMapping("/{id}")
	@ResponseBody
	ResponseEntity<?> delete(@PathVariable("id") String var1);

	@GetMapping()
	ResponseEntity<PagedContent<A>> get();

	@GetMapping("/{id}")
	ResponseEntity<A> get(@PathVariable("id") String var1);

	@PostMapping()
	ResponseEntity<A> post(@RequestBody A var1);

	@PutMapping("/{id}")
	ResponseEntity<A> put(@PathVariable("id") String var1, @RequestBody A var2);
}
