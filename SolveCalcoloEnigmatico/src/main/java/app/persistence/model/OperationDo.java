package app.persistence.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import app.persistence.services.OperationHelper;



/** 
 * 	This is a data structure, so
 *  fields can be public. (Clean-Code)
 */
@Entity
@Table(name="operations")
public class OperationDo {
	
    @Id    
    @Column(name="operation")
    public String operation;
    
    @Column(name="crypto")
    public String crypto;

	
	public OperationDo() {
		//Default constructor needed for JPA.
	}
	
	public OperationDo(String operation) {
		super();
		this.operation = operation;
		this.crypto = OperationHelper.ClearToCrypto(operation);
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getCrypto() {
		return crypto;
	}

	public void setCrypto(String crypto) {
		this.crypto = crypto;
	}

	@Override
	public String toString() {
		return "OperationDo [operation=" + operation + ", crypto=" + crypto + "]";
	}
	
}

