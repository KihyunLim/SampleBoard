/**
 * 
 */

$(function(){
	//나중에 init함수로 따로 만들어서 페이지 이동용 공통함수로 사용할 수 잇도록 분리ㄱ
	$.ajax({
		type : "POST",
		url : "getBoardListJSON.do",
		dataType : "json",
		success : function(res, status, xhr) {
			console.log(res);
			
			if(res.length < 1) {
				$("#tableBoardList").append(
					$("<tbody>").append(
						$("<tr>").append(
							$("<td>").prop("colspan", 5).append("등록된 게시물이 없습니다.")
						)
					)
				)
			} else {
				res.forEach(function(item){
					$("#tableBoardList").append(
							$("<tbody>").append(
									$("<tr>").append(
											$("<td>").append(item.seq),
											$("<td>").prop("align", "left").append(
													$("<a>").prop("href", "getBoard.do?seq=" + item.seq).append(item.title)
											),
											$("<td>").append(item.writer),
											$("<td>").append(item.regDate),
											$("<td>").append(item.cnt)
									)
							)
					)
				});
			}
		},
		error : function(jqXHR, textSatus, errorThrown) {
			console.log(errorThrown);
		}
	})
	
	$("#btnWrite").click(function(e){
		window.location.href = "insertBoard.do";
	});
});