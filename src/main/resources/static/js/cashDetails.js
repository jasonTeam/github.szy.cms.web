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
      $formReset = $form.find(':reset'),  //重置用户按钮
      
      $addCashForm = $("#wp_cashdetails"),//提交
      $addCashBtn = $addCashForm.find(':submit');//开通主播按钮
      
  /**
   * 页面初始化
   */
  function init () {
	  $('#extract_id').val(S.getQueryString('extractId'));
	  getCashDetail();//获取详情内容
	  
//	  $addCashBtn.click(cashModifyTrue);
	  $('.wp_cash_submit').click(cashModifyTrue);
	  
	  $(document).on('click', '.wp_cash_modify', cashModify);//修改
	  $(document).on('click', '.wp_cash_cancle', cashCancle);//取消修改
	  $(document).on('click', '.wp_cash_save', cashSave);//保存修改 
	  
	  $('.wp_cash_export').click(exprot);//导出
	  
	//重置刷新页面
    $formReset.click(function(){
		setTimeout(function(){
			$formBtn.click();
		},500);
	});
  }
  
  function exprot() {//导出
	    S.confirm('导出会消耗服务器性能,你确定要导出吗?', function () {
		  location.href = CONFIG.addr.extractMessageExport + '?extractId=' + S.getQueryString('extractId');
	    });
  }
  
  $(".opal_price").on("input propertychange",function(){
       if($(this).val() < 0){  
    	   $(this).val('');
    	   $('.all_num').html($('.calorie_price').val());
       } else {
    	   if (parseInt($('.realOpalNum').text()) < parseInt($(this).val())) {
	  			S.alert('输入金额不能比实际金额大');
	  			$(this).val('');
	  		} else {
	  			 $('.all_num').html(S.numAdd(Number($(this).val()), Number($('.calorie_price').val())));
	  		}
       }
  });
  
  $(".calorie_price").on("input propertychange",function(){
	  	if($(this).val() < 0){  
   	   		$(this).val('');
   	   		$('.all_num').html($('.opal_price').val());
	  	} else {
	  		if (parseInt($('.realCalorieNum').text()) < parseInt($(this).val())) {
	  			S.alert('输入金额不能比实际金额大');
	  			$(this).val('');
	  		} else {
	  			$('.all_num').html(S.numAdd(Number($(this).val()), Number($('.opal_price').val())));
	  		}
	  	}
  });
  
  function getCashDetail() {//通过订单号id查找详情内容
	 
	  $formBtn.click();//查询
	  
	  var $this = $(this);
      var data = {};
      data.extractId = S.getQueryString('extractId');
      console.log(data);
      $this.postBtn(CONFIG.addr.getExtractMessage, data, function(data) {
    	  console.log(data);
    	  $('#wp_cashdetails').find('[data-insert]').html('');
    	  $('#wp_cashdetails').insert(data);
    	  if (data.extractStatus == '3') {
    		  $('.extract_div').html('<p>' + data.extractStatusStr + '</p>');
    		  $('.remark_div').html('<p>' + data.remark + '</p>');
    		  $('.cash_readonly').attr({'readonly' : 'readonly'});
    	  } else if (data.extractStatus == '2') {
    		  $('.extract_div').html('<p>' + data.extractStatusStr + '</p>');
    		  $('.remark_div').html('<p>' + data.remark + '</p>');
    		  $('.cash_readonly').attr({'readonly' : 'readonly'});
    	  } else {
    		  $('.extract_div').html('<select name="extractStatus" class="inputsearch extract_status" title="审核状态"><option value="2">审核通过</option><option value="3">审核不通过</option></select>');
    		  $('select[name=extractStatus]').val('2');
    		  $('.opal_price').val('');
    		  $('.calorie_price').val('');
    		  $('.remark_div').html('<textarea name="remark" class="remark_text" data-insert="remark" placeholder="填写备注">' + data.remark + '</textarea>');
    		  $(".extract_status").change(function () {
    	          var statusExtract = $(this).children('option:selected').val();  
    	          if (statusExtract == "3") {
    	        	  $('.unusual').show();
    	        	  $('.cash_table').css({
    	        		  'width' : '800px'
    	        	  });
    	          } else {  
    	        	  $('.unusual').hide();
    	        	  $('.cash_table').css({
    	        		  'width' : '640px'
    	        	  });
    	          }
    	      });
    	  }
    	  $('#extractid_submit').val(data.extractId);
    	  $('.all_num').html(S.numAdd(Number(data.opalPrice), Number(data.caloriePrice)));
    	  if (data.confType == 1) {//支付宝
    		  $('.acc_name').text('支付宝姓名');
    		  $('.acc_num').text('支付宝账号');
    	  } else {//2,3银行
    		  $('.acc_name').text('银行名称');
    		  $('.acc_num').text('银行账号');
    	  }
    	  if (data.extractStatus == "3") {//异常（审核不通过） 
    		  setTimeout(function(){
    			  $('.unusual').show();
            	  $('.cash_table').css({'width' : '800px'});
            	  $('.handle_but').hide();
    		  },500);
    		  $('.wp_cash_submit').addClass('hide');
        	  $('.wp_cash_export').removeClass('hide');
    	  } else if (data.extractStatus == "2") {//审核通过
    		  $('.unusual').hide();
        	  $('.cash_table').css({'width' : '640px'});
        	  $('.wp_cash_submit').addClass('hide');
        	  $('.wp_cash_export').removeClass('hide');
    	  } else if (data.extractStatus == "1") {//待审核
        	  $('.unusual').hide();
        	  $('.cash_table').css({'width' : '640px'});
          }
    	  
		  $(document).on('click', '.pagination li', function(){
			  setTimeout(function(){
				  if (data.extractStatus == "3") {//异常（审核不通过） 
		        	  $('.unusual').show();
		        	  $('.cash_table').css({'width' : '800px'});
		        	  $('.handle_but').hide();
				  } else if (data.extractStatus == "1") {//待审核
					  if ($('.extract_status').val() == 3) {
			        	  $('.unusual').show();
			        	  $('.cash_table').css({'width' : '800px'});
			          } else {  
			        	  $('.unusual').hide();
			        	  $('.cash_table').css({
			        		  'width' : '640px'
			        	  });
			          }
				  }
			  }, 500);
		  });
	});
  }
  
  function cashModifyTrue(e) {
	  if ($('.extract_status').val() == 3) {
		  if ($('.remark_text').val() == '') {
			  S.alert('请输入备注！');
			  return false;
		  } else {
			  e.stopPropagation();
			  $('#wp_cashdetails').validate(function() {
				  $('#wp_cashdetails').setForm(function() {
					  S.alert('提交成功!');
					  getCashDetail();
				  });
			  });
			  return false;
		  }
	  } else if ($('.extract_status').val() == 2) {
		  if ($('.opal_price').val() == '') {
			  S.alert('请输入贡献值金额');
			  return false;
		  } else if ($('.calorie_price').val() == '') {
			  S.alert('请输入卡路里金额');
			  return false;
		  } else {
			  e.stopPropagation();
			  $('#wp_cashdetails').validate(function() {
				  $('#wp_cashdetails').setForm(function() {
					  S.alert('提交成功!');
					  getCashDetail();
				  });
			  });
			  return false;
		  }
	  }
  }
  
  function cashModify() {
	  var $this = $(this);
	  $this.addClass('hide');
	  $this.next().removeClass('hide');
	  $this.next().next().removeClass('hide');
	  $this.parent().prev().find('span').addClass('hide');
	  $this.parent().prev().find('input').removeClass('hide');
	  $this.parent().prev().prev().find('span').addClass('hide');
	  $this.parent().prev().prev().find('input').removeClass('hide');
  }
  function cashCancle() {
	  var $this = $(this);
	  $this.addClass('hide');
	  $this.prev().removeClass('hide');
	  $this.next().addClass('hide');
	  $this.parent().prev().find('span').removeClass('hide');
	  $this.parent().prev().find('input').addClass('hide');
	  $this.parent().prev().prev().find('span').removeClass('hide');
	  $this.parent().prev().prev().find('input').addClass('hide');
  }
  function cashSave() {
	  var $this = $(this);
	  var freeOpal = $this.parent().prev().prev().find('input').val();
	  var freeCalorie = $this.parent().prev().find('input').val();
	  
	  var totalOpal = $this.attr('data-totalOpal');
	  var totalCalorie = $this.attr('data-totalCalorie');
	  
	  if (parseInt(freeOpal) > parseInt(totalOpal)) {
		  S.alert('异常贡献值不能大于贡献值！')
		  return;
	  } else if (parseInt(freeCalorie) > parseInt(totalCalorie)) {
		  S.alert('异常卡路里不能大于卡路里！')
		  return;
	  } else {
		  var data = {};
		  data.detailId = $this.attr('data-detail-id');
		  data.freezeOpal = freeOpal;
		  data.freezeCalorie = freeCalorie;
		  $this.postBtn(CONFIG.addr.updateExtractDetail, data, function(data) {
	    	  console.log(data);
	    	  S.alert('保存成功');
	    	  $this.addClass('hide');
	    	  $this.prev().addClass('hide');
	    	  $this.prev().prev().removeClass('hide');
	    	  $this.parent().prev().find('span').removeClass('hide');
	    	  $this.parent().prev().find('input').addClass('hide');
	    	  $this.parent().prev().prev().find('span').removeClass('hide');
	    	  $this.parent().prev().prev().find('input').addClass('hide');
	    	  modifyCash();
	    	  $formBtn.click();//查询
	    	  setTimeout(function(){
	    		  $('.unusual').show();
	    	  },500);
		  });
	  }
  }
  
  function modifyCash() {//通过订单号id查找详情内容
	  var $this = $(this);
      var data = {};
      data.extractId = S.getQueryString('extractId');
      console.log(data);
      $this.postBtn(CONFIG.addr.getExtractMessage, data, function(data) {
    	  console.log(data);
    	  if (data.extractStatus != '2') {
    		  $('.totalOpal').text(data.totalOpal);
    		  $('.freezeOpal').text(data.freezeOpal);
    		  $('.realOpalNum').text(data.realOpalNum);
    		  $('.opal_price').val(data.opalPrice);
    		  $('.totalCalorie').text(data.totalCalorie);
    		  $('.freezeCalorie').text(data.freezeCalorie);
    		  $('.realCalorieNum').text(data.realCalorieNum);
    		  $('.calorie_price').val(data.caloriePrice);
          }
	});
  }
  
  // 页面JS初始化
  init();

});