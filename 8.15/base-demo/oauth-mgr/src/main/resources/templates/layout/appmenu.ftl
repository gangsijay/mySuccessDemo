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
        <#if currUser.email=='haiming.zhuang@trinasolar.com'>
            <li class="<#if req_uri=="/admin/apps/audit/list">active</#if>">
                <a href="/admin/apps/audit/list">
                    <i class="menu-icon fa fa-exclamation-circle"></i>
                    <span class="menu-text"> <@spring.message "tr.auditApp"/> </span>
                </a>

                <b class="arrow"></b>
            </li>
        </#if>
        <li class="<#if req_uri?index_of('admin') != -1>open</#if>">
            <a href="#" class="dropdown-toggle">
                <i class="menu-icon fa  fa-cog"></i>
							<span class="menu-text">
                            ${cid}-<@spring.message "tr.sysSet" />
							</span>

                <b class="arrow fa fa-angle-down"></b>
            </a>
            <b class="arrow"></b>
            <ul class="submenu">
                <li class="<#if req_uri?index_of('/admin/urls') != -1>active</#if>">
                    <a href="/admin/urls/list/${cid}">
                        <i class="menu-icon fa fa-caret-right"></i>
                    <@spring.message "tr.urlMgr" />
                    </a>

                    <b class="arrow"></b>
                </li>
                <li class="<#if req_uri?index_of('/admin/user') != -1>open</#if>">
                    <a href="#" class="dropdown-toggle">
                        <i class="menu-icon fa fa-caret-right"></i>
                    <@spring.message "tr.userMgr" />
                        <b class="arrow fa fa-angle-down"></b>
                    </a>
                    <b class="arrow"></b>
                    <ul class="submenu">
                        <li class="<#if req_uri?index_of('/admin/user/user') != -1>active</#if>">
                            <a href="/admin/user/user/list/${cid}">
                                <i class="menu-icon fa fa-caret-right"></i>
                            <@spring.message "tr.userMgr" />
                            </a>

                            <b class="arrow"></b>
                        </li>
                        <li class="<#if req_uri?index_of('/admin/user/role') != -1>active</#if>">
                            <a href="/admin/user/role/lstUserRole/${cid}">
                                <i class="menu-icon fa fa-caret-right"></i>
                            <@spring.message "tr.userRoleMgr" />
                            </a>

                            <b class="arrow"></b>
                        </li>
                        <li class="<#if req_uri?index_of('/admin/user/url') != -1>active</#if>">
                            <a href="/admin/user/url/lstUserUrls/${cid}">
                                <i class="menu-icon fa fa-caret-right"></i>
                            <@spring.message "tr.userUrlsMgr" />
                            </a>

                            <b class="arrow"></b>
                        </li>
                    </ul>
                </li>
                <li class="<#if req_uri?index_of('/admin/role') != -1>open</#if>">
                    <a href="#" class="dropdown-toggle">
                        <i class="menu-icon fa fa-caret-right"></i>
                    <@spring.message "tr.roleMgr" />
                        <b class="arrow fa fa-angle-down"></b>
                    </a>

                    <b class="arrow"></b>
                    <ul class="submenu">
                        <li class="<#if req_uri?index_of('/admin/role/role') != -1>active</#if>">
                            <a href="/admin/role/role/list/${cid}">
                                <i class="menu-icon fa fa-caret-right"></i>
                            <@spring.message "tr.roleMgr" />
                            </a>

                            <b class="arrow"></b>
                        </li>
                        <li class="<#if req_uri?index_of('/admin/role/url') != -1>active</#if>">
                            <a href="/admin/role/url/lstRoleUrls/${cid}">
                                <i class="menu-icon fa fa-caret-right"></i>
                            <@spring.message "tr.roleUrlsMgr" />
                            </a>

                            <b class="arrow"></b>
                        </li>
                    </ul>
                </li>

            </ul>
        </li>

    </ul><!-- /.nav-list -->

    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
        <i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>
</div>