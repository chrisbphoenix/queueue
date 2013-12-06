package queuemodel;

import java.util.Random;

public class UniformCustomerGenerator
{
	private Random myRandom;
	private ServiceQueueManager mySQM;
	private int maxDelay;

	public UniformCustomerGenerator(int maxTimeBetweenCustomers, ServiceQueueManager serviceQueueManager)
	{
		myRandom = new Random();
		maxDelay = maxTimeBetweenCustomers;
		mySQM = serviceQueueManager;
	}
	
	public long generateTimeBetweenCustomers()
	{
		return myRandom.nextInt(maxDelay);
	}
	
	public ServiceQueueManager getSQM()
	{
		return mySQM;
	}
}
