package com.musala.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JpaCrudRepository<T> extends JpaRepository<T, String>, JpaSpecificationExecutor<T> {

}
