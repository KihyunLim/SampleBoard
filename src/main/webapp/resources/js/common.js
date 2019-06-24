/**
 * 
 */

/* 
 * ajax data 및 url 셋팅 관련 유틸 -----------
 */

// url에서 parameter를 객체로 변환
function getUrlParams() {
    var params = {};
    window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str, key, value) { params[key] = value; });
    return params;
};

// 객체를 url 파라미터 형식 string로 변환
function setParams(data, type) {
	var param = "";
	
	if(typeof data != "object") {
		console.log("setParams : data is not object!!");
		return "";
	} else if($.isEmptyObject(data)) {
		console.log("setParams : data is empty object!!");
		return "";
	}
	
	Object.keys(data).forEach(function(key, index){
		if(index == (Object.keys(data).length - 1)) {
			param += (key + "=" + data[key]);
		} else {
			param += (key + "=" + data[key] + "&");
		}
	});
	
	console.log(param);
	if(type == "OBJ") {
		return param;
	} else if(type == "URL") {
		return "?" + param;
	} else {
		console.log("setParams : type is undefined!!");
		return "";
	}
};

/* 
 * ----------- ajax data 및 url 셋팅 관련 유틸
 */