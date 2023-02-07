package com.musala.model.base;


import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@MappedSuperclass
public class JpaModelBase {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	protected String id;

	@Column(nullable = false)
	protected ZonedDateTime created;
	protected ZonedDateTime deleted;

	@Column(nullable = false)
	protected ZonedDateTime updated;

	public ZonedDateTime getCreated() {

		return created;
	}

	public void setCreated(ZonedDateTime created) {

		this.created = created;
	}

	public ZonedDateTime getDeleted() {

		return deleted;
	}

	public void setDeleted(ZonedDateTime deleted) {

		this.deleted = deleted;
	}

	public String getId() {

		return id;
	}

	public void setId(String id) {
		// Do not permit null or empty id's to be set. We explicitly require an Id on this object.
		if (StringUtils.isBlank(id)) {
			return;
		}
		this.id = id;
	}

	public ZonedDateTime getUpdated() {

		return updated;
	}

	public void setUpdated(ZonedDateTime updated) {

		this.updated = updated;
	}
}
