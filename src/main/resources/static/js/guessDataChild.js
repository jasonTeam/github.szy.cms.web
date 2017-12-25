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
	$formBtn = $form.find(':submit'); //查询按钮
	
	/**
	 * 页面初始化
	 */
	function init() {
		
		searchTotalData();
		$(document).on('click', '.wp_guessDetails', guessDetailsBtn);//查询竞猜详情
		getGuesslist();
	}
	
	function searchTotalData() {//查询数据
		var $this = $(this);
		var data = {};
		data.anchorId = S.getQueryString('anchorId');
		data.playId = S.getQueryString('playId');
		
		console.log(data);
		$this.postBtn(CONFIG.addr.findTotalGuessGuardByPlayInfo, data, function(data) {
			console.log('asdasdasdasd');
			console.log(data);
			
			$('.guessNum').text(data.guessNum);
			$('.sysProfit').text(data.robProfit);
			$('.opal').text(data.opal);
			$('.percentTen').text(data.tenPercent);
			$('#title_name').text(data.nickName + '（' + data.anchorId + '）的竞猜数据');
		});
	}
	
	function getGuesslist() {//获取列表总数据
		var $this = $(this);
	    
		var anchorId = S.getQueryString('anchorId');
		var playId = S.getQueryString('playId');
		
		var data = {};
	    data.anchorId = anchorId;
	    data.playId = playId;
		data.pageNo = '1';
		data.pageSize = '100';
	    console.log(data)
	    $this.postBtn(CONFIG.addr.findGuessGuardByPlayInfo, data, function(data) {
	    	console.log('获取单次开播竞猜数据');
	    	console.log(data);
	    	$('#content').html(S.template(data.content, $('#temp').html()));
	    });
	}
	
	function guessDetailsBtn() {//获取单次开播竞猜数据
		var $this = $(this);
		var guessId = $this.attr('data-guess-id');
		
		if ($this.parent().parent().next().css('display') == 'none') {
			var data = {};
		    data.guessId = guessId;
			data.pageNo = '1';
			data.pageSize = '100';
		    $this.postBtn(CONFIG.addr.findGuessGuardByGuessId, data, function(data) {
		    	$('#guess_data' + guessId).html(S.template(data.content, $('#guess_temp').html()));
		    	$('.guess_trclass').hide();
		    	$('#guess_tr' + guessId).show();
		    });
		} else {
			$('.guess_trclass').hide();
		}
	}
		
	// 页面JS初始化
	init();

});