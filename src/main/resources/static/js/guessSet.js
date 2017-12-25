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
	
	var intDiff = 0;
	
	/**
	 * 页面初始化
	 */
	function init() {

		getSystemList();
		getGuessNum();
//		getHarvestInfo();
		
		$(document).on('click', '.appset_btn', btnModify);
		$(document).on('click', '.sure_btn', btnSure);//确定修改
		$(document).on('click', '.cancel_btn', btnCancel);//取消
		
		$(document).on('click', '.appset_btnmodal', showReap);//显示收割弹框
		
		$(document).on('click', '.btn_modify', modifyHarvest);//确定收割时间
		$(document).on('click', '.btn_cancel', modifyHarvest);//取消收割时间
		
		$(document).on('click', '.appset_refresh', getGuessNum);//刷新盘数
		
		$('input[name=harvest').bind('click',slectHarvest);
		
		$('.harvest_minute').blur(function() { 
			if ($(this).val() > 60) {
				S.alert('数值不能大于60');
				$(this).val('');
			}
		});  
		
	}

	function getSystemList() {//获取系统配置
		var $this = $(this);
		$this.postBtn(CONFIG.addr.findSysConfig, {}, function(data) {
//			console.log(data)
			for (var i = 0; i < data.length; i++) {
				if (data[i].id == 3) {
					if (data[i].val == 1) {
						$('#open').attr("checked",true).unbind('click');
						$('#shut').attr("checked",false).bind('click',modifyMasterSwitch);
					} else {
						$('#open').attr("checked",false).bind('click',modifyMasterSwitch);
						$('#shut').attr("checked",true).unbind('click');
					}
				}
				
				$('.appset_list').each(function(){
					if($(this).attr('data-id') == data[i].id){
//						$(this).find('.appset_name').text('');
						$(this).find('.appset_con').find('span').eq(0).text(data[i].val);
						$(this).find('.appset_input').find('input').val(data[i].val);
						$(this).find('button').attr({
							'data-id':data[i].id,
							'data-key':data[i].name
						});
					} 
				});
			}
		});
	}
	
	function modifyMasterSwitch(){//竞猜总开关
		var $this = $(this);
		
		if ($this.val() == 1) {
			var sureText = '确定打开竞猜总开关？';
		} else if ($this.val() == 2) {
			var sureText = '确定关闭竞猜总开关？';
		}
		
		S.confirm(sureText, function() {
			var data = {};
			data.sysId = $this.attr('data-id');
			data.sysKey = $this.attr('data-key');
			data.sysVal = $this.val();
			$this.postBtn(CONFIG.addr.editSysConfig, data, function() {
				S.alert('修改竞猜总开关成功!');
				getSystemList();
			});
		});
	}
	
	function btnModify() {//显示修改输入框
		var $this = $(this);
		$this.parent().hide();
		$this.parent().next().show();
	}
	
	function btnSure() {//确认修改数据
		var $this = $(this);
		
		if ($this.attr('data-id') == '39') {
			if ($('.appset_money').val() < 100000) {
				S.alert('注池金额不能少于10W');
				return;
			}
		}
		
		var data = {};
		data.sysId = $this.attr('data-id');
		data.sysKey = $this.attr('data-key');
		data.sysVal = $this.prev().prev().val();
		$this.postBtn(CONFIG.addr.editSysConfig, data, function() {
			S.alert('修改系统配置成功!');
			getSystemList();
			$this.parent().hide();
			$this.parent().prev().show();
		});
	}
	
	function btnCancel() {//关闭输入框
		var $this = $(this);
		$this.parent().hide();
		$this.parent().prev().show();
	}
	
	function getGuessNum() {//获取竞猜盘数
		
		$('.appset_refresh').hide();
		$('.mui-spinner').css({'display':'inline-block'});
		
		var $this = $(this);
		console.log('获取竞猜盘数');
		$this.postBtn(CONFIG.addr.getIntelGuessNum, {}, function(data) {
			console.log(data);
			$('.reap_num').text(data);
			$('.appset_modal_title p').eq(1).find('span').text(data);
			setTimeout(function(){
				$('.appset_refresh').show();
				$('.mui-spinner').css({'display':'none'});	
			},1000);
		});
	}
	
	function showReap() {//显示收割弹框
		getHarvestInfo();
		$('#wp_reap').modal('show');
	}
	
	Timersertim();
	function Timersertim() {
		var day = 0,
			hour = 0,
			AllHour = 0,
			minute = 0,
			second = 0; //时间默认值		
		if(intDiff > 0) {
			day = Math.floor(intDiff / (60 * 60 * 24));
			hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
			AllHour = Math.floor(intDiff / (60 * 60));
			minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
			second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
		} else if (intDiff <= 0) {
			
		}
		if(AllHour <= 9) AllHour = '0' + AllHour;
		if(minute <= 9) minute = '0' + minute;
		if(second <= 9) second = '0' + second;
		$('.harvest_countdown').text(minute + "'" + second);
		
		intDiff--;
		setTimeout(function(){
			Timersertim();
		},1000);
		
		if (intDiff > 0) {
//			intDiff--;
//			setTimeout(function(){
//				Timersertim();
//			},1000);
		} else if (intDiff == 0) {
//			window.location.reload();
			$('.btn_cancel').click();
		}
	}
	
	function slectHarvest() {//选择收割
		var $this = $(this);
		$('.btn_modify').attr('data-winVal',$this.val());
	}
	
	function getHarvestInfo() {//查询收割信息
		var $this = $(this);
		
		$this.postBtn(CONFIG.addr.findWinMachineRandom, {}, function(data) {
			console.log('查询收割信息');
			console.log(data);
			intDiff = parseInt(data.winSecond);
			if (data.val == 2) {//未开启
				$('.input_harvest').show();
				$('.harvest_countdown').hide();
				$('.btn_modify').removeClass('hide');
				$('.btn_cancel').addClass('hide');
				$('#harvest').removeAttr("disabled");
				$('#anti_harvest').removeAttr("disabled");
			} else {
				$('.input_harvest').hide();
				$('.harvest_countdown').show();
				$('.btn_modify').addClass('hide');
				$('.btn_cancel').removeClass('hide');
				$('.btn_modify').attr('data-winVal',data.val);
//				Timersertim();
				if (data.val == 1) {//已开启收割
					$('#harvest').attr({
						"checked" : true,
						"disabled" : "disabled"
					});
					$('#anti_harvest').attr({
						"checked" : false,
						"disabled" : "disabled"
					});
				} else if (data.val == 3) {//已开启反收割
					$('#anti_harvest').attr({
						"checked" : true,
						"disabled" : "disabled"
					});
					$('#harvest').attr({
						"checked" : false,
						"disabled" : "disabled"
					});
				}
			}
		});
	}
	
	function modifyHarvest(){//确定、取消收割
		var $this = $(this);
		
		var min = $('.harvest_minute').val();
		
		if (min > 60) {
			$('.harvest_minute').val('');
			return;
		}
		
		var data = {};
		data.winVal = $this.attr('data-winVal');
		
		if ($this.attr('data-winVal') == 1 || $this.attr('data-winVal') == 3) {
			if ($('.harvest_minute').val() == '') {
				S.alert('请输入时间!');
				return;
			}
			data.winSecond = min * 60;
		} else if ($this.attr('data-winVal') == 2) {
			data.winSecond = 0;
		}
		
		console.log('确定、取消收割');
		console.log(data);
		$this.postBtn(CONFIG.addr.updateWinMachineRandom, data, function(data) {
			console.log('true');
			console.log(data);
			$('.harvest_minute').val('');
			$('.harvest_countdown').text("00'00");
			getHarvestInfo();
		});
	}
	
	// 页面JS初始化
	init();

});