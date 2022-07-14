package com.k0j1r0n0.crud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "key_value_table")
public class KeyValue {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // 主キーに設定
    private Integer id;
        
    private String key;
    private String value;

    // 以下、getter, setter
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}