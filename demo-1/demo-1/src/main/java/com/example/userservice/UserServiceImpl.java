package com.example.userservice;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.Exception.FileStorageException;
import com.example.Exception.MyFileNotFoundException;
import com.example.Signupdto.DetailsDto;
import com.example.Signupdto.LoginDTO;
import com.example.Signupdto.SignupDTO;
import com.example.Util.JWTUtils;
import com.example.common.APIResponse;
import com.example.common.UploadFileResponse;
import com.example.common.UserDetailsResponse;
import com.example.config.FileStorageProperties;
import com.example.model.RoleEntity;
import com.example.model.UserDetails;
import com.example.model.UserEntity;
import com.example.repository.RoleRepositry;
import com.example.repository.UserDetailsRepository;
import com.example.repository.UserRepository;

import io.swagger.annotations.ApiResponse;


@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserRepository userepo;
	
	@Autowired
	private UserDetailsRepository userDetailsrepo;
	
	@Autowired
	private RoleRepositry rolerepo;
	@Override
	public APIResponse signup(SignupDTO signupdto) {
		// TODO Auto-generated method stub
    UserEntity u=new UserEntity();
    APIResponse apiResponse=new APIResponse();
         try {
        	 if(userepo.existsByuserName(signupdto.getUserName())) {
        		 return new APIResponse(false, "username already taken");
        	 }
		u.setUserName(signupdto.getUserName());
		u.setPassword(signupdto.getPassword());
       
		if(signupdto.getRoles().equals("ADMIN")) {
		RoleEntity userRole = rolerepo.findByName("ROLE_ADMIN").get();
    	u.setRoles(userRole);
      
        userDetailsrepo.saveAll(signupdto.getUserDetails());

        u.setUserDetails(signupdto.getUserDetails());
		}
		else {
			RoleEntity userRole = rolerepo.findByName("ROLE_USER").get();
	    	u.setRoles(userRole);
	        userDetailsrepo.saveAll(signupdto.getUserDetails());
	        u.setUserDetails(signupdto.getUserDetails());
		}
          
		userepo.save(u);
		UserDetails user=userDetailsrepo.findById(u.getId()).orElseThrow();
		 if(user.getPhotos()==null) {
	        	
	            user.setPhotos("null.jpg");
	            userDetailsrepo.save(user);
	            
	            
	        }
		return new APIResponse(true,"SAVED") ;
         }
         catch(Exception e) {
         return new APIResponse(false,e.toString());	 
         }
        
		
		}
	
	@Override
	public APIResponse login(LoginDTO logindto) {
		// TODO Auto-generated method stub
		APIResponse api=new APIResponse();

		try {
		UserEntity u=userepo.findOneByuserNameAndPassword(logindto.getUserName(),logindto.getPassword());
		if(u==null) {
		 api.setStatus(false);
		 return api;
		}
		
			String token=JWTUtils.generateJWt(u);
			api.setStatus(true);
			api.setToken(token);
		}
		catch(Exception e) {
			api.setStatus(false);
			api.setMessage(e.toString());
			
		}
			return api;
	
		
	}
	@Override
	public List<DetailsDto> getAll() {
		
		return userDetailsrepo.findAll().stream()
                .map(UserDetails -> new DetailsDto(UserDetails))
                .collect(Collectors.toList());
	}

	@Override
	public  APIResponse update(Long id,DetailsDto updatedto)  {
		// TODO Auto-generated method stub
		APIResponse api=new APIResponse();
        try {
		if(userDetailsrepo.existsById(id)) {
		UserDetails u=userDetailsrepo.findById(id).orElseThrow();
	    u.setFirstName(updatedto.getFirstName());
	    u.setLastName(updatedto.getLastName());
	    u.setEmail(updatedto.getEmail());
	    u.setPhoneNo(updatedto.getPhoneNo());
	    u.setDob(updatedto.getDob());
	    u.setAddress(updatedto.getAddress());
	    u.setCountry(updatedto.getCountry());
	    u.setState(updatedto.getState());
	    u.setGender(updatedto.getGender());
	    u.setId(u.getId());
	    userDetailsrepo.save(u);
	    api.setStatus(true);
	    api.setMessage("UPDATED");
		}
		}
        catch(Exception e) {
        	api.setStatus(false);
        	api.setMessage(e.toString());
        }
		return api;
	}

	@Override
	public APIResponse delete(Long id) {
		// TODO Auto-generated method stub
        try {
		UserDetails u=userDetailsrepo.findById(id).orElseThrow();
		UserEntity uid=userepo.findById(id).orElseThrow();
        userepo.delete(uid);
		userDetailsrepo.delete(u);
		return new APIResponse(true,"DELETED");
		}
        catch(Exception e) {
    		return new APIResponse(false,e.toString());

        }
		
	}

	@Override
	public UserDetailsResponse getUser(Long id) {
		// TODO Auto-generated method stub
		
		UserEntity role=userepo.findById(id).orElseThrow();
		try {
		UserDetails userdetail=userDetailsrepo.findById(id).orElseThrow();
	      Integer uid=Integer.valueOf(role.getId().toString())-1;
        
	      
        UserDetailsResponse response=new UserDetailsResponse();
        response.setFirstname(userdetail.getFirstName());
        response.setLastname(userdetail.getLastName());
        response.setPhoneno(userdetail.getPhoneNo());
        response.setEmail(userdetail.getEmail());
        response.setId(userdetail.getId());
        response.setPhotos(userdetail.getPhotos());
        response.setDob(userdetail.getDob());
        response.setCountry(userdetail.getCountry());
        response.setGender(userdetail.getGender());
        response.setAddress(userdetail.getAddress());
        response.setState(userdetail.getState());
		return response;
		}
		catch(Exception e) {
			return null;
		}
	}

	 private final Path fileStorageLocation;
	 

	    public UserServiceImpl(FileStorageProperties fileStorageProperties) {
	        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
	                .toAbsolutePath().normalize();

	        try {
	            Files.createDirectories(this.fileStorageLocation);
	        } catch (Exception ex) {
	            throw new FileStorageException("could not create", ex);
	        }
	    }
	
	    public UploadFileResponse storeFile(MultipartFile file,Long id) throws Exception {
	        // Normalize file name
	        
	    	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	        fileName=fileName.replace(fileName.substring(0, fileName.indexOf('.')),"" );
            UserDetails user=userDetailsrepo.findById(id).orElseThrow();
            
            
            fileName=id.toString()+fileName;
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/api/downloadFile/")
	                .path(fileName)
	                .toUriString();
	        try {
	            // Check if the file's name contains invalid characters
	            if(fileName.contains("..")) {
	            	throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
	            }

	            // Copy file to the target location (Replacing existing file with the same name)
	            Path targetLocation = this.fileStorageLocation.resolve(fileName);
	            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                 user.setPhotos(fileName);
                 userDetailsrepo.save(user);
	            return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize()) ;
	        } catch (IOException ex) {
	            throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
	        }
	    }
         
	    public Resource loadFileAsResource(String fileName) {
	        try {
	            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
	            Resource resource = new UrlResource(filePath.toUri());
	            if(resource.exists()) {
	                return resource;
	            } else {
	                throw new MyFileNotFoundException("File not found " + fileName);
	            }
	        } catch (MalformedURLException ex) {
	            throw new MyFileNotFoundException("File not found " + fileName, ex);
	        }
	    }
	

}
