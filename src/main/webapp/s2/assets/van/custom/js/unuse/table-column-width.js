(function($) {  
    $.fn.dragTable = function(options){  
        var $this = $(this);  
        $this.parent().css('width',null);  
        $this.css({'width':null,'table-layout':'fixed'});  
        var currentTd=null;      
        var cookieKeyName = getFileName()+'-'+$this.attr('id');  
        var limitWidth = options ? options.limitWidth : 1;  
        var tdWidth = getCookie(cookieKeyName);  
        var tdWidthArray = null;  
        if(null!=tdWidth && undefined!=tdWidth){tdWidthArray = tdWidth.split(',');}  
        $this.find('tr:first td').each(function(i){  
            $(this).css({'cursor':'col-resize'}).attr('nowrap', true);  
            $(this).css('width',null!=tdWidthArray?tdWidthArray[i]:$(this)[0].offsetWidth);  
        });  
        $this.find('tr:first td').bind('mousedown',function(event, obj){  
            obj=obj||this;  
            event=event||window.event;  
            currentTd=obj;  
            obj.mouseDownX=event.clientX;  
            obj.tdW=obj.offsetWidth;  
            if(obj.setCapture){   
                obj.setCapture();  
            }else if(window.captureEvents){    
                window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);      
            }  
        });  
          
        $this.find('tr:first td').bind('mousemove',function(event){  
            if(!currentTd) return ;  
            var obj=currentTd;  
            event=event||window.event;  
            if(!obj.mouseDownX) return false;  
            if(obj.parentNode.rowIndex==0) {  
                var newWidth=obj.tdW*1+event.clientX*1-obj.mouseDownX;  
                if(newWidth>limitWidth) obj.style.width = newWidth;  
                else obj.style.width =limitWidth;  
            }  
        });  
          
        $this.find('tr:first td').bind('mouseup',function(){  
            var widthTdTemp = [];  
            $this.find('tr:first td').each(function(){  
                    widthTdTemp.push($(this).css('width'));  
            });  
            addCookie(cookieKeyName, widthTdTemp, 360);  
            if(!currentTd) return;  
            if(currentTd.releaseCapture){     
                currentTd.releaseCapture();     
            }else if(window.captureEvents){  
                window.releaseEvents(Event.MOUSEMOVE|Event.MOUSEUP);      
            }  
            currentTd=null;  
        });       
        // 获取cookie  
        function getCookie(objName){  
            var arrStr = document.cookie.split("; ");  
            for(var i = 0;i < arrStr.length;i ++){  
                var temp = arrStr[i].split("=");  
                if(temp[0] == objName) return unescape(temp[1]);  
            }  
        }  
        // 添加cookie  
        function addCookie(name,value,expires,path,domain){  
            var str=name+"="+escape(value);  
            if(expires&&expires!=""){  
                var date=new Date();  
                date.setTime(date.getTime()+expires*24*3600*1000);//expires单位为天  
                str+=";expires="+date.toGMTString();  
            }  
            if(path&&path!=""){  
                str+=";path="+path;//指定可访问cookie的目录  
            }  
            if(domain&&domain!=""){  
                str+=";domain="+domain;//指定可访问cookie的域  
            }  
            document.cookie=str;  
        }  
        // 获取引用该js文件的文件名  
        function getFileName(){  
            var arrUrl=window.location.href.split("/");  
            return escape(arrUrl[arrUrl.length-1]);  
        }  
    }          
})(jQuery);
