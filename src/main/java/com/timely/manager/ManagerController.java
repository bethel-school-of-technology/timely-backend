package com.timely.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ManagerController {
	
	@Autowired
	ManagerRepository dao;
	
	@GetMapping("/manager")
	public List<Manager> getManagers() {
		List<Manager> foundManagers = dao.findAll();
		return foundManagers;
	}
	
	@PostMapping("/manager")
	public ResponseEntity<Manager> postManager(@RequestBody Manager manager){
		
		Manager createdManager = dao.save(manager);
		
		return ResponseEntity.ok(createdManager);
	}
	
	@PutMapping("manager/{id}")
	public ResponseEntity<Manager> updateManager(@PathVariable(value="id") Integer id, @RequestBody Manager manager){
		Manager updateManager = dao.findById(id).orElse(null);
		
		if(updateManager == null) {
			return ResponseEntity.notFound().header("Manager", "Nothing found with that id").build();
		}
		else {
			Manager updatedManager = dao.save(manager);
			return ResponseEntity.ok(updatedManager);
		}
	}
	
	@DeleteMapping("/manager/{id}")
	public ResponseEntity<Manager> postManager(@PathVariable(value="id") Integer id){
		Manager foundManager = dao.findById(id).orElse(null);
		
		if(foundManager == null) {
			return ResponseEntity.notFound().header("Manager", "Nothing found with that id").build();
		}else {
			dao.delete(foundManager);
		}
		return ResponseEntity.ok().build();
	}

}
