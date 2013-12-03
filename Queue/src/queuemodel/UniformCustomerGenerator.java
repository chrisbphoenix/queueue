package queuemodel;

import java.util.Random;

public class UniformCustomerGenerator
{
	private Random myRandom;
	private ServiceQueueManager mySQM;

	public UniformCustomerGenerator(int maxTimeBetweenCustomers, ServiceQueueManager serviceQueueManager)
	{
		myRandom = new Random(maxTimeBetweenCustomers);
		mySQM = serviceQueueManager;
	}
	
	public int generateTimeBetweenCustomers()
	{
		return myRandom.nextInt();
	}
	
	public ServiceQueueManager getSQM()
	{
		return mySQM;
	}
}
