package queuemodel;

/**
 * Cashier Class, extends the UniformCashier class. Runs in a separate thread
 * and serves customers.
 * 
 * @author Michael
 * 
 */
public class Cashier extends UniformCashier implements Runnable
{
	private int myMaxTimeOfService, myDelay;
	private ServiceQueue myServiceQueue;
	private Thread myThread;
	private Customer myCustomer;
	private boolean myIsRunning;

	/**
	 * Cashier Constructor. Calls the UniformCashier super constructor, passing
	 * it the maxTime of service and the serviceQueue attached to the cashier.
	 * Sets the delay between checks for a customer.
	 * 
	 * @param maxTime
	 * @param checkTime
	 * @param serviceQueue
	 */
	public Cashier(int maxTime, int checkTime, ServiceQueue serviceQueue)
	{
		super(maxTime, serviceQueue);
		myMaxTimeOfService = maxTime;
		myDelay = checkTime;
		myServiceQueue = serviceQueue;
		myIsRunning = true;
		myThread = new Thread(this);
	}

	/**
	 * Checks the queue to see if there is a customer to be served. If there is
	 * a customer it calls the serviceQueue's serveCustomer method, and returns
	 * the service time that was taken serving said customer.
	 * 
	 * @return
	 */
	public int serveCustomer()
	{
		if (myServiceQueue.getTotalCustomersInLine() == 0)
		{
			return 0;
		} else
		{
			myCustomer = myServiceQueue.serveCustomer();
			myServiceQueue.addToWaitTime((int) (System.currentTimeMillis() - myCustomer.getMyEntryTime()));
			myServiceQueue.addToServiceTime(super.getRandomServiceTime());
			return generateServiceTime();
		}
	}

	/**
	 * Generates a service time that the cashier will take in serving the
	 * current customer.
	 */
	public int generateServiceTime()
	{
		return super.generateServiceTime();
	}

	@Override
	public void run()
	{
		while (myIsRunning)
		{
			this.serveCustomer();
			myServiceQueue.addToServiceTime(this.serveCustomer());
			try
			{
				Thread.sleep(this.generateServiceTime());
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
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

	// ///////////////////////////////////
	// Accessors
	// //////////////////////////////////

	public void stop()
	{
		myIsRunning = false;
	}
	
	public void setMaxTime(int maxTime)
	{
		myMaxTimeOfService = maxTime;
	}

	public void setDelay(int delay)
	{
		myDelay = delay;
	}

	public void setServiceQueue(ServiceQueue serviceQueue)
	{
		myServiceQueue = serviceQueue;
	}

	public int getMaxTime()
	{
		return myMaxTimeOfService;
	}

	public int getDelay()
	{
		return myDelay;
	}

	public ServiceQueue getServiceQueue()
	{
		return myServiceQueue;
	}
}
