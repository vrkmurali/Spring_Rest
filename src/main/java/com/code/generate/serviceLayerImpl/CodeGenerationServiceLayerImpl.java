package com.code.generate.serviceLayerImpl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.generate.DTO.ProcessDTO;
import com.code.generate.dao.ProcessDAO;
import com.code.generate.daoLayer.ProcessCodeDAO;
import com.code.generate.exception.DataRetrivalException;
import com.code.generate.serviceLayer.CodeGenerationServiceLayer;

@Service
public class CodeGenerationServiceLayerImpl implements CodeGenerationServiceLayer{

	@Autowired
	private ProcessCodeDAO processCodeDAO;
	
	@Override
	public String saveSecureCode(ProcessDTO processDTO) throws Exception {
		ProcessDAO dao= new ProcessDAO();
		BeanUtils.copyProperties(processDTO, dao);
		if(processDTO.getUserId().length() > 5){
			dao.setSecure_Code(generateRandomPassword(processDTO.getUserId().length()));
		}else{
			dao.setSecure_Code(generateRandomPassword(10));	
		}
		dao.setCreated_On(new Date());
		return processCodeDAO.saveSecureCode(dao);
	}

	@Override
	public boolean verifySecureCode(String userId, String secureCode) throws DataRetrivalException {
		return processCodeDAO.verifySecureCode(new ProcessDAO(userId, secureCode));
	}

	
	
	public static String generateRandomPassword(int size) throws Exception {
		StringBuffer buffer = new StringBuffer();
		try{
	
			String	characters = "0123456789abcdefghiABCDEFGHIWXYZjklmnopqrstuvwxyzJKLMNOPQRSTUV0123456789";
		int charactersLength = characters.length();
		for (int i = 0; i < size; i++) {
			double index = Math.random() * charactersLength;
			char char1= characters.charAt((int) index);
			buffer.append(char1);
			}
		}catch (Exception exception){
			exception.fillInStackTrace();
		}
		return buffer.toString();
	}
}