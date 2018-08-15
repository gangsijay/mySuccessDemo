<script type="text/javascript">
    try{ace.settings.loadState('main-container')}catch(e){}
</script>

<div id="sidebar" class="sidebar                  responsive                    ace-save-state">
    <script type="text/javascript">
        try{ace.settings.loadState('sidebar')}catch(e){}
    </script>
    <ul class="nav nav-list">
        <li class="<#if req_uri=="/">active</#if>">
            <a href="/">
                <i class="menu-icon fa fa-tachometer"></i>
                <span class="menu-text"> <@spring.message "tr.index"/> </span>
            </a>

            <b class="arrow"></b>
        </li>
        <li class="<#if req_uri=="/admin/apps/list">active</#if>">
            <a href="/admin/apps/list">
                <i class="menu-icon fa fa-cloud"></i>
                <span class="menu-text"> <@spring.message "tr.myApps"/> </span>
            </a>

            <b class="arrow"></b>
        </li>
    <#if currUser.email=='286600136@qq.com'>
        <li class="<#if req_uri=="/admin/apps/audit/list">active</#if>">
            <a href="/admin/apps/audit/list">
                <i class="menu-icon fa fa-exclamation-circle"></i>
                <span class="menu-text"> <@spring.message "tr.auditApp"/> </span>
            </a>

            <b class="arrow"></b>
        </li>
    </#if>

    </ul><!-- /.nav-list -->

    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
        <i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>
</div>