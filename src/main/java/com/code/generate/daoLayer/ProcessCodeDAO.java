package com.code.generate.daoLayer;

import com.code.generate.dao.ProcessDAO;
import com.code.generate.exception.DataRetrivalException;
import com.code.generate.exception.SaveDataException;

public interface ProcessCodeDAO {

	String saveSecureCode(ProcessDAO processDAO) throws SaveDataException;
	
	boolean verifySecureCode(ProcessDAO processDAO) throws DataRetrivalException;
	
}
