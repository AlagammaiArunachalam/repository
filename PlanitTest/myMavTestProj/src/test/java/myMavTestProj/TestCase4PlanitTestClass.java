package myMavTestProj;

import org.testng.annotations.Test;

public class TestCase4PlanitTestClass {

	@Test
	public void testShopPage() {
		
		PlanitTest objPlanit = new PlanitTest();

		boolean testRes = objPlanit.verifyShoppingCart();
		objPlanit.driver.close();
		
		//test case 4
		//when the shopping cart items are selected as given, check for all itemized details and total appears as expected
		if (testRes)
			System.out.println("Testcase4 passed - All items, price, quantity, subtotal and total appeared as expected");
		else
			System.out.println("Testcase4 failed - One or more of items, price, quantity, subtotal and total did not appear as expected");
		
	}
}
