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

	
	var $form = $('[data-form="search"]'); // 查询表单
	var $formBtn = $form.find(':submit');
	var $formReset = $form.find(':reset');
	
	var $tvFeedback = $('#wp_feedback'); // 用户反馈详情处理弹框
	
	var $tvFeedbackForm = $('#wp_feedbackform'); //用户反馈详情弹框表单
	var $tvFeedbackFormBtn = $tvFeedbackForm.find(':submit'); //用户反馈详情弹框处理按钮
	
	var $tvFeeddetail = $('#wp_feeddetail'); // 用户反馈详情弹框
	var $tvFeeddetailfrom = $('#wp_feeddetailform'); // 用户反馈详情弹框
	
	function init() {//页面初始化

		$(document).on('click', '.wp_handles', modalHandleFeedbackShow);//处理按钮反馈
		
		$(document).on('click', '.wp_sees', modalHandleFeeddetailShow);//查看按钮反馈
		
		//反馈详情处理按钮点击事件
		$tvFeedbackFormBtn.click(HandleFeedback);
		
		//重置刷新页面
	    $formReset.click(function(){
			setTimeout(function(){
				$formBtn.click();
			},500);
		});
	}
	
	function modalHandleFeedbackShow () {//显示反馈详情处理弹框
		var $this = $(this);
		$this.postBtn(CONFIG.addr.findTvFeedbackById, $this.data(), function(data) {//获取单个反馈信息
			
			var feedbackUrl = data.feedbackRrl;
			if(feedbackUrl != ''){
				feedbackUrl = feedbackUrl.split(";");
				data.feedbackUrl = feedbackUrl;
				$('#feedbackimg_list').html(S.template(data.feedbackUrl, $('#feedbackimg_Temp').html()));
				
				var viewer = new Viewer(document.getElementById('wp_feedback'), {
					url: 'data-original'
//					shown: function() {
//						var marginLeft = $('.viewer-canvas img').css('margin-left');
//						$('.viewer-canvas img').css('margin-left',parseInt(marginLeft) - 220);
//						$('.viewer-list').css('margin-left',parseInt($('.viewer-list').css('margin-left')) - 220);
//					}
				});
			} else {
				$('.feedimg_dis').hide();
			}
			
//			$(document).on("mouseover mouseout", '.feedbackimg_width', function(event){//banner 查看大图
//				if(event.type == "mouseover"){//鼠标悬浮
//					$(this).next().show();
//				}else if(event.type == "mouseout"){ //鼠标离开
//					$(this).next().hide();
//				}
//			});
			
			$tvFeedbackForm.find('[data-insert]').html('');
			$tvFeedbackForm.insert(data);
			
			$tvFeedback.modal('show');//显示反馈详情弹框弹框
		});
	}
	
	function modalHandleFeeddetailShow () {//显示反馈详情处理弹框
		var $this = $(this);
		$this.postBtn(CONFIG.addr.findTvFeedbackById, $this.data(), function(data) {//获取单个反馈信息
			
			var feedbackUrl = data.feedbackRrl;
			
			if(feedbackUrl != ''){
				$('.feedimg_dis').show();
				feedbackUrl = feedbackUrl.split(";");
				data.feedbackUrl = feedbackUrl;
				$('#feedbackimg_listsee').html(S.template(data.feedbackUrl, $('#feedbackimgsee_Temp').html()));
				var viewer = new Viewer(document.getElementById('wp_feeddetail'), {
					url: 'data-original'
//					shown: function() {
//						var marginLeft = $('.viewer-canvas img').css('margin-left');
//						$('.viewer-canvas img').css('margin-left',parseInt(marginLeft) - 220);
//						$('.viewer-list').css('margin-left',parseInt($('.viewer-list').css('margin-left')) - 220);
//					}
				});
			} else {
				$('.feedimg_dis').hide();
			}
			
//			$(document).on("mouseover mouseout", '.feedbackimg_width', function(event){//banner 查看大图
//				if(event.type == "mouseover"){//鼠标悬浮
//					$(this).next().show();
//				}else if(event.type == "mouseout"){ //鼠标离开
//					$(this).next().hide();
//				}
//			});
			
			$tvFeeddetailfrom.find('[data-insert]').html('');
			$tvFeeddetailfrom.insert(data);
			
			$tvFeeddetail.modal('show');//显示反馈详情弹框弹框
		});
	}
	
	
	function HandleFeedback(e) {
		e.stopPropagation();
		$tvFeedbackForm.validate(function() {
			$tvFeedbackForm.setForm(function() {
				S.alert('处理反馈成功!');
				$tvFeedback.modal('hide');
				$formBtn.click();
			});
		});
		return false;
	}
	
	
	
	
	
//	function HandleFeedback () {//处理
//		var $this = $(this);
//		$this.postBtn(CONFIG.addr.updateTvFeedback, $this.data(), function(data) {//处理单个反馈信息
//			
//		});
		
//		var $this = $(this);
//		var data = {};
//		var dealUser = $('input[name=dealUser]').val();
//		var feedbackId = $('input[name=feedbackId]').val();
//		var dealRemark = $('#dealRemark').val();
//		console.log(data);
//		$this.postBtn(CONFIG.addr.updateTvFeedback, {'dealUser':dealUser,'feedbackId':feedbackId,'dealRemark':dealRemark}, function(data) {//处理单个反馈信息
//			
//		});
//	}
	
	
	// 页面JS初始化
	init();

});