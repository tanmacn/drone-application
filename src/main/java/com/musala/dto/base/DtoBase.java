package com.musala.dto.base;

import java.time.ZonedDateTime;

public class DtoBase {

	private String        id;
	private ZonedDateTime created;
	private ZonedDateTime updated;
	private ZonedDateTime deleted;

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public ZonedDateTime getCreated() {

		return created;
	}

	public void setCreated(ZonedDateTime created) {

		this.created = created;
	}

	public ZonedDateTime getUpdated() {

		return updated;
	}

	public void setUpdated(ZonedDateTime updated) {

		this.updated = updated;
	}

	public ZonedDateTime getDeleted() {

		return deleted;
	}

	public void setDeleted(ZonedDateTime deleted) {

		this.deleted = deleted;
	}
}
