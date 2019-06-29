/**
 * 
 */

$(function(){
	/* 
	 * 파일 업로드 관련 유틸 -----------
	 */
	
	/*$("#a").on("dragenter dragover", function(e){
		e.preventDefault();
	});
	
	$("#a").on("drop", function(e){
		e.preventDefault();
		var files = e.originalEvent.dataTransfer.files;
		var file = files[0];
		var formData1 = new FormData();
		formData1.append("file", file);
		console.log(typeof formData1.get("file"));
		console.log(formData1.get("file"));
	});*/
		
	// 취소 누르면 undefined로 떨어지네 이걸로 해도 되겠다 드래그드랍으로 하기엔 단일 파일인게 애매해 >> undefined도 판별 잘안되네;;
	$("#inpUploadFile").change(function(e){
		var file = $(this)[0].files[0];
		var formData = new FormData();
		formData.append("file", file);
		console.log(formData.get("file"));
		
		if(formData.get("file") != undefined) {
			uploadFile(formData);
		}
	});
	
	$("#divFileWrap").on("click", ".aDeleteFile", function(e){
		e.preventDefault();
		
		deleteFile("fileDelete.do", $(this));
	});
	
	function uploadFile(formData) {
		$.ajax({
			url : "fileUpload.do",
			data : formData,
			dataType : "text",
			processData : false,
			contentType : false,
			type : "POST",
			success : function(data) {
				console.log(data);
				
				if(data != "" && data != null && data != undefined) {
					printFiles(data);
				}
			}
		});
	};
	
	function printFiles(data) {
		var fileInfo = getFileInfo(data);
		console.log(fileInfo);
		
		$("#divFileWrap").append(
				$("<div>").addClass("divItem").append(
						$("<div>").append(
								$("<img>").attr({src : fileInfo.imgSrc, width : "150px", height : "100px"})
						),
						$("<div>").append(
								$("<a>").attr("href" , fileInfo.originalFileUrl).css("margin-right", "10px").html(fileInfo.originalFileName).addClass("aDisplayFile"),
								$("<a>").attr("href" , fileInfo.fullName).html("삭제").addClass("aDeleteFile")
						)
				)
		);
	};
	
	function deleteFile(url, that) {
		$.ajax({
			url : url,
			type : "POST",
			data : {fileName : that.attr("href")},
			dataType : "text",
			success : function(result) {
				console.log(result);
				
				if(result === "DELETED") {
					alert("삭제되었습니다.");
					that.parents(".divItem").remove();
				}
			}
		});
	};
	
	function getFileInfo(fullName) {
		var originalFileName, imgSrc, originalFileUrl, uuidFileName;
		
		if(checkImageType(fullName)) {
			imgSrc = "fileDisplay.do?fileName=" + fullName;
			uuidFileName = fullName.substr(14);
			var originalImg = fullName.substr(0,12) + fullName.substr(14);
			
			originalFileUrl = "fileDisplay.do?fileName=" + originalImg;
		} else {
			imgSrc = "resources/upload/files/fileIcon.png";
			uuidFileName = fullName.substr(12);
			originalFileUrl = "fileDisplay.do?fileName=" + fullName;
		}
		originalFileName = uuidFileName.substr(uuidFileName.indexOf("_") + 1);
		
		return {
			originalFileName : originalFileName,
			imgSrc : imgSrc,
			originalFileUrl : originalFileUrl,
			fullName : fullName
		};
	};
	
	function checkImageType(fullName) {
		var pattern = /jpg$|gif$|png$|jpeg$/i;
		
		return fullName.match(pattern);
	};
	
	/* 
	 * ----------- 파일 업로드 관련 유틸
	 */
});