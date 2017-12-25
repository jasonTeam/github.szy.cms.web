/**
 * Created by Storm on 2016/4/12.
 */
require.config({
	paths : {
		config : 'base/config',
		jquery : 'base/jquery.min',
		storm : 'base/storm-1.5',
		common : 'base/common',
	},
	shim : {
		md5 : {
			exports : 'md5'
		},
	}
});
require([ 'config', 'jquery', 'storm', 'common', 'base/md5' ], function(CONFIG,	$, S) {
	'use strict';

	/**
	 * 页面初始化
	 */
	function init() {

		$(document).on('click', '.appset_btn', modifyEdit);
		$(document).on('click', '.sure_share', modifyRemark);
		$(document).on('click', '.cancel_share', cancelRemark);
		
		getRemark();
	}

	function modifyEdit() {//修改直播间分享内容切换
		$('.appset_con').hide();
		$('.appset_input').show();
		$('.appset_share').focus();
	}

	function getRemark() {//获取remak
		var $this = $(this);
		$this.postBtn(CONFIG.addr.getRemark, {}, function(data) {
			console.log(data)
			$('.appset_con p').eq(0).text(data);
			$('.appset_share').val(data);
		});
	}

	function modifyRemark() {//修改remark
		var $this = $(this);
		var data = {};
		data.remark = $('.appset_share').val();
		$this.postBtn(CONFIG.addr.updateLiveRoomRemark, data, function(data) {
			console.log(data)
			$('.appset_con').show();
			$('.appset_input').hide();
			getRemark();
		});
	}
	
	function cancelRemark() {//关闭输入框
		$('.appset_con').show();
		$('.appset_input').hide();
	}

	// 页面JS初始化
	init();

});