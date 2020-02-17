package com.timely.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

}
