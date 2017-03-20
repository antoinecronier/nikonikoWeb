
<head>
    <#include "../includable/bootstrap.ftl">
</head>
<html>
<body>
    <h1>${page}</h1>
    <table class="table table-bordered table-hover">
        <#include "../includable/formShowContent.ftl">
    </table>
    <table class="table table-bordered table-hover">
        <tr>
            <#list items as item>
                <#list fields as field>
                    <#list item?keys as key>
                        <#if field == key && key != "id">
                            <th>${key}</th>
                        </#if>
                    </#list>
                </#list>
                <#break>
            </#list>
        </tr>

            <#list items as item>
                <tr>
                    <#list fields as field>
                        <#list item?keys as key>
                            <#if field == key>
                                <#if key != "id">
                                    <#if item[key]?is_boolean>
                                        <td>${item[key]?c}</td>
                                    <#elseif item[key]?is_date_like>
                                        <td>${item[key]?string("yyyy/MM/dd HH:mm:ss")}</td>
                                    <#else>
                                        <td>${item[key]}</td>
                                    </#if>
                                </#if>
                            </#if>
                        </#list>
                    </#list>
                </tr>
            </#list>
    </table>
</body>
</html>