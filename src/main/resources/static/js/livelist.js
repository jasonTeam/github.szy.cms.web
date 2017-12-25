/**
 * Created by Storm on 2016/4/12.
 */
require.config({
  paths: {
    config: 'base/config',
    jquery: ['base/jquery.min', 'jquery.min'],
    storm: 'base/storm-1.5',
    common: 'base/common',
    upload : 'base/uploadPreview',
	form : 'base/jquery.form',
  }
});
require(['config', 'jquery', 'storm', 'common', 'upload', 'form'], function (CONFIG, $, S) {
  'use strict';

  var $form = $('[data-form="search"]'),// 查询用户表单
      $formBtn = $form.find(':submit'),  //查询用户按钮
      $formReset = $form.find(':reset');  //重置用户按钮
  
  /**
   * 页面初始化
   */
  	function init () {
    
//	  	$formBtn.click();//查询
    
//   	 $(document).on('click', '.J_AnchorLogBtn', anchorLog);//通过id查找详情
    
    	$(document).on('click', '.wp_number_modify', numberModify);//修改
    	$(document).on('click', '.wp_number_cancle', numberCancle);//取消修改
    	$(document).on('click', '.wp_number_save', numberSave);//保存修改 
    	
    	$(document).on('click', '.wp_sort_modify', sortModify);//修改
    	$(document).on('click', '.wp_sort_cancle', sortCancle);//取消修改
    	$(document).on('click', '.wp_sort_save', sortSave);//保存修改 
    	
    	$(document).on('click', '.wp_cancle_top', cancleTop);//取消置顶
    	$(document).on('click', '.wp_sure_top', sureTop);//置顶
    	
    	$(document).on('click', '.wp_removelist', removeList);//直播列表移除主播
    	
    	$(document).on('keyup', '.input_num', function(){//校验正整数
    		var b = /^[0-9]*[1-9][0-9]*$/;
    		if (!b.test($(this).val())) {
    			S.alert('请输入正整数');
    			$(this).val('');
    		}
    	});
    	
    	//重置刷新页面
	    $formReset.click(function(){
			setTimeout(function(){
				$formBtn.click();
			},500);
		});
  	}
  
  	function removeList() {
  	  var $this = $(this);
  	  var userId = $this.attr("data-id");
  	  var txt = "确认直播列表移除【" + userId + "】主播？";
  	  S.confirm({
  		    width:500,
  		    hasHeader: true,// 是否需要头部
  	        headerTxt: "主播列表-移除",
  			text: txt,
  		    confirm: function(){
  		    	$this.postBtn(CONFIG.addr.removeList, {"userId":userId}, function (req) {
  		    		S.alert("操作完成!");
  		    		$formBtn.click();
  		        })
  		    },
  		    cancel: function(){}
  	   });
    }
  	
  	
  	function numberModify() {
  		var $this = $(this);
  		$this.addClass('hide');
  		$this.next().removeClass('hide');
  		$this.next().next().removeClass('hide');
  		
  		$this.parent().prev().prev().find('span').addClass('hide');
  		$this.parent().prev().prev().find('input').removeClass('hide');
	}
  	function numberCancle() {
  		var $this = $(this);
  		$this.addClass('hide');
  		$this.prev().removeClass('hide');
  		$this.next().addClass('hide');
  		
  		$this.parent().prev().prev().find('span').removeClass('hide');
  		$this.parent().prev().prev().find('input').addClass('hide');
	}
  	function numberSave() {//保存机器人数
  		var $this = $(this);
  		var robotNum = $this.parent().prev().prev().find('input').val();
  		if (robotNum == '') {
  			S.alert('机器人数不能为空')
  		} else {
	  		var data = {};
			data.userId = $this.attr('data-user-id');
			data.robot = robotNum;
			console.log(data);
			$this.postBtn(CONFIG.addr.modifyRoomRobotNum, data, function(data) {
				console.log(data);
				S.alert('修改机器人数成功');
				$formBtn.click();//查询
			});
  		}
  	}
  	function sortModify() {
  		var $this = $(this);
  		var $this = $(this);
  		$this.addClass('hide');
  		$this.next().removeClass('hide');
  		$this.next().next().removeClass('hide');
  		
  		$this.parent().prev().prev().find('span').addClass('hide');
  		$this.parent().prev().prev().find('input').removeClass('hide');
  		$this.parent().prev().prev().prev().find('span').addClass('hide');
  		$this.parent().prev().prev().prev().find('input').removeClass('hide');
  	}
  	function sortCancle() {
  		var $this = $(this);
  		var $this = $(this);
  		$this.addClass('hide');
  		$this.prev().removeClass('hide');
  		$this.next().addClass('hide');
  		
  		$this.parent().prev().prev().find('span').removeClass('hide');
  		$this.parent().prev().prev().find('input').addClass('hide');
  		$this.parent().prev().prev().prev().find('span').removeClass('hide');
  		$this.parent().prev().prev().prev().find('input').addClass('hide');
  	}
  	function sortSave() {//保存底数倍数
  		var $this = $(this);
  		var baseNum = $this.parent().prev().prev().prev().find('input').val();
  		var multipleNum = $this.parent().prev().prev().find('input').val();
  		if (baseNum == '') {
  			S.alert('底数不能为空');
  		} else if (multipleNum == '') {
  			S.alert('倍数不能为空');
  		} else {
	  		var data = {};
			data.userId = $this.attr('data-user-id');
			data.base = $this.parent().prev().prev().prev().find('input').val();
			data.multiple = $this.parent().prev().prev().find('input').val();
			console.log(data);
			$this.postBtn(CONFIG.addr.modifyRoomRobotNum, data, function(data) {
				console.log(data);
				S.alert('修改排序值成功');
				$formBtn.click();//查询
			});
  		}
  	}
  
  	function cancleTop() {
  		var data = {};
		data.userId = $this.attr('data-user-id');
		data.robot = robotNum;
		console.log(data);
		$this.postBtn(CONFIG.addr.modifyRoomRobotNum, data, function(data) {
			console.log(data);
			S.alert('取消置顶成功');
			$formBtn.click();//查询
		});
	}
  	
  	function sureTop() {
  		var data = {};
		data.userId = $this.attr('data-user-id');
		data.robot = robotNum;
		console.log(data);
		$this.postBtn(CONFIG.addr.modifyRoomRobotNum, data, function(data) {
			console.log(data);
			S.alert('置顶成功');
			$formBtn.click();//查询
		});
	}
  // 页面JS初始化
  init();

});