package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class test {
    public static void main(String[] args) throws Exception {
        // 1. ProcessBuilderインスタンスを生成する
//        ProcessBuilder p = new ProcessBuilder("java", "-version");
        ProcessBuilder p = new ProcessBuilder("cmd", "/c", "echo", "zzz");
        // 2. プロセスを開始する
        Process process = p.start();

        // 3. 結果を受け取る
        try (BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.defaultCharset()))) {
            String line;
            while ((line = r.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
