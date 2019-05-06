/**
 * 
 */

$(function(){
	//나중에 init함수로 따로 만들어서 페이지 이동용 공통함수로 사용할 수 잇도록 분리ㄱ
	$.ajax({
		type : "POST",
		url : "getBoardJSON.do" + setUrlParams(getUrlParams()),
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
	})
	
	$("#btnWrite").click(function(e){
		window.location.href = "insertBoard.do";
	});
});