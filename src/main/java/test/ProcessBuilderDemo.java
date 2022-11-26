package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ProcessBuilderDemo {
    public static void main(String[] args) throws Exception {
    	    	
        // 1. ProcessBuilderインスタンスを生成する
    	// cmd /c をつけないとIOエラーになった
        ProcessBuilder pb1 = new ProcessBuilder("cmd", "/c", "cd");
        File dir = new File("C:/ローカルドキュメント/work space/spring_practice/forest-demo/bat");
        pb1.directory(dir);
        pb1.command("cmd", "/c", "javac Test.java");

        // 2. プロセスを開始する
        Process process = pb1.start();
        
        process.waitFor();
    	int ret = process.exitValue();
    	System.out.println("戻り値：" + ret);
        // 3. 結果を受け取る
    	if("0".equals(ret)) {
            try (BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.defaultCharset()))) {
                String line;
                while ((line = r.readLine()) != null) {
                    System.out.println(line);
                }
            }
    	} else {
            try (BufferedReader r = new BufferedReader(new InputStreamReader(process.getErrorStream(), Charset.defaultCharset()))) {
                String line;
                while ((line = r.readLine()) != null) {
                    System.out.println(line);
                }
            }
            return;
    	}
        
    	// waitFor使うために別プロセスにした方がよい？
    	ProcessBuilder pb2 = new ProcessBuilder("cmd", "/c", "cd");
        pb2.directory(dir);
    	pb2.command("cmd", "/c", "java Test");

        // 2. プロセスを開始する
        process = pb2.start();
        process.waitFor();
    	ret = process.exitValue();
        // 3. 結果を受け取る
    	if("0".equals(ret)) {
            try (BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.defaultCharset()))) {
                String line;
                while ((line = r.readLine()) != null) {
                    System.out.println(line);
                }
            }
    	} else {
            try (BufferedReader r = new BufferedReader(new InputStreamReader(process.getErrorStream(), Charset.defaultCharset()))) {
                String line;
                while ((line = r.readLine()) != null) {
                    System.out.println(line);
                }
            }
            return;
    	}
    }
}
