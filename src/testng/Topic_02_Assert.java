package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assert {

	Object studentPrice = null; 
	@Test
	public void TC_01() {
		String studentName = "PiKa pika";
		
		Assert.assertTrue(studentName.contains("PiKa"));
		Assert.assertFalse(studentName.contains("Rick"));
		Assert.assertEquals(studentName,"PiKa pika");
		Assert.assertNotEquals(studentName,"PiKa thui");
		Assert.assertNull(studentPrice);
		
		studentPrice = 900;
		Assert.assertNotNull(studentPrice);
	}
	

}
