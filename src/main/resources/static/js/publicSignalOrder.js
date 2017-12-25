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

	var $wpOrderDetail = $('#wp_orderDetail');//详情显示框
	var $wpOrderForm = $('#wp_orderForm');//弹框表单id
	
	var $form = $('[data-form="search"]'),
    $formBtn = $form.find(':submit'),//查询
    $formReset = $form.find(':reset');//重置
	
	
	function init() {
		$(document).on('click', '.wp_detail', showDetail);// 点击详情
		 //重置刷新页面
	    $formReset.click(function(){
			setTimeout(function(){
				$formBtn.click();
			},500);
		});
	}

	function showDetail() {//显示详情
		var $this = $(this);
		console.log($this.data());
		$this.postBtn(CONFIG.addr.findOrderInfoById, $this.data(), function(data) {
			console.log(data);
			
			$wpOrderForm.find('[data-insert]').html('');
			$wpOrderForm.insert(data);
			
			$wpOrderDetail.modal('show');//显示订单详情弹框
		});
	}

	init();
});