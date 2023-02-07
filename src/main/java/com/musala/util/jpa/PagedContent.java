package com.musala.util.jpa;


import com.musala.util.Util;

import java.util.*;

import static com.musala.util.Util.toPagedContent;

public class PagedContent<T> implements Iterable<T> {

	private List<T> content = new ArrayList<T>();
	private int     number;
	private int     numberOfElements;
	private int     size;
	private long    totalElements;
	private long    totalPages;

	public PagedContent() {

	}

	public PagedContent(List<T> content) {

		this.content = content;
		number = 0;
		numberOfElements = content.size();
		size = content.size();
		totalElements = content.size();
		totalPages = 1;
	}

	public PagedContent(Set<T> content) {

		this.content = new LinkedList<>(content);
		number = 0;
		numberOfElements = content.size();
		size = content.size();
		totalElements = content.size();
		totalPages = 1;
	}

	public void add(T item) {

		content.add(item);
	}

	public List<T> getContent() {

		return content;
	}

	public void setContent(List<T> content) {

		this.content = content;
	}

	public int getNumber() {

		return number;
	}

	public void setNumber(int number) {

		this.number = number;
	}

	public int getNumberOfElements() {

		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {

		this.numberOfElements = numberOfElements;
	}

	public int getSize() {

		return size;
	}

	public void setSize(int size) {

		this.size = size;
	}

	public long getTotalElements() {

		return totalElements;
	}

	public void setTotalElements(long totalElements) {

		this.totalElements = totalElements;
	}

	public long getTotalPages() {

		return totalPages;
	}

	public void setTotalPages(long totalPages) {

		this.totalPages = totalPages;
	}

	@Override
	public Iterator<T> iterator() {

		return content.iterator();
	}

	public <U> PagedContent<U> map(Util.GenericMapper<U, T> genericMapper) {

		return toPagedContent(this, genericMapper);
	}
}
