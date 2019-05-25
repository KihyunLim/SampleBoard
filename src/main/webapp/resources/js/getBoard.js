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
				
				getBoardReplyList();
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
	
	$("#tbodyReplyList").on("click", ".btnReRelpy", function(){
		$(".trEnterReReply").remove();
		
		var $trEnterReReply = $("<tr>").addClass("trEnterReReply trReReply").append(
				$("<td>").append(getUserId),
				$("<td>").append(
						$("<span>").append("▶▶▶ "),
						$("<textarea>").attr({cols : 40, rows : 1}).addClass("textareaReReplyContent")
				),
				$("<td>").append(""),
				$("<td>").addClass("tdBtnWrap").append(
						$("<input>").attr({type : "button", value : "등록"}).addClass("btnInsertReReply")
				)
		);
		
		$(this).parents("tr").after($trEnterReReply);
	});
	
	$("#btnInsertReply").click(function(){
		var data = {
				"boardSeq" 	: seq,
				"parentSeq"	: "P",
				"writer" 		: getUserId,
				"content" 		: $("#textareaReplyContent").val()
		};
		
		requestInsertReply(data);
	});
	
	$("#tbodyReplyList").on("click", ".btnUpdateReply", function(){
		var $this = $(this),
			replyContent = "",
			$tdReplyContent = $this.parents("tr").find(".tdReplyContent");
		
		$this.attr("value", "수정 등록");
		replyContent = $tdReplyContent.children(":last").text();
		$tdReplyContent.children(":last").remove();
		$tdReplyContent.append(
				$("<textarea>").attr({cols : 40, rows : 1}).addClass("textareaUpdateContent").append(replyContent)
		);
		
		$this.addClass("btnUpdateReplyRequest").removeClass("btnUpdateReply");
	});
	
	$("#tbodyReplyList").on("click", ".btnUpdateReplyRequest", function(){
		var $trThis = $(this).parents("tr"),
			data = {
				"boardSeq"	: $trThis.data("info").boardSeq,
				"seq" 			: $trThis.data("info").seq,
				"content"		: $trThis.find(".textareaUpdateContent").val()
			};
		
		console.log(data);
	});
	
	function requestInsertReply(data) {
		$.ajax({
			type : "POST",
			url : "insertBoardReply.do",
			data : setParams(data, "OBJ"),
			dataType : "json",
			success : function(res, status, xhr) {
				console.log(res);
				
				if(res.result) {
					alert(res.message);
					
					$("#tbodyReplyList").empty();
					getBoardReplyList();
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
	
	function getBoardReplyList() {
		var data = {
				"boardSeq" : seq
		};
		
		$.ajax({
			type : "POST",
			url : "getBoardReplyList.do",
			data : setParams(data, "OBJ"),
			dataType : "json",
			success : function(res, status, xhr) {
				console.log(res);
				
				res.boardReplyList.forEach(function(item){
					$("#tbodyReplyList").append(
							$("<tr>").data("info", {"boardSeq" : item.boardSeq, "seq" : item.seq}).append(
									$("<td>").append(item.writer),
									$("<td>").addClass("tdReplyContent").append(
											$("<span>").append(item.content)
									),
									$("<td>").append(item.regDate),
									$("<td>").addClass("tdBtnWrap").append(
											$("<input>").attr({type : "button", value : "수정", style : "display:none"}).addClass("btnUpdateReply btnReply"),
											$("<input>").attr({type : "button", value : "삭제", style : "display:none"}).addClass("btnDeleteReply btnReply"),
											$("<input>").attr({type : "button", value : "대댓"}).addClass("btnReRelpy")
									)
							)
					);
					
					if(getUserId == item.writer) {
						$(".tdBtnWrap:last").find(".btnReply").removeAttr("style");
					}
				});
			},
			error : function(jqXHR, textSatus, errorThrown) {
				console.log("error");
				console.log(errorThrown);
				
				alert("댓글 조회에 실패했습니다.");
			}
		});
	};
});