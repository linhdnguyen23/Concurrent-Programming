# Concurrent Programming

Contains class materials submitted by Linh Nguyen for Rice University's Concurrent
Programming course
## Concepts
### Thread v. Process
All threads share same resources, such as memory. Threads have accesses to other threads' 
variables and state while different processes cannot access any other process except their 
own. In a single core processor, the processor switch between threads and execute the tasks 
inside the threads. From a user's point of view, the threads are running instantaneously.

### Structured Locks
Structured locks are implemented using `synchronized` statements and methods in Java. 
Structured locks can be used to enforced mutual exclusion and avoid data races. The *acquire* 
and *release of locks* are implicit; they are executed by the Java Runtime Environment JRE 
when entering and exiting the scope of `synchronized` statements and methods. Structured locks are known as **intrinsic locks** or **monitors**

### Unstructured Locks
Unstructured locks allow more freedom in when to *acquire* and *release lock*. *Trylock* 
is another operation that comes with unstructured locks; it allows thread to do something 
else if the lock is not available.

#### Read-Write Locks
Same as unstructured locks with a few additional restrictions. Multiple threads can acquire 
the lock in **read** mode, but only one thread can acquire the lock in **write** mode. This 
allow multiple threads to share the same resources and only block the resources when it makes 
sense to do so.

## Mini Project 1
Learn how to use [ReentrantLock](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/ReentrantLock.html) and [ReentrantReadWriteLock](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/ReentrantReadWriteLock.html) as **unstructured** locks and 
**unstructured read write** locks.
