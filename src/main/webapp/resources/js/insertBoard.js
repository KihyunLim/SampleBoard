/**
 * 
 */

$(function(){
	$("#btnRegist").click(function(e){
		var data;
		
		if($(".divItem").length > 0) {
			var str = "";
			
			$(".aDeleteFile").each(function(index) {
				str += "<input type='hiden' name='files[" + index + "]' value='" + $(this).attr("href") + "'>";
			});
			$(this).append(str);
			
			data = $("form").serialize();
		} else {
			data = $("form").serialize();
		}
		
		registInsertBoard(data);
	});
	
	$("#divFileWrap").on("click", ".aDeleteFile", function(e){
		e.preventDefault();
		
		deleteFile("fileDelete.do", $(this), "I");
	});
	
	function registInsertBoard(data) {
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
		});
	};
});