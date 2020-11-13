# Concurrent Programming

Contains class materials submitted by Linh Nguyen for Rice University's 
Concurrent Programming course
## Concepts
### Thread v. Process
All threads share same resources, such as memory. Threads have accesses to
other
threads' variables and state while different processes cannot access any other 
process except their own. In a single core processor, the processor switch 
between threads and execute the tasks inside the threads. From a user's point of
view, the threads are running instantaneously.

### Structured Locks
Structured locks are implemented using `synchronized` statements and methods in 
Java. Structured locks can be used to enforced mutual exclusion and avoid data 
races. The *acquire* and *release of locks* are implicit; they are executed by 
the Java Runtime Environment JRE when entering and exiting the scope of 
`synchronized` statements and methods. Structured locks are known as **intrinsic
locks** or **monitors**

### Unstructured Locks
Unstructured locks allow more freedom in when to *acquire* and *release lock*. 
*Trylock* is another operation that comes with unstructured locks; it allows 
thread to do something else if the lock is not available.

  - **Read-Write Locks**
    - Same as unstructured locks with a few additional restrictions. Multiple 
    threads can acquire the lock in **read** mode, but only one thread can 
    acquire the lock in **write** mode. This allow multiple threads to share 
    the same resources and only block the resources when it makes sense to do 
    so.

### Deadlock
Occurs when threads are waiting on each other and no actions are taken as a
result. For example, thread A is holding lock 1, and waits for thread B to
release lock 2 while thread B is waiting for thread A to release lock 1.

### Livelock
Threads are stuck in an infinite loop where no progress is made.

### Isolation

  - **Object Isolation**
    - An isolated construct can be extended with a set of objects that
    indicate the scope of isolation by using the following rules:
      - If 2 isolated constructs have an empty intersection in their object
        sets, then they can execute in parallel. Otherwise, they must execute
        in mutual exclusion
    - Is generally faster than global isolation

### Atomicity
The concept of all or nothing in order to prevent data races. If a method is 
atomic, and it fails, all the steps should rollback.

  - **Atomic Integer's Get-and-Add**
    - An atomicity pattern that gets a variable value and add to it in a thread 
    safe manner. 
    ```
    j=cur;
    cur=cur+1;
    ```

  - **Atomic Reference's Compare-and-Set**
    - An atomicity pattern that compare a variable value to an expected value, 
    and set it to a new value if the condition is met in a thread safe manner. 
    ```
    if (j == expected) {
      j = new;
      return true;
    }
    return false;
    ```

*Note: Instead of implementing atomicity on a low level using locks, it's better
to use Atomic Integer and Atomic Reference patterns whenever possible because 
these classes are implemented efficiently on the current hardware.*

## Mini Project 1
Learn how to use [ReentrantLock](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/ReentrantLock.html) 
and [ReentrantReadWriteLock](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/ReentrantReadWriteLock.html) 
as **unstructured** locks and **unstructured read write** locks.

## Mini Project 2
Use PCDP's [isolated
method](https://habanero-rice.github.io/PCDP/edu/rice/pcdp/PCDP.html) to 
implement object-based isolation.
