package forestdemo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class DemoProcessBuilder {
	
	private Process process;
	private File dir;
	private String fileName;
	private int result;
	
	private static final String COMMAND_CMD = "cmd";
	private static final String COMMAND_SLASH_C = "/c";
	private static final String COMMAND_CD = "cd";
	private static final String COMMAND_JAVA = "java";
	private static final String COMMAND_JAVAC = "javac";
	private static final String EXTENTION_JAVA = ".java";

	private static final int NORMAL_END = 0;
	private static final int ABNORMAL_END = 1;
	// ゲッターｾｯﾀｰいる？Bean化する？
	public File getDir() {
		return dir;
	}

	public void setDir(File dir) {
		this.dir = dir;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public DemoProcessBuilder(File dir, String fileName) {
		this.dir = dir;
		this.fileName = fileName;
	}
	
	// TODO
	// コンパイルと実行を分けた方がいいのか
	// batファイルにまとめて一処理にした方がいいのか検討
	public boolean complier() throws Exception {	

		ProcessBuilder pb = new ProcessBuilder(COMMAND_CMD, COMMAND_SLASH_C, COMMAND_CD);
		pb.directory(this.dir);
		pb.command(COMMAND_CMD, COMMAND_SLASH_C, COMMAND_JAVA, this.fileName);
		this.process = pb.start();
        process.waitFor();
        this.result = process.exitValue();
		output();
		return false;
	}
	
	public boolean runner() throws Exception {

		ProcessBuilder pb = new ProcessBuilder(COMMAND_CMD, COMMAND_SLASH_C, COMMAND_CD);
		pb.directory(this.dir);
		pb.command(COMMAND_CMD, COMMAND_SLASH_C, COMMAND_JAVAC, this.fileName + EXTENTION_JAVA);
		this.process = pb.start();
        process.waitFor();
        this.result = process.exitValue();
		output();
		return false;
	}
	
	private void output() throws IOException {
    	if(result == NORMAL_END) {
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
