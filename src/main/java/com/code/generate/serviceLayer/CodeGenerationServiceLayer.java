package com.code.generate.serviceLayer;

import com.code.generate.DTO.ProcessDTO;
import com.code.generate.exception.DataRetrivalException;
import com.code.generate.exception.SaveDataException;

public interface CodeGenerationServiceLayer {


	String saveSecureCode(ProcessDTO processDTO) throws SaveDataException, Exception;
	
	boolean verifySecureCode(String userId, String secureCode) throws DataRetrivalException;
	

}
