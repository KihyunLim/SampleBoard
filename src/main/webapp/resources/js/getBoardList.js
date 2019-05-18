/**
 * 
 */

$(function(){
	var extendParams = {};
	
	getBoardListJSON("");
	
	function getBoardListJSON(requestUrl) {
		$("#tbodyBoardList").empty();
		$("#tfootPageWrap").hide();
		$("#spanPageWrap").empty();
		
		var param = "";
		if(requestUrl == "") {
			var urlParams = getUrlParams(),
				search = {
					"searchCondition" : $("#selSearchCondition").val(),
					"searchKeyword" : $("#inpSearchKeyword").val()
				};
			extendParams = $.extend(true, urlParams, search);
			
			param = setParams(extendParams, "URL");
		} else {
			param = requestUrl;
		}
		
		if(param == "") {
			alert("게시글 조회에 실패했습니다.");
			return;
		}
		
		$.ajax({
			type : "GET",
			url : "getBoardListJSON.do" + param,
			dataType : "json",
			success : function(res, status, xhr) {
				console.log(res);
				
				if(res.boardList.length < 1) {
					alert("등록된 게시물이 없습니다.");
				} else {
					res.boardList.forEach(function(item){
						$("#tbodyBoardList").append(
								$("<tr>").append(
										$("<td>").append(item.seq),
										$("<td>").prop("align", "left").append(
												$("<a>").prop("href", "getBoard.do" + setParams($.extend({}, extendParams, {"seq" : item.seq}), "URL")).append(item.title)
										),
										$("<td>").append(item.writer),
										$("<td>").append(item.regDate),
										$("<td>").append(item.cnt)
								)
						);
					});
					
					for(var i = res.pageMaker.startPage ; i <= res.pageMaker.endPage ; i++) {
						if(res.pageMaker.cri.page == i) {
							$("#spanPageWrap").append(
									$("<span>").append("[" + i + "]")
							);
						} else {
							$("#spanPageWrap").append(
									$("<a>").prop("href", setParams($.extend(true, extendParams, {"page" : i}), "URL")).addClass("aPaging").append("[" + i + "]")
							);
						}
					}
					
					if(res.pageMaker.prev) {
						$("#aPrev").show();
						$("#aPrev").prop("href", setParams($.extend(true, extendParams, {"page" : res.pageMaker.startPage - 1}), "URL"));
					} else {
						$("#aPrev").hide();
					}
					
					if(res.pageMaker.next) {
						$("#aNext").show();
						$("#aNext").prop("href", setParams($.extend(true, extendParams, {"page" : res.pageMaker.endPage + 1}), "URL"));
					} else {
						$("#aNext").hide();
					}
					
					$("#tfootPageWrap").show();
				}
			},
			error : function(jqXHR, textSatus, errorThrown) {
				console.log(errorThrown);
				
				alert("게시글 조회에 실패했습니다.");
			}
		});
	};
	
	$("#btnSearch").click(function(){
		getBoardListJSON("");
	});
	
	$("#tfootPageWrap").on("click", ".aPaging", function(e){
		e.preventDefault();
		
		getBoardListJSON($(this).attr("href"));
	});
	
	$("#btnWrite").click(function(e){
		window.location.href = "insertBoard.do";
	});
});