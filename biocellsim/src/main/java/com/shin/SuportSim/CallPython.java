package com.shin.SuportSim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CallPython {
    public static void call(String path, String outName) {
        try {
            System.out.println("可視化を行います");

            // Pythonスクリプトへのパスと引数を指定
            String pythonScriptPath = "./Python/mains/main3.py";
            String firstArgument = path;
            String secondArgument = outName;

            // Pythonスクリプトを実行するコマンドを構築
            String[] cmd = new String[4];
            cmd[0] = "python"; // Pythonの実行コマンド
            cmd[1] = pythonScriptPath; // スクリプトのパス
            cmd[2] = firstArgument; // 最初の引数
            cmd[3] = secondArgument; // 2番目の引数

            // プロセスを実行
            Process p = Runtime.getRuntime().exec(cmd);

            // Pythonスクリプトの出力を取得
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            System.out.println("可視化が終了しました");

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
