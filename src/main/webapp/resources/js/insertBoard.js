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
		var formData1 = new FormData();
		formData1.append("file", file);
		console.log(typeof formData1.get("file"));
		console.log(formData1.get("file"));
	});
	
	// 취소 누르면 undefined로 떨어지네 이걸로 해도 되겠다 드래그드랍으로 하기엔 단일 파일인게 애매해
	$("#inpUploadFile").change(function(e){
		var file = $(this)[0].files[0];
		var formData2 = new FormData();
		formData2.append("file", file);
		console.log(typeof formData2.get("file"));
		console.log(formData2.get("file"));
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