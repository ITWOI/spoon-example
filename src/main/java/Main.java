
import spoon.Launcher;
import spoon.processing.ProcessingManager;
import spoon.reflect.factory.Factory;
import spoon.support.QueueProcessingManager;
import spoon.reflect.declaration.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;



public class Main {
    public  static String[] parseBuggyFile(String fileName) {
		BufferedReader reader;
		String[] res = null;
        if (fileName == null){
            return null;
        }
		try {
			reader = new BufferedReader(new FileReader(fileName));

			String line = reader.readLine();
			res = line.split("#");
			line = reader.readLine();
			if(line != null) {
				System.out.print("Multiple lines in the buggy file.");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static CtMethod returnBuggyLoc(String [] buggyInfo, final Factory factory) {
        if (buggyInfo == null) {
            return null;
        }
		final ProcessingManager processingManager = new QueueProcessingManager(factory);
		final fileLocProcessor processor = new fileLocProcessor(buggyInfo);
		processingManager.addProcessor(processor);
		processingManager.process(factory.Class().getAll());
		return processor.method;
	}

	public static void main(String [] args) {
		final String[] myArg = {
				//"-i", "/Users/fff000/Downloads/jfreechart-1.0.19",
				"-i", "src/test/java/GGNNTest",
				"-o", "target/spooned/",
		};
		//String buggyLines = "test.buggy.lines";
		if (args.length != 3 && args.length != 2) {
			System.out.print("args: inputdir [buggyFile] output.xml\n");
			return;
		}
		String buggyLines = null;
		String outputXML = "";
		if (args.length == 3) {
            myArg[1] = args[0];
            buggyLines = args[1];
            outputXML = args[2];
        }
        else if (args.length == 2) {
            myArg[1] = args[0];
            outputXML = args[1];
        }


		String[] buggyInfo = parseBuggyFile(buggyLines);

		final Launcher launcher = new Launcher();
		launcher.setArgs(myArg);
		//launcher.run();
		launcher.buildModel();


		int i = 0;
		final Factory factory = launcher.getFactory();

		CtMethod buggyMethod = returnBuggyLoc(buggyInfo, factory);
	}
}
