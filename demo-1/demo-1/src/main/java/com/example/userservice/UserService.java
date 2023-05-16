package com.example.userservice;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Signupdto.DetailsDto;
import com.example.Signupdto.LoginDTO;
import com.example.Signupdto.SignupDTO;
import com.example.common.APIResponse;
import com.example.common.UploadFileResponse;
import com.example.common.UserDetailsResponse;
import com.example.model.UserDetails;

import io.swagger.annotations.ApiResponse;
@Service
public interface UserService {
	public APIResponse signup(SignupDTO signupdto);
	public APIResponse login(LoginDTO logindto);
	public List<DetailsDto> getAll();
	public  APIResponse update(Long id,DetailsDto updatedto);
	public APIResponse delete(Long id) ;
	public UserDetailsResponse getUser(Long id);
	public UploadFileResponse storeFile(MultipartFile file,Long id) throws Exception;
	 public Resource loadFileAsResource(String fileName) throws Exception;
}
