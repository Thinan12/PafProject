<%@page import="com.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Details</title>
<link rel="stylesheet" href="Components/bootstrap.min.css">
<script src="Components/jquery.min.js"></script>
<script src="Components/Payment.js"></script>
<script src="Components/jquery.min.js"></script>
<link rel="stylesheet" href="assets">
<link rel="stylesheet" href="Views/bootstrap.min.css">
</head>
<body>
	

	<div class="row">
		<div class="col-lg-12">
			<div class="card m-b-32">
				<div class="card-body">

					<form id="formPayment" name="formPayment" method="post"
						action="index.jsp">

						<label>User ID</label> 
						<input id="userid" name="userid"
							type="text" class="form-control form-control-sm"> <br>

						<label>Order ID</label> 
						<input id="orderid" name="orderid"
							type="text" class="form-control form-control-sm"> <br>

						<label>Research ID</label> 
						<input id="researchid" name="researchid" type="text"
							class="form-control form-control-sm"> <br>
							
						 <label>Funding ID</label> 
						 <input id="fundingid" name="fundingid" type="text"
							class="form-control form-control-sm"> <br>														
						
						 <label>Payment Method</label> 
						 <input id="paymentmethod" name="paymentmethod" type="text"
							class="form-control form-control-sm"> <br>		
						
						<label>Purpose</label> 
						<input id="purpose" name="purpose" type="text"
							class="form-control form-control-sm"> <br>		
							
						<label>Status</label>
						<select id="statues" name="statues"
							class="form-control form-control-sm">
							<option value="paid">PAID</option>
							<option value="notpaid">Not paid</option>
							<option value="cancel">Cancel</option>
							
						</select> <br> <label>Amount</label> 
						<input id="amount" name="amount"
							type="text" class="form-control form-control-sm"> <br>
							
						
						<label>Card Type</label> 
						<select id="cardtype" name="cardtype"
							class="form-control form-control-sm">
							<option value="Visa">Visa</option>
							<option value="Master">Master</option>
							
						</select> <br> 
						<label>Card Number</label> 
						<input id="cardnumber" name="cardnumber"
							type="text" class="form-control form-control-sm"> <br>	
							
						</select> <br> <label>CVV</label> 
						<input id="cvv" name="cvv"
							type="text" class="form-control form-control-sm"> <br>	
						
						 <label>Expire Date</label> 
						 <input id="expiredate" name="expiredate" type="text"
							class="form-control form-control-sm"> <br> 
						<input id="btnSave" name="btnSave" type="button" value="Save"
							class="btn btn-primary"> 
						<input type="hidden"
							id="hidpaymentIdSave" name="hidpaymentIdSave" value="">
					</form>
					<br>
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<br> <br>

					<div id="divPaymentGrid">
						<%
							Payment PaymentObj = new Payment();
						out.print(PaymentObj.readAllPymentRecords());
						%>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>