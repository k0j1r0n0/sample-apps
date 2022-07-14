package com.k0j1r0n0.crud.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import com.k0j1r0n0.crud.entity.KeyValue;

public interface CrudService {
	// Spring Data JPAで実装されているクラスを後ほど使用する。ここではメソッドの定義のみ記載しておく。
	KeyValue findById(Integer id);
	List<KeyValue> findAll(Sort sort);
    void save(KeyValue keyValue);
    void deleteById(Integer id); 
}
