<%@include file = "/html/init.jsp" %>

<div class="add-slogan">
	<portlet:renderURL var="addSloganURL">
	  <portlet:param name="jspPage" value="/html/edit_slogan.jsp" />
	</portlet:renderURL>
	<input type="button" value="<liferay-ui:message key="add-slogan" />" onClick="location.href = '<%= addSloganURL.toString() %>';" />
</div>

<%-- Affichage des slogans. --%>
<liferay-ui:search-container emptyResultsMessage="there-are-no-slogans" delta="20">

	<liferay-ui:search-container-results>
	<%
	results = ActionUtil.getSlogans(renderRequest, -1, -1);
	total = ActionUtil.getSlogansCount(renderRequest);
	pageContext.setAttribute("results", results);
	pageContext.setAttribute("total", total);
	%>
	</liferay-ui:search-container-results>
	
	<liferay-ui:search-container-row
		className="com.inkwell.internet.slogan.model.Slogan"
		keyProperty="sloganId"
		modelVar="slogan">
	
		<portlet:renderURL windowState="maximized" var="rowURL">
			<portlet:param name="jspPage" value="/html/view_slogan.jsp" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(slogan.getSloganId()) %>" />
		</portlet:renderURL>
		
		<liferay-ui:search-container-column-text name="rating">
		<liferay-ui:ratings-score
		    score="<%= slogan.getAverageScore() %>" />
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text
		    href="<%= rowURL %>"
		  	name="slogan-text"
		  	property="sloganText"
		  	orderable="<%= true %>"
		/>
	
	</liferay-ui:search-container-row>
	
	<liferay-ui:search-iterator />
	
	
</liferay-ui:search-container>
