<%@include file="/html/init.jsp"%>

<portlet:renderURL windowState="normal" var="backURL">
  <portlet:param name="jspPage" value="/html/view.jsp" />
</portlet:renderURL>

<liferay-ui:header backURL="<%= backURL %>" title="Slogan Contest Entry" />

<%
Slogan slogan = (Slogan) request.getAttribute(WebKeys.SLOGAN_ENTRY);
%>

<h1><%= slogan.getSloganText() %></h1>

<liferay-ui:ratings className="<%= Slogan.class.getName() %>"
    classPK="<%= slogan.getSloganId() %>" type="stars" />

<liferay-ui:panel-container extended="<%= false %>"
    id="sloganCommentsPanelContainer" persistState="<%= true %>">

  <liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>"
      id="sloganCommentsPanel" persistState="<%= true %>"
      title='<%= LanguageUtil.get(pageContext, "comments") %>'>

    <portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />

    <liferay-ui:discussion className="<%= Slogan.class.getName() %>"
        classPK="<%= slogan.getSloganId() %>"
        formAction="<%= discussionURL %>" formName="fm2"
        ratingsEnabled="<%= true %>" redirect="<%= currentURL %>"
        subject="<%= slogan.getSloganText() %>"
        userId="<%= slogan.getUserId() %>" />

  </liferay-ui:panel>

</liferay-ui:panel-container>