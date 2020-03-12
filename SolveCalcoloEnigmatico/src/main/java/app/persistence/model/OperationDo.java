package app.persistence.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/** 
 * 	This is a data structure, so
 *  fields can be public. (Clean-Code)
 */
@Entity
@Table(name="operations")
public class OperationDo {
	
    @Id    
    @Column(name="crypto")
    public String cryptoOperation;
    
    @ElementCollection
    @CollectionTable(name="clearOperations", joinColumns=@JoinColumn(name="crypto"))
    @Column(name="operations")
    public List<String> operations;

	
	public OperationDo() {
		//Default constructor needed for JPA.
	}
	
	public OperationDo(String cryptoOperation) {
		super();
		this.cryptoOperation = cryptoOperation;
		this.operations = new ArrayList<String>();
	}

	public OperationDo(String cryptoOperation, List<String> operations) {
		super();
		this.cryptoOperation = cryptoOperation;
		this.operations = operations;
	}

	public String getCryptoOperation() {
		return cryptoOperation;
	}

	public void setCryptoOperation(String cryptoOperation) {
		this.cryptoOperation = cryptoOperation;
	}

	public List<String> getOperations() {
		return operations;
	}

	public void setOperations(List<String> operations) {
		this.operations = operations;
	}

	@Override
	public String toString() {
		return "OperationDo [cryptoOperation=" + cryptoOperation + ", operations=" + operations + "]";
	}

	
}

