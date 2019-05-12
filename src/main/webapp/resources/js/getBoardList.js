/**
 * 
 */

$(function(){
	
	getBoardListJSON();
	
	//나중에 init함수로 따로 만들어서 페이지 이동용 공통함수로 사용할 수 잇도록 분리ㄱ 상세조회 화면 바꾼거 처럼 ㄱㄱ
	// url에 변수처리해서 페이지 같은 파람 이어 전달
	function getBoardListJSON() {
		$("#tableBoardList").find("tbody").remove();
		
		var search = {
				"searchCondition" : $("#selSearchCondition").val(),
				"searchKeyword" : $("#inpSearchKeyword").val()
			},
			param = setParams(search, "URL");
		
		$.ajax({
			type : "POST",
			url : "getBoardListJSON.do" + param,
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
														$("<a>").prop("href", "getBoard.do" + setParams($.extend(true, {"seq" : item.seq}, search), "URL")).append(item.title)
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
				
				$("#tableBoardList").append(
						$("<tbody>").append(
								$("<tr>").append(
										$("<td>").prop("colspan", 5).append("게시글 조회에 실패했습니다.")
								)
						)
				)
			}
		});
	};
	
	$("#btnSearch").click(function(){
		getBoardListJSON();
	});
	
	$("#btnWrite").click(function(e){
		window.location.href = "insertBoard.do";
	});
});