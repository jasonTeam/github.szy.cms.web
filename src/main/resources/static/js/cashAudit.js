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
	WdatePicker : 'My97DatePicker/WdatePicker',
  }
});
require(['config', 'jquery', 'storm', 'common', 'upload', 'form', 'WdatePicker'], function (CONFIG, $, S) {
  'use strict';

  var $form = $('[data-form="search"]'),// 查询用户表单
      $formBtn = $form.find(':submit'),  //查询用户按钮
      $formReset = $form.find(':reset');  //重置用户按钮
      
  /**
   * 页面初始化
   */
  function init () {
//    $formBtn.click();//查询
    $(document).on('click', '.wp_cashDetails', getCashInfo);//通过id查找详情
    allGuild();
    
  //重置刷新页面
    $formReset.click(function(){
		setTimeout(function(){
			$formBtn.click();
		},500);
	});
  }

  function getCashInfo() {//通过订单号id查找
      var $this = $(this);
      var extractId = $this.attr('data-extract-id');
      $(window.parent.document).find("#mainFrame").attr("src","?extractId=" + extractId);
      window.location.href = "/cashDetails";
  }
  
  function allGuild(){//查询所有的公会
		var $this = $(this);
		$this.postBtn(CONFIG.addr.getAllConfName, $this.data(), function(data) {
			console.log(data);
			$('.guild_div').html(S.template(data, $('#guild_html').html()));
		});
	}
  
  
  // 页面JS初始化
  init();

});