<%@page language="java" pageEncoding="UTF-8" %>
    <!-- BEGIN FOOTER -->
    <div class="footer">
	    <div class="footer-inner">
	    	2015 &copy; Iaas. Copyright Reserved.
	    </div>
	    <div class="footer-tools">
		    <span class="go-top">
		    <i class="icon-angle-up"></i>
		    </span>
	    </div>
    </div>
    <!-- END FOOTER -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
    <!-- BEGIN CORE PLUGINS -->
    <!--[if lt IE 9]>
    <script src="${ctx}/s2/assets/plugins/respond.min.js"></script>
    <script src="${ctx}/s2/assets/plugins/excanvas.min.js"></script>
    <![endif]-->
    <script src="${ctx}/s2/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
    <!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
    <script src="${ctx}/s2/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript" ></script>
    <script src="${ctx}/s2/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/jquery.cokie.min.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript" ></script>
    <!-- END CORE PLUGINS -->
    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <script src="${ctx}/s2/assets/plugins/jqvmap/jqvmap/jquery.vmap.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/jqvmap/jqvmap/maps/jquery.vmap.russia.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/jqvmap/jqvmap/maps/jquery.vmap.world.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/jqvmap/jqvmap/maps/jquery.vmap.europe.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/jqvmap/jqvmap/maps/jquery.vmap.germany.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/jqvmap/jqvmap/maps/jquery.vmap.usa.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/jqvmap/jqvmap/data/jquery.vmap.sampledata.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/flot/jquery.flot.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/flot/jquery.flot.resize.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/jquery.pulsate.min.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/bootstrap-daterangepicker/moment.min.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/bootstrap-daterangepicker/daterangepicker.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/gritter/js/jquery.gritter.js" type="text/javascript"></script>
    <!-- IMPORTANT! fullcalendar depends on jquery-ui-1.10.3.custom.min.js for drag & drop support -->
    <script src="${ctx}/s2/assets/plugins/fullcalendar/fullcalendar/fullcalendar.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/jquery-easy-pie-chart/jquery.easy-pie-chart.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/jquery-nestable/jquery.nestable.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/ckeditor/ckeditor.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
    
    <!-- message -->
    <script src="${ctx}/s2/assets/plugins/jquery-sliding-message/jquery.slidingmessage.min.js" type="text/javascript" ></script>
    <script src="${ctx}/s2/assets/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/socketjs/sockjs-1.0.3.min.js" type="text/javascript"></script>
    
    <!-- time picker -->
    <script src="${ctx}/s2/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript" ></script>
    <script src="${ctx}/s2/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" type="text/javascript" ></script>
    
    <!-- END PAGE LEVEL PLUGINS -->
    <!-- BEGIN PAGE LEVEL SCRIPTS -->
    <script src="${ctx}/s2/assets/scripts/core/app.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/scripts/custom/index.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/scripts/custom/tasks.js" type="text/javascript"></script>
    
    <script src="${ctx}/s2/assets/scripts/custom/login-fluid.js" type="text/javascript"></script>
    <!--  
    <script src="${ctx}/s2/assets/scripts/custom/form-samples.js" type="text/javascript"></script>
    -->
    <!-- END PAGE LEVEL SCRIPTS -->
    
    <!-- 数据校验 
    <script src="${ctx}/s2/assets/plugins/jquery-validation/additional-methods.min.js" type="text/javascript"></script>
    -->
    <script src="${ctx}/s2/assets/plugins/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/plugins/jquery-validation/localization/messages_zh_CN.js" type="text/javascript" ></script>
    
    <!-- bootbox -->
    <script src="${ctx}/s2/assets/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
    <!-- Ztree -->
    <script src="${ctx}/s2/assets/plugins/ztree/jquery.ztree.all-3.5.min.js" type="text/javascript" ></script>
    <script src="${ctx}/s2/assets/plugins/ztree/jquery.ztree.core-3.5.min.js" type="text/javascript" ></script>
    <!--  
    <script src="${ctx}/s2/assets/plugins/ztree/jquery.ztree.excheck-3.5.js" type="text/javascript" ></script>
    -->
    <script src="${ctx}/s2/assets/plugins/ztree/jquery.ztree.exedit-3.5.js" type="text/javascript" ></script>
    
    <!-- SELECT2 -->
    <script src="${ctx}/s2/assets/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript" ></script>
    <script src="${ctx}/s2/assets/plugins/select2/select2.min.js" type="text/javascript" ></script>
    <script src="${ctx}/s2/assets/plugins/jquery-multi-select/js/jquery.multi-select.js" type="text/javascript" ></script>
    
    <!-- async file upload -->
    <script src="${ctx}/s2/assets/plugins/dropzone/dropzone.js" type="text/javascript" ></script>
    
    <!-- 表格分页,排序等数据 -->
    <!--  
    <script src="${ctx}/s2/assets/van/table/js/selectedItems.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/van/table/js/messages_zh_CN.js" type="text/javascript" ></script>
    -->
    <script src="${ctx}/s2/assets/van/table/js/pagination.js" type="text/javascript"></script>
    <script src="${ctx}/s2/assets/van/table/js/table.js" type="text/javascript"></script>
    
    <!-- 流程跟踪, 流程节点信息显示使用 -->
    <script src="${ctx}/s2/assets/van/custom/js/bpmgraph.js" type="text/javascript" ></script>
    <script src="${ctx}/s2/assets/plugins/qtip/jquery.qtip.pack.js" type="text/javascript"></script>
    
    <!-- pickers -->
    <script src="${ctx}/s2/assets/van/custom/js/picker-list.js" type="text/javascript" ></script>
    <script src="${ctx}/s2/assets/van/custom/js/picker-user.js" type="text/javascript" ></script>
    <script src="${ctx}/s2/assets/van/custom/js/picker-role.js" type="text/javascript" ></script>
    
     <!-- 异步操作提示信息 -->
    <script src="${ctx}/s2/assets/van/custom/js/showAsyncMessage.js" type="text/javascript" ></script>
    
    <!-- 表格列拖动改变大小 
    <script src="${ctx}/s2/assets/van/custom/js/table-column-width.js" type="text/javascript" ></script>
    -->
    <!-- 电子词典 -->
    <script src="${ctx}/s2/assets/van/custom/js/dictionary-td.js" type="text/javascript" ></script>
    
    <!--  拖动列改变大小
    <script src="${ctx}/s2/assets/van/custom/js/jquery.resizableColumns.js" type="text/javascript" ></script>
    <script src="${ctx}/s2/assets/van/custom/js/store.min.js" type="text/javascript" ></script>
    -->
   <!-- -->
    <!-- END JAVASCRIPTS -->
    
    <script type="text/javascript">
    	$(function() {
    	    $.showMessage($('#m-success-message').html(), {
    	        position: 'top',
    	        size: '130',
    	        fontSize: '20px',
    	        delay: 1500
    	        //,backgroundColor:'rgb(0, 92, 87)'
    	    });
    	    
    	    //$('#dynamicGridForm').resizableColumns({store: window.store});
    	    $('#searchAdvancedAcrticle').hide();
    	  	//判断查询条件是否显示
        	if($('#searchAcrticle').length > 0){
        		var flag = true;
        		$('#searchAcrticle input').each(function(i, item){
        			var val = $(item).val();
        			if(val != ''){
        				flag = false;
        			}
        		});
        		
    			$('#searchAcrticle select').each(function(i, item){
    				var val = $(item).val();
        			if(val != ''){
        				flag = false;
        			}
        		});
    			
    			if(flag == true){
    				//$('#searchAcrticle').hide();//暂时取消隐藏搜索选项
    			}
        	}
    	  
        	$("input:file").not('[data-no-uniform="true"],#uniform-is-ajax').uniform({
                fileDefaultHtml: '还未选择文件',
                fileButtonHtml: '选择文件'
            });
        	
        	//时间选择插件初始化
        	initDatePicker();
        	
            //异步超时处理,配合超时sessionExpiredFileter,完成异步超时的跳转处理
            $.ajaxSetup({
            	global:false,
            	type:'POST',
            	complete:function(XMLHttpRequest, textStatus){
            		var data = XMLHttpRequest.responseText;
            		console.log(textStatus);
            		if(data == 'timeout'){
            			var href = window.location.href;
            			window.location.href = href;
            		}
            	}
            });
            
            <c:if test="${isLogin == null}">
            	//pollAttention();//先执行一次，然后再循环
            	//var id = window.setInterval(pollAttention,60000);//暂时取消轮询 每20秒轮询提醒信息
            </c:if>
            /* document.onclick=function(){     
            	window.clearTimeout(id);
            } */
            
    	});
    	
    	function initDatePicker(){
			$('.datepicker').datepicker({
        		format:'yyyy-mm-dd',
        		autoclose: true,
                todayBtn: true,
                language: 'zh'
        	});
        	
            $('.datetimepicker').datetimepicker({
                format: "yyyy-mm-dd hh:ii:ss",
                autoclose: true,
                todayBtn: true,
                language: 'zh'
            });
		}
    	
    	//动态表格点击tr选择
    	$(document).delegate('#dynamicGrid tr td:not(:has(input))', 'click', function(){
    		$(this).parent().parent().find('tr td input').each(function(i, item){
    			$(item).removeAttr('checked');
    		});
    		$(this).parent().find(':first input').attr('checked', 'checked');
    		//添加背景标示
    		$(this).parent().parent().find('tr').each(function(i, item){
    			if($(item).find('td input').attr('checked') == 'checked'){
    				$(item).addClass('selected-tr');
    			}else{
    				$(item).removeClass('selected-tr');
    			}
    		});
    	});
    	//动态表格点击input选择
    	$(document).delegate('#dynamicGrid tr td input', 'click', function(){
    		$(this).parent().parent().parent().find('tr').each(function(i, item){
    			if($(item).find('td input').attr('checked') == 'checked'){
    				$(item).addClass('selected-tr');
    			}else{
    				$(item).removeClass('selected-tr');
    			}
    		});
    	});
    	
    	//同一个table中的tbody中的元素，使用checkbox选择
    	$(document).delegate('.selectedItemIdAll', 'click', function(){//扩展为通用
    		if($(this).attr('checked') == 'checked'){
    			var table = $(this).parent().parent().parent().parent().find('tbody');
    			$.each(table.find('tr'), function(i, item){
    				$.each($(item).find('td'), function(n, ele){
    					$(ele).children('.selectedItemId').attr('checked', 'checked');
    				});
    			});
    		}else{
    			var table = $(this).parent().parent().parent().parent().find('tbody');
    			$.each(table.find('tr'), function(i, item){
    				$.each($(item).find('td'), function(n, ele){
    					$(ele).children('.selectedItemId').removeAttr('checked');
    				});
    			});
    		}
    	});
    	
    	//推送信息
    	/* function pollAttention(){
    		$.post('../base/poll-attention.do', function(data){
    			var unreadMails = data.unreadMails;
    			var unreadMsgs = data.unreadMsgs;
    			var handleTasks = data.handleTasks;
    			var claimTasks = data.claimTasks;
    			if(unreadMails != null && unreadMails.results.length > 0){
    				$('#unreadMailsSizeSpan').text(unreadMails.totalCount);
    				$('#unreadMailsSizeP').text('你有' + unreadMails.totalCount + '封未读邮件');
    				var html = '';
    				$.each(unreadMails.results, function(i, item){
    					html += '<li>';
    					html += '<a href="../out/mail-receive-view.do?id='+ item.id +'">';
    					html += '<span class="photo"><img src="${ctx}/s2/assets/img/avatar1.jpg" alt="" /></span> ';
    					html += '<span class="subject"> ';
    					html += '<span class="from">'+ item.addressFrom +'</span> ';
    					html += '<span class="time">'+ item.status +'</span>';
    					html += '</span> ';
    					html += '<span class="message">'+ item.subject +'</span>';
    					html += '</a>';
    					html += '</li>';
    				});
    				$('#unreadMailsUl').html(html);
    			}else{
    				$('#unreadMailsSizeSpan').text(0);
    			}
    			
    			if(unreadMsgs != null && unreadMsgs.results.length > 0){
    				$('#unreadMsgsSizeSpan').text(unreadMsgs.totalCount);
    				$('#unreadMsgsSizeP').text('你有' + unreadMsgs.totalCount + '封未读消息');
    				var html = '';
    				$.each(unreadMsgs.results, function(i, item){
    					html += '<li>';
    					html += '<a href="../out/msg-info-reply.do?id='+ item.id +'">';
    					html += '<span class="photo"><img src="${ctx}/s2/assets/img/avatar1.jpg" alt="" /></span> ';
    					html += '<span class="subject"> ';
    					html += '<span class="from">'+ item.sender.displayName +'</span> ';
    					html += '<span class="time">'+ item.msgType +'</span>';
    					html += '</span> ';
    					html += '<span class="message">'+ item.content +'</span>';
    					html += '</a>';
    					html += '</li>';
    				});
    				$('#unreadMsgsUl').html(html);
    			}else{
    				$('#unreadMsgsSizeSpan').text(0);
    			}
    			
    			if(handleTasks != null && handleTasks.results.length > 0){
    				$('#handleTasksSizeSpan').text(handleTasks.totalCount);
    				$('#handleTasksSizeP').text('你有' + handleTasks.totalCount + '个待处理任务');
    				var html = '';
    				$.each(handleTasks.results, function(i, item){
    					
    					html += '<li><a href="../bpm/workspace-task-handle-input.do?taskId='+ item.id +'" target="_blank"">';
    					html += '<span class="task">';
    					html += '<span class="desc" style="width: 50px;">'+ item.processName +'</span> ';
    					html += '<span class="percent">'+ item.name +'</span>';
    					html += '</span> ';
    					html += '</a>';
    					html += '</li>';
    				});
    				$('#handleTasksUl').html(html);
    			}else{
    				$('#handleTasksSizeSpan').text(0);
    			}
    			
    			if(claimTasks != null && claimTasks.results.length > 0){
    				$('#claimTasksSizeSpan').text(claimTasks.totalCount);
    				$('#claimTasksSizeP').text('你有' + claimTasks.totalCount + '个待领取任务');
    				var html = '';
    				$.each(claimTasks.results, function(i, item){
    					html += '<li><a href="../bpm/bpm-view-business.do?processInstanceId='+ item.processInstanceId +'" target="_blank"">';
    					html += '<span class="task">';
    					html += '<span class="desc" style="width: 50px;">'+ item.processName +'</span> ';
    					html += '<span class="percent">'+ item.name +'</span>';
    					html += '</span> ';
    					html += '</a>';
    					html += '</li>';
    				});
    				$('#claimTasksUl').html(html);
    			}else{
    				$('#claimTasksSizeSpan').text(0);
    			}
    		})
    	}
    	//消息推送 
    	var toastCount = 0;
    	function toastrMessgae(messageType, messageTitle,  messageText){
            var msg = messageText;
            var title = messageType;//messageType success info warning error
            var toastIndex = toastCount++;
            toastr.options = {
                closeButton: true,//是否显示关闭按钮
                debug: false,//取消debug
                positionClass: 'toast-bottom-right',//位置
                onclick: function(){
                }
            };
            
            toastr.options.showDuration = 1000;//显示动画时间
            toastr.options.hideDuration = 1000;//隐藏动画时间
            toastr.options.timeOut = 8000;//停留时间
            toastr.options.extendedTimeOut = 1000;//多个提示额外停留时间
            toastr.options.showEasing = 'swing';
            toastr.options.hideEasing = 'linear';
            toastr.options.showMethod = 'fadeIn';
            toastr.options.hideMethod = 'fadeOut';
            
            var $toast = toastr[messageType](messageText, messageTitle);
    	}
    	
    	//Websocket消息推送
    	$(function(){
			if(window.location.href.indexOf('login.do') < 0
					&& window.location.href.indexOf('loginCheck.do') < 0){
				websocketConnect();
			}
    	});
    	var websocket = null;
    	function websocketConnect(){
    		if('WebSocket' in window){
                websocket = new WebSocket('ws://' + window.location.host + '${ctx}' + '/websocket.do');
            }else if('MozWebSocket' in window) {
                websocket = new MozWebSocket('wss://' + window.location.host + '${ctx}' + '/websocket.do');
            }else{
            	websocket = new SockJS('/sockjs/websocket.do');
            	//websocket = new SockJS('ws://' + window.location.host + '${ctx}' + '/sockjs/websocket.do');
            }
    		//获取连接
    		websocket.onopen = function(evnt){
    			console.log('connection build');
            };
            //接收消息
            websocket.onmessage = function(evnt){
				console.log(event.data);
				toastrMessgae('info', '消息推送', event.data);
            };
            //连接错误
            websocket.onerror = function(evnt){
            	console.log('connection error');
            	//console.log(event);
            	if (websocket != null){
					websocket.close();
					websocket = null;
	            }
            };
            //连接关闭
            websocket.onclose = function(evnt){
            	console.log('connection close');
            }
            //窗口关闭
            window.onbeforeunload = function() {
				console.log('connection close of window close');
				if (websocket != null){
					websocket.close();
					websocket = null;
	            }
			}
    	}
		//发送消息
		function websocketSend(messageText){
			websocket.send(messageText);
		}
		 */
    	
		//窗口最大化
    	/* function winLargest(){
    		var windowWidth = window.screen.availWidth;
    		var windowHeight = window.screen.availHeight;
    		window.moveTo(0,0);
    		window.resizeTo(windowWidth,windowHeight);
    	} */
</script>