/**
 * Created by Storm on 2016/4/12.
 */
require.config({
	paths: {
	    config : 'base/config',
		jquery : 'base/jquery.min',
		storm : 'base/storm-1.5',
		common : 'base/common',
		jqueryztree: 'zTree/jquery.ztree-2.6.min',
		upload : 'base/uploadPreview',
		form : 'base/jquery.form'
	}
});
require(['config', 'jquery', 'storm', 'common', 'base/md5', 'jqueryztree', 'upload', 'form'], function (CONFIG, $, S) {
  'use strict';


	var $form = $('[data-form="search"]'); // 查询表单
	var $formBtn = $form.find(':submit');
	var $formReset = $form.find(':reset');

	function init () {//页面初始化
		$formBtn.click();
		$(document).on('click', '.wp_sign_modify', signModify);//修改
		$(document).on('click', '.wp_sign_save', signSave);//保存修改 
		$(document).on('click', '.wp_sign_cancle', signCancle);//取消修改
		$(document).on('click', '.sign_upimg', selectImg);//取消修改
		
	}
  
	function signModify() {
	  	var $this = $(this);
	  	$this.addClass('hide');
	  	$this.next().removeClass('hide');
	  	$this.next().next().removeClass('hide');
	  	$this.parent().parent().find('.sign_val').addClass('hide');
	  	$this.parent().parent().find('.sign_edit').removeClass('hide');
	}
	
	function signCancle() {
		var $this = $(this);
		$this.addClass('hide');
	  	$this.prev().addClass('hide');
	  	$this.prev().prev().removeClass('hide');
	  	$this.parent().parent().find('.sign_val').removeClass('hide');
	  	$this.parent().parent().find('.sign_edit').addClass('hide');
	}
	
  function signSave() {
	  var $this = $(this);
	  
	  var actAssignment = {};
	  actAssignment.id = $this.attr('data-id');
	  actAssignment.assignmentHeadUrl = $("#sign_img_" + $this.attr('data-id')).val();
	  actAssignment.assignmentReward = $("#sign_num_" + $this.attr('data-id')).val();
	  actAssignment.remark = $("#sign_remark_" + $this.attr('data-id')).val();
	  var data = {};
	  data.actAssignment = JSON.stringify(actAssignment);
	  
	  console.log(data);
	  $this.postBtn(CONFIG.addr.updateActAssignment, data, function(data) {
		  	console.log(data);
		  	S.alert('保存成功');
  	 		$formBtn.click();//查询
	  });
  }
  
  new uploadPreview({//本地预览上传banner图片
		 UpBtn : "photo_upimg",
		 DivShow : "photo_divimg",
		 ImgShow : "photo_showimg",
		 callback: function () {
			 setTimeout(function(){
				var data = {};
				var $this = $(this);
				$("#J_photoImg").ajaxSubmit({
					type: "post", // 提交方式 get/post
					url: CONFIG.addr.fileupload, // 需要提交的 url
					data: data,
					success: function(data) {
						console.log(data);
						if(data.code = '000'){
							var r = data.data.downurl;
							r = r.substring(0,r.indexOf("?"));
							$(".sign_" + $("#photo_id").val()).attr('src', r);
							$("#sign_img_" + $("#photo_id").val()).val(r);
						} else {
							S.alert('机器人头像上传失败!');
						}
					},
				});
			 },500);
		 }
	});

  	function selectImg() {
  		var $this = $(this);
  		$("#photo_id").val($this.attr("data-id"));
  		$("#photo_upimg").click();
  	}
  
//  function modalRightsShow () {
//    $adminRights.find(':reset').trigger('click');
//    $adminRights.modal('show');
//  }
  
  // 页面JS初始化
  init();

});