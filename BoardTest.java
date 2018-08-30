import java.awt.Color;
import java.util.Iterator;

import junit.framework.TestCase;

public class BoardTest extends TestCase {
	
	// Check empty board.
	public void testEmptyBoard ( ) {
		Board b = new Board ( );
		assertTrue (b.isOK ( ));
//		checkCollection (b, 0, 0); // applies more tests
		assertTrue (!b.formsTriangle (new Connector (1, 2), Color.RED));
	}
	
	// Check one-connector board.
	public void test1Connector ( ) {
		Board b = new Board ( );
		b.add (new Connector (1, 2), Color.RED);
		assertTrue (b.isOK ( ));
//		checkCollection (b, 1, 0);
		
		Iterator<Connector> iter = b.connectors (Color.RED);
		assertTrue (iter.hasNext ( ));
		Connector cnctr = iter.next ( );
		assertEquals (b.colorOf (cnctr), Color.RED);
		assertEquals (new Connector (1, 2), cnctr);
		assertTrue (!iter.hasNext ( ));
		
		assertTrue (!b.formsTriangle (new Connector(1,3), Color.RED));
		assertTrue (!b.formsTriangle (new Connector(5,6), Color.RED));
		assertTrue (!b.choice ( ).equals (new Connector (1, 2)));
		assertEquals (b.colorOf (b.choice ( )), Color.WHITE);
	
	}
	
	// Check a board with one RED connector and one BLUE connector.
	public void test2Connector () {
		Board b = new Board();
		assertTrue(b.isOK());
		b.add(new Connector(1, 2), Color.RED);
		assertTrue(b.isOK());
		b.add(new Connector(3, 4), Color.BLUE);
		
		Iterator<Connector> iterBlue = b.connectors(Color.BLUE);
		Iterator<Connector> iterRed = b.connectors(Color.RED);
		
		assertTrue(iterBlue.hasNext()); // hasNext() should not affect the program.
		
		assertTrue(iterRed.hasNext()); // hasNext() should not affect the program.
		
		Connector redCnc = iterRed.next();
		assertFalse(b.colorOf(redCnc).equals(Color.BLUE));
		
		Connector blueCnc = iterBlue.next();
		assertEquals(new Connector(3, 4), blueCnc);
		assertEquals(new Connector(1, 2), redCnc);
		
		assertFalse(iterBlue.hasNext());
		
		Board b2 = new Board();  //creating a new board b2 in order to check the case that has two connectors that take the same color.
		assertTrue(b2.isOK());
		b2.add(new Connector(2, 3), Color.RED);
		b2.add(new Connector(2, 5), Color.RED);
		assertFalse(b2.isOK()); //isOK returns false since there are only two red connectors.
		assertTrue(b2.formsTriangle(new Connector(3, 5), Color.RED));
	}
	
	
	//Test if isOK works properly.
	public void test13Connector() { 
		Board b = new Board();
		b.add(new Connector(1, 2), Color.RED);
		b.add(new Connector(1, 3), Color.BLUE);
		b.add(new Connector(1, 5), Color.BLUE);
		assertFalse(b.isOK()); // isOK returns false because there are more BLUE connectors than RED connectors.
		
		b.add(new Connector(1, 4), Color.RED);
		assertTrue(b.isOK()); // isOK returns true as one more red connector is added.
		
		assertFalse(b.formsTriangle(new Connector(4, 6), Color.RED));
		b.add(new Connector(4, 6), Color.RED);
		
		// Never chooses a connector that forms a blue triangle because there are still other options.
		assertFalse(b.choice().equals(new Connector(3, 5))); 
		
		// Never chooses a connector that prevents the opponent from forming a triangle because there are better options.
		assertFalse(b.choice().equals(new Connector(1, 6))); 
		
		b.add(new Connector(2, 3), Color.BLUE);
		b.add(new Connector(2, 5), Color.RED);
		b.add(new Connector(2, 6), Color.BLUE);
		b.add(new Connector(5, 3), Color.RED); 
		b.add(new Connector(4, 3), Color.BLUE);
		b.add(new Connector(4, 5), Color.RED);
		b.add(new Connector(1, 6), Color.BLUE);
		b.add(new Connector(3, 6), Color.RED);
		
		assertTrue(b.isOK());
		assertEquals(b.colorOf(new Connector(1, 4)), Color.RED);
		assertFalse(!b.formsTriangle(b.choice(), Color.BLUE));
	}
	
	
	//Test if isOK works properly.
	public void testCount() { 
		Board b = new Board();
		b.add(new Connector(1, 2), Color.RED);
		b.add(new Connector(1, 3), Color.RED);
		assertFalse(b.isOK()); // isOK returns false because Board b has two connectors of only RED color.
		
		b.add(new Connector(4, 5), Color.BLUE);
		assertTrue(b.isOK()); // isOK returns true because a BLUE connector is added. 
		
		b.add(new Connector(4, 6), Color.BLUE); 
		assertTrue(b.isOK()); // isOK still returns true.
		
		b.add(new Connector(3, 5), Color.BLUE);
		assertFalse(b.isOK());// There are more BLUE connectors than RED connectors, so isOK returns false.
		
		// Connector(2, 4) doesn't make a triangle with other red connectors, so isOK returns false.
		assertFalse(b.formsTriangle(new Connector(2, 4), Color.RED)); 
		
		assertTrue(b.formsTriangle(new Connector (2, 3), Color.RED));
		b.add(new Connector(2, 3), Color.RED);
		assertFalse(b.isOK()); // isOK returns false because a triangle is formed.
		
		// The next connector that computer chooses can never be a connector that already exists.
		assertFalse(b.choice().equals(new Connector(1, 2))); 
		
		// Computer never chooses a connector that forms a blue triangle when there are other options.
		assertFalse(b.choice().equals(new Connector(5, 6))); 
	}
}