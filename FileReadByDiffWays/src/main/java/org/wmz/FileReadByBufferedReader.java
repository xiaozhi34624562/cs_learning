package org.wmz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author wwm
 * @date 2024/08/04  20:03
 * 读取5G的文件只需要 70s
 */
public class FileReadByBufferedReader {
    
    private File inputFile = new File("measure.txt");
    
    public FileReadByBufferedReader(File inputFile) {
        this.inputFile = inputFile;
    }
    
    /**
     * 没有使用br.readLine(),文本可能没有分行，这种会导致OOM的
     * @param letter
     * @return
     * @throws IOException
     */
    public long count(char letter) throws IOException {
        long result = 0L;
        try (BufferedReader br = new BufferedReader(new FileReader(this.inputFile))) {
            int content;
            while ((content = br.read()) != -1) {
                if (content == letter) {
                    result++;
                }
            }
        }
        return result;
    }
}
