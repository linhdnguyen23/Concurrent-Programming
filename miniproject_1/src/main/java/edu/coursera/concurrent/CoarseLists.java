package edu.coursera.concurrent;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Wrapper class for two lock-based concurrent list implementations.
 */
public final class CoarseLists {
    /**
     * An implementation of the ListSet interface that uses Java locks to
     * protect against concurrent accesses.
     *
     * Implement the add, remove, and contains methods below to support
     * correct, concurrent access to this list. Use a Java ReentrantLock object
     * to protect against those concurrent accesses. 
     */
    public static final class CoarseList extends ListSet {
        /*
         * Declare a lock for this class to be used in implementing the
         * concurrent add, remove, and contains methods below.
         */
        private final ReentrantLock lock = new ReentrantLock();

        /**
         * Default constructor.
         */
        public CoarseList() {
            super();
        }

        /**
         * {@inheritDoc}
         *
         * Use a lock to protect against concurrent access.
         */
        @Override
        boolean add(final Integer object) {
        	try {
        		lock.lock();
        		Entry pred = this.head;
                Entry curr = pred.next;
                while (curr.object.compareTo(object) < 0) {
                    pred = curr;
                    curr = curr.next;
                }

                if (object.equals(curr.object)) {
                    return false;
                } else {
                    final Entry entry = new Entry(object);
                    entry.next = curr;
                    pred.next = entry;
                    return true;
                }
        	} finally {
        		lock.unlock();
        	}
        }

        /**
         * {@inheritDoc}
         *
         * Use a lock to protect against concurrent access.
         */
        @Override
        boolean remove(final Integer object) {
        	try {
        		lock.lock();
        		Entry pred = this.head;
                Entry curr = pred.next;

                while (curr.object.compareTo(object) < 0) {
                    pred = curr;
                    curr = curr.next;
                }

                if (object.equals(curr.object)) {
                    pred.next = curr.next;
                    return true;
                } else {
                    return false;
                }
        	} finally {
        		lock.unlock();
        	}
        }

        /**
         * {@inheritDoc}
         *
         * Use a lock to protect against concurrent access.
         */
        @Override
        boolean contains(final Integer object) {
            Entry pred = this.head;
            Entry curr = pred.next;
            try {
            	lock.lock();
                while (curr.object.compareTo(object) < 0) {
                    pred = curr;
                    curr = curr.next;
                }
            } finally {
            	lock.unlock();
            }

            return object.equals(curr.object);
        }
    }

    /**
     * An implementation of the ListSet interface that uses Java read-write
     * locks to protect against concurrent accesses.
     *
     * Implement the add, remove, and contains methods below to support
     * correct, concurrent access to this list. Use a Java
     * ReentrantReadWriteLock object to protect against those concurrent
     * accesses.
     */
    public static final class RWCoarseList extends ListSet {
        /*
         * TODO Declare a read-write lock for this class to be used in
         * implementing the concurrent add, remove, and contains methods below.
         */
    	ReentrantReadWriteLock rwl;
        /**
         * Default constructor.
         */
        public RWCoarseList() {
            super();
            rwl = new ReentrantReadWriteLock();
        }

        /**
         * {@inheritDoc}
         *
         * Use a read-write lock to protect against concurrent access.
         */
        @Override
        boolean add(final Integer object) {
			try {
	        	rwl.writeLock().lock();
	            Entry pred = this.head;
	            Entry curr = pred.next;
	
	            while (curr.object.compareTo(object) < 0) {
	                pred = curr;
	                curr = curr.next;
	            }
	
	            if (object.equals(curr.object)) {
	                return false;
	            } else {
	                final Entry entry = new Entry(object);
	                entry.next = curr;
	                pred.next = entry;
	                return true;
	            }
        	} finally {
                rwl.writeLock().unlock();
        	}
        }

        /**
         * {@inheritDoc}
         *
         * TODO Use a read-write lock to protect against concurrent access.
         */
        @Override
        boolean remove(final Integer object) {
    		try {
    			rwl.writeLock().lock();
    			Entry pred = this.head;
	            Entry curr = pred.next;
	
	            while (curr.object.compareTo(object) < 0) {
	                pred = curr;
	                curr = curr.next;
	            }
	
	            if (object.equals(curr.object)) {
	                pred.next = curr.next;
	                return true;
	            } else {
	                return false;
	            }
    		} finally {
    			rwl.writeLock().unlock();
    		}
        }

        /**
         * {@inheritDoc}
         *
         * Use a read-write lock to protect against concurrent access.
         */
        @Override
        boolean contains(final Integer object) {
        	try {
	        	rwl.readLock().lock();
	            Entry pred = this.head;
	            Entry curr = pred.next;
	
	            while (curr.object.compareTo(object) < 0) {
	                pred = curr;
	                curr = curr.next;
	            }
	            return object.equals(curr.object);
        	} finally {
        		rwl.readLock().unlock();
        	}
        }
    }
}
