/*
* @author  Sebastian Strindlund (sest1601)
* @version 1.0
* @since   01-19-2018
*/

package dt062g.sest1601.tenta;

public class BingoBall {


	String number;
	private String imagepath;

	public BingoBall(int nr) {

		// Create a string based on what number we got
		if (nr <= 15) {
			number = "B" + Integer.toString(nr);
			imagepath = "red.png";
		}

		else if (nr <= 30 && nr > 15) {
			number = "I" + Integer.toString(nr);
			imagepath = "blue.png";
		}

		else if (nr <= 45 && nr > 30) {
			number = "N" + Integer.toString(nr);
			imagepath = "purple.png";
		}

		else if (nr <= 60 && nr > 45) {
			number = "G" + Integer.toString(nr);
			imagepath = "green.png";
		}

		else if (nr >= 61 && nr <= 75) {
			number = "O" + Integer.toString(nr);
			imagepath = "yellow.png";
		}
	}


	public String getNumber() {
		return number;
	}
	public String getImgPath() {
		return imagepath;
	}
}