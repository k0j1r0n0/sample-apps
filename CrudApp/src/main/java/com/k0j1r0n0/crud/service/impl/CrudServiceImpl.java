package com.k0j1r0n0.crud.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k0j1r0n0.crud.entity.KeyValue;
import com.k0j1r0n0.crud.repository.CrudRepository;
import com.k0j1r0n0.crud.service.CrudService;
import org.springframework.data.domain.Sort;

@Service
public class CrudServiceImpl implements CrudService {
    private CrudRepository repository;
    
    @Autowired
    public CrudServiceImpl(CrudRepository repository) {
    	this.repository = repository;
    }
    
    /*
     * 以下、CrudServiceで定義したメソッドを実装
     * 各メソッドでは、Spring Data JPAで用意されているCRUDメソッドを使用
     *   -> findById, findAll, save, deleteById
     */
    @Override
    public KeyValue findById(Integer id) {
    	Optional<KeyValue> entity = repository.findById(id);
        if (entity.isPresent()) {
        	return entity.get();
        } else {
        	return null;
        }
    }
    
    @Override
    public List<KeyValue> findAll(Sort sort) {
    	return repository.findAll(sort);
    }

	@Override
	public void save(KeyValue keyValue) {
		repository.save(keyValue);
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
}
