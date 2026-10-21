package com.doc.finder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.doc.finder.dto.UserDTO;
import com.doc.finder.service.UserServiceImpl;
import com.doc.finder.util.DataUtility;

@SpringBootApplication
public class OnlineDoctorFinderSystemApplication implements CommandLineRunner {
	
	@Autowired
	private UserServiceImpl userService;

	public static void main(String[] args) {
		SpringApplication.run(OnlineDoctorFinderSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(userService.countUser()==0) {
			UserDTO dto = new UserDTO();
			dto.setFirstName("Admin");
			dto.setLastName("Admin");
			dto.setGender("Male");
			dto.setLogin("Admin@admin.com");
			dto.setPassword("Admin@123");
			dto.setMobileNo("888888888");
			dto.setRoleId(1L);
			dto.setRoleName("ADMIN");
			dto.setCreatedDatetime(DataUtility.getCurrentTimestamp());
			dto.setModifiedDatetime(DataUtility.getCurrentTimestamp());
			dto.setCreatedBy("root");
			dto.setModifiedBy("root");
			long addAdmin = userService.add(dto);
			System.out.println("Admin added: "+addAdmin);
	}

	}
}
