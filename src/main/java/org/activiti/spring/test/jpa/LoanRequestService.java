package org.activiti.spring.test.jpa;


public interface LoanRequestService {

	public abstract LoanRequest newLoanRequest(String customerName, Long amount);

	public abstract LoanRequest getLoanRequest(Long id);
	
	public abstract LoanRequest newLoanRequest(LoanRequest lr);

}