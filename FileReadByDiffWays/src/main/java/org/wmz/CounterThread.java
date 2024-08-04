package org.wmz;

import java.nio.MappedByteBuffer;

/**
 * @author wwm
 * @date 2024/08/04  18:59
 */
class CounterThread extends Thread {
    private long count;
    private MappedByteBuffer buffer;
    
    private byte letter;
    
    public CounterThread(MappedByteBuffer buffer, byte letter) {
        this.buffer = buffer;
        this.letter = letter;
    }
    
    @Override
    public void run() {
        while (buffer.hasRemaining()) {
            if (buffer.get() == letter){
                count++;
            }
        }
    }
    
    public long count() {
        return count;
    }
}
