package com.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class RarUtil {

    static String RAR_HOME = "";
    private static String rarCmd = RAR_HOME + "rar.exe a ";
    private static String unrarCmd = RAR_HOME + "UnRar x ";

    public static void unRARFile(String rarFileName, String destDir) {
        unrarCmd = unrarCmd + rarFileName + " " + destDir;
        try {
            Runtime rt = Runtime.getRuntime();
            Process process = rt.exec(unrarCmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader br1 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((br.readLine() != null) || (br1.readLine() != null));
            process.waitFor();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        unRARFile("H:\\product\\xc-huangheguangchang.rar", "E:\\3dpub\\proj");
    }
}