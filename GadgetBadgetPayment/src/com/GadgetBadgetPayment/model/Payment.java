/**
 * 
 */
package com.GadgetBadgetPayment.model;
import java.sql.Timestamp;
/**
 * @author Thinan
 *
 */
public class Payment {

	/**
	 * @param args
	 */

		private int paymentid;
		private int userid;
		private int orderid;
		private int researchid;
		private int fundingid;
		private String paymentmethod;
		private Timestamp paymenttime;
		private String purpose;
		private String statues;
		private float amount;
		private String cardnumber;
		private String cvv;
		private String cardtype;
		private String expiredate;

		public Payment() {

		}

		public int getPaymentid() {
			return paymentid;
		}

		public void setPaymentid(int paymentid) {
			this.paymentid = paymentid;
		}

		public int getUserid() {
			return userid;
		}

		public void setUserid(int userid) {
			this.userid = userid;
		}

		public int getfundingid() {
			return fundingid;
		}

		public void setfundingid(int fundingid) {
			this.fundingid = fundingid;
		}

		public int getorderid() {
			return orderid;
		}

		public void setorderid(int orderid) {
			this.orderid = orderid;
		}

		public int getresearchid() {
			return researchid;
		}

		public void setresearchid(int researchid) {
			this.researchid = researchid;
		}

		public String getPaymentmethod() {
			return paymentmethod;
		}

		public void setPaymentmethod(String paymentmethod) {
			this.paymentmethod = paymentmethod;
		}

		public Timestamp getPaymenttime() {
			return paymenttime;
		}

		public void setPaymenttime(Timestamp ts) {
			this.paymenttime = ts;
		}

		public String getPurpose() {
			return purpose;
		}

		public void setPurpose(String purpose) {
			this.purpose = purpose;
		}

		public String getStatues() {
			return statues;
		}

		public void setStatues(String statues) {
			this.statues = statues;
		}

		public String getCardnumber() {
			return cardnumber;
		}

		public void setCardnumber(String cardnumber) {
			this.cardnumber = cardnumber;
		}

		public String getCvv() {
			return cvv;
		}

		public void setCvv(String cvv) {
			this.cvv = cvv;
		}

		public String getCardtype() {
			return cardtype;
		}

		public void setCardtype(String cardtype) {
			this.cardtype = cardtype;
		}

		public String getExpiredate() {
			return expiredate;
		}

		public void setExpiredate(String expiredate) {
			this.expiredate = expiredate;
		}

		public float getAmount() {
			return amount;
		}

		public void setAmount(float amount) {
			this.amount = amount;
		}

}
