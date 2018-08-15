package com.zhm.controller;

import com.zhm.db.OauthClientDetails;
import com.zhm.service.OauthClientDetailsService;
import com.zhm.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.Approval.ApprovalStatus;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Controller for retrieving the model for and displaying the confirmation page for access to a protected resource.
 * 
 * @author Ryan Heaton
 */
@Controller
@SessionAttributes("authorizationRequest")
public class AccessConfirmationController {
	@Autowired
	private ClientDetailsService clientDetailsService;
	@Autowired
	private ApprovalStore approvalStore;
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private OauthClientDetailsService oauthClientDetailsService;

	@RequestMapping("/oauth/confirm_access")
	public String getAccessConfirmation(Map<String, Object> model, Principal principal) throws AccessDeniedException {
		AuthorizationRequest clientAuth = (AuthorizationRequest) model.remove("authorizationRequest");
		if(appUserService.checkUserAllowVisitApp(clientAuth.getClientId(),principal.getName())==0){
			throw new AccessDeniedException("无权登录该系统！");
		}
		OauthClientDetails dbclient = oauthClientDetailsService.findByClientid(clientAuth.getClientId());
		if(dbclient.getStatus()==0){
			throw new AccessDeniedException(dbclient.getClient_name()+"应用还未通过审核，无法进行授权！");
		}
		ClientDetails client = clientDetailsService.loadClientByClientId(clientAuth.getClientId());
		model.put("auth_request", clientAuth);
		model.put("client", client);
		Map<String, String> scopes = new LinkedHashMap<String, String>();
		for (String scope : clientAuth.getScope()) {
			scopes.put(OAuth2Utils.SCOPE_PREFIX + scope, "false");
		}
		for (Approval approval : approvalStore.getApprovals(principal.getName(), client.getClientId())) {
			if (clientAuth.getScope().contains(approval.getScope())) {
				scopes.put(OAuth2Utils.SCOPE_PREFIX + approval.getScope(),
						approval.getStatus() == ApprovalStatus.APPROVED ? "true" : "false");
			}
		}
		model.put("scopes", scopes);
		return "/user_grant_scope";
	}

	@RequestMapping("/oauth/error")
	public String handleError(Map<String, Object> model) throws Exception {
		// We can add more stuff to the model here for JSP rendering. If the client was a machine then
		// the JSON will already have been rendered.
		model.put("message", "There was a problem with the OAuth2 protocol");
		return "oauth_error";
	}
}
