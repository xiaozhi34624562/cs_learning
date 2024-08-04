package org.wmz;

import java.io.File;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wwm
 * @date 2024/08/04  20:07
 * 读取5g的文件，只需要5s
 */
public class FileReaderByFileChannel {
    
    public File inputFile = new File("measure.txt");
    
    public FileReaderByFileChannel(File file) {
        this.inputFile = file;
    }
    
    public long count(char letter) throws IOException, InterruptedException {
        FileChannel fileChannel = FileChannel.open(inputFile.toPath(), StandardOpenOption.READ);
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        long chunkSize = fileChannel.size() / numberOfThreads;
        List<CounterThread> threads = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            long start = i*chunkSize;
            MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, start, chunkSize);
            CounterThread counterThread = new CounterThread(buffer, (byte)letter);
            threads.add(counterThread);
            counterThread.start();
            
        }
        long result = 0;
        for (CounterThread thread : threads) {
            thread.join();
            result += thread.count();
        }
        return result;
    }
}
