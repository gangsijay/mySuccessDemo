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
<#include "../../../layout/menu.ftl" />
    <div class="main-content">
        <div class="main-content-inner">
            <div class="page-content">
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
    jQuery(function($) {
        var grid_selector = "#grid-table";
        var pager_selector = "#grid-pager";


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
        });
        function operatBtn(cellvar,options,rowObject){
            //自定义按钮
            var results = "";
            if(rowObject['status']==0){
                results+="<input type=\"button\" class=\"btn btn-sm btn-primary\" value=\"<@spring.message "tr.auditApp" />\" onclick=\"auditApp('"+rowObject['client_id']+"')\"/>&nbsp;";
            }else{
                results+="<input type=\"button\" class=\"btn btn-sm btn-danger\" value=\"<@spring.message "tr.forbiddenApp" />\" onclick=\"forbiddenApp('"+rowObject['client_id']+"')\"/>&nbsp;";
            }
            return results;
        }

        //if your grid is inside another element, for example a tab pane, you should use its parent's width:
        /**
         $(window).on('resize.jqGrid', function () {
					var parent_width = $(grid_selector).closest('.tab-pane').width();
					$(grid_selector).jqGrid( 'setGridWidth', parent_width );
				})
         //and also set width when tab pane becomes visible
         $('#myTab a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
				  if($(e.target).attr('href') == '#mygrid') {
					var parent_width = $(grid_selector).closest('.tab-pane').width();
					$(grid_selector).jqGrid( 'setGridWidth', parent_width );
				  }
				})
         */

        jQuery(grid_selector).jqGrid({
            //direction: "rtl",
            url: "/admin/apps/audit/list/json",
            datatype: "json",
            height: 450,
            colNames:['Client Id','Client Name','Client Secret','Call Back Url','Status','Operation'],
            colModel:[
                {name:'client_id',index:'client_id',key:true,width:150, editable:false,editrules:{required:true}},
                {name:'client_name',index:'client_name',width:150, editable:false,editrules:{required:true}},
                {name:'client_secret',index:'client_secret',width:150, editable:false,editrules:{required:true}},
                {name:'web_server_redirect_uri',index:'web_server_redirect_uri',width:200, editable:true,editrules:{required:true}},
                {name:'status',index:'status',width:150, editable:false,edittype:'select',search:true,stype:'select',editoptions:{
            sopt:['eq','ne'],
                    value:{0:'No',1:'Yes'}
        },searchoptions:{
            sopt:['eq','ne'],
                    value:{0:'No',1:'Yes'}
        },formatter:'select'},
                {name:'opt',index:'opt', width:180, editable: false,search:false,sortable:false,formatter :operatBtn }
            ],
            jsonReader:{
                repeatitems: false,
                id: "client_id"
            },
            //默认排序的字段
            sortname: 'entry_date',
            sortorder:'asc',
            editurl: "/admin/apps/editData",
            ondblClickRow: function(rowid) {
                //双击显示数据
//                jQuery(this).jqGrid('viewGridRow', rowid,viewOptions);
            }
//            caption: "jqGrid with inline editing"

            //,autowidth: true,


            /**
             ,
             grouping:true,
             groupingView : {
						 groupField : ['name'],
						 groupDataSorted : true,
						 plusicon : 'fa fa-chevron-down bigger-110',
						 minusicon : 'fa fa-chevron-up bigger-110'
					},
             caption: "Grouping"
             */

        });
        $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size



        //enable search/filter toolbar
        //jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
        //jQuery(grid_selector).filterToolbar({});




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
                    //new record form
                    //width: 700,
                    closeAfterAdd: true,
                    recreateForm: true,
                    viewPagerButtons: false,
                    beforeShowForm : function(e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
                                .wrapInner('<div class="widget-header" />')
                        style_edit_form(form);
                    },
                    serializeEditData: function(data){
                        return $.param($.extend({},data,{id:0}));
                    }
                },
                {
                    //delete record form
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
        //var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

//        $(document).one('ajaxloadstart.page', function(e) {
//            $.jgrid.gridDestroy(grid_selector);
//            $('.ui-jqdialog').remove();
//        });
    });
    function auditApp(client_id) {
        bootbox.confirm('确认要将该应用审核通过吗？',function(result){
            if(result){
                $.ajax({
                    type:'POST',
                    url:'/admin/apps/audit',
                    data:{'client_id':client_id},
                    error:function () {

                    },success:function (msg) {
                        bootbox.alert('审核成功！');
                        $("#grid-table").trigger("reloadGrid");
                    }
                });
            }
        })

    }
    function forbiddenApp(client_id){
        bootbox.confirm('确认要禁用该应用吗？',function(result) {
            if (result) {
                $.ajax({
                    type: 'POST',
                    url: '/admin/apps/forbidden',
                    data: {'client_id': client_id},
                    error: function () {

                    }, success: function (msg) {
                        bootbox.alert('禁用成功！');
                        $("#grid-table").trigger("reloadGrid");
                    }
                });
            }
        });
    }
</script>
</body>
</html>