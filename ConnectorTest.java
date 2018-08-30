import junit.framework.TestCase;


public class ConnectorTest extends TestCase {
	
	// Check for the case where the input is not integers or 
	// the input has space in between.
	public void testNonIntegers () {
		int exc = 0;
		String message = "Input must be integers, without any space in between.";
		try {
			Connector.toConnector("   25.3 ");
		} catch (IllegalFormatException e1) {
			exc++;
			assertTrue(message.equals(e1.getMessage()));
		}
		assertEquals(exc, 1);
		
		try {
			Connector.toConnector("2 3   ");
		} catch (IllegalFormatException e2) {
			exc++;
			assertTrue(message.equals(e2.getMessage()));
		}
		assertEquals(exc, 2);
		
		
	}
	
	// Check for the case where the input has more than two digits or
	// each digit is not between 1 and 6 inclusive.
	public void testIllegalPoints () {
		int exc = 0;
		String message = "You can only input two points, and each must be between 1 and 6.";
		try {
			Connector.toConnector("   38253   ");
		} catch (IllegalFormatException e1) {
			exc++;
			assertTrue(message.equals(e1.getMessage()));
		}
		assertEquals(exc, 1);
		try {
			Connector.toConnector("   39 ");
		} catch (IllegalFormatException e2) {
			exc++;
			assertTrue(message.equals(e2.getMessage()));
		}
		assertEquals(exc, 2);
		try {
			Connector.toConnector("05  ");
		} catch (IllegalFormatException e3) {
			exc++;
			assertTrue(message.equals(e3.getMessage()));
		}
		assertEquals(exc, 3);
	}
	
	// Checks for the case where the two points are the same.
	public void testDistinctPoints () {
		int exc = 0;
		String message = "The two points must be distinct.";
		try {
			Connector.toConnector("   11 ");
		} catch (IllegalFormatException e1) {
			exc++;
			assertTrue(message.equals(e1.getMessage()));
		}
		assertEquals(exc, 1);
		try {
			Connector.toConnector("   33 ");
		} catch (IllegalFormatException e2) {
			exc++;
			assertTrue(message.equals(e2.getMessage()));
		}
		assertEquals(exc, 2);
	}
	
	// Correct cases; int exc should not increase.
	public void testCorrectPoints () {
		int exc = 0;
		try {
			Connector.toConnector("25");
		} catch (IllegalFormatException e1) {
			exc++;
		}
		assertEquals(exc, 0);
		try {
			Connector.toConnector("14 ");
		} catch (IllegalFormatException e2) {
			exc++;
		}
		assertEquals(exc, 0);
		try {
			Connector.toConnector("     64");
		} catch (IllegalFormatException e3) {
			exc++;
		}
		assertEquals(exc, 0);
		try {
			Connector.toConnector("    52      ");
		} catch (IllegalFormatException e4) {
			exc++;
		}
		assertEquals(exc, 0);
	}
}

	