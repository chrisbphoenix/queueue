package queuemodel;

import java.util.Random;

public class UniformCashier
{
    private ServiceQueue myServiceQueue;
    private int myRandomServiceTime;
    private int maxDelay;
    private Random myRand;
    
    public UniformCashier(int maxTime, ServiceQueue serviceQueue)
    {
        myServiceQueue = serviceQueue;
        maxDelay = maxTime;
        myRand = new Random();
    }
    
    public int generateServiceTime()
    {
        myRandomServiceTime = myRand.nextInt(maxDelay);
        return myRandomServiceTime;
    }
    
    public ServiceQueue getServiceQueue()
    {
        return myServiceQueue;
    }
    
    public int getRandomServiceTime()
    {
        return myRandomServiceTime;
    }
}
