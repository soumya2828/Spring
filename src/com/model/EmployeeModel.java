package com.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.EmployeeBean;
import com.dao.EmployeeDao;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class EmployeeModel {
	
	@Autowired
	private EmployeeDao empDao;

	public EmployeeDao getEmpDao() {
		return empDao;
	}

	public void setEmpDao(EmployeeDao empDao) {
		this.empDao = empDao;
	}
	
	@RequestMapping("/empform")
	public ModelAndView getEmployeeForm(){
		EmployeeBean empBean = new EmployeeBean();
		ModelAndView mv = new ModelAndView("empForm","command",empBean);
		return mv;
	}
	
	@RequestMapping(path="/empSave", method=RequestMethod.POST )
	public ModelAndView saveEmployeeData(@ModelAttribute()EmployeeBean empBean){
		empDao.saveEmployee(empBean);
		return new ModelAndView("redirect:/viewEmp");
	}
	
	@RequestMapping("/viewEmp")
	public ModelAndView getEmployeeList(){
		List<EmployeeBean> empList = empDao.getEmpListByPreparedStatement();
		 return new ModelAndView("employeeList","list",empList);  
	}
	
	@RequestMapping(value="/deleteEmp",method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")  
    public @ResponseBody void delete(HttpServletRequest requset,HttpServletResponse response) throws IOException{  
		String id = requset.getParameter("id");
		EmployeeBean empBean = new EmployeeBean();
		empBean.setId(id);
		empDao.deleteEmployee(empBean) ;
		List<EmployeeBean> empList = empDao.getEmpListByPreparedStatement();
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");            
        mapper.writeValue(response.getOutputStream(), empList);
    }  
	
	
}
