package org.codemahal.netizen.churner;

/*
 * This interface specifies the expected function to extract the information from the html content. 
 */
public interface ChurnIt {
	/*
	 * This method identifies the requested tag from the content and returns only those lines in between this tag
	 */
	public String getTag(String theContent,String tagName);
	
	/*
	 * This method extracts the title of the HTML page's code.
	 */
	public String getTitle(String theContent);

}
