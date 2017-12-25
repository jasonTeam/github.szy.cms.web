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

  	var $form = $('[data-form="search"]'),// 查询用户表单
  		$formBtn = $form.find(':submit'),  //查询用户按钮
  		$formReset = $form.find(':reset'); //重置用户按钮
  /**
   * 页面初始化
   */
  function init() {
	  
//	  $formBtn.click();
	  //重置刷新页面
	  $formReset.click(function(){
		  setTimeout(function(){
			  $formBtn.click();
		  },500);
	  });
	  
	  $('.sub_establish').on('click', addEstablish);//提交创建机器人语句
	  $('.sub_edit').on('click', editEstablish);//提交修改机器人语句
	  
	//创建注册化机器人
	 $(document).on('click', '.J_CreateRobotTalkInfo', createRobotTalkInfo);
	 $(document).on('click', '.wp_upper_lower', upperLower);//上下架
	 $(document).on('click', '.wp_edit', editRobit);//编辑
	 
  }

  	function upperLower() {//上下架
  		var $this = $(this);
  		$this.postBtn(CONFIG.addr.deleteRobotTalkInfo, $this.data(), function (req) {
  			S.hint('操作成功!');
  			$formBtn.click();
  		});
	}
  
  	function editRobit() {//编辑机器人语句
  		 var $this = $(this);
  		 var data = {};
  		 data.id = $this.attr('data-info-id');
  		 $this.postBtn(CONFIG.addr.getOneRobotTalkInfo, data, function (req) {
  			 $('.sentencePurpose_val').val(req.sentencePurpose);
  			 $('.talkType_val').val(req.talkType);
  			 $('.sentenceType_val').val(req.sentenceType);
  			 $('.sex_val').val(req.sex);
  			 $('.isGroup_val').val(req.isGroup);
  			 $('.isgroupNum_val').val(req.isgroupNum);
  			 $('.talkContent_val').val(req.talkContent);
  			 $('.isDelete_val').val(req.isDelete);
  			 $('.infoId_val').val(req.infoId);
  			 $('#wp_edit_modal').modal('show');
  		 });
	}
  	
  	 function editEstablish(e) {
  		  e.preventDefault();
  		  $('#wp_edit_form').validate(function () {
  			  $('#wp_edit_form').setForm(function () {
  				  S.hint('机器人语句修改成功成功!');
  				  $('#wp_edit_modal').trigger('Storm.modal.hide');
  				  $formBtn.click();//查询
  			  });
  		  });
  		  return false;
  	  }
  	
  function createRobotTalkInfo() {
	  var $this = $(this);
	  $('#wp_establish').modal('show');
  }
  function addEstablish(e) {
	  e.preventDefault();
	  $('#wp_addEstablish').validate(function () {
		  $('#wp_addEstablish').setForm(function () {
			  S.hint('机器人语句创建成功!');
			  $('#wp_establish').trigger('Storm.modal.hide');
			  $formBtn.click();//查询
			  setTimeout(function(){
				  if ($('#create_build').is(':checked')) {
					  $('#wp_establish').modal('show');
				  }
			  },1000);
		  });
	  });
	  return false;
  }
  
  $(".is_group").change(function () { 
	  var $this = $(this);
      var ss = $this.val();  
      if ($this.val() == "1") {//是
    	  $('.is_groupnum').attr({'required' : 'required'});
      } else if ($this.val() == "2") {//否  
    	  $('.is_groupnum').removeAttr('required');
      }  
  });
  
  // 页面JS初始化
  init();
});