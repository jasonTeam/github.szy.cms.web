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
	form : 'base/jquery.form'
  }
});
require(['config', 'jquery', 'storm', 'common', 'upload', 'form'], function (CONFIG, $, S) {
  'use strict';

  var id = "", type = "",
  	  $form = $('[data-form="search"]'),// 查询机器人表单
      $formBtn = $form.find(':submit');//提交查询机器人数据
  
  /**
   * 页面初始化
   */
  function init() {
	//创建注册化机器人
	  $(document).on('click', '.J_CreateShowRobot', createRobot);
  }

  function createRobot() {
	  S.confirm({
		    width:300,
		    hasHeader: false,// 是否需要头部
	        headerTxt: "创建机器人",
			text: "是否创建机器人？",
		    confirm: function(){
		    	var $this = $(this);
		  	  	$this.postBtn(CONFIG.addr.robotReg, {}, function (req) {
		  	  		if(req){
		  	  			S.alert('机器人创建注册成功!');
		  	  		} else {
		  	  			S.alert('机器人创建注册失败!');
		  	  		}
		  	  		$formBtn.click();
		  	  	});
		    },
		    cancel: function(){
//		    	$J_showBean.trigger('Storm.modal.hide');
		    }
	   });
	  
	  
  }
  
  // 页面JS初始化
  init();
});