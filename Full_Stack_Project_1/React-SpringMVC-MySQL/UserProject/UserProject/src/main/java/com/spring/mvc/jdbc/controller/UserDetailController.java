package com.spring.mvc.jdbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.spring.mvc.jdbc.model.UserDetail;
import com.spring.mvc.jdbc.service.UserDetailService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserDetailController {
	@Autowired
	private UserDetailService userDetailService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDetail> getUser(@PathVariable long id) {
		UserDetail user = userDetailService.getUserDetail(id);
		if (user == null) {
			return new ResponseEntity<UserDetail>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDetail>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ResponseEntity<List<UserDetail>> listAllUsers() {
		List<UserDetail> users = userDetailService.getAllUserDetail();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserDetail>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserDetail>>(users, HttpStatus.OK);
	}

	@PostMapping("/adduser")
	public ResponseEntity<String> addUser(@RequestBody UserDetail user) {
		try {
			userDetailService.addUserDetail(user);
			return new ResponseEntity<>("User added Successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable long id) {
		try {
			userDetailService.deleteUserDetail(id);
			return new ResponseEntity<>("User with ID " + id + " deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			// Handle exceptions
			return new ResponseEntity<>("Failed to delete user with ID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// update employee REST API
	@PutMapping("update/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDetail updatedUser) {
		try {
			UserDetail existingUser = userDetailService.getUserDetail(id);

			if (existingUser == null) {
				return new ResponseEntity<>("User with ID " + id + " not found", HttpStatus.NOT_FOUND);
			}

			// Update user properties
			existingUser.setFirstName(updatedUser.getFirstName());
			existingUser.setLastName(updatedUser.getLastName());
			existingUser.setEmail(updatedUser.getEmail());
			existingUser.setContactNo(updatedUser.getContactNo());

			// Save the updated user
			userDetailService.updateUserDetail(existingUser);

			return new ResponseEntity<>("User with ID " + id + " updated successfully", HttpStatus.OK);
		} catch (Exception e) {
			// Handle exceptions
			return new ResponseEntity<>("Failed to update user with ID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}