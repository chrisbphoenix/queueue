package queuemodel;

import java.util.Random;

public class UniformCustomerGenerator
{
	private Random myRandom;

	public UniformCustomerGenerator(int maxTimeBetweenCustomers, ServiceQueueManager serviceQueueManager)
	{
		myRandom = new Random(maxTimeBetweenCustomers);
	}
	
	public int generateTimeBetweenCustomers()
	{
		return myRandom.nextInt();
	}
}
