package myMavTestProj;

import org.testng.annotations.Test;

public class TestCase1PlanitTestClass {
	
	@Test
	public void testContactPageForErrors() {
		
		PlanitTest objPlanit = new PlanitTest();
		
		//test case1 part1
		//validate if all error messages are as expected when mandatory fields are not filled
		boolean testRes = objPlanit.validateError();
		if (testRes)
			System.out.println("Testcase1 part1 passed - All error messages are as expected");
		else
			System.out.println("Testcase1 part2 failed - One or more of the error messages are not as expected");
		
		//test case1 part2
		//populate mandatory fields with valid data and validate "valid message" is displayed at page top
		testRes = objPlanit.populateMandatoryFields();
		objPlanit.driver.close();
		if (testRes)
			System.out.println("Testcase1 part2 passed - Valid message appeared as expected");
		else
			System.out.println("Testcase1 part2 failed - Valid message did not appear as expected");
	}
}
