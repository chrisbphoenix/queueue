package queuemodel;

import java.util.Random;

public class CustomerGenerator extends UniformCustomerGenerator implements Runnable
{
	private int myMaxTimeBetweenCustomers, myRandomTimeBetweenCustomers, myMaxNumberCustomers, myGeneratedCustomers;
	private ServiceQueue myShortestServiceQueue;
	private Thread myThread;
	private boolean myIsRunning;

	/**
	 * Constructor for the CustomerGenerator. Calls the super constructor and
	 * passes the maxTimeBetweenCustomers, maxNumberCustomers and serviceQueueManager. Sets the max time between customers to
	 * maxTimeBetweenCustomers. Sets the max number of customers to maxNumberCustomers. 
	 * 
	 * @param maxTimeBetweenCustomers
	 * @param serviceQueueManager
	 */
	public CustomerGenerator(int maxTimeBetweenCustomers, int maxNumberCustomers, ServiceQueueManager serviceQueueManager)
	{
		super(maxTimeBetweenCustomers, serviceQueueManager);
		myMaxTimeBetweenCustomers = maxTimeBetweenCustomers;
		myMaxNumberCustomers = maxNumberCustomers; 
		myIsRunning = true;
		myThread = new Thread(this);
	}

	/**
	 * Returns the time the generator takes between generation of customers,
	 * using the super class generateTimeBetweenCustomers()
	 * 
	 * @return Long generated time
	 */
	public long generateTimeBetweenCustomers()
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
		while (myGeneratedCustomers < myMaxNumberCustomers && myIsRunning)
		{
			myShortestServiceQueue = super.getSQM().determineShortestQueue();
			myShortestServiceQueue.insertCustomer(this.generateCustomer());
			myGeneratedCustomers++;
			try
	        {
	            Thread.sleep(this.generateTimeBetweenCustomers());
	        } catch (InterruptedException e)
	        {
	            System.out.println("Thread derp");
	            e.printStackTrace();
	        }
		}

		
	}

	public void start()
	{
		myIsRunning = true;
		try
		{
			myThread.start();
		} catch (IllegalThreadStateException e)
		{
			System.out.println("Thread already started");
		}
	}
	
	public void stop()
	{
		myIsRunning = false;
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
	
	public int getMaxNumberCustomers()
	{
		return myMaxNumberCustomers;
	}
	
	public int getGeneratedCustomers()
	{
		return myGeneratedCustomers;
	}
}
