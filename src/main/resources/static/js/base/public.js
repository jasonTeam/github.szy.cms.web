function getQueryString(name) {//获取地址栏参数
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if(r != null)
		return unescape(r[2]);
	return null;
}

function isEmpty(value) {//判断是否为空
	if(value == null || value == undefined || value == '' || value == 'null' || value == 'undefined') {
		return true;
	}
	return false;
}