/**
 * 
 */

$(function(){
	var seq = 0;
	
	getBoardJSON();
	
	function getBoardJSON() {
		var data = setParams(getUrlParams(), "OBJ");
		
		$.ajax({
			type : "POST",
			url : "getBoardJSON.do",
			data : data,
			dataType : "json",
			success : function(res, status, xhr) {
				console.log(res);
				seq = res.seq;
				
				if(getUserId != res.writer) {
					$(".myBoard").prop("readonly", true);
					$("#btnUpdate").parents("tr").hide();
					$("#aDeleteBoard").hide();
				}
				$("#title").val(res.title);
				$("#writer").text(res.writer);
				$("#content").val(res.content);
				$("#regDate").text(res.regDate);
				$("#cnt").text(res.cnt);
			},
			error : function(jqXHR, textSatus, errorThrown) {
				console.log(errorThrown);
			}
		});
	};
	
	$("#btnUpdate").click(function(){
		var data = {
				"seq"			: seq,
				"title" 		: $("#title").val(),
				"content" 	: $("#content").val()
		};
		
		$.ajax({
			type : "POST",
			url : "updateBoard.do",
			data : setParams(data, "OBJ"),
			dataType : "json",
			success : function(res, status, xhr) {
				console.log(res);
				
				if(res.result) {
					getBoardJSON();
				} else {
					alert(res.message);
				}
			},
			error : function(jqXHR, textSatus, errorThrown) {
				console.log("error!!");
				console.log(errorThrown);
			}
		});
	});
	
	$("#aDeleteBoard").click(function(e){
		e.preventDefault();
		
		var data = {
				"seq" : seq
		}
		
		$.ajax({
			type : "POST",
			url : "deleteBoard.do",
			data : setParams(data, "OBJ"),
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
	});
	
	$("#aGetBoardList").click(function(e){
		e.preventDefault();
		
		history.back();
	});
});