package myMavTestProj;

import org.testng.annotations.Test;

public class TestCase2PlanitTestClass {
	
	@Test
	public void testContactPageForSucSub(){
		PlanitTest objPlanit = new PlanitTest();
		boolean testRes = objPlanit.chkSucSub();
		
		//test case 2
		//chk for successful submission message when submitting contact form with data for mandatory fields
		if (testRes)
			System.out.println("Testcase2 passed - Valid message appeared as expected");
		else
			System.out.println("Testcase2 failed - Valid message did not appear as expected");	
		objPlanit.driver.close();
	}

}
