package com.ts.us.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ts.us.dao.BranchDAO;
import com.ts.us.model.Branch;

public class BranchDAOTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static BranchDAO branchDAO;

	@Autowired
	static Branch branch;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ts.us");
		context.refresh();
		branchDAO = (BranchDAO) context.getBean("branchDAO");
		branch = (Branch) context.getBean("branch");
	}

	// @Test
	public void saveBranchTestCase() {
		branch.setLocation("Gachibowli");
		branch.setCity("Hyderabad");
		branch.setState("Telangana");
		branch.setCountry("India");
		branch.setPostalCode(500081);
		branch.setRestaurantId(1);
		boolean flag = branchDAO.save(branch);
		assertEquals("saveTestTestCase", true, flag);
	}

	@Test
	public void listBranchTestCase() {
		List<Branch> branches = branchDAO.list();
		System.out.println(branches);
		assertEquals("saveTestTestCase", 3, branches.size());

	}

}
