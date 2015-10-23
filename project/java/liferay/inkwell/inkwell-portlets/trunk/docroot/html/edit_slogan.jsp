<%@include file="/html/init.jsp" %>

<portlet:renderURL windowState="normal" var="backURL">
  <portlet:param name="jspPage" value="/html/view.jsp"></portlet:param>
</portlet:renderURL>

<liferay-ui:header
    backURL="<%= backURL %>"
    title="Slogan Contest Entry"
/>

<%
Slogan slogan = (Slogan)request.getAttribute(WebKeys.SLOGAN_ENTRY);
String redirect = ParamUtil.getString(request, "redirect");

long resourcePrimKey = BeanParamUtil.getLong(slogan, request, "sloganId");
int status = BeanParamUtil.getInteger(slogan, request, "status", WorkflowConstants.STATUS_DRAFT);
%>

<portlet:actionURL name="updateSlogan" var="updateSloganURL">
  <portlet:param name="redirect" value="<%= redirect %>" />
</portlet:actionURL>

<portlet:renderURL var="cancelURL">
	<portlet:param name="jspPage" value="/html/view.jsp" />
</portlet:renderURL>

<aui:form name="fm" action="<%= updateSloganURL.toString() %>" method="post">

  <aui:fieldset>

    <aui:model-context bean="<%= slogan %>" model="<%= Slogan.class %>" />

    <c:if test="<%= slogan != null %>">
      <aui:workflow-status id="<%= String.valueOf(resourcePrimKey) %>" status="<%= status %>" />
    </c:if>

    <aui:input name="sloganId" type="hidden" />

    <h1>Slogan Contest Entry</h1>

    <liferay-ui:error
        key="slogan-required"
        message="slogan-required" />

    <aui:input name="sloganText" first="true" autoFocus="true" size="45" />

    <aui:input name="categories" type="assetCategories" />

    <aui:input name="tags" type="assetTags" />

    <aui:button-row>

      <aui:button type="submit" />

      <aui:button
          type="cancel"
          value="Cancel"
          onClick="<%=cancelURL %>"
      />

    </aui:button-row>

  </aui:fieldset>

</aui:form>