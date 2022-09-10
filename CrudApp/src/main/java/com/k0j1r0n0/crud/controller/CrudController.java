package com.k0j1r0n0.crud.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Sort;
import com.k0j1r0n0.crud.entity.KeyValue;
import com.k0j1r0n0.crud.service.CrudService;

@Controller
@RequestMapping("/home")
public class CrudController {
    private CrudService service; 

    public CrudController(CrudService service) {
        this.service = service;
    }

    // Key-Valueリスト（一覧画面）を表示
    @GetMapping
    public String index(Model model) {
    	List<KeyValue> list = service.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("keyValueList", list);
        
        return "home/index";
    }

    // 新規にKey-Valueペアを追加する画面を表示
    @GetMapping("/add")
    public String form(Model model, @ModelAttribute KeyValue keyValue) {
    	model.addAttribute("keyValue", keyValue);
    	
    	return "home/add";
    }
    
    // 新規にKey-Valueペアを保存
    @PostMapping("/add")
    public String create(KeyValue keyValue) {
        KeyValue entity = new KeyValue();
        entity.setKey(keyValue.getKey());
        entity.setValue(keyValue.getValue());
        service.save(entity);
        
        return "redirect:/home";
    }

    // Key-Valueペアを編集する画面を表示
    @GetMapping("/edit/{id}")
    public String edit(Model model, @ModelAttribute KeyValue keyValue, @PathVariable int id) {
        KeyValue entity = service.findById(id);
        model.addAttribute("keyValue", entity);
        
        return "home/edit";
    }

    // 編集後のKey-Valueペアを保存
    @PostMapping("/edit/{id}")
    public String update(KeyValue keyValue, @PathVariable int id) {
        KeyValue entity = new KeyValue();
        entity.setId(keyValue.getId());
        entity.setKey(keyValue.getKey());
        entity.setValue(keyValue.getValue());
        service.save(entity);
        
        return "redirect:/home";
    }

    // Key-Valueペアの削除
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.deleteById(id);
        
        return "redirect:/home";
    }
}