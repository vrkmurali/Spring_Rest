package com.code.generate.daoLayerImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.code.generate.dao.ProcessDAO;
import com.code.generate.daoLayer.ProcessCodeDAO;
import com.code.generate.exception.DataRetrivalException;
import com.code.generate.exception.SaveDataException;

@Repository
public class ProcessCodeDAOImpl implements ProcessCodeDAO {
	
	Logger LOGGER=Logger.getLogger(ProcessCodeDAOImpl.class);

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public String saveSecureCode(ProcessDAO processDAO) throws SaveDataException {
		LOGGER.info("inside the saveSecureCode() of ProcessCodeDAOImpl");
		try{
		hibernateTemplate.save(processDAO);
		}catch(DataAccessException dataAccessException){
			dataAccessException.printStackTrace();
			throw new SaveDataException	("error occurred while accessing the database");
		}catch(Exception exception){
			exception.printStackTrace();
			throw new SaveDataException("error occurred while generating the code");
		}
		LOGGER.info("end of the saveSecureCode() of ProcessCodeDAOImpl");
		return processDAO.getSecure_Code();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean verifySecureCode(ProcessDAO processDAO) throws DataRetrivalException {
		LOGGER.info("inside  the verifySecureCode() of ProcessCodeDAOImpl");
			boolean flag=true;
			try{
			List<ProcessDAO> list= (List<ProcessDAO>) hibernateTemplate.find("from ProcessDAO dao where dao.userId ='"+processDAO.getUserId()+"' and dao.secure_Code ='"+processDAO.getSecure_Code()+"'" );
			
			LOGGER.info(list);
			if(list.isEmpty()){
				flag=false;
			}
			}catch(DataAccessException dataAccessException){
				dataAccessException.printStackTrace();
				throw new DataRetrivalException("error occurred while accessing the database");
			}catch(Exception exception){
				exception.printStackTrace();
				throw new DataRetrivalException("error occurred while verifying the code");
			}
			LOGGER.info("end of the verifySecureCode() of ProcessCodeDAOImpl:::"+flag);
		return flag	;
	} 
}