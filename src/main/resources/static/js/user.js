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
  	  $forbidMsgForm = $("#J_ForbidMsg"),//配置用户禁言表单
  	  $forbidMsgBtn = $forbidMsgForm.find(':submit');//配置用户禁言按钮

  var imgNum = 1;
  
  /**
   * 页面初始化
   */
  function init () {
    
//    $formBtn.click();//查询
   
    $(document).on('click', '.wp_seedetailBtn', getUserInfo);//通过id查找详情
    
    $editAnchorBtn.on('click', editAnchor);//编辑
    
    $('.code_img').on('click', uploadImg);//上传图片
    
    $(document).on('click', '.wp_gag', isGag);//用户禁言
    $(document).on('click', '.wp_ban', isBan);//用户封禁
    $(document).on('click', '.wp_super', isSuper);//app超管
    
    //SHOW审核
    $(document).on('click', '.J_viewBtn', showEdit);
    //创建
    $addAnchorBtn.on('click', addAnchor);
   
    //开播
    //$(document).on('click', '.J_openLive', openLive);
    //禁播
    //$(document).on('click', '.J_banLive', banLive);
    //取消主播
    //$(document).on('click', '.J_cancel', cancel);
    //用户封禁
    //$(document).on('click', '.J_SealBtn', seal);
    //用户解禁
    //$(document).on('click', '.J_UnbindBtn', unbind);
    
    //取消禁言
    //$(document).on('click', '.J_CancelBtn', showCancel);
    //用户禁言菜单
    //$(document).on('click', '.rTime', showTime);
    //用户禁言
    //$forbidMsgBtn.on('click', forbidMsg);
    
    //重置刷新页面
    $formReset.click(function(){
		setTimeout(function(){
			$formBtn.click();
		},500);
	});
    
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
    
  }
  function forbidMsg() {
	  var state = $("input[name='state']:checked").val();
	  if ("0" == state) {
		  $("#msg_time").val("0");
	  } else if ("1" == state) {
		  $("#msg_time").val("4294967295");
	  }
	  e.preventDefault();
	  $forbidMsgForm.validate(function () {
		  $forbidMsgForm.setForm(function (req) {
			  if (req) {
					  S.alert('用户禁言操作成功!');
		              $("#J_showforbidMsg").trigger('Storm.modal.hide');
		  	          //查询
		  	          $formBtn.click();
				  } else {
					  S.alert('用户禁言操作失败,请重试!');
				  } 
		  });
    });
    return false;
  }
  function showTime() {
	  var t = $(this).val();
	  if ("0" == t) {
		  $("#showTime").hide();
		  $("#msg_time").val("0");
	  } else if ("1" == t) {
		  $("#showTime").hide();
		  $("#msg_time").val("4294967295");
	  } else if ("2" == t) {
		  $("#showTime").show();
		  $("#msg_time").val("");
	  }
  }
  
  	function isGag() {//是否禁言
  		var $this = $(this);
  		var uid = $this.attr('data-user-id');
  		var nickName = $this.attr('data-nick-name');
  		var isGag = $this.attr('data-is-gag');
  		if (isGag == 1) {
  			var txt = "是否禁言【" + nickName + "】(" + uid + ")？";
  		} else if (isGag == 2) {
  			var txt = "取消禁言【" + nickName + "】(" + uid + ")？";
  		}
  		S.confirm({
  			width:500,
		    hasHeader: true,// 是否需要头部
	        headerTxt: "用户禁言-配置",
			text: txt,
		    confirm: function(){
		  		console.log($this.data());
				$this.postBtn(CONFIG.addr.modifyUserIsGag, $this.data(), function (req) {
					console.log(req);
					if(isGag == 1){
						S.alert('已禁言用户');
					} else if(isGag == 2) {
						S.alert('已取消用户禁言');
					}
					$formBtn.click();
				});
		    },
		    cancel: function(){
		    	
		    }
	   });
  }
  	function isBan() {//是否封禁
  		var $this = $(this);
  		var uid = $this.attr('data-user-id');
  		var nickName = $this.attr('data-nick-name');
  		var isClosure = $this.attr('data-is-closure');
  		if (isClosure == 1) {
  			var txt = "是否封禁【" + nickName + "】(" + uid + ")？";
  		} else if (isClosure == 2) {
  			var txt = "取消封禁【" + nickName + "】(" + uid + ")？";
  		}
//  		var = 
  		S.confirm({
  			width:500,
		    hasHeader: true,// 是否需要头部
	        headerTxt: "用户封禁-配置",
			text: txt,
		    confirm: function(){
		  		console.log($this.data());
				$this.postBtn(CONFIG.addr.modifyUserIsClosure, $this.data(), function (req) {
					console.log(req);
					if(isClosure == 1){
						S.alert('已封禁用户');
					} else if(isClosure == 2) {
						S.alert('已取消用户封禁');
					}
					$formBtn.click();
				});
		    },
		    cancel: function(){
//		    	alert(1)
		    }
	   });
  }
  	
  	function isSuper() {//是否设为超管
  		var $this = $(this);
  		var uid = $this.attr('data-user-id');
  		var nickName = $this.attr('data-nick-name');
  		var isDelete = $this.attr('data-is-delete');
  		if (isDelete == 1) {
  			var txt = "设置用户【" + nickName + "】(" + uid + ")为App超管？";
  		} else if (isDelete == -1) {
  			var txt = "取消设置用户【" + nickName + "】(" + uid + ")App超管？";
  		}
  		S.confirm({
  			width:500,
		    hasHeader: true,// 是否需要头部
	        headerTxt: "用户封禁-配置",
			text: txt,
		    confirm: function(){
		  		console.log($this.data());
				$this.postBtn(CONFIG.addr.modifySuperAdministrator, $this.data(), function (req) {
					console.log(req);
					if(isDelete == 1){
						S.alert('已设为App超管');
					} else if(isDelete == -1) {
						S.alert('已取消App超管');
					}
					$formBtn.click();
				});
		    },
		    cancel: function(){
		    	
		    }
	   });
  }
  
  function showForbid() {//用户禁言和
	  var $this = $(this);
	  $("#msg_userId").val($this.attr("data-id"));
	  $("#msg_anchorType").val($this.attr("data-type"));
	  $("input[name='state'][value='1']").prop("checked", "checked");
	  $("#msg_time").val("");
	  $("#showAging").show();
	  $("#hideAging").hide();
	  $("#showTime").hide();
	  $("#J_showforbidMsg").modal('show');
  }
  
  
  
  function showCancel() {
	  var $this = $(this);
	  $("#msg_userId").val($this.attr("data-id"));
	  $("#msg_anchorType").val($this.attr("data-type"));
	  $("input[name='state'][value='0']").prop("checked", "checked");
	  $("#msg_time").val("0");
	  $("#hideAging").show();
	  $("#showAging").hide();
	  $("#showTime").hide();
	  $("#J_showforbidMsg").modal('show');
  }
  /**
   * 用户封禁
   */
  function seal() {
	  var $this = $(this);
	  var uId = $this.attr("data-id");
	  var type = $this.attr("data-type");
	  var state = 1;
	  if (1 < (type * 1)) {
		  state = 3;
	  }
	  S.confirm({
		    width:300,
		    hasHeader: true,// 是否需要头部
	        headerTxt: "用户状态-配置",
			text: "设置【" + uId + "】用户状态:封禁？",
		    confirm: function(){
		    	$this.postBtn(CONFIG.addr.userBanned, {"userId":uId, "state" : state}, function (req) {
		    	  	if (req) {
		    	  		S.alert('用户封禁成功!', function () {
			    	    	$formBtn.click();
			    	  	});
		    	  	} else {
		    	  		S.alert('用户封禁失败!');
		    	  	}
		    	});
		    }
	  });
  }
  /**
   * 用户解禁
   */
  function unbind() {
	  var $this = $(this);
	  var uId = $this.attr("data-id");
	  var type = $this.attr("data-type");
	  var state = 0;
	  if (1 < (type * 1)) {
		  state = 2;
	  }
	  S.confirm({
		    width:300,
		    hasHeader: true,// 是否需要头部
	        headerTxt: "用户状态-配置",
			text: "设置【" + uId + "】用户状态:解禁？",
		    confirm: function(){
		    	$this.postBtn(CONFIG.addr.userBanned, {"userId":uId, "state" : state}, function (req) {
		    	  	if (req) {
		    	  		S.alert('用户解禁成功!', function () {
			    	    	$formBtn.click();
			    	  	});
		    	  	} else {
		    	  		S.alert('用户解禁失败!');
		    	  	}
		    	});
		    }
	  });
  }

  function getUserInfo() {// 通过id查找
      var $this = $(this);
      console.log($this.data());
      $this.postBtn(CONFIG.addr.getUserAudit, $this.data(), function (req) {
    	  
    	  $('.logo_img').attr('src',req.phoneId==''?'http://opq77mx3q.bkt.clouddn.com/20170830-logo_totoro.png':req.phoneId);
    	  $('.logo_img').next().val(req.phoneId==''?'http://opq77mx3q.bkt.clouddn.com/20170830-logo_totoro.png':req.phoneId);
    	  
    	  $('.code_just').attr('src',req.frontIdentityUrl==''?'http://opq77mx3q.bkt.clouddn.com/20171025-positive.png':req.frontIdentityUrl);
    	  $('.big_just').attr('src',req.frontIdentityUrl==''?'http://opq77mx3q.bkt.clouddn.com/20171025-positive.png':req.frontIdentityUrl);
    	  $('.code_back').attr('src',req.oppositeIdentityUrl==''?'http://opq77mx3q.bkt.clouddn.com/20171025-opposite.png':req.oppositeIdentityUrl);
    	  $('.big_back').attr('src',req.oppositeIdentityUrl==''?'http://opq77mx3q.bkt.clouddn.com/20171025-opposite.png':req.oppositeIdentityUrl);
    	  $('#add_userId').val(req.userId);
    	  
		  $J_detail.find('[data-insert]').val("");
    	  $J_detail.insert(req);
    	  
    	  if (req.userType == '1') {
    		  $('.users_type').html(req.userTypeStr);
    	  } else {
    		  $('.users_type').html('<select name="userType" class="inputsearch user_types" title="用户类型"><option value="0">普通用户</option><option value="3">托号用户</option></select>');
    		  $('.user_types').val(req.userType);
    	  }
    	  
    	  $J_detail.modal('show');
      });
  }
  
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
  
  function editAnchor(e) {//修改用户信息
	  e.preventDefault();
	  $editAnchorForm.validate(function () {
    	  $editAnchorForm.setForm(function () {
              S.alert('修改用户信息成功!');
              $J_detail.trigger('Storm.modal.hide');
  	          //查询
  	          $formBtn.click();
      });
    });
    return false;
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
  
  function showEdit() {//显示确定开通主播
	  var $this = $(this);
//	  $("#edit_userId").val($this.attr("data-id"));
	  
	  var uid = $this.attr('data-user-id');
	  var nickName = $this.attr('data-nick-name');
	  var txt = "是否开通【" + nickName + "】(" + uid + ")主播权限？";
	  S.confirm({
		    width:500,
		    hasHeader: true,// 是否需要头部
	        headerTxt: "开通主播-配置",
			text: txt,
		    confirm: function(){
//		    	alert('开通主播');
		  		console.log($this.data());
		  		var data = {};
	  	        data.anchor = JSON.stringify($this.data());
				$this.postBtn(CONFIG.addr.editAnchor, data, function (req) {
					console.log(req);
					S.alert('开通主播成功');
					$formBtn.click();
				});
		    },
		    cancel: function(){
		    	
		    }
	   });
	  
//	  $J_showEdit.modal('show');
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
  /**
   * 取消主播
   */
  function cancel() {
    var $this = $(this);
    var uId = $this.attr("data-id");
    S.confirm({
	    width:300,
	    hasHeader: true,// 是否需要头部
        headerTxt: "主播身份-配置",
		text: "设置【" + uId + "】主播为普通用户？",
	    confirm: function(){
	    	$this.postBtn(CONFIG.addr.cancelAnchor, {"userId":uId}, function () {
	        	S.alert('取消主播身份成功!', function () {
	        		$J_detail.trigger('Storm.modal.hide');
	        		$formBtn.click();
	           });
	        });
	    },
	    cancel: function(){
	    	$J_detail.trigger('Storm.modal.hide');
	    }
   });
  }
  // 页面JS初始化
  init();

});