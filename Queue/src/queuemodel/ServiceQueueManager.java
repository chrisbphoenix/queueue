package queuemodel;

public class ServiceQueueManager
{
    public static final int MAX_NUMBER_OF_QUEUES = 5;
    private int myNumberOfServiceQueues;
    private ServiceQueue[] myServiceQueues;
    private long myTotalWaitTime;
    private long myTotalServiceTime;
    private long myTotalIdleTime;
    private double myAverageWaitTime;
    private double myAverageServiceTime;
    private long myAverageIdleTime;
    private long myPresentTime;
    private long myStartTime;
    
    public ServiceQueueManager()
    {
        
    }
    
    public int totalServedSoFar()
    {
        int total = 0;
        for(ServiceQueue sq : myServiceQueues)
        {
            total += sq.getTotalServed();
        }
        
        return total;
    }
    
    public int totalWaitTime()
    {
        int total = 0;
        for(ServiceQueue sq : myServiceQueues)
        {
            total += sq.getTotalWait();
        }
        
        return total;
    }
    
    public int totalServiceTime()
    {
        int total = 0;
        for(ServiceQueue sq : myServiceQueues)
        {
            total += sq.getTotalService();
        }
        
        return total;
    }
    
    public ServiceQueue determineShortestQueue()
    {
        return null;
    }
    
    public int averageWaitTime()
    {
        int average = 0;
        for(ServiceQueue sq : myServiceQueues)
        {
            average += sq.getTotalWait();
        }
        average /= myServiceQueues.length;
        return average;
    }
    
    public int averageServiceTime()
    {
        int average = 0;
        for(ServiceQueue sq : myServiceQueues)
        {
            average += sq.getTotalService();
        }
        average /= myServiceQueues.length;
        return average;
    }
    
    public int averageIdleTime()
    {
        int average = 0;
        for(ServiceQueue sq : myServiceQueues)
        {
            average += sq.getTotalIdle();
        }
        average /= myServiceQueues.length;
        return average;
    }
}
