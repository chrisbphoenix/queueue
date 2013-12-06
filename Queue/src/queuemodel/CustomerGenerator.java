package queuemodel;

import java.util.Random;

public class CustomerGenerator extends UniformCustomerGenerator implements Runnable
{
	private int myMaxTimeBetweenCustomers, myRandomTimeBetweenCustomers, myMaxNumberCustomers;
	private ServiceQueue myShortestServiceQueue;
	private Thread myThread;

	/**
	 * Constructor for the CustomerGenerator. Calls the super constructor and
	 * passes the maxTimeBetweenCustomers and serviceQueueManager. Defaults the
	 * myMaxNumberCustomers value to 500. Sets the max time between customers to
	 * maxTimeBetweenCustomers.
	 * 
	 * @param maxTimeBetweenCustomers
	 * @param serviceQueueManager
	 */
	public CustomerGenerator(int maxTimeBetweenCustomers, ServiceQueueManager serviceQueueManager)
	{
		super(maxTimeBetweenCustomers, serviceQueueManager);
		myMaxTimeBetweenCustomers = maxTimeBetweenCustomers;
		myMaxNumberCustomers = 500;
		myThread = new Thread(this);
	}

	/**
	 * Returns the time the generator takes between generation of customers,
	 * using the super class generateTimeBetweenCustomers()
	 * 
	 * @return Int generated time
	 */
	public int generateTimeBetweenCustomers()
	{
		return super.generateTimeBetweenCustomers();
	}

	/**
	 * generateCustomer method. Creates a new Customer and returns it.
	 * 
	 * @return Customer newCustomer
	 */
	public Customer generateCustomer()
	{
		Customer newCustomer = new Customer();
		return newCustomer;
	}

	public void run()
	{
		while (myMaxNumberCustomers > 0)
		{
			myShortestServiceQueue = super.getSQM().determineShortestQueue();
			myShortestServiceQueue.insertCustomer(this.generateCustomer());
			myMaxNumberCustomers--;
		}

		try
		{
			Thread.sleep(this.generateTimeBetweenCustomers());
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start()
	{
		try
		{
			myThread.start();
		} catch (IllegalThreadStateException e)
		{
			System.out.println("Thread already started");
		}
	}

	/**
	 * Returns the max time between customers
	 * 
	 * @return int myMaxTimeBetweenCustomers
	 */
	public int getMyMaxTimeBetweenCustomers()
	{
		return myMaxTimeBetweenCustomers;
	}

	/**
	 * Generates a pseudorandom time that the generator will take between
	 * customer generation, bounded by the maxTimeBetweenCustomers Calls
	 * generateTimeBetweenCustomers()
	 * 
	 * @return int random time between customers.
	 */

	
	public void setMaxNumberCustomers(int maxNumberCustomers)
	{
		myMaxNumberCustomers = maxNumberCustomers;
	}
}
