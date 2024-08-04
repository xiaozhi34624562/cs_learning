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
 * @date 2024/08/04  20:10
 * 读入5g的文件，只需要3.4s
 */
public class FileReadByFileChannelWithVirtualThread {
    
    public File inputFile = new File("measure.txt");
    
    public FileReadByFileChannelWithVirtualThread(File file) {
        this.inputFile = file;
    }
    
    /**
     * 需要在jdk21的版本上才可以使用
     * @param letter
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public long count(char letter) throws IOException, InterruptedException {
        FileChannel fileChannel = FileChannel.open(inputFile.toPath(), StandardOpenOption.READ);
        int numberOfThreads = Runtime.getRuntime().availableProcessors() * 1000;
        long chunkSize = fileChannel.size() / numberOfThreads;
        List<Thread> threads = new ArrayList<>();
        List<CounterThread> runnables = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            long start = i * chunkSize;
            MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, start, chunkSize);
            CounterThread runnable = new CounterThread(buffer,                    (byte) letter);
            runnables.add(runnable);
            Thread counterThread =  Thread.startVirtualThread(runnable);
            threads.add(counterThread);
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long result = 0L;
        for (CounterThread runnable : runnables) {
            result+=runnable.count();
        }
        return result;
    }
}
