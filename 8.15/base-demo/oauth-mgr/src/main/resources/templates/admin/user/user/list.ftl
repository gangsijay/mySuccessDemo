<#include "../../../layout/common.ftl" />
<!DOCTYPE html>
<html lang="en">
<head>
    <title><@spring.message "tr.title"/></title>
<#include "../../../layout/head.ftl" />
</head>
<body class="no-skin">
<#include "../../../layout/navbar.ftl" />
<div class="main-container ace-save-state" id="main-container">
<#include "../../../layout/appmenu.ftl" />
    <div class="main-content">
        <div class="main-content-inner">
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <button class="btn btn-primary btn-sm" onclick="addUser()"><@spring.message "tr.userAdd" /></button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <table id="grid-table"></table>

                        <div id="grid-pager"></div>

                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div>
        </div>
    </div>
</div>
<#include "../../../layout/js.ftl" />
<!-- inline scripts related to this page -->
<style>
    #jqgh_grid-table_cb{text-align: center}
</style>
<script type="text/javascript">
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";
    jQuery(function($) {
        var parent_column = $(grid_selector).closest('[class*="col-"]');
        //resize to fit page size
        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
        })

        //resize on sidebar collapse/expand
        $(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
            if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
                //setTimeout is for webkit only to give time for DOM changes and then redraw!!!
                setTimeout(function() {
                    $(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
                }, 20);
            }
        })

        jQuery(grid_selector).jqGrid({
            //direction: "rtl",
            url: "/admin/user/user/list/json?cid=${cid}",
            editurl: "/admin/user/user/editData?cid=${cid}",
            datatype: "json",
            height: 450,
            sortname: 'entry_date',
            sortorder:'asc',
            jsonReader:{
                root: "rows",
                id: "userid"
            },
            colNames:['Userid','Username','Is Admin','Email','Create Date','Operation'],
            colModel:[
                {name:'userid',index:'userid',hidden:true},
                {name:'username',index:'username',width:100, editable:true,editoptions:{readonly:true}},
                {name:'isadmin',index:'isadmin',width:80,editable:true,edittype:'select',search:true,stype:'select',editoptions:{
                    sopt:['eq','ne'],
                    value:{0:'No',1:'Yes'}
                },searchoptions:{
                    sopt:['eq','ne'],
                    value:{0:'No',1:'Yes'}
                },formatter:'select'},
                {name:'email',index:'email', width:90,editable: true,editoptions:{readonly:true}},
                {name:'entry_date',index:'entry_date', width:90,editable: false,formatter :function(cellval,opts,rowObject){
                    if(!cellval||cellval==""){
                        return "";
                    }
                    return moment(cellval).format("YYYY-MM-DD");
                }},
                {name:'opt',index:'opt', width:180, editable: false,search:false,sortable:false,formatter :operatBtn }
            ],
            ondblClickRow: function(rowid) {
                //双击显示数据
//                jQuery(this).jqGrid('viewGridRow', rowid,viewOptions);
            }
        });
        $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size

        //navButtons
        jQuery(grid_selector).jqGrid('navGrid',pager_selector,
                { 	//navbar options
                    edit: false,
                    editicon : 'ace-icon fa fa-pencil blue',
                    add: false,
                    addicon : 'ace-icon fa fa-plus-circle purple',
                    del: false,
                    delicon : 'ace-icon fa fa-trash-o red',
                    search: true,
                    searchicon : 'ace-icon fa fa-search orange',
                    refresh: true,
                    refreshicon : 'ace-icon fa fa-refresh green',
                    view: false,
                    viewicon : 'ace-icon fa fa-search-plus grey',
                },
                {
                    editOptions
                },
                {
                    closeAfterAdd: true,
                    recreateForm: true,
                    viewPagerButtons: false,
                    beforeShowForm : function(e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
                                .wrapInner('<div class="widget-header" />')
                        style_edit_form(form);
                    }
                },
                {
                    delOptions
                },
                {
                    //search form
                    recreateForm: true,
                    afterShowSearch: function(e){
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                        style_search_form(form);
                    },
                    afterRedraw: function(){
                        style_search_filters($(this));
                    }
                    ,
                    multipleSearch: true,
                    /**
                     multipleGroup:true,
                     showQuery: true
                     */
                },
                viewOptions
        )
        jQuery(grid_selector).jqGrid('bindKeys');

    });
    function operatBtn(cellvar,options,rowObject){
        //自定义按钮
        var results = "";
        results+="<input type=\"button\" class=\"btn btn-sm btn-danger\" value=\"<@spring.message "tr.modify" />\" onclick=\"modUser('"+rowObject['userid']+"')\"/>&nbsp;";
        results+="<input type=\"button\" class=\"btn btn-sm btn-danger\" value=\"<@spring.message "tr.delete" />\" onclick=\"delUser('"+rowObject['userid']+"')\"/>&nbsp;";
        return results;
    }

    function addUser(){
        art.dialog.open("/admin/user/appUser/ulist?cid=${cid}",{
            title:'<@spring.message "tr.addAppUser" />',
            width:$(window).width()-150,
            height:$(window).height()-100,
            lock:true,
            async : false,
            okVal : '<@spring.message "tr.submit" />',
            ok : function(frm) {
                var selectedIds = frm.$("#grid-table").jqGrid("getGridParam", "selarrrow");
                var uids = selectedIds.join();
                $.ajax({
                    type:'POST',
                    url:'/admin/user/appUser/addUser',
                    data:{cid:'${cid}',uids:uids},
                    error:function(){},
                    success:function(){
                        bootbox.alert("添加成功！");
                        jQuery(grid_selector).trigger("reloadGrid");
                    }
                })
            }
        });
    }
    function modUser(userid){
        jQuery(grid_selector).jqGrid('editGridRow', userid, editOptions);
    }
    function delUser(userid){
        jQuery(grid_selector).jqGrid('delGridRow', userid, editOptions);
    }
</script>
</body>
</html>