package com.spring.edm.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.spring.edm.entity.Book;
import com.spring.edm.repository.BookRepository;

/** Hystrix Cricuit Breaker - When the Spring framework sees the @HystrixCommand, 
 *  it will dynamically generate a proxy that will wrapper the method and manage all
 *  calls to that method through a thread pool of threads specifically set aside to handle remote calls.*/
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	/**Fetching data from ODS - Operational Data store.
	 * In case of a fallback fetch data from Summarized Data store*/
	@Override
	@HystrixCommand(fallbackMethod="fallbackFindAllBooks",
			threadPoolKey="findAllBooksPool",
			threadPoolProperties= {
					@HystrixProperty(name="coreSize",value="5"),
					@HystrixProperty(name="maxQueueSize",value="2")
			},
			commandProperties= {
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="20"), //amount of calls in 15 second window, 10 seconds is (rollingStats.timeInMilliseconds) default window. Here 15 seconds 10 calls is the expectation
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="25"), //percentage of calls that fail in 10 seconds window
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="10000"), //amount of time CB will sleep before next monitoring window
					@HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="20000"), // control the size of the window that will be used by Hystrix to monitor 
					@HystrixProperty(name="metrics.rollingStats.numBuckets", value="10"),// collecting stats in 5 buckets period over 15 seconds
					//@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="12000"), //simple CB pattern using timeout: 12 seconds, usually required 4-5 seconds
					@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")//default execution.isolation.strategy is ExecutionIsolationStrategy.THREAD. So, the ThreadLocal variables we set before invoking @HystrixCommand methods won't be available within @HystrixCommand methods.
					})
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW, isolation=Isolation.REPEATABLE_READ)
	public List<Book> findAllBooks() throws Exception {
		return bookRepository.findAll();
	}

	@Override
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW, isolation=Isolation.REPEATABLE_READ)
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW, isolation=Isolation.REPEATABLE_READ)
	public Optional<Book> findByName(String name) {
		return bookRepository.findByName(name);
	}
	
	/**
	 * The following method helps in testing Circuit Breaker Pattern implementation
	 * @throws InterruptedException */
	public void sleep() throws InterruptedException {
		Thread.sleep(13000);
	}
	
	/**Temporary implementation of fallback method for findAllBooks().
	 * Solution: Get data from summarized data store*/
	public List<Book> fallbackFindAllBooks(){
		return Arrays.asList(new Book[] {Book.builder().id(0).build()});
	}

}
