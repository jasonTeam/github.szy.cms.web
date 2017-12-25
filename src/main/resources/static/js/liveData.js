/**
 * Created by Storm on 2016/4/12.
 */
require.config({
	paths : {
		config : 'base/config',
		jquery : 'base/jquery.min',
		storm : 'base/storm-1.5',
		common : 'base/common',
		viewer : 'view/viewer.min',
		WdatePicker : 'My97DatePicker/WdatePicker'
	}
});
require([ 'config', 'jquery', 'storm', 'common', 'viewer', 'WdatePicker' ], function(CONFIG, $, S) {
	'use strict';
	
	var $form = $('[data-form="search"]'),// 查询表单
	$formBtn = $form.find(':submit'), //查询按钮
	$formReset = $form.find(':reset'); //重置按钮
	/**
	 * 页面初始化
	 */
	function init() {
		
//		$formBtn.click();
		
//		$('input[name=beginDate]').val(GetDateStr(0));
//		$('input[name=finishDate]').val(GetDateStr(0));
		
		$('.exportBtn').click(exprot);
		
//		$formBtn.click(searchTotalData);
		searchTotalData();
		
		$('.search_livedata').click(function(){
			searchTotalData();
		});
		
		$(document).on('click', '.see_detail', guessDetailsBtn);//查询竞猜详情
		
		//重置刷新页面
//	    $formReset.click(function(){
//			setTimeout(function(){
//				$formBtn.click();
//			},500);
//		});
		
	}
	
	function exprot() {//导出
		S.confirm('导出会消耗服务器性能,你确定要导出吗?', function () {
			var data = {};
			data[$form.data('param')] = JSON.stringify($form.formData());
			location.href = CONFIG.addr.GuessGuardInfoExprot+'?' + $.param(data);
	    });
	}
	
	function searchTotalData() {//查询数据
		var $this = $(this);
		var data = {};
		data.anchorVO = JSON.stringify($form.formData());
		console.log(data);
		$this.postBtn(CONFIG.addr.getAnchorMessageTotal, data, function(data) {
			console.log(data);
			$('.calorie_total').text(data.calorieTotal);
			$('.calorie_canuse').text(data.calorieCanUse);
		});
	}
	
	function GetDateStr(AddDayCount) {
		var dd = new Date();
		dd.setDate(dd.getDate() + AddDayCount); //获取AddDayCount天后的日期 
		var y = dd.getFullYear();
		var m = dd.getMonth() + 1; //获取当前月份的日期 
		var d = dd.getDate();
		if(m < 10) {
			m = "0" + m;
		}
		if(d < 10) {
			d = "0" + d;
		}
		return y + "-" + m + "-" + d;
	}
	//document.write("前天："+GetDateStr(-2));
	//document.write("<br />昨天："+GetDateStr(-1));
	//document.write("<br />今天："+GetDateStr(0));
	//document.write("<br />明天："+GetDateStr(1));
	//document.write("<br />后天："+GetDateStr(2));
	//document.write("<br />大后天："+GetDateStr(3));
	
	function guessDetailsBtn() {//获取单次开播竞猜数据
		var $this = $(this);
	    
		var userId = $this.attr('data-user-id');
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		
		$(window.parent.document).find("#mainFrame").attr("src","?userId=" + userId + "&startDate=" + startDate + "&endDate=" + endDate);
		window.location.href = "/livedataChild";
		
	}
	
	// 页面JS初始化
	init();

});