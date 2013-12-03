package queuemodel;

public class CustomerGenerator extends UniformCustomerGenerator implements Runnable 
{
	private int myMaxTimeBetweenCustomers;
	
	public CustomerGenerator(int maxTimeBetweenCustomers, ServiceQueueManager serviceQueueManager)
	{
	    super(maxTimeBetweenCustomers, serviceQueueManager);
		myMaxTimeBetweenCustomers = maxTimeBetweenCustomers;
	}
	
	public int generateTimeBetweenCustomers()
	{
		return 0;
	}
	
	public Customer generateCustomer()
	{
		return null;
	}
	
	public void run()
	{
		
	}
	
	public void start()
	{
		
	}
	
	public Customer getCustomer()
	{
		return null;
	}
	
	public int getTimeBetweenCustomers()
	{
		return 0;
	}
}
