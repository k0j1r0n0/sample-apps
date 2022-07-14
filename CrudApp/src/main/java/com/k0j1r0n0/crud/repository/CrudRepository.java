package com.k0j1r0n0.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.k0j1r0n0.crud.entity.KeyValue;

@Repository
public interface CrudRepository extends JpaRepository<KeyValue, Integer> {
    // JpaRepositoryで基本的なCRUDメソッドが定義されているため、ここでは継承するだけ
}
