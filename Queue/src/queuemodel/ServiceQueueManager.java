package queuemodel;

public class ServiceQueueManager
{
    public static final int MAX_NUMBER_OF_QUEUES = 5;
    private int myNumberOfServiceLines;
    private ServiceQueue[] myServiceQueues;
    private Cashier[] myCashiers;
    private CustomerGenerator myCustomerGenerator;
    private long myTotalWaitTime;
    private long myTotalServiceTime;
    private long myTotalIdleTime;
    private double myAverageWaitTime;
    private double myAverageServiceTime;
    private long myAverageIdleTime;
    private long myPresentTime;
    private long myStartTime;
    
    public ServiceQueueManager(int serviceLines, int maxTimeBetweenCustomers, int maxTimeService, int checkTime)
    {
        myNumberOfServiceLines = serviceLines;
        myServiceQueues = new ServiceQueue[myNumberOfServiceLines];
        myCashiers = new Cashier[myNumberOfServiceLines];
        myCustomerGenerator = new CustomerGenerator(maxTimeBetweenCustomers, this);
        
        for(int i = 0; i < myNumberOfServiceLines; i++)
        {
        	myServiceQueues[i] = new ServiceQueue();
        	myCashiers[i] = new Cashier(maxTimeService, checkTime, myServiceQueues[i]);
        }
    }
    
    /**
     * Default ServiceQueueManager constructor. Creates three service lines, and initializes the 
     * maximum time between customer generation, customer service and the time between the cashier
     * checking for a new customer to 100 milliseconds. 
     */
    public ServiceQueueManager()
    {
    	myNumberOfServiceLines = 3;
    	myServiceQueues = new ServiceQueue[myNumberOfServiceLines];
    	myCashiers = new Cashier[myNumberOfServiceLines];
    	myCustomerGenerator = new CustomerGenerator(100, this);
    	
    	for(int i = 0; i < myNumberOfServiceLines; i++)
    	{
    		myServiceQueues[i] = new ServiceQueue();
    		myCashiers[i] = new Cashier(100, 100, myServiceQueues[i]);
    	}
    }
    
    /**
     * Returns the total number of customers served 
     * @return int total served
     */
    public int totalServedSoFar()
    {
        int total = 0;
        for(ServiceQueue sq : myServiceQueues)
        {
            total += sq.getTotalServed();
        }
        
        return total;
    }
    
    /**
     * Returns the total amount of time the simulation had waiting customers
     * @return
     */
    public int totalWaitTime()
    {
        int total = 0;
        for(ServiceQueue sq : myServiceQueues)
        {
            total += sq.getTotalWait();
        }
        
        return total;
    }
    
    /**
     * Returns the total amount of time spent serving customers
     * @return
     */
    public int totalServiceTime()
    {
        int total = 0;
        for(ServiceQueue sq : myServiceQueues)
        {
            total += sq.getTotalService();
        }
        
        return total;
    }
    
    /**
     * Returns the shortest Queue in the simulation. First sets the shortestQueue to the first queue, 
     * and then loops through the array of service queues, changing the shortest queue to the following
     * queue if it has fewer customers in line. 
     * @return ServiceQueue shortestQueue
     */
    public ServiceQueue determineShortestQueue()
    {
    	ServiceQueue shortestQueue = myServiceQueues[0];
    	int totalNumberCustomersInQueue = myServiceQueues[0].getTotalCustomersInLine();
    	for(ServiceQueue sq : myServiceQueues)
    	{
    		if(sq.getTotalCustomersInLine() < totalNumberCustomersInQueue)
    		{
    			totalNumberCustomersInQueue = sq.getTotalCustomersInLine();
    			shortestQueue = sq;
    		}
    	}
        return shortestQueue;
    }
    
    /**
     * Calculates the average wait time across the entire simulation
     * @return int averageWaitTime
     */
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
    
    /**
     * Calculates the average service time across the entire simulation
     * @return int averageServiceTime
     */
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
    
    /**
     * Calculates the average idle time across the entire simulation
     * @return int averageIdleTime
     */
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
    
    /**
     * Called by the ViewControl. Starts all runnable objects. 
     */
    public void startProgram()
    {
    	for(int i = 0; i < myNumberOfServiceLines; i++)
    	{
    		myCashiers[i].start();
    	}
    	
    	myCustomerGenerator.start();
    }
}
