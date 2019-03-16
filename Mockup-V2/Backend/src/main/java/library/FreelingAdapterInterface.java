package library;

import java.io.IOException;

public interface FreelingAdapterInterface {
	/**
	 * 
	 * @param sentence
	 * @return
	 */
	String getCorrection(String sentence);

	/**
	 * 
	 * @throws IOException
	 */
	void closeConnection() throws IOException;
}
