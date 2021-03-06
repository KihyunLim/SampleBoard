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
				
				if(loginId != res.writer) {
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
	
	$("#btnInsertReply").click(function(){
		var data = {
				"boardSeq" 	: seq,
				"parentSeq"	: "P",
				"writer" 		: loginId,
				"content" 		: $("#textareaReplyContent").val()
		};
		
		requestInsertReply(data);
		$("#textareaReplyContent").val("");
	});
	
	$("#tbodyReplyList").on("click", ".btnReRelpy", function(){
		$(".trEnterReReply").remove();
		
		var $trEnterReReply = $("<tr>").addClass("trEnterReReply trReReply").append(
				$("<td>").append(loginId),
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
		$(".textareaReReplyContent").focus();
	});
	
	$("#tbodyReplyList").on("click", ".btnInsertReReply", function(){
		var $trThis = $(this).parents("tr"),
			data = {
				"boardSeq"	: $trThis.prev().data("info").boardSeq,
				"parentSeq"	: $trThis.prev().data("info").seq,
				"writer"			: loginId,
				"content"		: $trThis.find(".textareaReReplyContent").val()
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
				"seq" 			: $trThis.data("info").seq,
				"content"		: $trThis.find(".textareaUpdateContent").val()
			};
		
		requestUpdateReply(data);
	});
	
	$("#tbodyReplyList").on("click", ".btnDeleteReply", function(){
		var $trThis = $(this).parents("tr"),
			data = {
				"seq" : $trThis.data("info").seq
			};
		
		requestDeleteReply(data);
	});
	
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
				
				var reply = [],
					rereply = [];
	
				res.boardReplyList.forEach(function(item){
					if(item.parentSeq == "P") {
						reply.push(item);
					} else {
						rereply.push(item);
					}
				});
	
				// 최신 대댓글이 맨위로 올라올 수 있드록 하기위함
				rereply.sort(function(a, b){
					return a.seq - b.seq;
				});
	
				rereply.forEach(function(item){
					var index = reply.findIndex(function(element){
						return Number(item.parentSeq) == element.seq;
					});
					
					reply.splice(index+1, 0, item);
				});
				
				console.log(reply);
				
				reply.forEach(function(item){
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
					
					if(loginId == item.writer) {
						$(".tdBtnWrap:last").find(".btnReply").removeAttr("style");
					}
					
					if(item.parentSeq != "P") {
						$(".tdBtnWrap:last").parents("tr").find(".tdReplyContent").prepend($("<span>").append("▶▶▶ "));
						$(".tdBtnWrap:last").find(".btnReRelpy").attr("style", "display:none");
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
	
	function requestUpdateReply(data) {
		$.ajax({
			type : "POST",
			url : "updateBoardReply.do",
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
	
	function requestDeleteReply(data) {
		$.ajax({
			type : "POST",
			url : "deleteBoardReply.do",
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
});