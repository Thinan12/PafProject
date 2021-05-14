$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});


//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	console.log("#btnSave");
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
//var status = validatePaymentForm();
//if (status != true)
// {
 //	$("#alertError").text(status);
// 	$("#alertError").show();
//return;
// }

var type = ($("#hidpaymentIdSave").val() == "") ? "POST" : "PUT";
	console.log($("#formPayment :input")
                .filter(function(index, element){
                	return $(element).val() != '';
                })
        		.serialize());
	console.log(type);
	$("#alertSuccess").text("");
$.ajax(
		{
		 url : "PaymentAPI",
		 type : type,
		 data : $("#formPayment :input")
                .filter(function(index, element){
                	return $(element).val() != '';
                })
        		.serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
				console.log(response);
		 onPaymentSaveComplete(response.responseText, status);
		 }
		});

});


function onPaymentSaveComplete(response, status)
{
if (status == "success")
 {
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
		$("#alertSuccess").text("Successfully saved.");
		$("#alertSuccess").show();
		
	} else if (resultSet.status.trim() == "error")
	{
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
	}
 	} else if (status == "error")
 	{
 		$("#alertError").text("Error while saving.");
 		$("#alertError").show();
 	} else
 	{
 		$("#alertError").text("Unknown error while saving..");
 		$("#alertError").show();
 	}
		$("#hidpaymentIdSave").val("");
		$("#formPayment")[0].reset();
}


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidpaymentIdSave").val($(this).closest("tr").find('#hidpaymentIdUpdate').val());
 
 $("#paymentmethod").val($(this).closest("tr").find('td:eq(4)').text());
 $("#purpose").val($(this).closest("tr").find('td:eq(6)').text());
 $("#statues").val($(this).closest("tr").find('td:eq(8)').text());
 $("#amount").val($(this).closest("tr").find('td:eq(7)').text());
 
 
});

$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "PaymentAPI",
		 type : "DELETE",
		 data : "paymentid=" + $(this).data("paymentid"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onPaymentDeleteComplete(response.responseText, status);
		 }
		 });
		});

function onPaymentDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}

//CLIENTMODEL=========================================================================
function validatePaymentForm()
{
	
//User id
if ($("#userid").val().trim() == "")
{
return "Insert user id.";
}

//User id
if ($("#userid").val().trim() == "")
{
return "Insert user id.";
}

//order id
if ($("#orderid").val().trim() == "")
{
return "Insert order id.";
}

//research id
if ($("#researchid").val().trim() == "")
{
return "Insert research id.";
}


}

