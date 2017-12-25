/**
 * Created by Storm on 2016/4/12.
 */
require.config({
  paths: {
	    config : 'base/config',
		jquery : 'base/jquery.min',
		storm : 'base/storm-1.5',
		common : 'base/common'
  }
});
require(['config', 'jquery', 'storm', 'common'], function (CONFIG, $, S) {
  'use strict';
  
  var $form = $('[data-form="search"]'),// 查询IOS支付配置表单
  $formBtn = $form.find(':submit');  //查询IOS支付配置按钮
  
  function init () {//页面初始化
	  //查询系统配置
	  $formBtn.click();
	  //修改机器人配置
	  $(document).on('click', '.J_editBtn', editSys);
  }
    
  //修改机器人配置
  function editSys(e) {
	  var $this = $(this);
	  
	  var number = /^[0-9]*[1-9][0-9]*$/;
	  var time = /^(?:(?:[0-2][0-3])|(?:[0-1][0-9])):[0-5][0-9]$/;
	  
	  var oldNum = $this.attr("data-val");
	  var configId = $this.attr("data-id");
	  var val = $this.parent().prev().prev().find('input').val();
	  var childEname = $this.attr('data-childEname');
	  if (val == '') {
		  S.alert('值不能为空！');
		  return;
	  } else {
		  
  		if (childEname == 'ROBOT_START_TIME' || childEname == 'ROBOT_STOP_TIME') {
			if (!time.test(val)) {
  	  			S.alert('请输入正确时间');
  	  			$this.parent().prev().prev().find('input').val(oldNum);
  	  			return;
  	  		}
  		} else {
  			if (!number.test(val)) {
  	  			S.alert('请输入正整数');
  	  			$this.parent().prev().prev().find('input').val(oldNum);
  	  		 return;
  	  		}
  		}
  		if (childEname == 'ROBOT_THREAD_MAX_ACTIVATE_SIZE') {
			  var v = $("#val_ROBOT_THREAD_POOL_MAX_SIZE").val();
			  if(Number(val) > Number(v)){
				  S.alert('激活线程最大值不能大于线程池最大数!');
				  $this.parent().prev().prev().find('input').val(oldNum);
				  return;
			  }
		  }
	  }
	  $this.postBtn(CONFIG.addr.updateRobotConfig, {
			"configId" : configId,
			"val" : val
		}, function(req) {
			if (req) {
				S.alert('修改机器人配置成功!');
				$formBtn.click();
			}
	  });
  }
  
  // 页面JS初始化
  init();

});