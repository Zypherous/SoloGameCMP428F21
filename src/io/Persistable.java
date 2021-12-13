package io;

public interface Persistable {

	String DELIMITER = ":";
	String SECTION_DELIMITER = System.lineSeparator() + "###" + System.lineSeparator();
	
	String LIST_DELIMITER = ", ";
	String COLUMN_DELIMITER  = System.lineSeparator();
	
	String serialize();
	void applySerializedData(String serializedData);
}




/*Serialization is the process of converting an object into a stream of bytes
 *  to store the object or transmit it to memory, a database, or a file. Its
 *   main purpose is to save the state of an object in order to be able to 
 *   recreate it when needed. The reverse process is called deserialization
 *   
 *   This does not work well with saving files like maps for my purpose
 */