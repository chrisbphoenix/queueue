package queuemodel;

import java.util.Random;

public class UniformCashier
{
    private ServiceQueue myServiceQueue;
    private int myRandomServiceTime;
    private Random myRand;
    
    public UniformCashier(int maxTime, ServiceQueue serviceQueue)
    {
        myServiceQueue = serviceQueue;
        myRand = new Random(maxTime);
    }
    
    public int generateServiceTime()
    {
        myRandomServiceTime = myRand.nextInt();
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
