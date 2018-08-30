import java.awt.Color;
import java.util.*;

public class Board {
	
	public static boolean iAmDebugging = true;
	
	private ArrayList<Connector> connectorStore;
	private ArrayList<Color> colors;
	
	// Initialize an empty board with no colored edges.
	
	public Board ( ) {
		
		connectorStore = new ArrayList<Connector>(15);
		// Store all possible connectors to connectorStore ArrayList.
		for (int j = 1; j <= 6; j++) {
			for (int k = j+1; k <= 6; k++) {
				connectorStore.add(new Connector(j,k));
			}
		}
		
		colors = new ArrayList<Color>(15);
		// Set all colors to WHITE.
		for (int i = 0; i < 15; i++) {
			colors.add(i, Color.WHITE);
		}
	}
	
	
	// Add the given connector with the given color to the board.
	// Unchecked precondition: the given connector is not already chosen 
	// as RED or BLUE.
	
	public void add (Connector cnctr, Color c) {
		// Loop through connectorStore and 
		// find the index i of the Connector that equals the given connector cnctr.
		// Then in colors ArrayList, change the color at index i (originally WHITE) 
		// to the given color c.
		for (int i = 0; i < connectorStore.size(); i++) {
			if (connectorStore.get(i).equals(cnctr)) {
				colors.set(i, c);
			}
		}
	}
	
	
	// Set up an iterator through the connectors of the given color, 
	// which is either RED, BLUE, or WHITE. 
	// If the color is WHITE, iterate through the uncolored connectors.
	// No connector should appear twice in the iteration.  
	
	public Iterator<Connector> connectors (Color c) {
		// Make a new ArrayList that only contains the connectors of the given color c.
		ArrayList<Connector> connectorsColor = new ArrayList<Connector>();
		for (int i = 0; i < connectorStore.size(); i++) {
			if (colors.get(i) == c) {
				Connector con = connectorStore.get(i);
				connectorsColor.add(con);
			}
		}
		return connectorsColor.iterator();
	}
	
	
	// Set up an iterator through all the 15 connectors.
	
	public Iterator<Connector> connectors ( ) {
		return connectorStore.iterator();
	}
	
	
	// Return the color of the given connector.
	// If the connector is colored, its color will be RED or BLUE;
	// otherwise, its color is WHITE.
	
	public Color colorOf (Connector e) {
		// First, find the index of the connector in the connectorStore ArrayList 
		// that equals the given connector e.
		// Second, return the color at the index of the colors ArrayList.
		for (int i = 0; i < connectorStore.size(); i++) {
			if (connectorStore.get(i).equals(e)) {
				return colors.get(i);
			}
		}
		return null; 					// so that the method returns something
	}
	
	
	// Unchecked prerequisite: cnctr is an initialized uncolored connector.
	
	public boolean formsTriangle (Connector cnctr, Color c) {
		// Let p1 and p2 be the endpoint of the given connector cnctr. 
		// Because of how connectors are implemented, p2 is greater than p1.
		int p1 = cnctr.endPt1();
		int p2 = cnctr.endPt2();
		for(int p3=1; p3<=6; p3++) {	// Loop through all possible points, 1 to 6.
			if (p3==p1 || p3==p2) {
				continue;				// Skip if p3 is equal to p1 or p2.
			}

			Connector tempConnector1 = new Connector(p1, p3);
			Connector tempConnector2 = new Connector(p2, p3);
			
			if (colorOf(tempConnector1) == c && colorOf(tempConnector2) == c) {
				return true;
			}
		}
		return false;
	}	
		
	
	// The computer (playing BLUE) wants a move to make.
	
	public Connector choice ( ) {
		Connector temp; 
		// Iterator for the uncolored (WHITE) connectors
		Iterator<Connector> iter = connectors(Color.WHITE);
		ArrayList<Connector> tempConnectors1 = new ArrayList<Connector>();
		ArrayList<Connector> tempConnectors2 = new ArrayList<Connector>();
		ArrayList<Connector> tempConnectors3 = new ArrayList<Connector>();
		
		while (iter.hasNext()) {
			temp = iter.next();
			if (formsTriangle(temp, Color.BLUE)) {
				// Store all the connectors that form triangles with BLUE connectors 
				// in tempConnectors1 ArrayList.
				tempConnectors1.add(temp);	
			} else if (formsTriangle(temp, Color.RED)) {
				// Store all the connectors that form triangles with RED connectors 
				// (but not with BlUE connectors) in tempConnectors2 ArrayList.
				tempConnectors2.add(temp);
			} else {
				// Store the rest of the connectors (that do not form triangles with any color)
				// in tempConnectors3 ArrayList.
				tempConnectors3.add(temp);
			}
		}
		
		// Return a random connector in tempConnectors3 ArrayList 
		// If none is stored, then return the first connector in tempConnectors2 ArrayList.
		// If none is stored in tempConnectors2, return the first connector in tempConnectors1.
		if (tempConnectors3.size() > 0) {
			Random r = new Random();
			int randomInt = r.nextInt(tempConnectors3.size());
			return tempConnectors3.get(randomInt);
		} else if (tempConnectors2.size() > 0) {
			return tempConnectors2.get(0);
		} else {
			return tempConnectors1.get(0);
		}
	}	

	
	// isFormingTriangle returns formsTriangle.
	// The difference is solely on the usage:  
	// isFormingTriangle is used in isOK and takes colored connectors as its first argument, whereas 
	// formsTriangle has an unchecked prerequisite to take uncolored connectors as its first argument.
	
	private boolean isFormingTriangle(Connector cnctr, Color c) {
		return formsTriangle(cnctr, c);
	}

	
	// Unchecked prerequisites:
	//	Each connector in the board is properly initialized so that 
	// 	1 <= myPoint1 < myPoint2 <= 6.
	
	public boolean isOK ( ) {
		int redCount = 0;
		int blueCount = 0;
		int whiteCount= 0;
		
		for (int i = 0; i < colors.size(); i++) {
			if (colors.get(i) == Color.RED) {
				redCount ++;
			} else if (colors.get(i) == Color.BLUE) {
				blueCount ++;
			} else if (colors.get(i) == Color.WHITE)
				whiteCount ++;
			}
		
		
		// Check for RED and BLUE count:
		// there can't be more BLUE than RED, 
		// nor can the difference between the two colors be greater than 1.
		if (blueCount > redCount) {
			return false;
		} else if (redCount > blueCount + 1) {
			return false;
		}
		
		// The sum of RED, BLUE and WHITE colors must equal 15.
		if (redCount + blueCount + whiteCount != 15) {
			return false;
		}
		
		
		// Check for duplicate connectors.
		// Loop from the first connector to check 
		// if that connector equals any connectors that follows .
		for (int i = 0; i < connectorStore.size(); i++) {
			for (int j = i + 1; j < connectorStore.size(); j++) {
				if (connectorStore.get(i).equals(connectorStore.get(j))) {
					return false;
				}
			}	
		}	
		
		
		// Check for triangles in each color.
		Iterator<Connector> itr1 = connectors(Color.BLUE);
		while (itr1.hasNext()) {
			if (isFormingTriangle (itr1.next(), Color.BLUE)) {
				return false;
			}
		}
		
		Iterator<Connector> itr2 = connectors(Color.RED);
		while (itr2.hasNext()) {
			if (isFormingTriangle (itr2.next(), Color.RED)) {
				return false;
			}
		}
		
		return true;
	}
}

