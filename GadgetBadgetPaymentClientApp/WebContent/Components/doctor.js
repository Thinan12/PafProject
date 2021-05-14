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
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateDoctorForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }

var type = ($("#hiddoctorIdSave").val() == "") ? "POST" : "PUT";


$.ajax(
		{
		 url : "DoctorAPI",
		 type : type,
		 data : $("#formDoctor").serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onDoctorSaveComplete(response.responseText, status);
		 }
		});

});
function onDoctorSaveComplete(response, status)
{
if (status == "success")
 {
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
		$("#alertSuccess").text("Successfully saved.");
		$("#alertSuccess").show();
		
		$("#divDoctorGrid").html(resultSet.data);
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
		$("#hiddoctorIdSave").val("");
		$("#formDoctor")[0].reset();
}

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hiddoctorIdSave").val($(this).closest("tr").find('#hiddoctorIdUpdate').val());
 $("#doctorName").val($(this).closest("tr").find('td:eq(0)').text());
 $("#specialization").val($(this).closest("tr").find('td:eq(1)').text());
 $("#phone").val($(this).closest("tr").find('td:eq(2)').text());
 $("#email").val($(this).closest("tr").find('td:eq(3)').text());
 $("#hospitalName").val($(this).closest("tr").find('td:eq(4)').text());
 $("#availableDay").val($(this).closest("tr").find('td:eq(5)').text());
 $("#availableTime").val($(this).closest("tr").find('td:eq(6)').text());
 $("#doctorCharge").val($(this).closest("tr").find('td:eq(7)').text());

});


$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "DoctorAPI",
		 type : "DELETE",
		 data : "doctorId=" + $(this).data("itemid"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onDoctorDeleteComplete(response.responseText, status);
		 }
		 });
		});

function onDoctorDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divDoctorGrid").html(resultSet.data);
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
function validateDoctorForm()
{
	
//doctor Name
if ($("#doctorName").val().trim() == "")
{
return "Insert doctor Name.";
}

var letterReg1 = /^[a-zA-Z ]+$/;
var tmpfName =  $("#doctorName").val().trim();
if(!tmpfName.match(letterReg1)){
	return "First Letter must have alphabet charaters only...!";
}
//specialization
if ($("#specialization").val().trim() == "")
{
return "Insert specialization.";
}

//phone
if ($("#phone").val().trim() == "") {
	return "Insert Phone.";
}
var contactReg = /^\d{10}$/;
var tmpPhone =  $("#phone").val().trim();
if(!tmpPhone.match(contactReg)){
	return "Insert a valid Phone Number...!";
}



//Email

function isEmail(email) {
	var regex =  /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/; 
		return regex.test(email);
}


if ($("#email").val().trim() == "")
{
return "Insert Email.";
}
else if(!isEmail($("#email").val())){
	return "Insert valid Email";
}



//Hospital ID
if ($("#hospitalName").val().trim() == "")
{
return "Insert Hospital Name.";
} 

//Available Day
if ($("#availableDay").val().trim() == "")
{
return "Insert Available day.";
}

//Available time
if ($("#availableTime").val().trim() == "")
{
return "Insert Available Time.";
}

//Doctor Charge
if ($("#doctorCharge").val().trim() == "")
{
return "Insert Doctor Charge.";
}
//is numerical valuee
var tmpCharge = $("#doctorCharge").val().trim();
if (!$.isNumeric(tmpCharge))
{
return "Insert a numerical value for Doctor Charge.";
}
$("#doctorCharge").val(parseFloat(tmpCharge).toFixed(6));

return true;
}


