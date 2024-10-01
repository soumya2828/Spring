package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import com.bean.EmployeeBean;

public class EmployeeDao {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public boolean saveEmployee(EmployeeBean emp) {
		boolean b = false;
		String sql = "insert into employee values(?,?,?)";
		int returnValue = jdbcTemplate.update(sql, emp.getId(), emp.getName(), emp.getSalary());
		if (returnValue > 0)
			b = true;
		return b;
	}

	public boolean deleteEmployee(EmployeeBean emp) {
		boolean b = false;
		String sql = "delete from employee where id=?";
		int returnValue = jdbcTemplate.update(sql, emp.getId());
		if (returnValue > 0)
			b = true;
		return b;
	}

	public boolean updateEmployee(EmployeeBean emp) {
		boolean b = false;
		String sql = "update employee set salary=? where id=?";
		int returnValue = jdbcTemplate.update(sql, emp.getSalary(), emp.getId());
		if (returnValue > 0)
			b = true;
		return b;
	}

	public List<EmployeeBean> getEmpListByPreparedStatement() {

		String sql = "select * from employee";
		return jdbcTemplate.execute(sql, new PreparedStatementCallback<List<EmployeeBean>>() {

			@Override
			public List<EmployeeBean> doInPreparedStatement(PreparedStatement pst)
					throws SQLException, DataAccessException {
				ResultSet rs = pst.executeQuery();
				List<EmployeeBean> empList = new ArrayList<EmployeeBean>();
				while (rs != null && rs.next()) {
					EmployeeBean bean = new EmployeeBean();
					bean.setId(rs.getString(1));
					bean.setName(rs.getString(2));
					bean.setSalary(rs.getString(3));
					empList.add(bean);
				}

				return empList;
			}
		});

	}
}
