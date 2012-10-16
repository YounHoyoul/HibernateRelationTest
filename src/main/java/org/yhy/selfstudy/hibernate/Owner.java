package org.yhy.selfstudy.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
 
@Entity
@Table(name="OWNER")
@PrimaryKeyJoinColumn(name="PERSON_ID")
public class Owner extends Person {
    @Column(name="STOCKS")
    private Long stocks;
     
    @Column(name="PARTNERSHIP_STAKE")
    private Long partnershipStake;
    
    public Owner(){
    	
    }
    
    public Owner(String firstname, String lastname, Long stocks, Long partnershipStake) {
        
        super(firstname, lastname);
         
        this.stocks = stocks;
        this.partnershipStake = partnershipStake;
    }
}
