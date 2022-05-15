$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});


// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	//var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
		{
			url: "ComplaintAPI",
			type: "POST",
			data: $("#formCom").serialize(),
			dataType: "text",
			complete: function(response, status) {
				onItemSaveComplete(response.responseText, status);
			}
		});
});


function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.stringify(response);
		if (status == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divGrid").html(resultSet);
		} else if (status == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (resultSet.status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

$(document).on("click", ".btnRemove", function(event) {
	$.ajax(
		{
			url: "ComplaintAPI",
			type: "DELETE",
			data: "complaint_id=" + $(this).data("comid"),
			dataType: "text",
			complete: function(response, status) {
				onItemDeleteComplete(response.responseText, status);
			}
		});
});



// CLIENT-MODEL================================================================
function validateItemForm() {
	// CODE
	if ($("#im").val().trim() == "") {
		return "Insert Inquiry Message.";
	}
	// NAME
	if ($("#section").val().trim() == "") {
		return "Insert Section.";
	}
	// PRICE-------------------------------
	if ($("#cus").val().trim() == "") {
		return "Insert custome ID.";
	}
	
	return true;
}


function onItemSaveComplete(response, status) {
	console.log("insideeeeeeeeee3");
	if (status == "success") {
		var resultSet = JSON.stringify(response);
		console.log("inside");
		if (status == "success") {
			console.log("inner inside");
			$("#alertSuccess").text("Successfully Retreived.");
			console.log("i'm here'");
			$("#alertSuccess").show();
			$("#divGrid").html(resultSet);
		} else if (resultSet.status == "error") {
			console.log("inner outside");
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while Retreiving Data.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while Retreiving..");
		$("#alertError").show();
	} $("#comid").val("");
	$("#formCom")[0].reset();
}