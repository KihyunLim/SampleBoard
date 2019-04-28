/**
 * 
 */

$(function(){
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
					window.location.href = "getBoardList.do";
				} else {
					alert(res.message);
				}
			},
			error : function(jqXHR, textSatus, errorThrown) {
				console.log("error!!");
				console.log(errorThrown);
			}
		})
	})
});