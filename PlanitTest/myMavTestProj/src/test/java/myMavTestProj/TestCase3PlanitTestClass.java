package myMavTestProj;

import org.testng.annotations.Test;

public class TestCase3PlanitTestClass {
	
	@Test
	public void testContactPageForInvalidData() {
		
		PlanitTest objPlanit = new PlanitTest();
		boolean testRes = objPlanit.chkInvalidData();
		objPlanit.driver.close();
		
		//test case 3
		//check invalid data error message displayed for email field and error message at page top, when invalid data is supplied to mandatory fields
		if (testRes)
			System.out.println("Testcase3 passed - Invalid data error messages appeared as expected");
		else
			System.out.println("Testcase3 failed - Invalid data error messages did not appear as expected");
}

}
