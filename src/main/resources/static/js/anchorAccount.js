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

  var $form = $('[data-form="search"]'),// 查询流水表单
      $formBtn = $form.find(':submit'),  //查询流水按钮
      $formReset = $form.find(':reset'),
      $J_LordBtn = $('#J_Lord'),//显示主账户信息
  	  $J_AnchorBtn = $('#J_Anchor'),//显示主播账户信息
      $J_showAccount = $('#J_showAccount'),//显示主播账户菜单
      $addSubAccountForm = $("#J_AddSubAccount"),//提交主播增减账户菜单
  	  $addSubAccountBtn = $addSubAccountForm.find(':submit'),//提交主播增减账户按钮
  	  $J_convertBtn = $(".J_convertBtn"),//主账户兑换龙猫豆按钮
  	  $J_rechargeBtn = $(".J_rechargeBtn"),//主账户充值龙猫币按钮
  	  $J_showBean = $("#J_showBean"),//显示兑换龙猫豆菜单
  	  $J_showCoin = $("#J_showCoin"),//显示充值龙猫币菜单
  	  
      $convertBeanForm = $("#J_ConvertBean"),//提交主账户兑换龙猫豆菜单
	  $convertBeanBtn = $convertBeanForm.find(':submit'),//提交主账户兑换龙猫豆按钮
	  $convertBeanButton = $convertBeanForm.find(':button'),
	  
	  $rechargeCoinForm = $("#J_RechargeCoin"),//提交主账户充值龙猫币菜单
	  $rechargeCoinBtn = $rechargeCoinForm.find(':submit'),//提交主账户充值龙猫币按钮
  	  $rechargeCoinButton = $rechargeCoinForm.find(':button');
  
  /**
   * 页面初始化
   */
  function init () {
	//创建主账户方法  
	$J_LordBtn.on('click', lordInfo);
	//查询主账户信息
	$J_LordBtn.click();
	//创建主播账户方法
	$J_AnchorBtn.on('click', anchorInfo);
	//创建主播增减账户方法
	$addSubAccountBtn.on('click', accountAddSub);
	//显示兑换龙猫豆菜单
	$J_convertBtn.on('click', showBean);
	//显示充值龙猫币菜单
	$J_rechargeBtn.on('click', showCoin);
	//创建主账户兑换龙猫豆方法
	$convertBeanBtn.on('click', convertBean);
	//创建主账户充值龙猫币方法
	$rechargeCoinBtn.on('click', rechargeCoin);
	
	//重置刷新页面
    $formReset.click(function(){
		setTimeout(function(){
			$formBtn.click();
		},500);
	});
  }
  
  function showBean() {
	  var $this = $(this);
	  var uId = $this.attr("data-id");
	  $this.postBtn(CONFIG.addr.findAccount, {"userId":uId}, function (req) {
    	  $("#convert_userId").val(req.userId);
    	  $("#convert_bean").val(req.lmBeanNum);
    	  $("#convert_coin").val(req.lmCoinNum);
    	  $("#convert_Num").val("");
    	  $J_showBean.modal('show');
      })
  }
  
  function convertBean(e) {
	  e.preventDefault();
	  $convertBeanForm.validate(function () {
		  var userId = $('#convert_userId').val();
		  var convertNum = $('#convert_Num').val();
		  var lordCoin = $('#convert_coin').val();

		  var reg = /^[1-9]\d*$/;
		  if (!"777777" == userId) {
			  S.alert('非主账户不可兑换,请重新操作!');
			  return false;
		  } else if ("" == $.trim(convertNum)) {
			  S.alert('兑换数量不能为空!');
			  $('#convert_Num').val("");
			  return false;
		  } else if (!reg.test($.trim(convertNum))) {
			  S.alert('兑换数量只能为正整数!');
			  $('#convert_Num').val("");
			  return false;
		  } else if ((lordCoin * 1) < (convertNum * 1)) {
			  S.alert('兑换数量超过主账户余额!');
			  $('#convert_Num').val("");
			  return false;
		  } else {
			  S.confirm({
				    width:300,
				    hasHeader: false,// 是否需要头部
			        headerTxt: "主账户龙猫豆-兑换",
					text: "【777777】主账户 " + convertNum + " 龙猫币兑换龙猫豆？",
				    confirm: function(){
				    	$convertBeanForm.setForm(function (req) {
							  if (req.put) {
								  S.alert('主账户兑换龙猫豆成功!');
								  $("#convert_userId").val(req.userId);
					        	  $("#convert_bean").val(req.lmBeanNum);
					        	  $("#convert_coin").val(req.lmCoinNum);
					        	  $("#lord_bean").html(req.lmBeanNum);
					        	  $("#lord_coin").html(req.lmCoinNum);
							  } else {
								  S.alert('主账户兑换龙猫豆失败!');
							  }
							  $("#convert_Num").val("");
					    	  $J_showBean.modal('hide');
					  	});
				    },
				    cancel: function(){
//				    	$J_showBean.trigger('Storm.modal.hide');
				    }
			   });
		  }
	  });
	  return false;
  }
  
  function showCoin() {
	  var $this = $(this);
	  var uId = $this.attr("data-id");
	  $this.postBtn(CONFIG.addr.findAccount, {"userId":uId}, function (req) {
    	  $("#recharge_userId").val(req.userId);
    	  $("#recharge_bean").val(req.lmBeanNum);
    	  $("#recharge_coin").val(req.lmCoinNum);
    	  $("#recharge_Num").val("");
    	  $J_showCoin.modal('show');
      })
  }
  
  function rechargeCoin(e) {
	  e.preventDefault();
	  $rechargeCoinForm.validate(function () {
		  var userId = $('#recharge_userId').val();
		  var rechargeNum = $('#recharge_Num').val();

		  var reg = /^[1-9]\d*$/;
		  if (!"777777" == userId) {
			  S.alert('非主账户不可充值,请重新操作!');
			  return false;
		  } else if ("" == $.trim(rechargeNum)) {
			  S.alert('充值数量不能为空!');
			  $('#recharge_Num').val("");
			  return false;
		  } else if (!reg.test($.trim(rechargeNum))) {
			  S.alert('充值数量只能为正整数!');
			  $('#recharge_Num').val("");
			  return false;
		  } else if (("10000000" * 1) < (rechargeNum * 1)) {
			  S.alert('充值数量不能超过1千万!');
			  $('#recharge_Num').val("");
			  return false;
		  } else {
			  S.confirm({
				    width:300,
				    hasHeader: false,// 是否需要头部
			        headerTxt: "主账户龙猫币-充值",
					text: "【777777】主账户充值龙猫币：" + rechargeNum + "？",
				    confirm: function(){
				    	$rechargeCoinForm.setForm(function (req) {
							  if (req.put) {
								  S.alert('主账户充值龙猫币成功!');
								  $("#recharge_userId").val(req.userId);
					        	  $("#recharge_bean").val(req.lmBeanNum);
					        	  $("#recharge_coin").val(req.lmCoinNum);
					        	  $("#lord_bean").html(req.lmBeanNum);
					        	  $("#lord_coin").html(req.lmCoinNum);
							  } else {
								  S.alert('主账户充值龙猫币失败!');
							  }
							  $("#recharge_Num").val("");
							  $J_showCoin.modal('hide');
					  	});
				    },
				    cancel: function(){
//				    	$J_showCoin.trigger('Storm.modal.hide');
				    }
			   });
		  }
	  });
	  return false;
  }
  
  function lordInfo() {
	  var $this = $(this);
      var uId = $this.attr("data-id");
      $this.postBtn(CONFIG.addr.findAccount, {"userId":uId}, function (req) {
    	  console.log('-------------------------------------------------------------')
    	  console.log(req)
    	  $("#lord_userId").html(req.userId);
    	  $("#lord_bean").html(req.lmBeanNum);
    	  $("#lord_coin").html(req.lmCoinNum);
      })
  }
  
  function anchorInfo() {
	  var $this = $(this);
      var uId = $("#J_AnchorId").val();
      var reg = /^[1-9][0-9]+/;
      if ("" == $.trim(uId)) {
    	  S.alert('UserId为空,请重新操作!');
		  return false; 
      } else if (!reg.test($.trim(uId))) {
    	  S.alert('UserId不合法,请重新输入!');
    	  return false;
      } else {
    	  $this.postBtn(CONFIG.addr.findAccount, {"userId":uId}, function (req) {
        	  $("#anchor_userId").val(req.userId);
        	  $("#anchor_bean").val(req.lmBeanNum);
        	  $("#anchor_coin").val(req.lmCoinNum);
        	  $("#changeNum").val("");
        	  $("input[name='changeType'][value='0']").prop("checked", "checked");
        	  $J_showAccount.modal('show');
          })
      }
  }
  
  function accountAddSub(e) {
	  e.preventDefault();
	  $addSubAccountForm.validate(function () {
		  
		  var numType = $('select[name=bizType]').find('option:checked').val();
		  
		  var userId = $('#anchor_userId').val();
		  var changeNum = parseInt($('#changeNum').val()); //修改龙猫豆或币的数量
		  var anchorBean = parseInt($('#anchor_bean').val()); //龙猫豆的余额
		  var anchorCoin = parseInt($('#anchor_coin').val()); //龙猫币的余额
		  var lordBean = parseInt($("#lord_bean").html()); //主账号龙猫豆
		  var lordCoin = parseInt($("#lord_coin").html()); //主账号龙猫币
		  
		  var changeType = $("input[name='changeType']:checked").val();
		  var txt = ("0" == changeType) ? "增加" : "减少";
		  var num = ("0" == changeType) ? (lordBean * 1) - (changeNum * 1) : (lordBean * 1) + (changeNum * 1);
		  
		  var reg = /^[1-9]\d*$/;
		  if("" == userId){
			  S.alert('UserId为空,请重新操作!');
			  return false;
		  } else if ("777777" == userId) {
			  S.alert('不能为主账户增减,请重新操作!');
			  return false;
		  } else if ("" == $.trim(changeNum)) {
			  S.alert('增减数量不能为空!');
			  $('#changeNum').val("");
			  return false;
		  } else if (!reg.test($.trim(changeNum))) {
			  S.alert('增减数量只能为正整数!');
			  $('#changeNum').val("");
			  return false;
		  } else {
			  if(numType == '1') {
				  var numName = '龙猫豆';
				  if ("0" == changeType && (lordBean * 1) < (changeNum * 1)) {
					  S.alert('增加龙猫豆数量超过主账户余额!');
					  $('#changeNum').val("");
					  return false;
				  } else if ("1" == changeType && (anchorBean * 1) < (changeNum * 1)) {
					  S.alert('减少龙猫豆数量超过主播账户余额!');
					  $('#changeNum').val("");
					  return false;
				  }
			  } else if(numType == '2') {
				  var numName = '龙猫币';
				  if ("0" == changeType && (lordCoin * 1) < (changeNum * 1)) {
					  S.alert('增加龙猫币数量超过主账户余额!');
					  $('#changeNum').val("");
					  return false;
				  } else if ("1" == changeType && (anchorCoin * 1) < (changeNum * 1)) {
					  S.alert('减少龙猫币数量超过主播账户余额!');
					  $('#changeNum').val("");
					  return false;
				  }
			  }
			  S.confirm({
				    width:310,
				    hasHeader: false,// 是否需要头部
			        headerTxt: "主播账户-增减",
					text: "【" + userId + "】主播" + txt + numName + changeNum + "？",
				    confirm: function(){
				    	$addSubAccountForm.setForm(function (req) {
							  if (req.put) {
								  S.alert('主播账户' + numName + txt + '成功!');
								  $("#anchor_userId").val(req.userId);
					        	  $("#anchor_bean").val(req.lmBeanNum);
					        	  $("#anchor_coin").val(req.lmCoinNum);
					        	  $("#lord_bean").html(num);
					        	  $("#changeNum").val("");
					        	  $("input[name='changeType'][value='0']").prop("checked", "checked");
					        	  $J_showAccount.modal('hide');
					        	  $J_LordBtn.click();
							  } else {
								  S.alert('主播账户' + numName + txt + '失败!');
							  }
							  $formBtn.click();
					  	});
				    },
				    cancel: function(){
//				    	$J_showAccount.trigger('Storm.modal.hide');
				    }
			   });
		  }
	  });
	  return false;
  }
  
  // 页面JS初始化
  init();

});