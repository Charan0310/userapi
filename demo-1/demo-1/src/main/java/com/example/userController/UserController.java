package com.example.userController;

import java.io.IOException;

import java.util.List;


import org.apache.catalina.User;
import org.apache.coyote.http11.Http11InputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.Signupdto.DetailsDto;
import com.example.Signupdto.LoginDTO;
import com.example.Signupdto.SignupDTO;
import com.example.common.APIResponse;
import com.example.common.UploadFileResponse;
import com.example.common.UserDetailsResponse;
import com.example.common.showApi;
import com.example.model.UserDetails;
import com.example.model.UserEntity;
import com.example.repository.UserRepository;
import com.example.userservice.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api")
@CrossOrigin
@showApi
public class UserController {


	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getuser/{id}")
	public UserDetailsResponse getUser(@PathVariable Long id){
		return userService.getUser(id);
	}
	
	// @PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getallusers")
	public List<DetailsDto> getAll(){
		
		return userService.getAll();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DetailsDto> update(@PathVariable Long id,@RequestBody DetailsDto updatedto){
		userService.update(id, updatedto);
		return ResponseEntity.ok(updatedto);
	}
	
	
	@PostMapping(value="/signup")
	public APIResponse signup(@RequestBody SignupDTO user ) throws Exception {
	
		
		return userService.signup(user);
		
		 
	
	}
	
	@PostMapping("/login")
	public APIResponse login(@RequestBody LoginDTO user) {
		APIResponse api=new APIResponse();
		api=userService.login(user);
		
		return api;
	}
	
	@DeleteMapping("/delete/{id}")
	public APIResponse delete(@PathVariable Long id){
		return 	userService.delete(id);

	}
	
	@PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,@RequestParam Long id) throws Exception {


        return userService.storeFile(file,id);
    }
	
	@GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws Exception {
        // Load file as Resource
        Resource resource = userService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
