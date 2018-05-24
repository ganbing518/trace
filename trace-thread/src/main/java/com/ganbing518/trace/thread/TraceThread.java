package com.ganbing518.trace.thread;

/**
 * Description:
 *
 * @author gan bing
 * @version V1.0
 * @date 2018-05-24 19:18
 */
public class TraceThread extends Thread{

    public TraceThread() {
        super();
    }

    public TraceThread(Runnable target) {
        super(target);
    }

    public TraceThread(ThreadGroup group, Runnable target) {
        super(group, target);
    }

    public TraceThread(String name) {
        super(name);
    }

    public TraceThread(ThreadGroup group, String name) {
        super(group, name);
    }

    public TraceThread(Runnable target, String name) {
        super(target, name);
    }

    public TraceThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
    }

    public TraceThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
    }

    @Override
    public void run() {
        super.run();
    }
}
