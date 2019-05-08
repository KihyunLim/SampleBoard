/**
 * 
 */

$(function(){
	//나중에 init함수로 따로 만들어서 페이지 이동용 공통함수로 사용할 수 잇도록 분리ㄱ
	(function init() {
		var data = setParams(getUrlParams(), "POST");
		
		$.ajax({
			type : "POST",
			url : "getBoardJSON.do",
			data : data,
			dataType : "json",
			success : function(res, status, xhr) {
				console.log(res);
				
				if(getUserId != res.writer) {
					$(".myBoard").prop("readonly", true);
					$("#btnUpdate").parents("tr").hide();
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
	})();
	
	$("#btnUpdate").click(function(){
		var data = {
				"title" 		: $("#title").val(),
				"writer" 	: $("#writer").text(),
				"content" 	: $("#content").val(),
				"regDate" 	: $("#regDate").text(),
				"cnt" 		: $("#cnt").text()
		};
		
		$.ajax({
			type : "POST",
			url : "updateBoard.do",
			data : setParams(data, "POST"),
			dataType : "json",
			success : function(res, status, xhr) {
				
			},
			error : function(jqXHR, textSatus, errorThrown) {
				console.log("error!!");
				console.log(errorThrown);
			}
		});
	});
	
	$("#aGetBoardList").click(function(){
		var param = "?파람 뒤에 이을꺼";
		
		window.location.href = "getBoardList.do";
	});
});