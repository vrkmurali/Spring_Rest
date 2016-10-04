package com.code.generate;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.generate.DTO.ProcessDTO;
import com.code.generate.DTO.UserSubmitionResponse;
import com.code.generate.exception.SaveDataException;
import com.code.generate.serviceLayer.CodeGenerationServiceLayer;

@RestController

public class SecureCodeController {
	
	private static final Logger LOGGER = Logger.getLogger(SecureCodeController.class);
	
	@Autowired
	private CodeGenerationServiceLayer codeGenerationServiceLayer;

	@RequestMapping(value={"/verification_Code/{userId}","/verification_Code/{userId}.xml",
			"/verification_Code/{userId}.json"}, 
			method={RequestMethod.POST},produces={"application/json","application/xml"})
    public  UserSubmitionResponse getSecureCode (@PathVariable String userId,HttpSession httpSession) throws Exception{
		LOGGER.info(userId);
		return getResponse(userId);
	}
	private UserSubmitionResponse getResponse(String userId) throws SaveDataException, Exception{
		ProcessDTO dto = new ProcessDTO();
		dto.setUserId(userId);
		String secureCode = codeGenerationServiceLayer.saveSecureCode(dto);
		UserSubmitionResponse userSubmitionResponse =new UserSubmitionResponse();
		
	if(StringUtils.hasText(secureCode)){
		userSubmitionResponse.setSecure_code(secureCode);
		userSubmitionResponse.setMessage("successFully submitted");
		userSubmitionResponse.setResult_code(1);
	} else{
		userSubmitionResponse.setSecure_code("000000000");
		userSubmitionResponse.setMessage("Failed to generate the securecode");
		userSubmitionResponse.setResult_code(0);
	}	
	return userSubmitionResponse;
	}
	
	@ExceptionHandler(SaveDataException.class)
	public @ResponseBody UserSubmitionResponse handleDataBaseAccessException(){
	     LOGGER.error("Process Submition failed Exception ");
			UserSubmitionResponse userSubmitionResponse =new UserSubmitionResponse();
			userSubmitionResponse.setSecure_code("000000000");
			userSubmitionResponse.setMessage("Securecode generating is failed");
			userSubmitionResponse.setResult_code(0);
	    return userSubmitionResponse;
	}

	@ExceptionHandler(Exception.class)
	public @ResponseBody UserSubmitionResponse handlerException( Exception exception){
	     LOGGER.error("Exception Occured ");
	     exception.printStackTrace();LOGGER.error("Process Submition failed Exception ");
			UserSubmitionResponse userSubmitionResponse =new UserSubmitionResponse();
			userSubmitionResponse.setSecure_code("000000000");
			userSubmitionResponse.setMessage("Failed to generate the securecode");
			userSubmitionResponse.setResult_code(0);
	    return userSubmitionResponse;
	}
}
