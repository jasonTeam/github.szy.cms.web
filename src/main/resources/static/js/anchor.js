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
      $formReset = $form.find(':reset'),  //重置用户按钮
      $addAnchorForm = $("#J_AddAnchor"),//开通主播表单
      $addAnchorBtn = $addAnchorForm.find(':submit'),//开通主播按钮
      $editAnchorForm = $("#J_EditAnchor"),//编辑主播表单
      $editAnchorBtn = $editAnchorForm.find(':submit'),//编辑主播按钮
      $J_detail = $('#J_detail'),//显示主播详情
  	  $J_showEdit = $('#J_showEdit'),//显示开通主播菜单
  	  $J_showCalorie = $('#J_showCalorie'),//显示主播卡路里
  	  $addCalorieForm = $("#J_AddCalorie"),//提交主播卡路里菜单
  	  $addCalorieBtn = $addCalorieForm.find(':submit'),//提交主播卡路里按钮
  	  $editPermitForm = $("#J_EditPermit"),//提交主播权限菜单
	  $editPermitBtn = $editPermitForm.find(':submit'),//提交主播权限按钮
  	  $J_permit = $('#J_permit'),//显示主播权限
  	  $wp_base = $('#wp_base'),//显示主播基数弹框
  	  $wp_fromBase = $('#wp_frombase'),//主播基数from
  	  $wp_frombaseBtn = $wp_base.find(':submit');//提交基数数值
 var imgNum = 1;
  
  /**
   * 页面初始化
   */
  function init () {
    
//    $formBtn.click();//查询
    
    $(document).on('click', '.J_AnchorLogBtn', anchorLog);//通过id查找详情
    //SHOW审核
    $(document).on('click', '.J_viewBtn', showEdit);
    //创建
    $addAnchorBtn.on('click', addAnchor);
    //编辑
    $editAnchorBtn.on('click', editAnchor);
    //开播
    $(document).on('click', '.J_openLive', openLive);
    //禁播
    $(document).on('click', '.J_banLive', banLive);
    //取消主播
    $(document).on('click', '.wp_cancleLive', cancleLive);
    //卡路里
    $(document).on('click', '.J_CalorieBtn', showCalorie);
    //权限
    $(document).on('click', '.J_PermitBtn', showPermit);
    //提交卡路里
    $addCalorieBtn.on('click', addCalorie);
    //提交权限
    $editPermitBtn.on('click', editPermit);
    //显示基数弹框按钮
    $(document).on('click', '.wp_baseBtn', showBase);
    //提交基数按钮
    $wp_frombaseBtn.on('click', modifyBase);
    $(document).on('click', '.wp_allowBtn', ifOpening);//允许主播是否开盘
    
    $('.code_img').on('click', uploadImg);//上传图片
    
    //重置刷新页面 
//    $formReset.click(function(){
//		setTimeout(function(){
//			$formBtn.click();
//		},500);
//	});
    
    $(document).on("mouseover mouseout", '.code_just', function(event){//banner 查看大图
		if(event.type == "mouseover"){//鼠标悬浮
			$(this).next().removeClass('hide');
		}else if(event.type == "mouseout"){ //鼠标离开
			$(this).next().addClass('hide');
		}
	});
    $(document).on("mouseover mouseout", '.code_back', function(event){//banner 查看大图
		if(event.type == "mouseover"){//鼠标悬浮
			$(this).next().removeClass('hide');
		}else if(event.type == "mouseout"){ //鼠标离开
			$(this).next().addClass('hide');
		}
	});
    
    allGuild();
    
  }
  
  function cancleLive() {//取消主播
	  var $this = $(this);
	  var uId = $this.attr("data-id");
	  var htmlA = '';
	  if ($this.attr("data-islive") == 1) {
		  S.alert('先禁播才能取消主播!');
	  } else {
		  $this.postBtn(CONFIG.addr.anchorLog, {"userId":uId}, function (req) {
			  $('#permit_userId').val(req.userId);
			  $("#permit_name").val(req.nickName);
			  $('#permit_remark').val("");
			  if(0 == req.isLive){
				  htmlA = '<label>取消主播&nbsp;<input type="radio" name="state" checked="checked" value="2" /></label>';
	    	  }
			  $("#stateInfo").html(htmlA);
			  
			  $J_permit.modal('show');
	      });
	  }
  }
  
  function showPermit() {//禁播或者开播
	  var $this = $(this);
	  var uId = $this.attr("data-id");
	  var htmlA = '<label>主播禁播&nbsp;<input type="radio" name="state" value="0" checked="checked" /></label>';
	  var htmlB = '';
	  $this.postBtn(CONFIG.addr.anchorLog, {"userId":uId}, function (req) {
		  $('#permit_userId').val(req.userId);
		  $("#permit_name").val(req.nickName);
		  $('#permit_remark').val("");
		  if(0 == req.isLive){
			  htmlA = '<label>主播开播&nbsp;<input type="radio" name="state" value="1" checked="checked" /></label>';
			  htmlB = '<label>取消主播&nbsp;<input type="radio" name="state" value="2" /></label>';
    	  }
		  $("#stateInfo").html(htmlA + htmlB);
		  
		  $J_permit.modal('show');
      });
  }
  
  function showCalorie() {
	  var $this = $(this);
	  $("#calorie_userId").val($this.attr("data-id"));
	  $("#calorie_num").val($this.attr("data-calorie"));
	  $addCalorieForm.find('#calorie_calorie').val("");
	  $J_showCalorie.modal('show');
  }
  function editPermit(e) {
	  e.preventDefault();
	  $editPermitForm.validate(function () {
		  var userId = $('#permit_userId').val();
		  var permitName = $('#permit_name').val();
		  var remark = $('#permit_remark').val();
		  var state = $("input[name='state']:checked").val();
		  var txt = "确认【" + permitName + "】(" + userId + ")主播禁播？";
		  if ("1" == state) {
			  txt = "确认【" + permitName + "】(" + userId + ")主播开播？";
		  } else if ("2" == state) {
			  txt = "设置【" + permitName + "】(" + userId + ")主播普通用户？";
		  }
		  
		  if("" == userId){
			  S.alert('UserId为空,请重新操作!');
			  return false;
		  } else if ("" == $.trim(remark)) {
			  S.alert('备注不能为空!');
			  return false;
		  } else {
			  S.confirm({
				    width:310,
				    hasHeader: true,// 是否需要头部
			        headerTxt: "主播权限-配置",
					text: txt,
				    confirm: function(){
				    	$editPermitForm.setForm(function (req) {
							  if (req) {
								  S.alert('主播权限修改成功!');
								  $J_permit.trigger('Storm.modal.hide');
								  if ("2" == state) {
									  $("#tr_"+userId).hide();
								  }
								  $formBtn.click();//查询
							  } else {
								  S.alert('主播权限修改失败!');
							  }
					  	  });
				    },
				    cancel: function(){
				    	$J_permit.trigger('Storm.modal.hide');
				    }
			   });
		  }
	  });
	  return false;
  }
  function addCalorie(e) {
	  e.preventDefault();
	  $addCalorieForm.validate(function () {
		  var userId = $addCalorieForm.find('#calorie_userId').val();
		  var calorie = $addCalorieForm.find('#calorie_calorie').val();
		  var calorieNum = $addCalorieForm.find('#calorie_num').val();
		  var reg = /^[1-9]\d*$/;
		  if("" == userId){
			  S.alert('UserId为空,请重新操作!');
			  return false;
		  } else if ("" == $.trim(calorie)) {
			  S.alert('卡路里数量不能为空!');
			  $addCalorieForm.find('#calorie_calorie').val("");
			  return false;
		  } else if (!reg.test($.trim(calorie))) {
			  S.alert('卡路里数量只能为正整数!');
			  $addCalorieForm.find('#calorie_calorie').val("");
			  return false;
		  }  else if ((calorieNum * 1) < (calorie * 1)) {
			  S.alert('卡路里数量超过余额!');
			  $addCalorieForm.find('#calorie_calorie').val("");
			  return false;
		  } else {
			  $addCalorieForm.setForm(function (req) {
				  if (req) {
					  S.alert('主播卡路里提现成功!');
					  $J_showCalorie.trigger('Storm.modal.hide');
					  //查询
					  $formBtn.click();
				  } else {
					  S.alert('主播卡路里提现失败!');
				  }
		  	  });
		  }
	  });
	  return false;
  }

  function anchorLog() {//通过id查找
      var $this = $(this);
      var uId = $this.attr("data-id");
      $this.postBtn(CONFIG.addr.anchorLog, {"userId":uId}, function (req) {
    	  console.log('通过id查找');
		  console.log(req);
		  $J_detail.find('[data-insert]').val("");
    	  $J_detail.insert(req);
    	  $('.logo_img').attr('src', req.phoneId==''?'http://opq77mx3q.bkt.clouddn.com/20170830-logo_totoro.png':req.phoneId);
    	  $('.logo_img').next().val(req.phoneId==''?'http://opq77mx3q.bkt.clouddn.com/20170830-logo_totoro.png':req.phoneId);
    	  $('.code_just').attr('src', req.frontIdentityUrl==''?'http://opq77mx3q.bkt.clouddn.com/20171025-positive.png':req.frontIdentityUrl);
    	  $('.big_just').attr('src', req.frontIdentityUrl==''?'http://opq77mx3q.bkt.clouddn.com/20171025-positive.png':req.frontIdentityUrl);
    	  $('.code_back').attr('src', req.oppositeIdentityUrl==''?'http://opq77mx3q.bkt.clouddn.com/20171025-opposite.png':req.oppositeIdentityUrl);
    	  $('.big_back').attr('src', req.oppositeIdentityUrl==''?'http://opq77mx3q.bkt.clouddn.com/20171025-opposite.png':req.oppositeIdentityUrl);
    	  $('.anchor_types').val(req.anchorType);
    	  $('#add_userId').val(req.userId);
    	  $('.guild_div_modal').val(req.confraternityId);
    	  $J_detail.modal('show');
      })
  }
  
  	function uploadImg() {
		var $this = $(this);
		imgNum = $this.attr('data-img');
		$('#upload_img').click();
	}
	
	new uploadPreview({//本地预览上传banner图片
		 UpBtn : "upload_img",
		 DivShow : "upload_from",
		 ImgShow : "showload_img",
		 callback: function () {
			 setTimeout(function(){
				 var data = {};
				 $("#upload_from").ajaxSubmit({
					 type: "post", // 提交方式 get/post
					 url: CONFIG.addr.fileupload, // 需要提交的 url
					 data: data,
					 success: function(data) {
						 console.log(data);
						 if(data.code = '000'){
							 var r = data.data.downurl;
							 r = r.substring(0,r.indexOf("?"));
							 if (imgNum == 1) {
								 $('.code_just').attr('src',r);
								 $('.big_just').attr('src',r); 
								 $('input[name=frontIdentityUrl]').val(r);
							 } else if (imgNum == 2) {
								 $('.code_back').attr('src',r);
								 $('.big_back').attr('src',r);
								 $('input[name=oppositeIdentityUrl]').val(r);
							 } else if (imgNum == 3) {
								 $('.logo_img').attr('src',r);
								 $('input[name=phoneId]').val(r);
							 }
							 $("#upload_img").val('');
						 }
					 },
				 });
			 },500);
		 }
	 });
  
  
  
  
  function addAnchor(e) {
	  e.preventDefault();
	  $addAnchorForm.validate(function () {
		  $addAnchorForm.setForm(function () {
              S.alert('主播开通成功!');
              $J_showEdit.trigger('Storm.modal.hide');
  	          //查询
  	          $formBtn.click();
		  });
    });
    return false;
  }
  function editAnchor(e) {
	  e.preventDefault();
	  $editAnchorForm.validate(function () {
    	  $editAnchorForm.setForm(function () {
              S.alert('主播信息修改成功!');
              $J_detail.trigger('Storm.modal.hide');
  	          //查询
  	          $formBtn.click();
      });
    });
    return false;
  }
  
  function modifyBase(e){
	  e.preventDefault();
	  $wp_fromBase.validate(function () {
		  $wp_fromBase.setForm(function () {
              S.alert('主播基数修改成功!');
              $wp_base.trigger('Storm.modal.hide');
  	          //查询
  	          $formBtn.click();
      });
    });
    return false;
  }
  
  
  
  function showEdit() {
	  var $this = $(this);
	  $("#edit_userId").val($this.attr("data-id"));
	  $J_showEdit.modal('show');
  }
  
  function showBase() {//显示基数弹框
	  var $this = $(this);
	  $("#base_userId").val($this.attr("data-id"));
	  $("#base_base").val($this.attr("data-base"));
	  $wp_base.modal('show');
  }
  
  /**
   * 禁播
   */
  function banLive() {
    var $this = $(this);
    var uId = $this.attr("data-id");
    $this.postBtn(CONFIG.addr.editAnchorLive, {"userId":uId, "isLive" : "0"}, function () {
    	S.alert('禁止主播直播成功!', function () {
    		$J_detail.trigger('Storm.modal.hide');
    		$formBtn.click();
       });
    });
  }
  /**
   * 开播
   */
  function openLive() {
    var $this = $(this);
    var uId = $this.attr("data-id");
    $this.postBtn(CONFIG.addr.editAnchorLive, {"userId":uId, "isLive" : "1"}, function () {
    	S.alert('开通主播直播成功!', function () {
    		$J_detail.trigger('Storm.modal.hide');
    		$formBtn.click();
       });
    });
  }
  
  	function ifOpening(){//是否允许主播开盘
  		var $this = $(this);
  		var uid = $this.attr('data-user-id');
  		var nickName = $this.attr('data-nick-name');
  		var guessText = $this.attr('data-guesstext');
  		var txt = "设置【" + nickName + "】(" + uid + ")" + guessText + "？";
  		S.confirm({
  		    width:500,
  		    hasHeader: true,// 是否需要头部
  	        headerTxt: "主播禁猜-配置",
  			text: txt,
  		    confirm: function(){
  		  		console.log($this.data());
  				$this.postBtn(CONFIG.addr.isOrNotGuess, $this.data(), function (req) {
  					console.log(req);
  					if(req.isguess == 1){
  						S.alert('已允许主播开竞猜');
  					} else if(req.isguess == 2) {
  						S.alert('已禁止主播开竞猜');
  					}
  					$formBtn.click();
  				});
  		    },
  		    cancel: function(){
  		    	
  		    }
  	   });
	}
  	
  	function allGuild(){//查询所有的公会
		var $this = $(this);
		$this.postBtn(CONFIG.addr.getAllConfName, $this.data(), function(data) {
			console.log(data);
			$('.guild_div').html(S.template(data, $('#guild_html').html()));
			$('.guild_div_modal').html(S.template(data, $('#guild_html_modal').html()));
		});
	}
  
  // 页面JS初始化
  init();

});