package queuemodel;

public class Customer
{
    private long myServiceTime, myEntryTime, myWaitTime;
    
    public Customer()
    {
        myServiceTime = 0;
        myWaitTime = 0;
        myEntryTime = System.currentTimeMillis();
    }
    
    public long getMyServiceTime()
    {
        return myServiceTime;
    }
    
    public long getMyEntryTime()
    {
        return myEntryTime;
    }
    
    public long getMyWaitTime()
    {
        return myWaitTime;
    }
    
    public void setMyServiceTime(int serviceTime)
    {
        myServiceTime = serviceTime;
    }
    
    public void setMyEntryTime(int entryTime)
    {
        myEntryTime = entryTime;
    }
    
    public void setMyWaitTime(int waitTime)
    {
        myWaitTime = waitTime;
    }
}
