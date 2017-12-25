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
		$('#startDate').val(S.getQueryString('startDate'));
		$('#endDate').val(S.getQueryString('endDate'));
		$('input[name=userId]').val(S.getQueryString('userId'));
		searchTotalData();
		$formBtn.click();
		$('.search_list').click(function(){
			searchTotalData();
		});
		//重置刷新页面
	    $formReset.click(function(){
			setTimeout(function(){
				$formBtn.click();
			},500);
		});
	}
	
	function searchTotalData() {//查询数据
		var $this = $(this);
		var anchorVO = {};
		anchorVO.userId = S.getQueryString('userId');
		if ($('#startDate').val() == '') {
			anchorVO.startTime = S.getQueryString('startDate');
		} else {
			anchorVO.startTime = $('#startDate').val();
		}
		if ($('#endDate').val() == '') {
			anchorVO.finishTime = S.getQueryString('endDate');
		} else {
			anchorVO.finishTime = $('#endDate').val();
		}
		var data = {};
		data.anchorVO = JSON.stringify(anchorVO);
		console.log(data);
		$this.postBtn(CONFIG.addr.getOneAnchorMessageTotal, data, function(data) {
			console.log('asdasdasdasd');
			console.log(data);
			$('.total_day').text(data.totalDays);
			$('.total_hour').text(data.totalTime);
			$('.total_spot').text(data.totalOpalChangeNum);
			$('.total_calorie').text(data.totalCalorieChangeNum);
			$('#title_name').text(data.anchorNickName + '（' + data.totoroId + '）的直播数据');
		});
	}
	
	// 页面JS初始化
	init();

});