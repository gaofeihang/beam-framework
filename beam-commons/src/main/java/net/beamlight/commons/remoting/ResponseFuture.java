package net.beamlight.commons.remoting;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gaofeihang
 * @since Feb 12, 2015
 */
public class ResponseFuture implements Future<RemotingResponse> {
    
    private static final Logger logger = LoggerFactory.getLogger(ResponseFuture.class); 
    
    private static ConcurrentHashMap<Integer, ResponseFuture> futureMap = new ConcurrentHashMap<Integer, ResponseFuture>();
    
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    
    private Integer requestId;
    private volatile RemotingResponse response;
    
    public ResponseFuture(Integer requestId) {
        this.requestId = requestId;
        
        if (futureMap.putIfAbsent(requestId, this) != null) {
            logger.warn("requestId conflict: {}, thread: {}", requestId, Thread.currentThread().getName());
        }
    }
    
    public static void receiveResponse(RemotingResponse response) {
        Integer requestId = response.getId();
        ResponseFuture future = futureMap.remove(requestId);
        if (future != null) {
            future.setResponse(response);
        }
    }
    
    public void setResponse(RemotingResponse response) {
        this.response = response;
        
        try {
            lock.lock();
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public RemotingResponse get() throws InterruptedException, ExecutionException {
        
        try {
            lock.lock();
            condition.await();
            return response;
        } finally {
            futureMap.remove(requestId);
            lock.unlock();
        }
    }

    @Override
    public RemotingResponse get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        
        try {
            lock.lock();
            condition.await(timeout, unit);
            return response;
        } finally {
            futureMap.remove(requestId);
            lock.unlock();
        }
    }

}
