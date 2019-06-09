/**
 * 
 */

$(function(){
	$("#a").on("dragenter dragover", function(e){
		e.preventDefault();
	});
	
	$("#a").on("drop", function(e){
		e.preventDefault();
		var files = e.originalEvent.dataTransfer.files;
		var file = files[0];
		var formData = new FormData();
		formData.append("file", file);
		console.log(formData.get("file"));
	});
	
	$("#btnRegist").click(function(e){
		var data = $("form").serialize();
		
		console.log(data);
		$.ajax({
			type : "POST",
			url : "insertBoard.do",
			data : data,
			dataType : "json",
			success : function(res, status, xhr) {
				console.log(res);
				
				if(res.result) {
					history.back();
				} else {
					alert(res.message);
				}
			},
			error : function(jqXHR, textSatus, errorThrown) {
				console.log("error!!");
				console.log(errorThrown);
			}
		})
	});
});