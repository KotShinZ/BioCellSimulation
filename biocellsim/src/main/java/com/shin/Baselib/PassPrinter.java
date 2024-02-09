package com.shin.Baselib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/** 指定したパスのフォルダにn番目の値を書き込むクラス */
public class PassPrinter {
    int pren = -1;
    FileWriter file;
    PrintWriter prePW = null;
    String path;

    public PassPrinter(String path) {
        this.path = path;
        var folder = getPathFromStr(path);
        CreateFolderWithExist(folder);
    }

    /// n番目のPathのPrintWriterを出力（重複対策済み）
    public PrintWriter GetPW(int n) {
        if (pren != n || prePW == null) {
            if (prePW != null) {
                // System.err.println(pren);
                prePW.close();
                try {
                    file.close();
                } catch (IOException e) {
                }

            }
            try {
                file = new FileWriter(GetPath(n));
            } catch (IOException e) {
            }
            prePW = new PrintWriter(new BufferedWriter(file));// PrintWriterクラスのオブジェクトを生成する
        }

        pren = n;
        return prePW;
    }

    /// n番目のPathを出力
    public String GetPath(int n) {
        return path + String.format("%05d", n) + ".txt";
    }

    public String getPathFromStr(String originalPath) {
        // 最後のスラッシュの位置を見つけます
        int lastIndex = originalPath.lastIndexOf('/');

        // 最後のスラッシュまでの部分を抽出します
        String trimmedPath = originalPath.substring(0, lastIndex);

        return trimmedPath;
    }

    void CreateFolderWithExist(String directoryPath) {
        // Fileオブジェクトを作成します
        File directory = new File(directoryPath);

        // フォルダが存在しない場合には作成します
        if (!directory.exists()) {
            boolean result = directory.mkdirs();
            if (result) {
                System.out.println("フォルダが正常に作成されました: " + directoryPath);
            } else {
                System.out.println("フォルダの作成に失敗しました");
            }
        } else {
            // System.out.println("フォルダは既に存在します: " + directoryPath);
        }
    }
}
