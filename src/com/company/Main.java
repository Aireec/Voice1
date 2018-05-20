package com.company;

import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
public class Main {

    public static void main(String[] args) {


        ConfigurationManager cm = new ConfigurationManager();

        Recognizer recognizer = cm.lookup("recognizer");

        Microphone microphone = cm.lookup("microphone");
        if (microphone.startRecording()) {
            System.out.println("Cannot start microphone");
            recognizer.deallocate();
            System.exit(1);
        }

        System.out.println("Listening for letters and numbers");

        while (true) {
            System.out.println("STart speaking. Press Ctrl-C to quit");

            Result result = recognizer.recognize();

            if (result!= null) {
                String resultText = result.getBestFinalResultNoFiller();

                System.out.println("You said " + resultText + '\n');
            }else{
                System.out.println("I cant hear what you said.\n");
            }
        }
    }
}
