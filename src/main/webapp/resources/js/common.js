/**
 * 
 */

// url에서 parameter를 객체로 변환
function getUrlParams() {
    var params = {};
    window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str, key, value) { params[key] = value; });
    return params;
};

// 객체를 url 파라미터 형식 string로 변환
function setUrlParams(data) {
	var param = "";
	
	Object.keys(data).forEach(function(key, index){
		param += (key + "=" + data[key]);
	});
	
	console.log(param);
	return "?" + param;
}