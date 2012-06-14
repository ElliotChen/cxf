package com.sforce.service.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.sforce.dao.BaseDao;
import com.sforce.domain.support.Condition;
import com.sforce.domain.support.LikeMode;
import com.sforce.domain.support.Page;
import com.sforce.service.BaseDomainService;

@Transactional(readOnly = true)
public abstract class AbstractDomainService<Dao extends BaseDao<T, Oid>, T, Oid extends Serializable>
		implements BaseDomainService<T, Oid> {

	@Override
	public T findByOid(Oid oid) {
		return this.getDao().findByOid(oid);
	}

	@Override
	@Transactional(readOnly = false)
	public void create(T entity) {
		this.getDao().create(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(T entity) {
		this.getDao().delete(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(T entity) {
		this.getDao().update(entity);

	}

	@Override
	@Transactional(readOnly = false)
	public void saveOrUpdate(T entity) {
		this.getDao().saveOrUpdate(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void merge(T entity) {
		this.getDao().merge(entity);
	}

	@Override
	public List<T> listAll() {
		return this.getDao().listAll();
	}

	@Override
	public List<T> listByExample(T example) {
		return this.getDao().listByExample(example);
	}

	@Override
	public List<T> listByExample(T example, List<Condition> conditions,
			LikeMode mode, String[] ascOrders, String[] descOrders) {
		return this.getDao().listByExample(example, conditions, mode,
				ascOrders, descOrders);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<T> listByPage(Page<T> page) {
		return this.getDao().listByPage(page);
	}

	public abstract Dao getDao();

	public abstract Logger getLogger();

}
