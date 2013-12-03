package queuemodel;

import java.util.LinkedList;
/**
 * Queue<T>
 * A basic implementation of a Queue. Uses generics.
 * 
 */
public class Queue<T>
{
    LinkedList<T> myQueue;
    
    public Queue()
    {
        myQueue = new LinkedList<T>();
    }
    /**
     * Adds a new element to the queue.
     * @param enqueueMe
     */
    public void enqueue(T enqueueMe)
    {
        myQueue.addFirst(enqueueMe);
    }
    /**
     * Removes the end of the line.
     * @return
     */
    public T dequeue()
    {
        return myQueue.pollLast();
    }
    
    /**
     * Returns, but does not remove the next up in the queue.
     * @return
     */
    public T peek()
    {
        return myQueue.peekLast();
    }
    
    /**
     * Returns true if there is at least one element in the queue.
     * @return
     */
    public boolean hasNext()
    {
        return !myQueue.isEmpty();
    }
}
