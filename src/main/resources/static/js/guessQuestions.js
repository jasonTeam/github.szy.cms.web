/**
 * Created by Storm on 2016/4/12.
 */
require.config({
  paths: {
    config: 'base/config',
    jquery: ['base/jquery.min', 'jquery.min'],
    storm: 'base/storm-1.5',
    common: 'base/common',
    ddsort : 'base/ddsort'
  },
  shim:{
	    'ddsort':{
	        deps: ['jquery'],
	        exports: 'ddsort'
	    }
	}
});
require(['config', 'jquery', 'storm', 'common', 'ddsort'], function (CONFIG, $, S) {
  'use strict';

  var $form = $('[data-form="search"]'),// 查询表单
      $formBtn = $form.find(':submit'), //查询按钮
      $formReset = $form.find(':reset'), //重置按钮
      
  	  $exprotBtn = $('.wp_shelf'); // 导出按钮

  function init () {//页面初始化
    $(document).on('click', '.classify ul li', getGuessGame);//查询所有的游戏
    $('.classify ul li').eq(0).click();//默认显示人机游戏
    $(document).on('click', '.wp_shelf', shelfGuess);//下架竞猜话题
    $(document).on('click', '.wp_delete', deleteGuess);//删除竞猜话题
    $(document).on('click', '.add_new_game', addGuess);//添加新的竞猜话题
    $(document).on('click', '.wp_edit', editGuessModal);//编辑查询竞猜话题显示弹框回填数据
    
    
    $('.wp_addbutton').on('click',addGuessQues);//确定添加竞猜话题
    $('.wp_modifybutton').on('click',modifyGuessQues);//确定修改竞猜话题
    
  }
  
  function getGuessGame () {//查找题库游戏
      var $this = $(this);
      
      $('.classify ul li').removeClass('select_classify');
      $this.addClass('select_classify');
      
      if ($this.attr('data-is-man') == 1) {
    	  $('.add_new_game').show();
      } else {
    	  $('.add_new_game').hide();
      }
      
      var data = {};
      data.guessSubject = JSON.stringify($this.data());
	  data.pageNo = '1';
	  data.pageSize = '50';
	  
      console.log(data)
      $this.postBtn(CONFIG.addr.getAllGuessSubject, data, function(data) {
    	  console.log('data------------------');
    	  console.log(data);
    	  
    	  $('#shelves_yes').html(S.template(data.content, $('#shelves_html_yes').html()));
    	  if ($('#shelves_yes tr').length <= 0) {
    		  $('#shelves_yes').html('<tr><td colspan="6">没有数据...</td></tr>');
    	  }

    	  setTimeout(function(){
    			$('#shelves_yes').DDSort({
    				target: 'tr',// 示例而用,默认即 li,
    		        delay: 100,// 延时处理,默认为 50ms,防止手抖点击 A链接无效
    		        floatStyle: {
    		        	'border': '1px solid #ccc',
    		            'background-color': '#fff'
    		        },
    		        changeNum: function(thisId, changeSortNum, changeId, thisSortNum){
    		        	var data = {};
    					data.subId1 = thisId;
    					data.sorts1 = changeSortNum;
    					data.subId2 = changeId;
    					data.sorts2 = thisSortNum;
    					
    					console.log(data);
    					$.post(CONFIG.addr.exchangeGuessSubject,data,function(result){
    						console.log("data----------------------------");
    						console.log(result);
    					});
    		        }
    			},function(){
    				
    	        });
    		},1000);
    	  
    	  $('#shelves_not').html(S.template(data.content, $('#shelves_html_not').html()));
    	  if ($('#shelves_not tr').length <= 0) {
    		  $('#shelves_not').html('<tr><td colspan="6">没有数据...</td></tr>');
    	  }
      });
  }
  
  function shelfGuess(){//上下架竞猜话题
	  var $this = $(this);
	  console.log($this.data());
	  $this.postBtn(CONFIG.addr.upOrDownGuessSubject, $this.data(), function(data) {
    	  console.log(data);
    	  if (data.isEnable == 1) {//被上架
    		  S.alert('上架竞猜话题成功');
    	  } else if (data.isEnable == 2) {//被下架
    		  S.alert('下架竞猜话题成功');
    	  }
    	  
    	  if (data.isMan == 1) {//人控
    		  $('.classify ul li').eq(0).click();
    	  } else if (data.isMan == 2) {//机控
    		  $('.classify ul li').eq(1).click();
    	  }
    	  
      });
  }
  
  function deleteGuess(){//删除竞猜话题
	  var $this = $(this);
	  $this.postBtn(CONFIG.addr.deleteGuessSubject, $this.data(), function(data) {
    	  console.log(data);
    	  S.alert('删除竞猜话题成功');
    	  if (data.isMan == 1) {//人控
    		  $('.classify ul li').eq(0).click();
    	  } else if (data.isMan == 2) {//机控
    		  $('.classify ul li').eq(1).click();
    	  }
      });
  }
  
  function addGuess(){//添加新的竞猜话题（游戏）弹框
	  $('#wp_addquestions').modal('show');
  }
  
  function addGuessQues(e){//添加竞猜话题
	  e.stopPropagation();
		$('#wp_addquestionsform').validate(function() {
			$('#wp_addquestionsform').setForm(function() {
				S.alert('添加人控游戏成功!');
				$('#wp_addquestions').modal('hide');
				
				$('.empty_in').val('');
				$('.empty_select option').removeAttr('selected');
				$('.empty_select option').eq(0).attr('selected','selected');
				$('.classify ul li').eq(0).click();
			});
		});
		return false;
  }
  
  function editGuessModal(){//查询单条竞猜数据
	  var $this = $(this);
	  console.log($this.data());
	  $this.postBtn(CONFIG.addr.findOneGuessSubject, $this.data(), function(data) {
    	  console.log(data);
    	  
    	  $('#wp_modifyquestionsform').find('[data-insert]').html('');
    	  $('#wp_modifyquestionsform').insert(data);
    	  
    	  $('.dis_time option').each(function(){
				if($(this).val() == data.disTime){
					$(this).attr("selected",true);
				}
    	  });
    	  
    	  $('#wp_modifyquestions').modal('show');
    	  
      });
  }
  
  function modifyGuessQues(e){//修改竞猜话题
	  e.stopPropagation();
		$('#wp_modifyquestionsform').validate(function() {
			$('#wp_modifyquestionsform').setForm(function() {
				S.alert('修改人控游戏成功!');
				$('#wp_modifyquestions').modal('hide');
				
				$('.empty_in').val('');
				$('.empty_select option').removeAttr('selected');
				$('.empty_select option').eq(0).attr('selected','selected');
				$('.classify ul li').eq(0).click();
			});
		});
		return false;
  }
  
  init();

});