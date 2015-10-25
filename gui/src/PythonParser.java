/**
 * Created by Robert on 11/10/2015.
 */

//import java.io.*;
import java.lang.*;

import org.python.core.PyString;
import org.python.util.PythonInterpreter;

public class PythonParser {

    public static void hash(String passdw, String filename) {

        PythonInterpreter py = new PythonInterpreter();
        py.set("passdw", new PyString(passdw));
        py.set("filename", new PyString(filename));


        /*
        * The following code is the Java way to run python code without using third party libraries
        *
        *

        try {
            String pythonPath = "E:\\jkCrypt\\crypto.py";
            String[] cmd = new String[2 + args.length];
            cmd[0] = "python";
            cmd[1] = pythonPath;
            for (int i = 0; i < args.length; i++) {
                cmd[i + 2] = args[i];
            }

            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(cmd);

            BufferedReader buffO = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String outPut = "";
            while ((outPut = buffO.readLine()) != null) {
                System.out.println(outPut);
            }
        }catch (IOException e){
            System.out.println(e);
        }
        */
    }
}
