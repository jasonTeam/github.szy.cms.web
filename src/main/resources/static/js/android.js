/**
 * Created by Storm on 2016/4/12.
 */
require.config({
  paths: {
    config: 'base/config',
    jquery: ['base/jquery.min', 'jquery.min'],
    storm: 'base/storm-1.5',
    common: 'base/common'
  }
});
require(['config', 'jquery', 'storm', 'common'], function (CONFIG, $, S) {
  'use strict';

  var $form = $('[data-form="search"]'),// 查询Android表单
      $formBtn = $form.find(':submit'),  //查询Android按钮
      $formReset = $form.find(':reset'),  //重置Android按钮
      $J_showAndroid = $('#J_showAndroid'),//显示新增Android菜单
      $J_detail = $('#J_detail'),//显示Android详情
      $addAndroidForm = $("#J_AddAndroid"),//新增Android表单
      $addAndroidBtn = $addAndroidForm.find(':submit'),//新增Android按钮
      $editAndroidForm = $("#J_EditAndroid"),//编辑Android表单
      $editAndroidBtn = $editAndroidForm.find(':submit');//编辑Android按钮

  
  /**
   * 页面初始化
   */
  function init () {
    //查询
    //$formBtn.click();
    //通过id查找详情
    $(document).on('click', '.J_AndroidLogBtn', androidLog);
    //显示Android菜单
    $(document).on('click', '.J_AddBtn', showAndroid);
    //新增Android
    $addAndroidBtn.on('click', addAndroid);
    //编辑Android
    $editAndroidBtn.on('click', editAndroid);
    
    //重置刷新页面
    $formReset.click(function(){
		setTimeout(function(){
			$formBtn.click();
		},500);
	});
  }
  
  function showAndroid() {
	  var $this = $(this);
	  $J_showAndroid.modal('show');
  }
  
  /**
   * 通过id查找
   */
  function androidLog() {
      var $this = $(this);
      var chlId = $this.attr("data-id");
      $this.postBtn(CONFIG.addr.androidLog, {"chlId":chlId}, function (req) {
    	  if ("" == req) {
    		  $J_detail.find('[data-insert]').val("");
    		  $("#verExplain").val("");
    	  } else {
    		  $J_detail.find('[data-insert]').val("");
    		  $("#verExplain").val(req.verExplain);
        	  $J_detail.insert(req);
    	  }
    	  $J_detail.modal('show');
      })
  }

  function editAndroid(e) {
	  e.preventDefault();
	  $editAndroidForm.validate(function () {
		  $editAndroidForm.setForm(function (req) {
			  if (req) {
				  S.alert('修改Android版本信息成功!');
				  $J_detail.trigger('Storm.modal.hide');
				  //查询
				  $formBtn.click();
			  } else {
				  S.alert('修改Android版本信息失败!');
			  }
	  	  });
	  });
	  return false;
  }
  function addAndroid(e) {
	  e.preventDefault();
	  $addAndroidForm.validate(function () {
		  $addAndroidForm.setForm(function (req) {
			  if (req) {
				  S.alert('新增Android版本信息成功!');
				  $J_showAndroid.trigger('Storm.modal.hide');
				  //查询
				  $formBtn.click();
			  } else {
				  S.alert('新增Android版本信息失败!');
			  }
	  	  });
	  });
	  return false;
  }

  // 页面JS初始化
  init();

});