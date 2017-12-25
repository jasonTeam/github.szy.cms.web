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
		
//		$('input[name=beginDate]').val(GetDateStr(0));
//		$('input[name=finishDate]').val(GetDateStr(0));
		
		$('.exportBtn').click(exprot);
		
//		$formBtn.click(searchTotalData);
		searchTotalData();
		
		$('.search_guessdata').click(function(){
			searchTotalData();
		});
		
		$(document).on('click', '.wp_guessDetails', guessDetailsBtn);//查询竞猜详情
		
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
		data.guessGuardInfo = JSON.stringify($form.formData());
		console.log(data);
		$this.postBtn(CONFIG.addr.findTotalPalyGuessGuardInfo, data, function(data) {
			console.log('asdasdasdasd');
			console.log(data);
			
			$('.guessNum').text(data.guessNum);
			$('.sysProfit').text(data.sysProfit);
			$('.opal').text(data.opal);
			$('.percentTen').text(data.percentTen);
			
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
	    
		var anchorId = $this.attr('data-anchor-id');
		var playId = $this.attr('data-play-id');
		
		$(window.parent.document).find("#mainFrame").attr("src","?anchorId=" + anchorId + "&playId=" + playId);
		window.location.href = "/guessDataChild";
		
//		var data = {};
//	    data.anchorId = anchorId;
//	    data.playId = playId;
//		data.pageNo = '1';
//		data.pageSize = '100';
//	    console.log(data)
//	    $this.postBtn(CONFIG.addr.findGuessGuardByPlayInfo, data, function(data) {
//	    	console.log('获取单次开播竞猜数据');
//	    	console.log(data);
//	    	$('#guess_data' + anchorId).html(S.template(data.content, $('#guess_temp').html()));
//	    });
	}
	
	
	
//	function guessDetailsBtn() {//获取单次开播竞猜数据
//		var $this = $(this);
//	    
//		var anchorId = $this.attr('data-anchor-id');
//		var playId = $this.attr('data-play-id');
//		
//		if ($('#guess_tr' + anchorId).css('display') == 'none') {
//			var data = {};
//		    data.anchorId = anchorId;
//		    data.playId = playId;
//			data.pageNo = '1';
//			data.pageSize = '100';
//		    console.log(data)
//		    $this.postBtn(CONFIG.addr.findGuessGuardByGuessId, data, function(data) {
//		    	console.log('获取单次开播竞猜数据');
//		    	console.log(data);
//		    	$('#guess_data' + anchorId).html(S.template(data.content, $('#guess_temp').html()));
//		    	$('#guess_tr' + anchorId).slideDown();
//		    });
//		} else {
//			$('#guess_tr' + anchorId).slideUp();
//		}
//	}
		
	
	
	
	// 页面JS初始化
	init();

});