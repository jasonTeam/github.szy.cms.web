/**
 * Created by Storm on 2017/5/12.
 */
require.config({
	paths : {
		config : 'base/config',
		jquery : 'base/jquery.min',
		storm : 'base/storm-1.5',
		common : 'base/common',
		WdatePicker : 'My97DatePicker/WdatePicker'
	}
});
require([ 'config', 'jquery', 'storm', 'common', 'WdatePicker' ], function(CONFIG, $, S) {
	'use strict';

	var $form = $('[data-form="search"]'); // 查询表单
	var $formBtn = $form.find(':submit'); 
	var $formReset = $form.find(':reset');
	var wpReport = $('#wp_report'); //举报弹框
	var wpReporForm = $('#wp_reportform'); //举报弹框表单
	var wpReporFormBtn = wpReporForm.find(':submit'); //举报弹框表单按钮

	var $wpCheck = $('#wp_check'); //举报查看弹框
	var $wpCheckForm = $('#wp_checkform'); //举报弹框表单
	
	/**
	 * 页面初始化
	 */
	function init() {
		// 表单按钮事件
//		$tvBannerBtn.click();
		$(document).on('click', '.wp_handles', modalReportShow);
		$(document).on('click', '.wp_sees', modalSeeShow);
		
		wpReporFormBtn.click(ReportTrue);
		//重置刷新页面
	    $formReset.click(function(){
			setTimeout(function(){
				$formBtn.click();
			},500);
		});
	}
	
	function modalReportShow () {//显示举报弹框
		var $this = $(this);
		console.log($this.data());
		$this.postBtn(CONFIG.addr.findTvIllegalReportById, $this.data(), function(data) {//获取单个反馈信息
			wpReporForm.find('[data-insert]').html('');
			wpReporForm.insert(data);
			
			wpReport.modal('show');//显示反馈详情弹框
		});
	}
	
	function modalSeeShow () {//显示举报弹框
		var $this = $(this);
		console.log($this.data());
		$this.postBtn(CONFIG.addr.findTvIllegalReportById, $this.data(), function(data) {//获取单个反馈信息
			$wpCheckForm.find('[data-insert]').html('');
			$wpCheckForm.insert(data);
			
			$wpCheck.modal('show');//显示反馈详情弹框
		});
	}
	
	function ReportTrue(e) {//提交处理
		e.stopPropagation();
		wpReporForm.validate(function() {
			wpReporForm.setForm(function() {
				S.alert('处理举报成功!');
				wpReport.modal('hide');
				$formBtn.click();
			});
		});
		return false;
	}
	
	
	// 页面JS初始化
	init();

});