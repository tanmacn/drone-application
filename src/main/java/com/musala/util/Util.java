package com.musala.util;


import com.musala.util.jpa.PagedContent;

public class Util {

	public static <T, F> PagedContent<T> toPagedContent(PagedContent<F> from, GenericMapper<T, F> genericMapper) {

		if (from == null) {
			return null;
		}

		PagedContent<T> to = new PagedContent<>();
		for (F item : from) {
			to.getContent().add(genericMapper.map(item));
		}
		to.setNumber(from.getNumber());
		to.setNumberOfElements(from.getNumberOfElements());
		to.setSize(from.getSize());
		to.setTotalPages(from.getTotalPages());
		to.setTotalElements(from.getTotalElements());
		return to;
	}

	public interface GenericMapper<T, F> {

		T map(F item);
	}

}
