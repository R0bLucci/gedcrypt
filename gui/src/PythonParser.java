/**
 * Created by Robert on 11/10/2015.
 */

//import java.io.*;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;


public class PythonParser {

    private String[] cmd;
    private static PythonParser pythonParser;

    private PythonParser(){
        cmd = new String[5];
    }

    //singleton system
    public static PythonParser getPythonParser(){
        if(pythonParser == null){
            pythonParser = new PythonParser();
        }
        return pythonParser;
    }

    public void hash(String option, String filename, String pass) {

        try {
            String pythonPath = new File("").getAbsolutePath() + "/edcrypt.py";
            cmd[0] = "python"; // python interpreter
            cmd[1] = pythonPath; // encryption and decryption python script location
            cmd[2] = option; // option to either run the encrypt or decrypt python script
            cmd[3] = filename; // full path to the file to encrypt or decrypt
            cmd[4] = pass; // password provided by the user

            Runtime runtime = Runtime.getRuntime();
            runtime.exec(cmd);

            /*
            Code to get console output from Python to Java for debugging purposes
             */

            //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            //String outPut = "";
            //while ((outPut = bufferedReader.readLine()) != null) {
            //  System.out.println(outPut);
            //}

        }catch (IOException e){
            //System.out.println(e);
        }

    }
}
