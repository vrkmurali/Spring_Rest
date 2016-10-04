package com.code.generate;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.generate.DTO.ProcessDTO;
import com.code.generate.DTO.ProcessVerificationResponse;
import com.code.generate.exception.DataRetrivalException;
import com.code.generate.serviceLayer.CodeGenerationServiceLayer;

@RestController

public class VerifySecureCodeController {
	
	private static final Logger LOGGER = Logger.getLogger(VerifySecureCodeController.class);
	
	@Autowired
	private CodeGenerationServiceLayer codeGenerationServiceLayer;

	@RequestMapping(value={"/verify_Code/{userId}/{securecode}","/verify_Code/{userId}/{securecode}.xml","/verify_Code/{userId}/{securecode}.json"}, 
			method={RequestMethod.POST},
			produces={"application/json","application/xml"})
    public ProcessVerificationResponse veriSecureCode (@PathVariable String userId,@PathVariable String securecode,HttpSession httpSession) throws  DataRetrivalException{
		LOGGER.info(userId);
		LOGGER.info(securecode);
		ProcessDTO dto = new ProcessDTO();
		dto.setUserId(userId);
		boolean status= codeGenerationServiceLayer.verifySecureCode(userId, securecode);
		ProcessVerificationResponse processVerificationResponse =new ProcessVerificationResponse();
		
	if(status){
		processVerificationResponse.setResult_code(1);
		processVerificationResponse.setValid(status);
		processVerificationResponse.setMessage("Successfully verified");
		
	} else{
		processVerificationResponse.setResult_code(0);
		processVerificationResponse.setValid(status);
		processVerificationResponse.setMessage("Successfully verified,Code is wrong or user id is not found");
	}	
		return processVerificationResponse;
	}
	@ExceptionHandler(DataRetrivalException.class)
	public @ResponseBody ProcessVerificationResponse handleDataBaseAccessException(){
		LOGGER.error("verify code exception");
	     ProcessVerificationResponse processVerificationResponse =new ProcessVerificationResponse();
	     processVerificationResponse.setResult_code(0);
			processVerificationResponse.setValid(false);
			processVerificationResponse.setMessage("while data base accessing error occurred");
	    return processVerificationResponse;
	}

	@ExceptionHandler(Exception.class)
	public @ResponseBody ProcessVerificationResponse handlerException( Exception exception){
	     LOGGER.error("verify code exception");
	     ProcessVerificationResponse processVerificationResponse =new ProcessVerificationResponse();
	     processVerificationResponse.setResult_code(0);
			processVerificationResponse.setValid(false);
			processVerificationResponse.setMessage("unknown error");
	    return processVerificationResponse;
	}
}
