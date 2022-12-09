package com.xjjlearning.java.util.stream.pojo;

import java.util.Arrays;
import java.util.List;

public class Transaction{

	private Trader trader;
	private int year;
	private int value;

	public Transaction(Trader trader, int year, int value)
	{
		this.trader = trader;
		this.year = year;
		this.value = value;
	}

	public Trader getTrader(){ 
		return this.trader;
	}

	public int getYear(){
		return this.year;
	}

	public int getValue(){
		return this.value;
	}
	
	public String toString(){
	    return "{" + this.trader + ", " +
	           "year: "+this.year+", " +
	           "value:" + this.value +"}";
	}


	private static final Trader raoul = new Trader("Raoul", "Cambridge");
	private static final Trader mario = new Trader("Mario","Milan");
	private static final Trader alan = new Trader("Alan","Cambridge");
	private static final Trader brian = new Trader("Brian","Cambridge");

	public static final List<Transaction> TRANSACTIONS = Arrays.asList(
			new Transaction(brian, 2011, 300),
			new Transaction(raoul, 2012, 1000),
			new Transaction(raoul, 2011, 400),
			new Transaction(mario, 2012, 710),
			new Transaction(mario, 2012, 700),
			new Transaction(alan, 2012, 950)
	);
}