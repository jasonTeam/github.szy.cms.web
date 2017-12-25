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
  
  var $sysConfigForm = $('#J_sysconfigForm'); 
  var $sysConfigFormBtn = $sysConfigForm.find(':submit'); //权限表单修改保存按钮
  var $form = $('[data-form="search"]'),// 查询系统配置表单
  $formBtn = $form.find(':submit'),  //查询系统配置按钮
  $formReset = $form.find(':reset');  //重置用户按钮
  /**
   * 页面初始化
   */
  function init () {
	  
	  //查询系统配置
	  $formBtn.click();
	  $('[data-select="selectType"]').each(selectType);
	  
	  //修改系统数据
	  $sysConfigFormBtn.on('click',submitsysConfigForm);
	  $(document).on('click', '.J_editBtn', editSys);
	  
	  //选择系统配置
	  //classify  0:其他 1:版本 2:支付 3:直播
	  $(document).on('click', '.classify ul li', selectConfigure);

	  setTimeout(function(){
		  $('.classify ul li').eq(0).click();
	  },500);
	  
	//重置刷新页面
	    $formReset.click(function(){
			setTimeout(function(){
				$formBtn.click();
			},500);
		});
  
  }
  
  function editSys(e) {
	  var $this = $(this);
	  var sysId = $this.attr("data-id");
	  var sysKey = $("#key_" + sysId).val();
	  var sysVal = $("#val_" + sysId).val();
	  
	  $this.postBtn(CONFIG.addr.editSysConfig, {
			"sysId" : sysId,
			"sysKey" : sysKey,
			"sysVal" : sysVal
		}, function() {
			S.alert('修改系统配置成功!', function() {
				$formBtn.click();
				 setTimeout(function(){
					 if($this.attr('data-classify') == '0'){
						 $('.classify ul li').eq(3).click();
					 } else {
						 $('.classify ul li').eq($this.attr('data-classify') - 1).click();
					 }
				  },500);
			});
	  });
  }
  
  
  function selectConfigure(){//选择系统配置
	  var $this = $(this);
	  
	  $('.classify ul li').removeClass('select_classify');
	  $this.addClass('select_classify');
	  
	  $('#content tr').addClass('hide');
	  $('#content tr').each(function(){
		  if($(this).attr('data-classify') == $this.attr('data-sify')){
			  $(this).removeClass();
			  $('#mainFrame', parent.document).height($('.main').height());
		  }
	  });
  }
  
  /**
   * 查询系统配置
   */
//  function sysConfig () {
//    var $this = $(this);
//	$this.postBtn(CONFIG.addr.findSysConfig, $this.data(), function (data) {
//		
//		$('input[name=id]').val(data.id);
//		$('select[name=appletSet]').find('option').each(function(){//支付回填
//			if($(this).val() == data.appletSet){
//				$(this).attr('selected','selected');
//			}
//		});
//		$('select[name=liveSet]').find('option').each(function(){//全线直播回填
//			if($(this).val() == data.liveSet){
//				$(this).attr('selected','selected');
//			}
//		});
//		$('select[name=guessSet]').find('option').each(function(){//全线竞猜回填
//			if($(this).val() == data.guessSet){
//				$(this).attr('selected','selected');
//			}
//		});
//	})
//  }
/**
 * 选择类型
 */
  function selectType () {
    var $this = $(this), html = '';
    $.each(CONFIG.selectType, function (index, value) {
      html += '<option value="' + (index+1) + '">' + value + '</option>';
    });
    $this.html(html);
  }
  
  /**
   * 提交修改系统数据
   * @param e
   */
  function submitsysConfigForm () {
	  $sysConfigForm.validate(function () {
		$sysConfigForm.setForm(function () {
        S.alert('保存成功!', function () {
        	location.reload();//刷新当前页面
        });
      });
    });
    return false;
  }

  // 页面JS初始化
  init();

});