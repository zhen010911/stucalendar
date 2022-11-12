/**
 *
 *  created by lero
 *  滑动模态框插件
 *  2018.08.03
 *  version:1.0.3
 *
 * */

;(function ($) {

    'use strict';

    var default_opt = {
        // jquery 对象
        "element": null,
        // 可填的参数是 top,bottom,left,right,alert,tips
        "type": "bottom",
        // 模态框中的内容
        "content": "",
        // 类型为tips时，自动关闭时间 传 0 为一直显示不会自动关闭，需要手动调用 closeTips() 关闭
        "time":'1500',
        // 全部销毁
        "destroy": function () {
            modal.closeAll();
        },
        // 关闭modal
        "close": function () {
            modal.close();
        },
        "closeTips": function () {
            modal.closeTips();
        },
        // 打开alert 前调用
        "beforeOpen": function () {},
        // 打开alert 后调用
        "afterOpen": function () {},
        // 关闭前调用
        "beforeClose": function () {},
        // 关闭 alert 之后调用
        "afterClose": function () {},
        // 是否点击遮罩关闭
        "shadeClose": true
    };
    var opt = {};

    var $modal = null,
        $shadow = null,
        $content = null,
        $tips = null,
        $body = $('body'),
        docScrollTop = 0;

    var html =
        "<div id='J-jq-sd-alert' class='sd-alert'>" +
        "   <div id='J-sd-alert-shadow' class='sd-shadow'></div>" +
        "   <div class='sd-content'>" +
        "       <div id='J-sd-content'></div>" +
        "       <div id='J-sd-tips'></div>" +
        "   </div>"+
        "</div>";

    var tips_flag = false,
        t = null;

    var modal = {
        showModal:function(type){
            if(tips_flag) {
                this.closeTips();
            }
            if(type === 'tips'){
                tips_flag = true;
                this.tips(type);
                if(~~opt.time !== 0){
                    t = setTimeout(function () {
                        this.closeTips();
                    }.bind(this), opt.time);
                }
                return true;
            }
            opt.beforeOpen();
            $modal.data('data-type', type);
            $content.html(opt.content).parent().removeClass('sd-'+type+'-hide').addClass('sd-'+type+'-show');
            opt.afterOpen();


        },
        tips: function(type){
            $tips.data('data-type', type).html(opt.content).removeClass('sd-'+type+'-hide').addClass('sd-'+type+'-show');
        },
        close: function(){
            opt.beforeClose();
            this.closeTips();
            var type = $modal.data('data-type');
            !!type ? this.closeModal(type): this.closeAll();
            setTimeout(function () {
                $content
                    .unbind()
                    .html('')
                    .parent()
                    .removeClass('sd-bottom-hide sd-top-hide sd-left-hide sd-right-hide sd-alert-hide sd-tips-hide');

                $modal.hide();
                $shadow.hide();
                $body.removeClass('overflowHide').css('top', 0);
                $('html').scrollTop(docScrollTop);
                document.body.scrollTop = docScrollTop;
                opt.afterClose();
            }, 300);
        },
        closeTips:function () {
            tips_flag = false;
            clearTimeout(t);
            $tips.html('').removeClass('sd-tips-hide sd-tips-show');
        },
        closeAll: function() {
            this.closeTips();
            $modal.prop('outerHTML', '');
        },
        closeModal: function(type){
            $content.parent().removeClass('sd-'+type+'-show').addClass('sd-'+type+'-hide');
        },
        valid: function (element, option) {
            var canPass = true;
            element[0].nodeType !== 1 && (canPass = false, console.error("请传入合适的jquery选择器"));
            if(!option.type || "top,bottom,left,right,alert,tips".indexOf(option.type.trim()) < 0){
                canPass = false;
                console.error("invalid option.type");
            }
            return canPass;
        }
    };

    var init= function (option) {
        option.type = option.type.trim();
        opt = $.extend({}, default_opt, option);
        docScrollTop = $('html').scrollTop();
        if(docScrollTop <= document.body.scrollTop){
            docScrollTop = document.body.scrollTop;
        }
        if(opt.type !== "alert" && opt.type !== "tips" ){
            $body.addClass('overflowHide').css("top", "-" + docScrollTop + "px");
        }
        // 添加dom和遮罩层
        if(!$modal){
            $body.append($('<div></div>')).children().last().prop('outerHTML', html);
            $modal = $('#J-jq-sd-alert');
            $shadow = $('#J-sd-alert-shadow');
            $content = $('#J-sd-content');
            $tips = $('#J-sd-tips');
        } else {
            $modal.show();
        }
        opt.type !== 'tips' && $shadow.show();
    };
    var addEvent= function () {
        $shadow
            .off('click.sd')
            .on('click.sd', function (event) {
                opt.shadeClose && modal.close();
            })
            .off('touchmove')
            .on('touchmove', function (event) {
                event.preventDefault();
            });
    };
    $.fn.slideAlert = function (option) {
        if(!modal.valid(this, option)){
            return false;
        }
        option.element = this;
        init(option);
        addEvent();
        modal.showModal(opt.type);
        return opt;
    }
}($));
