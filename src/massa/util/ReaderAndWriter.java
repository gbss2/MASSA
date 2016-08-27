package massa.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class ReaderAndWriter {

	// constructor
	public ReaderAndWriter() {
	}

	// methods

	// file read test

	public ArrayList<String> fileReadSpaced(String fileToRead) {
		ArrayList<String> fileAL = new ArrayList<String>();

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileToRead));

			String line;
			while ((line = br.readLine()) != null) {
				fileAL.add(line);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto- generated catch block
			e.printStackTrace();

		}
		return fileAL;
	}

	// filereader
	public ArrayList<String> fileRead(String fileToread) {

		File file = new File("" + fileToread);

		Scanner input;
		ArrayList<String> fileAL = new ArrayList<String>();
		try {
			input = new Scanner(file);

			while (input.hasNext()) {

				fileAL.add(input.next());

			}
			input.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fileAL;

	}

	// g zipped file reader
	public ArrayList<String> gzipFileRead(String fileToread) {

		ArrayList<String> fileAL = new ArrayList<String>();
		BufferedReader br = null;
		String readed;
		try {
			GZIPInputStream gzip = new GZIPInputStream(new FileInputStream(fileToread));
			br = new BufferedReader(new InputStreamReader(gzip));

			while ((readed = br.readLine()) != null) {

				fileAL.add(readed);
			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return fileAL;

	}

	// arraylist of string file writer
	public void fileWriter(String filePath, ArrayList<String> output) {

		try {
			// Create file
			FileWriter fstream = new FileWriter(filePath);
			BufferedWriter out = new BufferedWriter(fstream);

			Iterator<String> outputIT = output.iterator();
			while (outputIT.hasNext())
				out.write(outputIT.next() + "\n");
			out.newLine();
			// Close the output stream
			out.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

	}
}
