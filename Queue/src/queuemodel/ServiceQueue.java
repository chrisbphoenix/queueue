package queuemodel;

public class ServiceQueue
{
    private int myNumberCustomersServedSoFar;
    private int myNumberCustomersInLine;
    private int myTotalWaitTime;
    private int myTotalServiceTime;
    private int myTotalIdleTime;
    private Queue<Customer> myQueue;

    public ServiceQueue()
    {
        myQueue = new Queue<Customer>();
        myNumberCustomersServedSoFar = 0;
        myNumberCustomersInLine = 0;
        myTotalWaitTime = 0;
        myTotalServiceTime = 0;
        myTotalIdleTime = 0;
    }

    public void addToElapsedTime(int time)
    {
        
    }

    public void addToIdleTime(int time)
    {
        myTotalIdleTime += time;
    }

    public void addToWaitTime(int time)
    {
        myTotalWaitTime += time;
    }

    public void addToServiceTime(int time)
    {
        myTotalServiceTime += time;
    }

    public void insertCustomer(Customer newCustomer)
    {
        myQueue.enqueue(newCustomer);
        myNumberCustomersInLine++;
    }

    public Customer serveCustomer()
    {
        if (myQueue.hasNext())
        {
        	myNumberCustomersInLine--;
            return myQueue.dequeue();
        }
        return null;
    }

    public int averageWaitTime()
    {
        return myTotalWaitTime / myNumberCustomersServedSoFar;
    }

    public long averageServiceTime()
    {
        return myTotalServiceTime / myNumberCustomersServedSoFar;
    }

    public long averageIdleTime()
    {
        return myTotalIdleTime / myNumberCustomersServedSoFar;
    }
    // ///////////////////////////////////////
    // Accessors
    // ///////////////////////////////////////
    
    public int getTotalServed()
    {
        return myNumberCustomersServedSoFar;
    }
    
    public int getTotalWait()
    {
        return myTotalWaitTime;
    }
    
    public int getTotalService()
    {
        return myTotalServiceTime;
    }
    
    public int getTotalIdle()
    {
        return myTotalIdleTime;
    }
    
    public int getTotalCustomersInLine()
    {
    	return myNumberCustomersInLine;
    }
    
    public int getNumberCustomersServedSoFar()
    {
    	return this.myNumberCustomersServedSoFar;
    }
}
