package org.wmz;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author wwm
 * @date 2024/08/04  19:53
 * 通过FileInputStream读取5g的文件， 耗时48min，非常慢
 */
public class FileReadByFileInputStream {
    
    public File inputFile = new File("measure.txt");
    
    public FileReadByFileInputStream(File file) {
        this.inputFile = file;
    }
    
    /**
     *
     * @param letter
     * @return
     * @throws IOException
     */
    public long count(char letter) throws IOException {
        long result = 0L;
        try (FileInputStream fis = new FileInputStream(this.inputFile)) {
            int content;
            while ((content = fis.read()) != -1) {
                if (content == letter) {
                    result++;
                }
            }
        }
        return result;
    }
}
