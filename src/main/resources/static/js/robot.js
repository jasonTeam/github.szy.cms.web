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
  	  $form = $('[data-form="search"]'),// 查询机器人注册信息表单
      $formBtn = $form.find(':submit'),//提交查询机器人注册信息数据
      $formReset = $form.find(':reset'),
      //$formConfig = $('[data-form="searchConfig"]'),// 查询机器人系统配置表单
      //$formBtnConfig = $formConfig.find(':submit'),  //查询机器人系统配置按钮
  	  $J_showAddRobot = $('#J_showAddRobot'),//显示初始化机器人
	  $addRobotForm = $("#J_AddRobot"),//提交初始化机器人菜单
  	  $addRobotBtn = $addRobotForm.find(':submit');//提交初始化机器人按钮
  
  /**
   * 页面初始化
   */
  function init() {
	  
//	 $formBtn.click();
	//显示初始化机器人
      $(document).on('click', '.J_AddShowRobot', function(){
    	  $J_showAddRobot.modal('show')
      });
	//提交初始化机器人
	  $addRobotBtn.on('click', initializeInfo);
    //上传机器人头像
      $(document).on('click', '.show_photo', selectImg);
    //显示文本框
      $(document).on('click', '.td_css', showText);
    //提交文本
      $(document).on('click', '.submit_css', editRobot);
    //隐藏文本框
      $(document).on('click', '.cancel_css', function(){
    	  $(".div_css").hide();
	  });
      
      $formReset.click(function(){
  		setTimeout(function(){
  			$formBtn.click();
  		},500);
  	});
  }

  function showText() {
	  var $this = $(this);
	  var top = $this.position().top, 
	  left = $this.position().left, 
	  width = $this.css("width"),
	  height = $this.css("height").replace("px", "");
	  var height1 = $("#robot_text").css("height").replace("px", "");
	  var top1 = (height - height1) / 2;
	  id = $this.attr("data-id");
	  type = $this.attr("data-title");

	  $("#robot_text").hide();
	  $("#robot_select").hide();
	  if ("name" == type || "grade" == type) {
		  $("#robot_text").val($this.html());
		  $("#robot_text").css("width", (width.replace("px", "") - 60) + "px");
		  $("#robot_text").show();
	  } else if ("sex" == type) {
		  $("#robot_select").val("女" == $this.html() ? "0" : "1");
		  $("#robot_select").css("width", (width.replace("px", "") - 60) + "px");
		  $("#robot_select").show();
	  }
	  $(".div_css").css("width", width);
	  $(".div_css").css('left', left);
	  $(".div_css").css('top', top + top1);
	  $(".div_css").show();
  }
  
  function editRobot(){
	  var $this = $(this);
	  var sex = "", name = "", grade = "", val = $("#robot_text").val();
	  if ("name" == type) {
		  name = $("#robot_text").val();
	  } else if ("sex" == type) {
		  sex = $("#robot_select").val();
	  } else if ("grade" == type) {
		  grade = $("#robot_text").val();
	  }
	  $this.postBtn(CONFIG.addr.editRobotData, {"userId":id,"sex":sex,"name":name,"grade":grade}, function (req) {
		  if(req){
			  if ("sex" == type) {
				  val = "0" == sex ? "女" : "男";
			  }
			  $("#" + type + "_" + id).html(val);
		  } else {
			  S.alert('机器人资料修改失败!');
		  }
	  });
	  $("#robot_text").val("")
	  $("#robot_select").val("0");
	  $(".div_css").hide();
  }
  
  function initializeInfo(e) {
	  e.preventDefault();
	  $addRobotForm.validate(function () {
		  $addRobotForm.setForm(function (req) {
			  if (req) {
				  S.alert('初始化机器人注册信息成功!');
				  $J_showAddRobot.trigger('Storm.modal.hide');
				  //查询
				  $formBtn.click();
			  } else {
				  S.alert('初始化机器人注册信息失败!');
				  $J_showAddRobot.trigger('Storm.modal.hide');
			  }
	  	  });
	  });
	  return false;
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
							submitPhoto(r);
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
	  if ("" != $("#photo_id").val()) {
		  $("#photo_upimg").click();
	  }
  }
  
  function submitPhoto(imgSrc){
	  var $this = $(this);
	  var userId = $("#photo_id").val();
	  $this.postBtn(CONFIG.addr.editRobotData, {"userId":userId, "photo":imgSrc}, function (req) {
		  if(req){
			  $('#img_'+userId).attr("src", imgSrc);
		  } else {
			  S.alert('机器人头像修改失败!');
		  }
	  });
	  $("#photo_id").val("");
	  $('#photo_upimg').val("");
  }
  
  // 页面JS初始化
  init();
});